package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {
    ImageView homeNB;
    ImageView locationNB;
    ImageView mainNB;
    ImageView searchNB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        mainNB = (ImageView) findViewById(R.id.iv_user);
        searchNB = (ImageView) findViewById(R.id.iv_search);

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
        mainNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScene();
            }
        });
        searchNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchScene();
            }
        });
    }

    private void homeScene(){
        Intent intent = new Intent(PhotoActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(PhotoActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(PhotoActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
