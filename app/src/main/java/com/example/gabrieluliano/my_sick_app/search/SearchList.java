package com.example.gabrieluliano.my_sick_app.search;

/**
 * Created by gabrieluliano on 15/02/2017.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.gabrieluliano.helper.CustomListAdapter;
import com.example.gabrieluliano.my_sick_app.home.HomeActivity;
import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.example.gabrieluliano.my_sick_app.home.MainActivity;
import com.example.gabrieluliano.helper.Product;
import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.photo.PhotoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SearchList extends AppCompatActivity {

    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;
    ImageView mainNB;
        ArrayList<Product> arrayList;
        ListView lv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_list);

            Bundle b = getIntent().getExtras();
            final String category = b.getString("category");
            final String brand = b.getString("brand");
            final String colour = b.getString("colour");

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
            arrayList = new ArrayList<>();
            lv = (ListView) findViewById(R.id.listView);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //new ReadJSON().execute("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/get/images.php?category="+category+"&brand="+brand+"&colour="+colour);
                    new ReadJSON().execute("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/get/images.php?category="+category);

                }
            });


        }

        class ReadJSON extends AsyncTask<String, Integer, String> {

            @Override
            protected String doInBackground(String... params) {
                return readURL(params[0]);
            }

            @Override
            protected void onPostExecute(String content) {
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    JSONArray jsonArray =  jsonObject.getJSONArray("photos");

                    for(int i =0;i<jsonArray.length(); i++){
                        JSONObject productObject = jsonArray.getJSONObject(i);
                        arrayList.add(new Product("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/"+
                                productObject.getString("name")+".jpg",
                                productObject.getString("userID"),
                                productObject.getString("category"),
                                productObject.getString("locationX"),
                                productObject.getString("locationY"),
                                productObject.getString("title"),
                                productObject.getString("brand"),
                                productObject.getString("colour"),
                                productObject.getString("username")
                        ));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                CustomListAdapter adapter = new CustomListAdapter(
                        getApplicationContext(), R.layout.custom_list_layout, arrayList
                );



                lv.setAdapter(adapter);

            }
        }


        private static String readURL(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {
                // create a url object
                URL url = new URL(theUrl);
                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();
                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }

    private void homeScene(){
        Intent intent = new Intent(SearchList.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(SearchList.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(SearchList.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(SearchList.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    }



//
