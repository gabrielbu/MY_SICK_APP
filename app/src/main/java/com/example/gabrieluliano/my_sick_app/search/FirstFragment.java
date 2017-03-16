package com.example.gabrieluliano.my_sick_app.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.facebook.FacebookHome;
import com.example.gabrieluliano.my_sick_app.facebook.FacebookMain;
import com.example.gabrieluliano.my_sick_app.home.MainActivity;
import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import static com.example.gabrieluliano.my_sick_app.R.id.imageView;
import static com.facebook.FacebookSdk.getApplicationContext;
//import static com.example.gabrieluliano.my_sick_app.R.id.textView;

public class FirstFragment extends Fragment  {
    private Bundle b;
    TextView txt;
    ImageView imageView;
    Context context;
    ImageButton fbShare;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_first_fragment, container, false); //Setting up a view
        b = this.getArguments();
        final String name = b.getString("name");
        fbShare = (ImageButton) v.findViewById(R.id.imageButton);

        context = getActivity().getApplicationContext(); // getting application context
        imageView = (ImageView) v.findViewById(R.id.imageView);
        Picasso.with(context).load(name).into(imageView);


        txt = (TextView) v.findViewById(R.id.textView5);

        txt.setText(name);


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        fbShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), FacebookMain.class);
                //startActivity(intent);

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentTitle("How to develop an android camera application")
                            .setImageUrl(Uri.parse("http://ubuntu@ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/623ec61b-de69-438c-a81e-9f7ae7685119.jpg"))
                            .setContentDescription("simple android camera application")
                            .setContentUrl(Uri.parse("http://ubuntu@ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/623ec61b-de69-438c-a81e-9f7ae7685119.jpg"))
                            .build();

                    shareDialog.show(content);
                }
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


}