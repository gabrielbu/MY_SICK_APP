package com.example.gabrieluliano.my_sick_app.photo;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gabrieluliano.helper.SQLiteHandler;
import com.example.gabrieluliano.helper.SessionManager;
import com.example.gabrieluliano.my_sick_app.BuildConfig;
import com.example.gabrieluliano.helper.GPS_Service;
import com.example.gabrieluliano.my_sick_app.home.HomeActivity;
import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.example.gabrieluliano.my_sick_app.home.MainActivity;
import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.search.ClothesSearch;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class PhotoActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    ImageView homeNB;
    ImageView locationNB;
    ImageView mainNB;
    ImageView searchNB;
    TextView txt;
    private Button button;
    private String encoded_string, image_name;
    private Bitmap bitmap;
    private File file;
    private Uri file_uri;
    private static final String POST_URL = "http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/sendtest.php";

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int LOCATION_PERMISSION_CODE = 102;
    private SQLiteHandler db;
    private SessionManager session;

    GoogleMap mMap;
    MapView mapView;
    View mView;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button btn1, btn2;
    private TextView textView;

    Double Long = 0.0;
    Double Lat=0.0;
    String ln;
    private BroadcastReceiver broadcastReceiver;

    String userID;
    String imageName;
    String Coordinate="0";
    String CoordinateAPI;
    String longAPI;
    String latAPI;
    Double test;



    ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        btn1 = (Button)findViewById(R.id.but1);
        btn2 = (Button)findViewById(R.id.but2);
        textView = (TextView)findViewById(R.id.textView3);
        mImageView = (ImageView)findViewById(R.id.imageView);
        txt = (TextView)findViewById(R.id.textView4);

        //PERMISSIONS
        requestStoragePermission();


        //SQLite
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        //Getting user data
        HashMap<String, String> user = db.getUserDetails();

        userID = user.get("uid");
        System.out.println("this" +userID);
        //TODO SAVE THIS ID TO DB WITH PICTURE LOCATION N THAT

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Try to go into next method with normal GPS function


                if(!Coordinate.equals("0")) {
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
                    Intent i = new Intent(PhotoActivity.this, SelectCategory.class);
                    i.putExtra("dbData", userID + "@" + imageName + "@" + Coordinate);
                    startActivity(i);
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@22");
                }
                // Catch exception where Coordinates are not given
                else{
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@3");
                    Intent i = new Intent(PhotoActivity.this, SelectCategory.class);
                    i.putExtra("dbData", userID + "@" + imageName + "@" + CoordinateAPI);
                    startActivity(i);
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@33");
                }
            }
        });


        button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(i, 10);
            }
        });

        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        mainNB = (ImageView) findViewById(R.id.iv_user);
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
        mainNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userScene();
            }
        });
        searchNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchScene();
            }
        });


        //location

        if(!requestLocation())
            enableButton();


        //This is another way of getting location through the google API (works better on phone)
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }


    //######################### COORDINATES WITH API ##########################
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        requestLocation();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latAPI = String.valueOf(mLastLocation.getLatitude());
            longAPI = String.valueOf(mLastLocation.getLongitude());
            txt.setText(String.valueOf(mLastLocation.getLongitude()));
            CoordinateAPI = longAPI +" "+latAPI;
            test = mLastLocation.getLongitude();


        }
    }
    @Override
    public void onConnectionSuspended( int i ){

    }
    @Override
    public void onConnectionFailed( ConnectionResult connectionResult ){
        if( connectionResult.hasResolution() ){
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(PhotoActivity.this, 3);
            }catch( IntentSender.SendIntentException e ){
                e.printStackTrace();
            }
        }else{
            System.out.println("failed");
        }




    }

    //############################## COORDINATES ##############################

    private void enableButton() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GPS_Service.class);
                startService(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GPS_Service.class);
                stopService(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    textView.append("\n"+ intent.getExtras().get("coordinates"));
                    ln = textView.getText().toString();
                    Coordinate = intent.getStringExtra("coordinates");
                    System.out.println(Coordinate);

                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    //#########################################################################



    //############################## IMAGE ##############################

    private class Encode_image extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try{
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(file_uri));

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            bitmap.recycle();

            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);
            return null;}

            catch(Exception e){
                System.out.println("@@@@error");
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            makeRequest();
        }
    }
    private void makeRequest() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, POST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("encoded_string",encoded_string);
                map.put("image_name",image_name);

                return map;
            }
        };
        requestQueue.add(request);
    }

    private void getFileUri() {

        String uuid = UUID.randomUUID().toString();
        imageName=uuid;

        image_name = uuid + ".jpg";
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + File.separator + image_name
        );

        //file_uri = Uri.fromFile(file);
        file_uri = FileProvider.getUriForFile(PhotoActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
    }


    //###################################################################




    //################### REQUESTS AND RESPONSES ##############################
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10 && resultCode == RESULT_OK) {
            new PhotoActivity.Encode_image().execute();
            //////

          //  Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
          //  mImageView.setImageBitmap(imageBitmap);
            //////

        }
    }

    private boolean requestLocation() {
        if (Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_PERMISSION_CODE);
            return true;
        }
        return false;
    }



    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            Toast.makeText(this, "Permission is needed", Toast.LENGTH_LONG).show();
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            Toast.makeText(this, "Permission is needed", Toast.LENGTH_LONG).show();
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode ==LOCATION_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can use location services", Toast.LENGTH_LONG).show();
                enableButton();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
                requestLocation();
            }
        }


        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
        //Checking the request code of our request
        if (requestCode == CAMERA_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can use the camera", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    //########################################################################
    private void homeScene(){
        Intent intent = new Intent(PhotoActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(PhotoActivity.this, ClothesSearch.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(PhotoActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void userScene(){
        Intent intent = new Intent(PhotoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
