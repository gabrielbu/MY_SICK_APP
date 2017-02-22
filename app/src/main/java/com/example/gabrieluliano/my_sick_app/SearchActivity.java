package com.example.gabrieluliano.my_sick_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;
    ImageView mainNB;
    Button foodBT;
    Button clothesBT;
    //TODO MOVE THIS URL TO CONFIG
    private static final String IMAGES_URL = "http://ubuntu@ec2-54-171-67-69.eu-west-1.compute.amazonaws.com/fetch_image.php";

    private Button buttonFetchImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        mainNB = (ImageView) findViewById(R.id.iv_user);
        foodBT = (Button) findViewById(R.id.women);
        clothesBT = (Button) findViewById(R.id.men);

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
        foodBT.setOnClickListener(new View.OnClickListener() {
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

        buttonFetchImages = (Button) findViewById(R.id.buttonFetchImages);
        buttonFetchImages.setOnClickListener(this);


    }

    private void getAllImages() {
        class GetAllImages extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SearchActivity.this, "Fetching Data","Please Wait...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(SearchActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetAllImages gai = new GetAllImages();
        gai.execute(IMAGES_URL);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonFetchImages) {
            getAllImages();
        }
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
    private void foodScene(){
        Intent intent = new Intent(SearchActivity.this, FoodSearch.class);
        startActivity(intent);
        finish();
    }
    private void clothesScene(){
        Intent intent = new Intent(SearchActivity.this, ClothesSearch.class);
        startActivity(intent);
        finish();
    }


}
