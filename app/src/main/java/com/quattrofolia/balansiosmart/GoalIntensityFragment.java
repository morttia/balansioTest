package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private TextView tvMeasurementNumber;
    private TextView tvTimeframe;
    private Button btn;
    private int selectedAmount;
    private String selectedTime;
    private String TAG = "debyg";
    private NumberPicker npTimeframe;
    private NumberPicker npAmount;
    private final String[] values = {"day","week", "month"};;

    public static GoalIntensityFragment newInstance() {
        GoalIntensityFragment fragment = new GoalIntensityFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amountMin = 1;
        amountMax = 10;
        amountDefault = 5;
        timeMin = 0;
        timeMax = values.length-1;
        timeDefault = 8;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout myView =(RelativeLayout) inflater.inflate(R.layout.goal_intensity_fragment, container, false);
        tvMeasurementNumber = (TextView) myView.findViewById(R.id.textViewGoalIntensity);
        tvTimeframe = (TextView) myView.findViewById(R.id.textViewGoalIntensityDesc);
        btn = (Button) myView.findViewById(R.id.btnIntensityNext);
        npAmount = (NumberPicker) myView.findViewById(R.id.npGoalIntensityAmount);
        npTimeframe = (NumberPicker) myView.findViewById(R.id.npGoalIntensityTime);
        tvMeasurementNumber.setText("Number of measurements");
        
        //Initialize the first NumberPicker
        npAmount.setMinValue(amountMin);
        npAmount.setMaxValue(amountMax);
        npAmount.setValue(amountDefault);
        npAmount.setWrapSelectorWheel(false);


        //handle the swiping to the next fragment by clicking on the button
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity) getActivity()).getViewPager().setCurrentItem(2);
            }
        });

        //Set a value change listener for amount NumberPicker
        npAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Set the selected value to a variable
                selectedAmount = newVal;
                Log.d(TAG, "onValueChange: selectedAmount: "+selectedAmount);
            }
        });

        //Initialize the second NumberPicker
        npTimeframe.setDisplayedValues(values);
        npTimeframe.setMinValue(timeMin);
        npTimeframe.setMaxValue(timeMax);
        npTimeframe.setValue(timeDefault);

        //Set a value change listener for time NumberPicker
        npTimeframe.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Set the selected value to a variable
                selectedTime = values[newVal];
                Log.d(TAG, "onValueChange: selectedTime: "+selectedTime);
            }
        });

        return myView;
    }

    public void weightMode(){
        amountMin = 1;
        amountMax = 10;
        amountDefault = 3;
        Log.d(TAG, "weightMode: called");
    }

    public void bgMode(){
        npTimeframe.setVisibility(View.GONE);
        tvTimeframe.setVisibility(View.GONE);
        tvMeasurementNumber.setText("Number of measurements a day");
        tvMeasurementNumber.setPaddingRelative(0,300,0,0);
        Log.d(TAG, "bgMode: ");
    }

    public String getSelectedTime() {
        return selectedTime;
    }

    public int getSelectedAmount() {
        return selectedAmount;
    }
}
