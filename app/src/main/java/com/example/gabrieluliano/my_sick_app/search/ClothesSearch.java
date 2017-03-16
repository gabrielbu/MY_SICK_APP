package com.example.gabrieluliano.my_sick_app.search;

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

import com.example.gabrieluliano.my_sick_app.home.HomeActivity;
import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.example.gabrieluliano.my_sick_app.home.MainActivity;
import com.example.gabrieluliano.my_sick_app.photo.PhotoActivity;
import com.example.gabrieluliano.my_sick_app.R;

public class ClothesSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //Button women;
    Button men;
    Spinner spinner;
    String category, COLOUR, BRAND;
    ImageView homeNB, mainNB, locationNB, photoNB, black, white, red, orange, yellow, green, blue, purple, pink;
    TextView selectColour;
    EditText enterBrand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_search);

        COLOUR="";
        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        mainNB = (ImageView) findViewById(R.id.iv_user);

        black = (ImageButton)findViewById(R.id.black);
        white = (ImageButton)findViewById(R.id.white);
        red = (ImageButton)findViewById(R.id.red);
        orange = (ImageButton)findViewById(R.id.orange);
        yellow = (ImageButton)findViewById(R.id.yellow);
        green =(ImageButton)findViewById(R.id.green);
        blue = (ImageButton)findViewById(R.id.blue);
        purple = (ImageButton)findViewById(R.id.purple);
        pink = (ImageButton)findViewById(R.id.pink);
        selectColour = (TextView)findViewById(R.id.textView13);
        enterBrand = (EditText)findViewById(R.id.enterBrand);

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
        mainNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScene();
            }
        });

        men = (Button) findViewById(R.id.men) ;

        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menScene();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinnerMen);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mens_clothing, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void changeColourText(String colour) {
        selectColour.setText("You've selected: "+colour);
        COLOUR =colour;
    }
    private void menScene(){
        BRAND = enterBrand.getText().toString();
        Intent intent = new Intent(ClothesSearch.this, SearchList.class);
        intent.putExtra("category",category);
        intent.putExtra("brand", BRAND);
        intent.putExtra("colour", COLOUR);
        startActivity(intent);
        finish();
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
        Intent intent = new Intent(ClothesSearch.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(ClothesSearch.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(ClothesSearch.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(ClothesSearch.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
//