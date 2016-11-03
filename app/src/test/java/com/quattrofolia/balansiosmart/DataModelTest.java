package com.quattrofolia.balansiosmart;

import com.quattrofolia.balansiosmart.models.Discipline;
import com.quattrofolia.balansiosmart.models.Goal;
import com.quattrofolia.balansiosmart.models.HealthDataType;
import com.quattrofolia.balansiosmart.models.MonitoringPeriod;
import com.quattrofolia.balansiosmart.models.Range;
import com.quattrofolia.balansiosmart.models.User;

import org.junit.Before;
import org.junit.Test;

public class DataModelTest {

    User user;
    MonitoringPeriod period;
    Discipline discipline;
    Range range;
    Goal goal;

    @Before
    public void createUser() throws Exception {
        user = new User();
    }

    @Test
    public void createGoalForUser() throws Exception {

        period = MonitoringPeriod.DAY;
        discipline = new Discipline(5, period);
        range = new Range(3.8, 4.3);
        goal = new Goal(
                HealthDataType.BLOOD_GLUCOSE,
                discipline,
                range
                );
        user.goals.add(goal);
    }
}
