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

public class GoalIntensityFragment extends Fragment {

    public static GoalIntensityFragment newInstance() {
        GoalIntensityFragment fragment = new GoalIntensityFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_intensity_fragment, container, false);
        TextView tv = (TextView) myView.findViewById(R.id.textViewGoalIntensity);
        tv.setText("this is the goal input intensity fragment");
        return myView;
    }
}
