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

public class GoalTypeFragment extends Fragment {

    public static GoalTypeFragment newInstance() {
        GoalTypeFragment fragment = new GoalTypeFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_type_fragment, container, false);
        TextView tv = (TextView) myView.findViewById(R.id.textViewGoalType);
        tv.setText("This is the goal type selection fragment");
        return myView;
    }
}
