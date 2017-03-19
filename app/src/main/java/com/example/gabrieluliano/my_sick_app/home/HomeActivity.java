package com.example.gabrieluliano.my_sick_app.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.photo.PhotoActivity;
import com.example.gabrieluliano.my_sick_app.search.ClothesSearch;
import com.squareup.picasso.Picasso;


public class HomeActivity extends AppCompatActivity {

    ImageView locationNB;
    ImageView photoNB;
    ImageView mainNB;
    ImageView searchNB;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        mainNB = (ImageView) findViewById(R.id.iv_user);
        searchNB = (ImageView) findViewById(R.id.iv_search);
        pic = (ImageView) findViewById(R.id.imageView3);
        Picasso.with(getApplicationContext()).load("http://ubuntu@ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/profilepics/images/e161f3e1-1999-4a4b-9c57-e86bb53395c7.jpg").into(pic);


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
        Intent intent = new Intent(HomeActivity.this, ClothesSearch.class);
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
