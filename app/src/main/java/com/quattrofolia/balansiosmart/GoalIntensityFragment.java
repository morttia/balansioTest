package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

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
    private String selectedTimeframe;
    private NumberPicker npTimeframe;
    private NumberPicker npAmount;
    private final String[] values = {"day","week", "month"};
    private String goalType;

    public static GoalIntensityFragment newInstance(String goalType) {
        GoalIntensityFragment fragment = new GoalIntensityFragment();
        Bundle args = new Bundle();
        args.putString("goalType", goalType);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        amountMin = 1;
        amountMax = 10;
        amountDefault = 5;
        timeMin = 0;
        timeMax = values.length-1;
        timeDefault = 0;
        selectedAmount = amountDefault;
        selectedTimeframe = values[0];

        //get data from the previous fragment
        if (getArguments() != null) {
            goalType = getArguments().getString("goalType");
        } else {
            Log.d(TAG, "onCreate: arguments null");
        }
        Log.d(TAG, "onCreate: selected type: "+goalType);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout myView =(RelativeLayout) inflater.inflate(R.layout.goal_intensity_fragment, container, false);
        tvMeasurementNumber = (TextView) myView.findViewById(R.id.textViewGoalIntensity);
        tvTimeframe = (TextView) myView.findViewById(R.id.textViewGoalIntensityDesc);
        btn = (Button) myView.findViewById(R.id.btnIntensityNext);
        npAmount = (NumberPicker) myView.findViewById(R.id.npGoalIntensityAmount);
        npTimeframe = (NumberPicker) myView.findViewById(R.id.npGoalIntensityTime);
        tvMeasurementNumber.setText("Number of measurements");

        //Initialize the NumberPickers
        npAmount.setMinValue(amountMin);
        npAmount.setMaxValue(amountMax);
        npAmount.setValue(amountDefault);
        npAmount.setWrapSelectorWheel(false);
        npTimeframe.setDisplayedValues(values);
        npTimeframe.setMinValue(timeMin);
        npTimeframe.setMaxValue(timeMax);
        npTimeframe.setValue(timeDefault);

        //Set a value change listener for amount NumberPicker
        npAmount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Set the selected value to a variable
                selectedAmount = newVal;
                Log.d(TAG, "onValueChange: selectedAmount: "+selectedAmount);
            }
        });

        //Set a value change listener for time frame NumberPicker
        npTimeframe.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Set the selected value to a variable
                selectedTimeframe = values[newVal];
                Log.d(TAG, "onValueChange: selectedTimeframe: "+selectedTimeframe);
            }
        });

        //check if a certain goal type has been selected & modify the fragment accordingly
        if (goalType.equals("Weight")) {
            weightMode();
        } else if(goalType.equals("Blood Glucose")) {
            bgMode();
        } else if (goalType.equals("Sleep")) {
            sleepMode();
        } else if (goalType.equals("Exercise")) {
            execriseMode();
        }

        //handle the swiping to the next fragment by clicking on the button
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Move to the next fragment
                if (goalType.equals("Sleep") || goalType.equals("Exercise")) {
                    // Create fragment and pass the selected values as arguments to the next fragment
                    Log.d(TAG, "onClick: about to create a fragment, goalType: " + goalType + " selected amount: " + selectedAmount + " selected timeframe: " + selectedTimeframe);
                    GoalNotificationFragment newFragment = GoalNotificationFragment.newInstance(goalType, selectedAmount, selectedTimeframe, 0, 0);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter, R.anim.exit);

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                } else {
                    Log.d(TAG, "onClick: about to create a fragment, goalType: " + goalType + " selected amount: " + selectedAmount + " selected timeframe: " + selectedTimeframe);
                    GoalRangeFragment newFragment = GoalRangeFragment.newInstance(goalType, selectedAmount, selectedTimeframe);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter, R.anim.exit);
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        return myView;
    }

    //Methods for initializing the fragment for different goal types.
    public void weightMode(){
        int weightDefaultAmount = 2;
        npAmount.setMinValue(1);
        npAmount.setMaxValue(10);
        npAmount.setValue(weightDefaultAmount);
        selectedAmount = weightDefaultAmount;
        selectedTimeframe = values[0];
        Log.d(TAG, "weightMode: ");
    }

    public void bgMode(){
        selectedTimeframe = values[0];
        npTimeframe.setVisibility(View.GONE);
        tvTimeframe.setVisibility(View.GONE);
        tvMeasurementNumber.setText("Number of measurements a day");
        tvMeasurementNumber.setPaddingRelative(0,300,0,0);
        Log.d(TAG, "bgMode: ");
    }

    public void sleepMode(){
        selectedTimeframe = values[0];
        npTimeframe.setVisibility(View.GONE);
        tvTimeframe.setVisibility(View.GONE);
        tvMeasurementNumber.setText("Hours of sleep per night");
        tvMeasurementNumber.setPaddingRelative(0,300,0,0);
        Log.d(TAG, "sleepMode: ");
    }

    public void execriseMode(){
        selectedTimeframe = values[1];
        npTimeframe.setVisibility(View.GONE);
        tvTimeframe.setVisibility(View.GONE);
        tvMeasurementNumber.setText("Times of exercise a week");
        tvMeasurementNumber.setPaddingRelative(0,300,0,0);
        Log.d(TAG, "execriseMode: ");
    }
}
