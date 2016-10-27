package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalIntensityFragment extends Fragment {
    private int amountMin;
    private int amountMax;
    private int timeMin;
    private int timeMax;
    private int amountDefault;
    private int timeDefault;
    private TextView tv;
    private int selectedAmount;
    private int selectedTime;
    private String TAG = "debug";

    public static GoalIntensityFragment newInstance() {
        GoalIntensityFragment fragment = new GoalIntensityFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amountMin = 1;
        amountMax = 10;
        amountDefault = 5;
        timeMin = 1;
        timeMax = 24;
        timeDefault = 8;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout myView =(RelativeLayout) inflater.inflate(R.layout.goal_intensity_fragment, container, false);
        tv = (TextView) myView.findViewById(R.id.textViewGoalIntensity);
        NumberPicker npAmount = (NumberPicker) myView.findViewById(R.id.npGoalIntensityAmount);
        NumberPicker npTime = (NumberPicker) myView.findViewById(R.id.npGoalIntensityTime);
        tv.setText("this is the goal input intensity fragment");
        npAmount.setMinValue(amountMin);
        npAmount.setMaxValue(amountMax);
        npAmount.setValue(amountDefault);
        npAmount.setWrapSelectorWheel(false);

        //Set a value change listener for amount NumberPicker
        npAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText("Selected Number : " + newVal);
                selectedAmount = newVal;
                Log.d(TAG, "onValueChange: selectedAmount: "+selectedAmount);
            }
        });

        npTime.setMinValue(timeMin);
        npTime.setMaxValue(timeMax);
        npTime.setValue(timeDefault);

        //Set a value change listener for time NumberPicker
        npTime.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText("Selected Number : " + newVal);
                selectedTime = newVal;
                Log.d(TAG, "onValueChange: selectedTime: "+selectedTime);
            }
        });

        return myView;
    }


}
