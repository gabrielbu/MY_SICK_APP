package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    ImageView locationNB;
    ImageView photoNB;
    ImageView mainNB;
    ImageView searchNB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        mainNB = (ImageView) findViewById(R.id.iv_user);
        searchNB = (ImageView) findViewById(R.id.iv_search);


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
        searchNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchScene();
            }
        });
    }


    private void searchScene(){
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
