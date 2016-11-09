package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalNotificationFragment extends Fragment {

    private RadioButton beforehand;
    private RadioButton ontime;
    private RadioButton late;
    private String goalType;
    private int measurementAmount;
    private String timeframe;
    private int idealRangeMin;
    private int idealRangeMax;

    public static GoalNotificationFragment newInstance(String GoalType, int measurementAmount, String timeframe, int idealRangeMin, int idealRangeMax) {
        GoalNotificationFragment fragment = new GoalNotificationFragment();
        Bundle args = new Bundle();
        args.putString("goalType", GoalType);
        args.putInt("amount", measurementAmount);
        args.putString("timeframe", timeframe);
        args.putInt("rangeMin", idealRangeMin);
        args.putInt("rangeMax", idealRangeMax);
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
            Log.d(TAG, "onCreate: goaltype: "+goalType);
            Log.d(TAG, "onCreate: measurement amount: "+measurementAmount);
            Log.d(TAG, "onCreate: timeframe: "+timeframe);
            Log.d(TAG, "onCreate: ideal range minimum value: "+idealRangeMin);
            Log.d(TAG, "onCreate: ideal range maximum value: "+idealRangeMax);
        } else {
            Log.d(TAG, "onCreate: arguments null");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_notification_fragment, container, false);
        TextView tv = (TextView) myView.findViewById(R.id.textViewGoalNotification);
        tv.setText("Remind me to measure "+goalType);
        return myView;
    }
}
