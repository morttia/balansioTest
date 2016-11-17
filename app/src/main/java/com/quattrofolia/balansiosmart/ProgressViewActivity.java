package com.quattrofolia.balansiosmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.quattrofolia.balansiosmart.cardstack.CardStack;


public class ProgressViewActivity extends Activity {
    private CardStack mCardStack;
    private CardsDataAdapter mCardAdapter;
    private Button createGoalButton;
    private RecyclerView goalRecyclerView;
    private RecyclerView.Adapter goalAdapter;
    private RecyclerView.LayoutManager goalLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_view);
        goalRecyclerView = (RecyclerView) findViewById(R.id.goal_recycler_view);
        goalRecyclerView.setHasFixedSize(false);
        goalLayoutManager = new LinearLayoutManager(this);
        goalRecyclerView.setLayoutManager(goalLayoutManager);
        String[] goalDataset = {"one", "two", "three"};
        goalAdapter = new GoalAdapter(goalDataset);
        goalRecyclerView.setAdapter(goalAdapter);

        createGoalButton = (Button)findViewById(R.id.create_goal_button);

        createGoalButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(ProgressViewActivity.this, GoalComposerActivity.class);
                startActivity(i);
            }
        });

        mCardStack = (CardStack)findViewById(R.id.container);

        mCardStack.setContentResource(R.layout.card_content);
        //mCardStack.setStackMargin(20);

        mCardAdapter = new CardsDataAdapter(getApplicationContext());
        mCardAdapter.add("test1");
        mCardAdapter.add("test2");
        mCardAdapter.add("test3");
        mCardAdapter.add("test4");
        mCardAdapter.add("test5");

        mCardStack.setAdapter(mCardAdapter);

        if(mCardStack.getAdapter() != null) {
            Log.i("MyActivity", "Card Stack size: " + mCardStack.getAdapter().getCount());
        }
    }

}
