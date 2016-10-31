package com.quattrofolia.balansiosmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalTypeFragment extends Fragment {

    private RecyclerView recyclerView;
    private GoalTypeAdapter goalTypeAdapter;

    public static GoalTypeFragment newInstance() {
        GoalTypeFragment fragment = new GoalTypeFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout myView =(LinearLayout) inflater.inflate(R.layout.goal_type_fragment, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        goalTypeAdapter = new GoalTypeAdapter(GoalTypeListData.getListData(), getActivity());
        recyclerView.setAdapter(goalTypeAdapter);
        return myView;
    }

}
