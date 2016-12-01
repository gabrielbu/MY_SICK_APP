package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;

    ImageView searchNB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
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
        photoNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoScene();
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
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }



}
