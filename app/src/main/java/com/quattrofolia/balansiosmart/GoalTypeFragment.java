package com.quattrofolia.balansiosmart;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by eemeliheinonen on 27/10/2016.
 */

public class GoalTypeFragment extends Fragment implements RecyclerViewClickListener {
    DataTypePasser dataPasser;
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
        goalTypeAdapter = new GoalTypeAdapter(GoalTypeListData.getListData(), getActivity(), this);
        recyclerView.setAdapter(goalTypeAdapter);


        //calling pass data with the type
        passData("kcal");
        return myView;
    }

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        activity = getActivity();
        dataPasser = (DataTypePasser) activity;
    }

    public void passData(String data) {
        dataPasser.setSelectedDataType(data);
    }

    @Override
    public void recyclerViewListClicked(View v, int position, String itemName){
        Log.d("jes", "recyclerViewListClicked: "+ position+ " "+ itemName);

        // Create fragment and give it an argument specifying the article it should show
        GoalIntensityFragment newFragment = GoalIntensityFragment.newInstance(itemName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }
}
