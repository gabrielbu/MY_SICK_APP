package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SearchActivity extends AppCompatActivity {
    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;
    ImageView mainNB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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


    }

    private void homeScene(){
        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(SearchActivity.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(SearchActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
