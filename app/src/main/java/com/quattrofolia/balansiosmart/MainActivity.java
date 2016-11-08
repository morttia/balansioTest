package com.quattrofolia.balansiosmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this); // Initialize Realm only once when the app starts.
        setContentView(R.layout.activity_main);
    }
}
