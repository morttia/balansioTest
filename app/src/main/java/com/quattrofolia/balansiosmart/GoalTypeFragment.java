package com.quattrofolia.balansiosmart;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalTypeFragment extends Fragment {
    OnTypeDataPass dataPasser;
    Activity activity;

    private RecyclerView recyclerView;
    private GoalTypeAdapter goalTypeAdapter;

    public static GoalTypeFragment newInstance() {
        GoalTypeFragment fragment = new GoalTypeFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_type_fragment, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        goalTypeAdapter = new GoalTypeAdapter(GoalTypeListData.getListData(), getActivity());
        recyclerView.setAdapter(goalTypeAdapter);
        TextView tv = (TextView) myView.findViewById(R.id.textViewGoalType);
        tv.setText("This is the goal type selection fragment");

        //calling pass data with the type
        passData("kcal");
        return myView;
    }

    ////// selected type is sent to main activity (where from the other fragments can get it)////////
    public interface OnTypeDataPass {
        public void onTypeDataPass(String data);
    }


    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        activity = getActivity();
        dataPasser = (OnTypeDataPass) activity;
    }

    public void passData(String data) {
        dataPasser.onTypeDataPass(data);
    }
    /////////////////////
}
