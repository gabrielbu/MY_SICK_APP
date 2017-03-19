package com.example.gabrieluliano.my_sick_app.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gabrieluliano.app.AppConfig;
import com.example.gabrieluliano.helper.CustomListAdapter;
import com.example.gabrieluliano.helper.Product;
import com.example.gabrieluliano.helper.SQLiteHandler;
import com.example.gabrieluliano.helper.SessionManager;
import com.example.gabrieluliano.my_sick_app.BuildConfig;
import com.example.gabrieluliano.my_sick_app.facebook.FacebookHome;
import com.example.gabrieluliano.my_sick_app.facebook.FacebookMain;
import com.example.gabrieluliano.my_sick_app.facebook.FacebookShare;
import com.example.gabrieluliano.my_sick_app.login.LoginActivity;
import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.location.LocationActivity;
import com.example.gabrieluliano.my_sick_app.photo.PhotoActivity;
import com.example.gabrieluliano.my_sick_app.search.ClothesSearch;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import static android.R.attr.bitmap;
import static com.example.gabrieluliano.app.AppConfig.SAVE_PROFILE_PIC;
import static java.sql.Types.NULL;


public class MainActivity extends AppCompatActivity {

    //VARIABLE DECLARATION
    private File file;
    private Uri file_uri;
    ImageView homeNB;
    ImageView locationNB;
    ImageView photoNB;
    ImageView searchNB;
    ImageButton takePic;
    String search = AppConfig.get_name();
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private Button buttonnext;
    String image_name, encoded_string, imageName;
    private Bitmap bitmap;

    ArrayList<Product> arrayList;
    ListView lv;

    private SQLiteHandler db;
    private SessionManager session;
    ImageView imageView;
    Context context;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();


        //VARIABLE INITIALISATION
        homeNB  = (ImageView) findViewById(R.id.iv_home);
        locationNB = (ImageView) findViewById(R.id.iv_location);
        photoNB = (ImageView) findViewById(R.id.iv_photo);
        searchNB = (ImageView) findViewById(R.id.iv_search);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        //btnLogout = (Button) findViewById(R.id.btnLogout);
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.mainList);
        //ON CLICK LISTENERS
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


        takePic = (ImageButton) findViewById(R.id.imageButton5);
        takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getFileUri();
                i.putExtra(MediaStore.EXTRA_OUTPUT, file_uri);
                startActivityForResult(i, 10);
            }
        });




        db = new SQLiteHandler(getApplicationContext());

        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        //Getting user data
        HashMap<String, String> user = db.getUserDetails();
        String name = user.get("name");
        String email = user.get("email");
        userID= user.get("uid");


        // Display user name and email
        txtName.setText(name);
        txtEmail.setText(email);

        //Picasso.with(getApplicationContext()).load("http://ubuntu@ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/profilepics/images/e161f3e1-1999-4a4b-9c57-e86bb53395c7.jpg").into(takePic);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //new ReadJSON().execute("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/get/images.php?category="+category+"&brand="+brand+"&colour="+colour);
                new ReadJSON().execute("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/get/userImages.php?userID="+userID);

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:{
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            }
            case R.id.action_logout:{
                logoutUser();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray jsonArray =  jsonObject.getJSONArray("photos");
                JSONArray jsonArray1 = jsonObject.getJSONArray("profile");
                for(int i =0;i<jsonArray.length(); i++){
                    JSONObject productObject = jsonArray.getJSONObject(i);
                    arrayList.add(new Product("http://ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/images/"+
                            productObject.getString("name")+".jpg",
                            productObject.getString("userID"),
                            productObject.getString("category"),
                            productObject.getString("locationX"),
                            productObject.getString("locationY"),
                            productObject.getString("title"),
                            productObject.getString("brand"),
                            productObject.getString("colour"),
                            productObject.getString("username")

                    ));
                    String profilePicPath="http://ubuntu@ec2-54-194-183-72.eu-west-1.compute.amazonaws.com/profilepics/images/"+ jsonArray1.getJSONObject(0).getString("picture");
                    Picasso.with(getApplicationContext()).load(profilePicPath).into(takePic);
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
        StringRequest request = new StringRequest(Request.Method.POST, SAVE_PROFILE_PIC,
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
                map.put("userID", userID);

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
        file_uri = FileProvider.getUriForFile(MainActivity.this,
                BuildConfig.APPLICATION_ID + ".provider",
                file);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10 && resultCode == RESULT_OK) {
            new MainActivity.Encode_image().execute();
            //////

            //  Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            //  mImageView.setImageBitmap(imageBitmap);
            //////

        }
    }
    //###################################################################


    private void homeScene(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
    private void searchScene(){
        Intent intent = new Intent(MainActivity.this, ClothesSearch.class);
        startActivity(intent);
        finish();
    }
    private void photoScene(){
        Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
        startActivity(intent);
        finish();
    }
    private void locationScene(){
        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
        startActivity(intent);
        finish();
    }
    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

