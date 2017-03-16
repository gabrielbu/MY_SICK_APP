package com.example.gabrieluliano.my_sick_app.photo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.gabrieluliano.my_sick_app.home.HomeActivity;
import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.search.ClothesSearch;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.HashMap;
import java.util.Map;

import static com.example.gabrieluliano.app.AppConfig.IMAGE_UPLOAD;

//
public class SelectCategory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String db;
    TextView txt, selectColour;
    private final int REQUEST_CODE_PLACEPICKER = 1;
    String userID, fileName, fileLocation, locationX, locationY, category, COLOUR, BRAND, COMMENT, TITLE;
    ImageView homeNB, searchNB, locationNB, photoNB, black, white, red, orange, yellow, green, blue, purple, pink;
    Button bt, upload;
    EditText enterComment, enterBrand, enterTitle;

    public static final String KEY_NAME = "name";
    public static final String KEY_USERID = "userID";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_LOCATIONX = "locationX";
    public static final String KEY_LOCATIONY = "locationY";
    public static final String KEY_TITLE = "title";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_COLOUR = "colour";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        COLOUR = "NULL";
        BRAND = "NULL";
        COMMENT = "NULL";
        TITLE = "NULL";

        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        searchNB = (ImageView) findViewById(R.id.iv_search);

        enterBrand = (EditText) findViewById(R.id.enterBrand);
        enterComment = (EditText) findViewById(R.id.enterComment);
        enterTitle = (EditText) findViewById(R.id.enterTitle);

        black = (ImageButton)findViewById(R.id.black);
        white = (ImageButton)findViewById(R.id.white);
        red = (ImageButton)findViewById(R.id.red);
        orange = (ImageButton)findViewById(R.id.orange);
        yellow = (ImageButton)findViewById(R.id.yellow);
        green =(ImageButton)findViewById(R.id.green);
        blue = (ImageButton)findViewById(R.id.blue);
        purple = (ImageButton)findViewById(R.id.purple);
        pink = (ImageButton)findViewById(R.id.pink);
        selectColour = (TextView)findViewById(R.id.selectColour);

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Black");
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("White");
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Red");
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Orange");
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Yellow");
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Green");
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Blue");
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Purple");
            }
        });
        pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColourText("Pink");
            }
        });

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


      //  txt = (TextView)findViewById(R.id.textView);
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
       // txt.setText(getIntent().getExtras().getString("dbData"));
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
                BRAND = enterBrand.getText().toString();
                COMMENT = enterComment.getText().toString();
                TITLE = enterComment.getText().toString();
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
                TITLE = enterComment.getText().toString();
                System.out.println("#####################"+BRAND);
                System.out.println("#####################"+COMMENT);
                startPlacePickerActivity();
            }
        });
    }

    private void changeColourText(String colour) {
        selectColour.setText("You've selected: "+colour);
        COLOUR =colour;
    }

    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();
        String ltlg = placeSelected.getLatLng().toString();
        String one = placeSelected.getPhoneNumber().toString();


        String [] newLtLg = ltlg.split(",");
        locationY = newLtLg[0].replaceAll("[^0-9|^\\.|^\\-]","");
        locationX = newLtLg[1].replaceAll("[^0-9|^\\.|^\\-]","");




        System.out.println("name: " +name+ "####### address: " + address);
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
            params.put(KEY_TITLE, TITLE);
            params.put(KEY_BRAND, BRAND);
            params.put(KEY_COMMENT, COMMENT);
            params.put(KEY_COLOUR, COLOUR);
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

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }
    }
    private void homeScene(){
        Intent intent = new Intent(SelectCategory.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(SelectCategory.this, ClothesSearch.class);
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
