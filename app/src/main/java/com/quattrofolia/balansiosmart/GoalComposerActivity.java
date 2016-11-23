package com.quattrofolia.balansiosmart;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.quattrofolia.balansiosmart.models.Discipline;
import com.quattrofolia.balansiosmart.models.Goal;
import com.quattrofolia.balansiosmart.models.Range;
import com.quattrofolia.balansiosmart.models.User;

import io.realm.Realm;
import io.realm.RealmList;

import com.quattrofolia.balansiosmart.models.Discipline;
import com.quattrofolia.balansiosmart.models.Goal;
import com.quattrofolia.balansiosmart.models.Range;

import java.math.BigDecimal;


import static android.content.ContentValues.TAG;
import static com.quattrofolia.balansiosmart.models.MonitoringPeriod.*;
import static com.quattrofolia.balansiosmart.models.HealthDataType.*;


public class GoalComposerActivity extends FragmentActivity{
    private User user;
    private Goal goal;
    private Discipline discipline;
    private Range range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this); // Initialize Realm only once when the app starts.
        setContentView(R.layout.activity_main);
        user = new User();
        user.goals = new RealmList<>();

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // If we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            GoalTypeFragment typeFragment = new GoalTypeFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            typeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, typeFragment).commit();
        }
    }

    public void addGoal(Goal g){
        Log.d("jes", "addGoal: ");
        user.goals.add(g);
        Log.d("jes", "addGoal: Frequency: "+g.getDiscipline().getFrequency());
    }


    //notes for creating default goals
    //user object created in GoalComposerActivity


    public void addDefaultGoals(){
        ////////blood glucose default goal////////
        discipline = new Discipline();
        range = new Range();
        goal = new Goal();
        goal.setType(BLOOD_GLUCOSE);
        discipline.setFrequency(R.integer.bg_measurements_min_per_day);
        discipline.setMonitoringPeriod(day);
        range.setLow(new BigDecimal(R.string.bg_target_range_low));
        range.setHigh(new BigDecimal(R.string.bg_target_range_high));
        goal.setDiscipline(discipline);
        goal.setTargetRange(range);
        /////////////

        //TODO set notification style

        //TODO create rest of default goals

    }
}
