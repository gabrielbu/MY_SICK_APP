package com.example.gabrieluliano.my_sick_app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectCategory extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String db;
    TextView txt;

    String userID, fileName, fileLocation, locationX, locationY, category;

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        txt = (TextView)findViewById(R.id.textView);
        bt = (Button)findViewById(R.id.printinfo);

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
        userID = arr[0];
        fileName = arr[1];
        fileLocation = arr[2];
        String [] arr1 = fileLocation.split(" ");
        locationX = arr1[0];
        locationY = arr1[1];
        spinner.setOnItemSelectedListener(this);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView txt = (TextView) view;
        category = txt.getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
