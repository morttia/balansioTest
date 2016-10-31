package com.quattrofolia.balansiosmart;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalRangeFragment extends Fragment {
    private int minRangeMin;
    private int minRangeMax;
    private int minRangeDefault;
    private int maxRangeMin;
    private int maxRangeMax;
    private int maxRangeDefault;
    private int minSelectedValue;
    private int maxSelectedValue;

    private String type;

    public static GoalRangeFragment newInstance() {
        GoalRangeFragment fragment = new GoalRangeFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //type is received from main activity
        type = ((MainActivity) getActivity()).getSelectedDataType();

        minRangeMin = 50;
        minRangeMax = 150;
        minRangeDefault = 80;
        minSelectedValue = minRangeDefault;
        maxRangeMin = 50;
        maxRangeMax = 150;
        maxRangeDefault = 120;
        maxSelectedValue = maxRangeDefault;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_range_fragment, container, false);
        TextView tv = (TextView) myView.findViewById(R.id.textViewGoalRange);
        NumberPicker npMin = (NumberPicker) myView.findViewById(R.id.numberPicker_min);
        NumberPicker npMax = (NumberPicker) myView.findViewById(R.id.numberPicker_max);
        tv.setText("This is the goal ideal input range fragment");

        // picker values are set according to the type
        if(type.equals("weight")){
            minRangeMin=50;
            minRangeMax=120;
            minRangeDefault = 75;
        }
        else if(type.equals("kcal")){
            minRangeMin = 2000;
            minRangeMax = 3000;
            minRangeDefault = 2500;

        }
        npMin.setMaxValue(minRangeMax);
        npMin.setMinValue(minRangeMin);
        npMin.setValue(minRangeDefault);
        npMax.setMaxValue(maxRangeMax);
        npMax.setMinValue(maxRangeMin);
        npMax.setValue(maxRangeDefault);

        npMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                Log.d(TAG, "onValueChange: min: "+newVal);
                minSelectedValue = newVal;
            }
        });

        npMax.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                Log.d(TAG, "onValueChange: max: "+newVal);
                maxSelectedValue = newVal;
            }
        });

        return myView;
    }
}
