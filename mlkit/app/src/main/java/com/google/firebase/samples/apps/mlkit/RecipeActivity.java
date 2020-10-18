package com.google.firebase.samples.apps.mlkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.samples.apps.mlkit.java.StillImageActivity;

public class RecipeActivity extends AppCompatActivity {

    Button homeButton, profileButton, scanButton, forumButton, recipeButton, cookButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe);
        homeButton = findViewById(R.id.home_button2);
        profileButton = findViewById(R.id.profile_button2);
        scanButton = findViewById(R.id.scan_button2);
        forumButton = findViewById(R.id.forum_button2);
        recipeButton = findViewById(R.id.recipe_button2);
        cookButton = findViewById(R.id.btn_cook);

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

        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForumActivity.class);
                startActivity(intent);
            }
        });

        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Step1.class);
                startActivity(intent);
            }
        });
    }
}