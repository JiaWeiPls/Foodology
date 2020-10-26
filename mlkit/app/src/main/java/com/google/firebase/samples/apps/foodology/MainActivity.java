package com.google.firebase.samples.apps.foodology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.samples.apps.foodology.java.StillImageActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button homeButton, profileButton, scanButton, forumButton, recipeButton, seeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        myDb = new DatabaseHelper(this);
        homeButton = findViewById(R.id.home_button);
        profileButton = findViewById(R.id.profile_button);
        scanButton = findViewById(R.id.scan_button);
        forumButton = findViewById(R.id.forum_button);
        recipeButton = findViewById(R.id.recipe_button);
        seeButton = findViewById(R.id.home_see_all_2_button);
        ImageButton eggfriedriceButton1 = (ImageButton)findViewById(R.id.home_recipe_photo_1);
        ImageButton eggfriedriceButton2 = (ImageButton)findViewById(R.id.home_recipe_photo_2);
        ImageButton eggfriedriceButton3 = (ImageButton)findViewById(R.id.home_recipe_photo_3);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StillImageActivity.class);
                startActivity(intent);
            }
        });

        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        seeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        eggfriedriceButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        eggfriedriceButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        eggfriedriceButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForumActivity.class);
                startActivity(intent);
            }
        });
    }

}