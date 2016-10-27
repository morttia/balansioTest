package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalNotificationFragment extends Fragment {

    public static GoalNotificationFragment newInstance() {
        GoalNotificationFragment fragment = new GoalNotificationFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_notification_fragment, container, false);
        TextView tv = (TextView) myView.findViewById(R.id.textViewGoalNotification);
        tv.setText("This is the goal notification intensity fragment");
        return myView;
    }
}
