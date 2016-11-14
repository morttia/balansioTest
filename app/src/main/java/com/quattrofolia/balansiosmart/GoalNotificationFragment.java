package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

// Fragment class for selcting goal's notification preferneces

public class GoalNotificationFragment extends Fragment {

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
        RelativeLayout myView =(RelativeLayout) inflater.inflate(R.layout.goal_notification_fragment, container, false);
        TextView tv = (TextView) myView.findViewById(R.id.tvNotificationRemind);
        Button btnNext = (Button) myView.findViewById(R.id.btnNotificationNext);
        tv.setText("Remind me to measure "+goalType);

        final CheckBox cbBefore = (CheckBox) myView.findViewById(R.id.cbBefore);
        final CheckBox cbOnTime = (CheckBox) myView.findViewById(R.id.cbOnTime);
        final CheckBox cbForgot = (CheckBox) myView.findViewById(R.id.cbForgot);

        //handle the swiping to the next fragment by clicking on the button
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Move to the next fragment

                // Create fragment and pass the selected values as arguments to the next fragment
                GoalOverviewFragment newFragment = GoalOverviewFragment.newInstance(goalType, measurementAmount, timeframe, idealRangeMin, idealRangeMax, cbBefore.isChecked(), cbOnTime.isChecked(), cbForgot.isChecked());
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
}
