package com.quattrofolia.balansiosmart;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements GoalTypeFragment.OnTypeDataPass{
    String LOG = "loki";
    private String selectedDataType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onTypeDataPass(String data) {
        Log.d(LOG+"valittu datatyyppi",data);
        selectedDataType = data;
    }


    public String getSelectedDataType(){
        return selectedDataType;
    }
}
