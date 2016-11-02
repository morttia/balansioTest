package com.quattrofolia.balansiosmart;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements DataTypePasser {
    String LOG = "debyg";
    private String selectedDataType;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void setSelectedDataType(String data) {
        Log.d(LOG+"selected dataype",data);
        selectedDataType = data;
    }

    @Override
    public String getSelectedDataType(){
        return selectedDataType;
    }

    public ViewPager getViewPager(){
        return viewPager;
    }
}
