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

// Fragment class for selcting goals range


public class GoalRangeFragment extends Fragment {
    private NumberPicker npMin;
    private NumberPicker npMax;
    private int minRangeMin;
    private int minRangeMax;
    private int minRangeDefault;
    private int maxRangeMin;
    private int maxRangeMax;
    private int maxRangeDefault;
    private int minSelectedValue;
    private int maxSelectedValue;
    private int userWeight;
    private String goalType;
    private int frequency;
    private String monitoringPeriod;
    private Button btn;
    private TextView tvRangeMin;
    private TextView tvRangeMax;


    public static GoalRangeFragment newInstance(String goalType, int frequency, String monitoringPeriod) {
        GoalRangeFragment fragment = new GoalRangeFragment();
        Bundle args = new Bundle();
        args.putString("goalType", goalType);
        args.putInt("frequency", frequency);
        args.putString("monitoringPeriod", monitoringPeriod);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userWeight = 70;
        minRangeMin = 50;
        minRangeMax = 150;
        minRangeDefault = 80;
        minSelectedValue = minRangeDefault;
        maxRangeMin = 50;
        maxRangeMax = 150;
        maxRangeDefault = 120;
        maxSelectedValue = maxRangeDefault;

        //get data from the previous fragments
        if (getArguments() != null) {
            goalType = getArguments().getString("goalType");
            frequency = getArguments().getInt("frequency");
            monitoringPeriod = getArguments().getString("monitoringPeriod");
            Log.d(TAG, "onCreate: goalType is "+goalType);
            Log.d(TAG, "onCreate: frequency is "+frequency);
            Log.d(TAG, "onCreate: monitoringPeriod is "+monitoringPeriod);
        } else {
            Log.d(TAG, "onCreate: arguments null");
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout myView =(RelativeLayout) inflater.inflate(R.layout.goal_range_fragment, container, false);
        tvRangeMin = (TextView) myView.findViewById(R.id.textViewGoalRangeMin);
        tvRangeMax = (TextView) myView.findViewById(R.id.textViewGoalRangeMax);
        npMin = (NumberPicker) myView.findViewById(R.id.numberPicker_min);
        npMax = (NumberPicker) myView.findViewById(R.id.numberPicker_max);
        btn = (Button) myView.findViewById(R.id.btnRangeNext);
        //tv.setText("Select the ideal range minimum value");

        // Initialize the pickers
        npMin.setMaxValue(minRangeMax);
        npMin.setMinValue(minRangeMin);
        npMin.setValue(minRangeDefault);
        npMax.setMaxValue(maxRangeMax);
        npMax.setMinValue(maxRangeMin);
        npMax.setValue(maxRangeDefault);

        //check if a certain goal type has been selected & modify the fragment accordingly
        if (goalType.equals("Weight")) {
            weightMode();
        } else if(goalType.equals("Sleep")) {
            sleepMode();
        }

        npMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                Log.d(TAG, "onValueChange: min: "+newVal);
                if (goalType.equals("Sleep")){
                    minSelectedValue = newVal;
                    maxSelectedValue = newVal;
                } else {
                    minSelectedValue = newVal;
                }
            }
        });

        npMax.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                Log.d(TAG, "onValueChange: max: "+newVal);
                maxSelectedValue = newVal;
            }
        });

        //handle the swiping to the next fragment by clicking on the button
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Move to the next fragment

                // Create fragment and pass the selected values as arguments to the next fragment
                GoalNotificationFragment newFragment = GoalNotificationFragment.newInstance(goalType, frequency, monitoringPeriod, minSelectedValue, maxSelectedValue);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit);

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });
        return myView;
    }

    //Methods for initializing the fragment for different goal types.
    public void weightMode(){
        int defaultmin = userWeight-5;
        int defaultmax = userWeight+5;
        minSelectedValue = defaultmin;
        maxSelectedValue = defaultmax;
        npMin.setMinValue(userWeight-10);
        npMin.setMaxValue(userWeight);
        npMin.setValue(defaultmin);
        npMax.setMinValue(userWeight);
        npMax.setMaxValue(userWeight+10);
        npMax.setValue(defaultmax);
        Log.d(TAG, "weightMode: called");
    }

    public void sleepMode(){
        npMin.setMinValue(4);
        npMin.setMaxValue(9);
        npMin.setValue(8);
        npMin.setWrapSelectorWheel(false);
        npMax.setVisibility(View.GONE);
        tvRangeMax.setVisibility(View.GONE);
        tvRangeMin.setText("How many hours should you try to sleep a night?");
        //npMin.setPaddingRelative(0,300,0,0);
    }
}
