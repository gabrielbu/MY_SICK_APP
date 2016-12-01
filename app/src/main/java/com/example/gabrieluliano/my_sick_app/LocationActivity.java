package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LocationActivity extends AppCompatActivity {
    ImageView homeNB;
    ImageView photoNB;
    ImageView mainNB;
    ImageView searchNB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        homeNB  = (ImageView) findViewById(R.id.iv_home);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        mainNB = (ImageView) findViewById(R.id.iv_user);
        searchNB = (ImageView) findViewById(R.id.iv_search);

        homeNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScene();
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
        searchNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchScene();
            }
        });


    }

    private void homeScene(){
        Intent intent = new Intent(LocationActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(LocationActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(LocationActivity.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(LocationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
