package com.example.gabrieluliano.my_sick_app.search;

/**
 * Created by gabrieluliano on 15/02/2017.
 */

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrieluliano.my_sick_app.R;
import com.example.gabrieluliano.my_sick_app.search.FirstFragment;
import com.example.gabrieluliano.my_sick_app.search.SecondFragment;
import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SearchSelected extends AppCompatActivity {
        private TextView textView;
        private ImageView imageView;
        Context context;
        public Bundle b;

    //    private Toolbar toolbar;
        private TabLayout tabLayout;
        private ViewPager viewPager;

        String name ="";
        String userID ="";
        String category ="";
        String locationX ="";
        String locationY ="";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search_selected);


            FacebookSdk.sdkInitialize(getApplicationContext());

            b = getIntent().getExtras();
            name = b.getString("image");
            userID = b.getString("userID");
            category = b.getString("category");
            locationX = b.getString("locationX");
            locationY = b.getString("locationY");

            //toolbar = (Toolbar) findViewById(R.id.toolbar);
           // setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            //Getting data required data



            context = getApplicationContext();


           // textView = (TextView) findViewById(textView);
           // textView.setText(name);

           // imageView = (ImageView) findViewById(imageView);
           // Picasso.with(context).load(name).into(imageView);

        }
    public String getName(){
        return name;
    }
    public String getUserID(){
        return userID;
    }
    public String getCategory(){
        return category;
    }
    public String getLocationX(){
        return locationX;
    }
    public String getLocationY(){
        return locationY;
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();

        Bundle bundle =new Bundle();
        bundle.putString("name", name);
        bundle.putString("userID", userID);
        bundle.putString("category",category );
        bundle.putString("locationX", locationX);
        bundle.putString("locationY", locationY);

        firstFragment.setArguments(bundle);
        secondFragment.setArguments(bundle);


        adapter.addFragment(firstFragment, "Photo");
        adapter.addFragment(secondFragment, "Location");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    }


//