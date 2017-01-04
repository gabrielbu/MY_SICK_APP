package com.example.gabrieluliano.my_sick_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClothesSearch extends AppCompatActivity {
    Button women;
    Button men;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_search);

        women  = (Button) findViewById(R.id.women);
        men = (Button) findViewById(R.id.men) ;
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                womenScene();
            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menScene();
            }
        });
    }
    private void womenScene(){
        Intent intent = new Intent(ClothesSearch.this, ClothesSearchF.class);
        startActivity(intent);
        finish();
    }
    private void menScene(){
        Intent intent = new Intent(ClothesSearch.this, ClothesSearchM.class);
        startActivity(intent);
        finish();
    }
}
