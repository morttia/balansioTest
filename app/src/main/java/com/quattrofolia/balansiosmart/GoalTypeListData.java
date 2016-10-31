package com.quattrofolia.balansiosmart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrbeva on 10/30/16.
 */

public class GoalTypeListData {

    private static final String[] headers = {"Blood Glucose", "Blood Pressure", "Weight", "Exercise", "Sleep", "Nutrition", "Heart Rate"};

    public static List<GoalTypeListItem> getListData() {
        List<GoalTypeListItem> goal_composer_type = new ArrayList<>();


        for (int i = 0; i < headers.length; i++) {
            GoalTypeListItem listItem = new GoalTypeListItem();
            listItem.setHeader(headers[i]);
            goal_composer_type.add(listItem);
        }
        return goal_composer_type;
    }
}
