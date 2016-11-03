package com.quattrofolia.balansiosmart;

import com.quattrofolia.balansiosmart.models.Discipline;
import com.quattrofolia.balansiosmart.models.Goal;
import com.quattrofolia.balansiosmart.models.HealthDataEntry;
import com.quattrofolia.balansiosmart.models.HealthDataType;
import com.quattrofolia.balansiosmart.models.Range;
import com.quattrofolia.balansiosmart.models.User;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.quattrofolia.balansiosmart.models.HealthDataType.BLOOD_GLUCOSE;
import static com.quattrofolia.balansiosmart.models.MonitoringPeriod.DAY;

public class DataModelTest {

    private static final String TAG = "DataModelTest";
    User user = new User();
    HealthDataType type = BLOOD_GLUCOSE;
    List<HealthDataEntry> healthDataEntries = new ArrayList<HealthDataEntry>();
    Discipline discipline = new Discipline(4, DAY);
    Range range = new Range(3.8, 4.2);
    Goal goal;
    Random r = new Random();

    @Before
    public void createMeasurements() throws Exception {
        int ms = 1;

        // dates
        DateTime now;
        DateTime yesterday;
        DateTime yesterdayMidnight;
        DateTime yesterdayAfterMidnight;
        List<DateTime> yesterdayRandomTimes = new ArrayList<>();
        DateTime yesterdayBeforeMidnight;
        DateTime todayMidnight;
        DateTime todayAfterMidnight;
        List<DateTime> todayRandomTimes = new ArrayList<>();
        DateTime todayBeforeMidnight;
        DateTime tomorrowMidnight;
        DateTime tomorrowAfterMidnight;

        now = new DateTime();
        yesterday = now.minusDays(1);
        yesterdayMidnight = midnightByDate(yesterday);
        yesterdayAfterMidnight = yesterdayMidnight.plusMillis(ms);
        for (int i = 0; i < 4; i++) {
            yesterdayRandomTimes.add(randomTimeByDate(yesterday));
        }
        todayMidnight = midnightByDate(now);
        yesterdayBeforeMidnight = todayMidnight.minusMillis(ms);
        todayAfterMidnight = todayMidnight.plusMillis(ms);
        for (int i = 0; i < 4; i++) {
            todayRandomTimes.add(randomTimeByDate(now));
        }
        tomorrowMidnight = todayMidnight.plusDays(1);
        todayBeforeMidnight = tomorrowMidnight.minusMillis(ms);
        tomorrowAfterMidnight = tomorrowMidnight.plusMillis(ms);

        // healthDataEntries
        List<HealthDataEntry> healthDataEntries = new ArrayList<>();
        healthDataEntries.add(new HealthDataEntry(type, randomBgValue(), yesterdayMidnight.toInstant()));
        healthDataEntries.add(new HealthDataEntry(type, randomBgValue(), yesterdayAfterMidnight.toInstant()));
        for (DateTime dt : yesterdayRandomTimes) {
            healthDataEntries.add(new HealthDataEntry(type, randomBgValue(), dt.toInstant()));
        }
        healthDataEntries.add(new HealthDataEntry(type, randomBgValue(), yesterdayBeforeMidnight.toInstant()));
        healthDataEntries.add(new HealthDataEntry(type, randomBgValue(), todayMidnight.toInstant()));
        healthDataEntries.add(new HealthDataEntry(type, randomBgValue(), todayAfterMidnight.toInstant()));
    }

    private DateTime randomTimeByDate(DateTime dateTime) {
        Random r = new Random();
        return new DateTime(
                dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(),
                r.nextInt(23),
                r.nextInt(59),
                r.nextInt(59)
        );
    }

    private DateTime midnightByDate(DateTime dateTime) {
        return new DateTime(
                dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(),
                0,
                0);
    }

    private BigDecimal randomBgValue() {
        Number n = (Number) (r.nextInt(500) + 300);
        return new BigDecimal(n.toString()).movePointLeft(2);
    }

    @Test
    public void createGoalForUser() throws Exception {

        goal = new Goal(
                BLOOD_GLUCOSE,
                discipline,
                range
        );
        user.goals.add(goal);

        Collections.sort(healthDataEntries, new Comparator<HealthDataEntry>() {
            public int compare(HealthDataEntry e1, HealthDataEntry e2) {
                return e1.getInstant().compareTo(e2.getInstant());
            }
        });

        for (Goal goal : user.goals) {
            List<HealthDataEntry> requiredEntries = new ArrayList<>();
            int requiredAmount = goal.getDiscipline().getFrequency();
            for (int i = 0; i < requiredAmount; i++) {
                for (HealthDataEntry entry : healthDataEntries) {
                    if (entry.getType() == type) {
                        requiredEntries.add(entry);
                    }
                    if (requiredEntries.size() == requiredAmount) {
                        break;
                    }
                }
            }
        }
    }
}
