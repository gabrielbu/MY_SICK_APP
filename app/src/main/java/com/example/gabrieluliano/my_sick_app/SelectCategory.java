package com.example.gabrieluliano.my_sick_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.gabrieluliano.app.AppConfig.IMAGE_UPLOAD;

public class SelectCategory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String db;
    TextView txt;

    String userID, fileName, fileLocation, locationX, locationY, category;

    Button bt, upload;

    public static final String KEY_NAME = "name";
    public static final String KEY_USERID = "userID";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_LOCATIONX = "locationX";
    public static final String KEY_LOCATIONY = "locationY";

    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;
    ImageView searchNB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

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


        txt = (TextView)findViewById(R.id.textView);
        bt = (Button)findViewById(R.id.printinfo);
        upload=(Button)findViewById(R.id.upload);

        spinner = (Spinner) findViewById(R.id.spinnerMen);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mens_clothing, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        db = getIntent().getStringExtra("dbData");
        System.out.println("this"+db);
        txt.setText(getIntent().getExtras().getString("dbData"));
        String [] arr = db.split("@");
        fileName = arr[0];
        userID= arr[1];
        fileLocation = arr[2];
        String [] arr1 = fileLocation.split(" ");
        locationX = arr1[0];
        locationY = arr1[1];
        spinner.setOnItemSelectedListener(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO SAVE IMAGE INFO TO DB
                // AND GO TO USER AREA
                saveImage();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("user id "+userID);
                System.out.println("file name "+fileName);
                System.out.println("X "+locationX);
                System.out.println("Y "+locationY);
                System.out.println("category "+category);
            }
        });
    }

    public void saveImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, IMAGE_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SelectCategory.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SelectCategory.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){

        @Override
        protected Map<String, String> getParams () {
            Map<String, String> params = new HashMap<String, String>();
            params.put(KEY_NAME, userID);
            params.put(KEY_USERID, fileName);
            params.put(KEY_CATEGORY, category);
            params.put(KEY_LOCATIONX, locationX);
            params.put(KEY_LOCATIONY, locationY);
            return params;
        }};
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txt = (TextView) view;
        category = txt.getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void homeScene(){
        Intent intent = new Intent(SelectCategory.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(SelectCategory.this, FoodOrClothesActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(SelectCategory.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(SelectCategory.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
}
