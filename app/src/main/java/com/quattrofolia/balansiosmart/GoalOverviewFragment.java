package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by eemeliheinonen on 09/11/2016.
 */

public class GoalOverviewFragment extends Fragment {

    private String goalType;
    private int measurementAmount;
    private String timeframe;
    private int idealRangeMin;
    private int idealRangeMax;
    private boolean notificationBefore;
    private boolean notificationOnTime;
    private boolean notificationForgot;

    public static GoalOverviewFragment newInstance(String GoalType, int measurementAmount, String timeframe, int idealRangeMin, int idealRangeMax, boolean checkboxBefore, boolean checkboxOnTime, boolean checkboxForgot) {
        GoalOverviewFragment fragment = new GoalOverviewFragment();
        Bundle args = new Bundle();
        args.putString("goalType", GoalType);
        args.putInt("amount", measurementAmount);
        args.putString("timeframe", timeframe);
        args.putInt("rangeMin", idealRangeMin);
        args.putInt("rangeMax", idealRangeMax);
        args.putBoolean("cbBefore", checkboxBefore);
        args.putBoolean("cbOnTime", checkboxOnTime);
        args.putBoolean("cbForgot", checkboxForgot);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get data from the previous fragments
        if (getArguments() != null) {
            goalType = getArguments().getString("goalType");
            measurementAmount = getArguments().getInt("amount");
            timeframe = getArguments().getString("timeframe");
            idealRangeMin = getArguments().getInt("rangeMin");
            idealRangeMax = getArguments().getInt("rangeMax");
            notificationBefore = getArguments().getBoolean("cbBefore");
            notificationOnTime = getArguments().getBoolean("cbOnTime");
            notificationForgot = getArguments().getBoolean("cbForgot");

            Log.d(TAG, "onCreate: goaltype: "+goalType);
            Log.d(TAG, "onCreate: measurement amount: "+measurementAmount);
            Log.d(TAG, "onCreate: timeframe: "+timeframe);
            Log.d(TAG, "onCreate: ideal range minimum value: "+idealRangeMin);
            Log.d(TAG, "onCreate: ideal range maximum value: "+idealRangeMax);
            Log.d(TAG, "onCreate: Before is checked: "+notificationBefore);
            Log.d(TAG, "onCreate: OnTime is checked: "+notificationOnTime);
            Log.d(TAG, "onCreate: Forgot is checked: "+notificationForgot);
        } else {
            Log.d(TAG, "onCreate: arguments null");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RelativeLayout myView = (RelativeLayout) inflater.inflate(R.layout.goal_overview_fragment, container, false);
        TextView tvType = (TextView) myView.findViewById(R.id.tvOverviewType);
        TextView tvFrequency = (TextView) myView.findViewById(R.id.tvOverviewFrequency);
        TextView tvRangeMin = (TextView) myView.findViewById(R.id.tvOverviewRangeMin);
        TextView tvRangeMax = (TextView) myView.findViewById(R.id.tvOverviewRangeMax);
        Button btnCreateGoal = (Button) myView.findViewById(R.id.btnCreateGoal);
        tvType.setText("Goal type: "+goalType);
        tvFrequency.setText(measurementAmount+" Measurement(s) a "+timeframe);
        if (idealRangeMin != 0 && idealRangeMax != 0){
            tvRangeMin.setText("Goal range minimum value: "+idealRangeMin);
            tvRangeMax.setText("Goal range maximum value: "+idealRangeMax);
        }

        btnCreateGoal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO Implement goal creation here
            }
        });

        return myView;
    }
}
