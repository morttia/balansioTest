package com.quattrofolia.balansiosmart;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by mrbeva on 10/29/16.
 */

public class GoalTypeAdapter extends RecyclerView.Adapter<GoalTypeAdapter.ListDataHolder>{

    private List<GoalTypeListItem> listData;
    private LayoutInflater inflater;

    public GoalTypeAdapter (List<GoalTypeListItem> listData, Context context){
        this.inflater = LayoutInflater.from(context);
        this.listData= listData;

    }

    @Override
    public ListDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.goal_type_fragment_list_item, parent, false);
        return new ListDataHolder(view);
    }

    @Override
    public void onBindViewHolder(ListDataHolder holder, int position) {
        GoalTypeListItem item = listData.get(position);
        holder.header.setText(item.getHeader());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ListDataHolder extends RecyclerView.ViewHolder {

        private Button header;
        private View container;

        public ListDataHolder(View itemView) {
            super(itemView);

            header = (Button) itemView.findViewById(R.id.button);
            container = itemView.findViewById(R.id.type_list_item);
        }
    }

}
