package com.quattrofolia.balansiosmart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {
    private String[] goalDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView goalTestTextView;

        public ViewHolder(View v) {
            super(v);
            goalTestTextView = (TextView) v.findViewById(R.id.goal_item_test_text);
        }
    }

    public GoalAdapter(String[] goalDataset) {
        this.goalDataset = goalDataset;
    }

    @Override
    public GoalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_view_goal_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(GoalAdapter.ViewHolder holder, int position) {
        holder.goalTestTextView.setText(goalDataset[position]);
    }

    @Override
    public int getItemCount() {
        return goalDataset.length;
    }
}
