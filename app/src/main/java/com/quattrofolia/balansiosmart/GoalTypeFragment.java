package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalTypeFragment extends Fragment {

    private Button bloodGlucose;
    private Button activity;
    private Button sleep;
    private Button weight;

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

        bloodGlucose = (Button) myView.findViewById(R.id.bloodGlucose);
        activity= (Button) myView.findViewById(R.id.activity);
        sleep = (Button) myView.findViewById(R.id.sleep);
        weight = (Button) myView.findViewById(R.id.weight);

        tv.setText("This is the goal type selection fragment");
        return myView;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == bloodGlucose) {
                Log.i("listener", "blood glucose button clicked");
            }else if(v == activity) {
                Log.i("listener", "activity button clicked");
            }else if(v == sleep) {
                Log.i("listener", "sleep button clicked");
            }else if(v == weight) {
                Log.i("listener", "weight button clicked");
            }
        }
    };

}
