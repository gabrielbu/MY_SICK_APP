package com.example.gabrieluliano.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.search.SearchSelected;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> products;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_list_layout, null, true);

        }
        final Product product = getItem(position);
        final String image = product.getImage();
        final String userID = product.getUserID();
        final String category = product.getCategory();
        final String locationX = product.getLocationX();
        final String locationY = product.getLocationY();
        final String title = product.getTitle();
        final String brand = product.getBrand();
        final String colour = product.getColour();
        final String username = product.getUserName();


        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
        Picasso.with(context).load(product.getImage()).into(imageView);




        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setText(product.getUserName());



        TextView txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
        txtPrice.setText(product.getTitle());

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomListAdapter.this.getContext(), SearchSelected.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image",image);
                intent.putExtra("userID", userID);
                intent.putExtra("category", category);
                intent.putExtra("locationX",locationX);
                intent.putExtra("locationY", locationY);
                intent.putExtra("title", title);
                intent.putExtra("brand", brand);
                intent.putExtra("colour", colour);
                intent.putExtra("username", username);
                context.startActivity(intent);

            }
        });

        return convertView;
    }





}
