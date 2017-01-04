package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FoodOrClothesActivity extends AppCompatActivity {
    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;
    ImageView mainNB;
    Button foodBT;
    Button clothesBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_or_clothes);
        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        mainNB = (ImageView) findViewById(R.id.iv_user);

        homeNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScene();
            }
        });
        locationNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationScene();
            }
        });
        photoNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoScene();
            }
        });
        mainNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScene();
            }
        });
   /*     foodBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodScene();
            }
        });

        clothesBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothesScene();
            }
        });
*/

    }

    private void homeScene(){
        Intent intent = new Intent(FoodOrClothesActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(FoodOrClothesActivity.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(FoodOrClothesActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(FoodOrClothesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void foodScene(){
        Intent intent = new Intent(FoodOrClothesActivity.this, FoodSearch.class);
        startActivity(intent);
        finish();
    }
    private void clothesScene(){
        Intent intent = new Intent(FoodOrClothesActivity.this, ClothesSearch.class);
        startActivity(intent);
        finish();
    }
}
