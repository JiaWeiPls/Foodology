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
package com.DIPGroup3.foodology.java.cloudimagelabeling;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionCloudImageLabelerOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.DIPGroup3.foodology.common.FrameMetadata;
import com.DIPGroup3.foodology.common.GraphicOverlay;
import com.DIPGroup3.foodology.common.LabelReader;
import com.DIPGroup3.foodology.java.VisionProcessorBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Cloud Label Detector Demo.
 */
public class CloudImageLabelingProcessor
        extends VisionProcessorBase<List<FirebaseVisionImageLabel>> {
    private final String TAG = "CloudImgLabelProc";

    private final FirebaseVisionImageLabeler detector;

    public CloudImageLabelingProcessor() {
        FirebaseVisionCloudImageLabelerOptions.Builder optionsBuilder =
                new FirebaseVisionCloudImageLabelerOptions.Builder();

        detector = FirebaseVision.getInstance().getCloudImageLabeler(optionsBuilder.build());
    }
    //detection process, the detected value will be stored in FirebaseVisionImageLabel.
    @Override
    protected Task<List<FirebaseVisionImageLabel>> detectInImage(FirebaseVisionImage image) {
        return detector.processImage(image);
    }

    @Override
    protected void onSuccess(
            @Nullable Bitmap originalCameraImage,
            @NonNull List<FirebaseVisionImageLabel> labels,
            @NonNull FrameMetadata frameMetadata,
            @NonNull GraphicOverlay graphicOverlay) {
        DatabaseReference rootRef, demoRef, demoRef_Second;
        //FirebaseVisionImageLabel then be processed into label.
        graphicOverlay.clear();
        Log.d(TAG, "cloud label size: " + labels.size());
        List<String> labelsStr = new ArrayList<>();

        List<String> SecondStr = new ArrayList<>();

        FirebaseVisionImageLabel label = labels.get(0);
        labelsStr.add(label.getText());

        // change the number to get confidence.
        // not sure on 23/sep as real data in firebase is not working
        FirebaseVisionImageLabel Second = labels.get(1);
        SecondStr.add(Second.getText());

        rootRef = FirebaseDatabase.getInstance().getReference();
        //refer here for extracting ingredient if needed for menu.
        demoRef = rootRef.child("ingredient");
        demoRef.setValue(labelsStr);
        demoRef_Second = rootRef.child("second ingredient");
        demoRef_Second.setValue(SecondStr);



        CloudLabelGraphic cloudLabelGraphic = new CloudLabelGraphic(graphicOverlay, labelsStr, SecondStr);
        graphicOverlay.add(cloudLabelGraphic);
        graphicOverlay.postInvalidate();

// Step for passing out the detected label to Testprint
        String First_result = label.getText();
        String Second_result = Second.getText();
        //String entityId = label.getEntityId();
        float confidence1 = label.getConfidence();
        float confidence2 = Second.getConfidence();
        LabelReader.add_First_result(First_result);
        LabelReader.add_Second_result(Second_result);
        LabelReader.add_Confidence_result1(confidence1);
        LabelReader.add_Confidence_result2(confidence2);

        //DatabaseReference rootRef, demoRef;
        //rootRef = FirebaseDatabase.getInstance().getReference();
        //demoRef = rootRef.child("ingredient");
        /* demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(StillImageActivity.this, "You have scanned:" + value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StillImageActivity.this, "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });*/


    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Cloud Label detection failed " + e);
    }
}
