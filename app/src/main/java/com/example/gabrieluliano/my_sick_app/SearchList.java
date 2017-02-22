package com.example.gabrieluliano.my_sick_app;

/**
 * Created by gabrieluliano on 15/02/2017.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SearchList extends AppCompatActivity {

        ArrayList<Product> arrayList;
        ListView lv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_list);
            arrayList = new ArrayList<>();
            lv = (ListView) findViewById(R.id.listView);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/get/images.php");
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
                                productObject.getString("locationY")
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
    }




