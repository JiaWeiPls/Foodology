// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.DIPGroup3.foodology.java;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.google.android.gms.common.annotation.KeepName;
import com.DIPGroup3.foodology.DatabaseHelper;
import com.DIPGroup3.foodology.R;
import com.DIPGroup3.foodology.RecipeActivity;
import com.DIPGroup3.foodology.common.VisionImageProcessor;
import com.DIPGroup3.foodology.common.LabelReader;
import com.DIPGroup3.foodology.databinding.ActivityStillImageBinding;
import com.DIPGroup3.foodology.java.cloudimagelabeling.CloudImageLabelingProcessor;
import com.DIPGroup3.foodology.common.preference.SettingsActivity;
import com.DIPGroup3.foodology.common.preference.SettingsActivity.LaunchSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//dealing with the camera interface, e.g. situation of rotating phone
/** Activity demonstrating different image detector features with a still image from camera. */
@KeepName
public class StillImageActivity extends AppCompatActivity {

  private final String TAG = "StillImageActivity";
  private final int PERMISSION_REQUESTS = 1;

  private final String SIZE_PREVIEW = "w:max"; // Available on-screen width.
  private final String SIZE_1024_768 = "w:1024"; // ~1024*768 in a normal ratio
  private final String SIZE_640_480 = "w:640"; // ~640*480 in a normal ratio

  private  final String KEY_IMAGE_URI = "com.googletest.firebase.ml.demo.KEY_IMAGE_URI";
  private  final String KEY_IMAGE_MAX_WIDTH =
      "com.googletest.firebase.ml.demo.KEY_IMAGE_MAX_WIDTH";
  private  final String KEY_IMAGE_MAX_HEIGHT =
      "com.googletest.firebase.ml.demo.KEY_IMAGE_MAX_HEIGHT";
  private  final String KEY_SELECTED_SIZE =
      "com.googletest.firebase.ml.demo.KEY_SELECTED_SIZE";

  private  final int REQUEST_IMAGE_CAPTURE = 1001;
  private  final int REQUEST_CHOOSE_IMAGE = 1002;

  private String selectedSize = SIZE_PREVIEW;

  boolean isLandScape;

  private Uri imageUri;
  // Max width (portrait mode)
  private Integer imageMaxWidth;
  // Max height (portrait mode)
  private Integer imageMaxHeight;
  private VisionImageProcessor imageProcessor;

  private ActivityStillImageBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityStillImageBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    ImageButton scan_btn = (ImageButton) findViewById(R.id.stillimg_buttonScan);
    scan_btn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View view) {
        ScanNow(view);
      }

      private void ScanNow(View view) {
        // Clean up last time's image
        imageUri = null;
        binding.previewPane.setImageBitmap(null);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
          ContentValues values = new ContentValues();
          values.put(MediaStore.Images.Media.TITLE, "New Picture");
          values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
          imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
          startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
      }
    });

    ImageButton import_btn = (ImageButton) findViewById(R.id.stillimg_btnImport);
    import_btn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View view) {
        Import(view);
      }

      private void Import(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CHOOSE_IMAGE);
      }
    });

    binding.getImageButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            // Menu for selecting either: a) take new photo b) select from existing
            PopupMenu popup = new PopupMenu(StillImageActivity.this, view);
            popup.setOnMenuItemClickListener(
                new OnMenuItemClickListener() {
                  @Override
                  public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                      case R.id.display_string_test:

                        Intent intent = new Intent(view.getContext(), RecipeActivity.class);
                        startActivity(intent);

                        testprint();
                        return true;

                      case R.id.select_images_from_local:
                        startChooseImageIntentForResult();
                        return true;
                      case R.id.take_photo_using_camera:
                        startCameraIntentForResult();
                        return true;
                      default:
                        return false;
                    }
                  }
                });

            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.camera_button_menu, popup.getMenu());
            popup.show();
          }
        });


    createImageProcessor();


    isLandScape =
        (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);

    if (savedInstanceState != null) {
      imageUri = savedInstanceState.getParcelable(KEY_IMAGE_URI);
      imageMaxWidth = savedInstanceState.getInt(KEY_IMAGE_MAX_WIDTH);
      imageMaxHeight = savedInstanceState.getInt(KEY_IMAGE_MAX_HEIGHT);
      selectedSize = savedInstanceState.getString(KEY_SELECTED_SIZE);

      if (imageUri != null) {
        tryReloadAndDetectInImage();
      }
    }

    if (!allPermissionsGranted()) {
      getRuntimePermissions();
    }


  }

  @Override
  public void onResume() {
    super.onResume();
    Log.d(TAG, "onResume");
    createImageProcessor();
    tryReloadAndDetectInImage();

  }

  private String[] getRequiredPermissions() {
    try {
      PackageInfo info =
              this.getPackageManager()
                      .getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
      String[] ps = info.requestedPermissions;
      if (ps != null && ps.length > 0) {
        return ps;
      } else {
        return new String[0];
      }
    } catch (Exception e) {
      return new String[0];
    }
  }

  private boolean allPermissionsGranted() {
    for (String permission : getRequiredPermissions()) {
      if (!isPermissionGranted(this, permission)) {
        return false;
      }
    }
    return true;
  }

  private void getRuntimePermissions() {
    List<String> allNeededPermissions = new ArrayList<>();
    for (String permission : getRequiredPermissions()) {
      if (!isPermissionGranted(this, permission)) {
        allNeededPermissions.add(permission);
      }
    }

    if (!allNeededPermissions.isEmpty()) {
      ActivityCompat.requestPermissions(
              this, allNeededPermissions.toArray(new String[0]), PERMISSION_REQUESTS);
    }
  }

  private  boolean isPermissionGranted(Context context, String permission) {
    if (ContextCompat.checkSelfPermission(context, permission)
            == PackageManager.PERMISSION_GRANTED) {
      Log.i(TAG, "Permission granted: " + permission);
      return true;
    }
    Log.i(TAG, "Permission NOT granted: " + permission);
    return false;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.still_image_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.settings) {
      Intent intent = new Intent(this, SettingsActivity.class);
      intent.putExtra(SettingsActivity.EXTRA_LAUNCH_SOURCE, LaunchSource.STILL_IMAGE);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }



  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putParcelable(KEY_IMAGE_URI, imageUri);
    if (imageMaxWidth != null) {
      outState.putInt(KEY_IMAGE_MAX_WIDTH, imageMaxWidth);
    }
    if (imageMaxHeight != null) {
      outState.putInt(KEY_IMAGE_MAX_HEIGHT, imageMaxHeight);
    }
    outState.putString(KEY_SELECTED_SIZE, selectedSize);
  }

  private void startCameraIntentForResult() {
    // Clean up last time's image
    imageUri = null;
    binding.previewPane.setImageBitmap(null);

    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      ContentValues values = new ContentValues();
      values.put(MediaStore.Images.Media.TITLE, "New Picture");
      values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
      imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
      takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }

// Text carried from CloudImageLabelingProcessor. Showing the Label detected by a toast.
// CloudImageLabelingProcessor > LabelReader > StillimageActivity.

  private void testprint(){
    String first_result = LabelReader.First_result();
    String second_result = LabelReader.Second_result();
    String confidence1 = LabelReader.Confidence_result1();
    String confidence2 = LabelReader.Confidence_result2();
    Context context = getApplicationContext();



    DatabaseHelper myDb = new DatabaseHelper(this);
    SQLiteDatabase sqliteDatabase = myDb.getReadableDatabase();

    String RecipeID;
    String RecipeName;
    String RecipeIngredient;
    String testtext = "The recipes are: ";
    //Cursor cursor = sqliteDatabase.query(TABLE_Recipe, new String[]{"rowid", COL_RecipeName, COL_RecipeSteps, COL_RecipeIngredient}, TABLE_Recipe + " MATCH ?", new String[]{s + "*"}, null, null, null, null);
    try{
      Cursor cursor = sqliteDatabase.query("Recipe", new String[] {"RecipeID","RecipeName","RecipeIngredient"}, "RecipeIngredient"+" LIKE?", new String[] { "%"+first_result + "%"}, null, null, null);


      while (cursor.moveToNext()) {
        RecipeID = cursor.getString(cursor.getColumnIndex("RecipeID"));
        RecipeName = cursor.getString(cursor.getColumnIndex("RecipeName"));
        RecipeIngredient = cursor.getString(cursor.getColumnIndex("RecipeIngredient"));
        testtext += "\n"+RecipeID + ",\n" +RecipeName+ RecipeIngredient + "\n";

      }
      sqliteDatabase.close();
      if (cursor != null) {
        cursor.close();
      }
    }
    catch(Exception e){
      testtext = "ERROR.ERROR.ERROR";

    }





    CharSequence out = first_result + " with confidence: "+confidence1 + " \n\n " + second_result + " with confidence: "+confidence2 + "\n\n"+testtext;
    int duration = Toast.LENGTH_LONG;

    Toast toast = Toast.makeText(context, out, duration);
    toast.show();
  }
  private void startChooseImageIntentForResult() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CHOOSE_IMAGE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      tryReloadAndDetectInImage();
    } else if (requestCode == REQUEST_CHOOSE_IMAGE && resultCode == RESULT_OK) {
      // In this case, imageUri is returned by the chooser, save it.
      imageUri = data.getData();
      tryReloadAndDetectInImage();

    }
  }

  private void tryReloadAndDetectInImage() {
    try {
      if (imageUri == null) {
        return;
      }

      // Clear the overlay first
      binding.previewOverlay.clear();

      Bitmap imageBitmap;
      if (Build.VERSION.SDK_INT < 29) {
        imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
      } else {
        ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
        imageBitmap = ImageDecoder.decodeBitmap(source);
      }

      // Get the dimensions of the View
      Pair<Integer, Integer> targetedSize = getTargetedWidthHeight();

      int targetWidth = targetedSize.first;
      int maxHeight = targetedSize.second;

      // Determine how much to scale down the image
      float scaleFactor =
          Math.max(
              (float) imageBitmap.getWidth() / (float) targetWidth,
              (float) imageBitmap.getHeight() / (float) maxHeight);

      Bitmap resizedBitmap =
          Bitmap.createScaledBitmap(
              imageBitmap,
              (int) (imageBitmap.getWidth() / scaleFactor),
              (int) (imageBitmap.getHeight() / scaleFactor),
              true);

      binding.previewPane.setImageBitmap(resizedBitmap);

      imageProcessor.process(resizedBitmap, binding.previewOverlay);



    } catch (IOException e) {
      Log.e(TAG, "Error retrieving saved image");
    }
  }

  // Returns max image width, always for portrait mode. Caller needs to swap width / height for
  // landscape mode.
  private Integer getImageMaxWidth() {
    if (imageMaxWidth == null) {
      // Calculate the max width in portrait mode. This is done lazily since we need to wait for
      // a UI layout pass to get the right values. So delay it to first time image rendering time.
      if (isLandScape) {
        imageMaxWidth =
            ((View) binding.previewPane.getParent()).getHeight() - binding.controlPanel.getHeight();
      } else {
        imageMaxWidth = ((View) binding.previewPane.getParent()).getWidth();
      }
    }

    return imageMaxWidth;
  }

  // Returns max image height, always for portrait mode. Caller needs to swap width / height for
  // landscape mode.
  private Integer getImageMaxHeight() {
    if (imageMaxHeight == null) {
      // Calculate the max width in portrait mode. This is done lazily since we need to wait for
      // a UI layout pass to get the right values. So delay it to first time image rendering time.
      if (isLandScape) {
        imageMaxHeight = ((View) binding.previewPane.getParent()).getWidth();
      } else {
        imageMaxHeight =
            ((View) binding.previewPane.getParent()).getHeight() - findViewById(R.id.controlPanel).getHeight();
      }
    }

    return imageMaxHeight;
  }

  // Gets the targeted width / height.
  private Pair<Integer, Integer> getTargetedWidthHeight() {
    int targetWidth;
    int targetHeight;

    switch (selectedSize) {
      case SIZE_PREVIEW:
        int maxWidthForPortraitMode = getImageMaxWidth();
        int maxHeightForPortraitMode = getImageMaxHeight();
        targetWidth = isLandScape ? maxHeightForPortraitMode : maxWidthForPortraitMode;
        targetHeight = isLandScape ? maxWidthForPortraitMode : maxHeightForPortraitMode;
        break;
      case SIZE_640_480:
        targetWidth = isLandScape ? 640 : 480;
        targetHeight = isLandScape ? 480 : 640;
        break;
      case SIZE_1024_768:
        targetWidth = isLandScape ? 1024 : 768;
        targetHeight = isLandScape ? 768 : 1024;
        break;
      default:
        throw new IllegalStateException("Unknown size");
    }

    return new Pair<>(targetWidth, targetHeight);
  }

  private void createImageProcessor() {
        imageProcessor = new CloudImageLabelingProcessor();
    
  }
}
