package com.example.gabrieluliano.my_sick_app;

/**
 * Created by gabrieluliano on 15/02/2017.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class SearchSelected extends AppCompatActivity {
        private TextView textView;
        private ImageView imageView;
        Context context;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_selected);
            Bundle b = getIntent().getExtras();
            String name ="";
            name = b.getString("image");
            context = getApplicationContext();

            textView = (TextView) findViewById(R.id.textView);
            textView.setText(name);

            imageView = (ImageView) findViewById(R.id.imageView);
            Picasso.with(context).load(name).into(imageView);



        }

    }


