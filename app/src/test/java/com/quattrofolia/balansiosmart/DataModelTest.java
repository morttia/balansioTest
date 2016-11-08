package com.quattrofolia.balansiosmart;

import com.quattrofolia.balansiosmart.models.Discipline;
import com.quattrofolia.balansiosmart.models.Goal;
import com.quattrofolia.balansiosmart.models.HealthDataEntry;
import com.quattrofolia.balansiosmart.models.HealthDataType;
import com.quattrofolia.balansiosmart.models.Range;
import com.quattrofolia.balansiosmart.models.User;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.quattrofolia.balansiosmart.models.HealthDataType.BLOOD_GLUCOSE;
import static com.quattrofolia.balansiosmart.models.HealthDataType.WEIGHT;
import static com.quattrofolia.balansiosmart.models.MonitoringPeriod.DAY;
import static com.quattrofolia.balansiosmart.models.MonitoringPeriod.MONTH;
import static org.mockito.Mockito.mock;

public class DataModelTest {

    User user = new User();
    HealthDataType bgType = BLOOD_GLUCOSE;
    HealthDataType weightType = WEIGHT;
    Goal bgGoal;
    Goal weightGoal;
    Discipline bgDiscipline = new Discipline(4, DAY);
    Discipline weightDiscipline = new Discipline(1, MONTH);
    List<HealthDataEntry> healthDataEntries = new ArrayList<HealthDataEntry>();
    Range bgRange = new Range(3.8, 4.2);
    Range weightRange = new Range(78, 82);
    Random r = new Random();
    int ms;

    // Mocks
    User mockedUser = mock(User.class);
    List<HealthDataEntry> mockedHealthDataEntries = mock(ArrayList.class);
    Discipline mockedDiscipline = mock(Discipline.class);
    Range mockedRange = mock(Range.class);
    Goal mockedGoal = mock(Goal.class);

    @Before
    public void createMeasurements() throws Exception {
        ms = 1;

        // Declare test dates
        DateTime now;
        DateTime yesterday;
        DateTime yesterdayMidnight;
        DateTime yesterdayAfterMidnight;
        List<DateTime> bgRandomTimesYesterday;
        DateTime yesterdayBeforeMidnight;
        DateTime todayMidnight;
        DateTime todayAfterMidnight;
        List<DateTime> todayRandomTimes;
        DateTime todayBeforeMidnight;
        DateTime tomorrowMidnight;
        DateTime tomorrowAfterMidnight;

        // Init test dates
        now = new DateTime();
        yesterday = now.minusDays(1);
        // yesterdayMidnight = midnightByDate(yesterday);
        // yesterdayAfterMidnight = yesterdayMidnight.plusMillis(ms);
        bgRandomTimesYesterday = randomTimes(r.nextInt(5), yesterday);
        // todayMidnight = midnightByDate(now);
        // yesterdayBeforeMidnight = todayMidnight.minusMillis(ms);
        // todayAfterMidnight = todayMidnight.plusMillis(ms);
        todayRandomTimes = randomTimes(r.nextInt(4), now);
        // tomorrowMidnight = todayMidnight.plusDays(1);
        // todayBeforeMidnight = tomorrowMidnight.minusMillis(ms);
        // tomorrowAfterMidnight = tomorrowMidnight.plusMillis(ms);

        // Create test entries
        healthDataEntries = new ArrayList<>();
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), yesterdayMidnight.toInstant()));
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), yesterdayAfterMidnight.toInstant()));
        for (DateTime dt : bgRandomTimesYesterday) {
            healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), dt.toInstant()));
        }
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), yesterdayBeforeMidnight.toInstant()));
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), todayMidnight.toInstant()));
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), todayAfterMidnight.toInstant()));
        for (DateTime dt : todayRandomTimes) {
            healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), dt.toInstant()));
        }
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), todayBeforeMidnight.toInstant()));
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), tomorrowMidnight.toInstant()));
        // healthDataEntries.add(new HealthDataEntry(bgType, randomBgValue(), tomorrowAfterMidnight.toInstant()));

        healthDataEntries.add(new HealthDataEntry(weightType, randomWeightValue(), new DateTime(2016, 11, 7, 12, 5).toInstant()));
    }

    @Test
    public void createGoalForUser() throws Exception {

        bgGoal = new Goal(BLOOD_GLUCOSE, bgDiscipline, bgRange);

        weightGoal = new Goal(WEIGHT, weightDiscipline, weightRange);

        user.goals.add(bgGoal);
        user.goals.add(weightGoal);

        for (Goal goal : user.goals) {

            Discipline discipline = goal.getDiscipline();

            Interval currentPeriod = goal
                    .getDiscipline()
                    .getMonitoringPeriod()
                    .quantizedInterval(null, 0);

            // Clone the sample pool
            List<HealthDataEntry> filteredEntries = new ArrayList<>(healthDataEntries);

            /*Filter out unwanted samples*/
            Iterator<HealthDataEntry> evalIterator = filteredEntries.iterator();
            while (evalIterator.hasNext()) {
                HealthDataEntry entry = evalIterator.next();
                if (!currentPeriod.contains(entry.getInstant()) ||
                        entry.getType() != goal.getType()) {
                    evalIterator.remove();
                }
            }
            System.out.println("Goal " + goal.getType().toString() + "completion for current " + discipline.getMonitoringPeriod().name() + ": " + filteredEntries.size() + "/" + discipline.getFrequency());
        }
    }

    private List<HealthDataEntry> entriesByInterval(List<HealthDataEntry> fromEntries, Interval byInterval) {
        List<HealthDataEntry> entries = new ArrayList<>();
        for (HealthDataEntry entry : fromEntries) {
            if (byInterval.contains(entry.getInstant())) {
                entries.add(entry);
            }
        }
        return entries;
    }

    private List<HealthDataEntry> entriesByType(List<HealthDataEntry> fromEntries, HealthDataType byType) {
        List<HealthDataEntry> entries = new ArrayList<>();
        for (HealthDataEntry entry : fromEntries) {
            if (entry.getType() == byType) {
                entries.add(entry);
            }
        }
        return entries;
    }

    private int completed(Discipline discipline, List<HealthDataEntry> entryPool, Interval onPeriod, HealthDataType byType) {
        List<HealthDataEntry> entriesByType = new ArrayList<>();
        int accomplishments = 0;
        int queryIndex = entryPool.size();
        while (queryIndex >= 0) {
            HealthDataEntry entry = entryPool.get(queryIndex);
            if (entry.getType() == byType) {
                entriesByType.add(entry);
            }
            queryIndex--;
        }
        for (HealthDataEntry entry : entriesByType) {
            if (onPeriod.contains(entry.getInstant())) {
                accomplishments++;
            }
        }
        return accomplishments;
    }

    // Returns a random time on provided date
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

    // Returns provided date with clock turned back to 00:00
    private DateTime midnightByDate(DateTime dateTime) {
        return new DateTime(
                dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(),
                0,
                0);
    }

    // Returns a list containing provided amount of random times on provided date
    private List<DateTime> randomTimes(int amount, DateTime byDate) {
        List<DateTime> randomTimes = new ArrayList<>();
        for (int i = 0; i < amount; amount--) {
            randomTimes.add(randomTimeByDate(byDate));
        }
        return randomTimes;
    }

    // Returns a random BigDecimal for mocking blood glucose values
    private BigDecimal randomBgValue() {
        Number n = (Number) (r.nextInt(500) + 200);
        return new BigDecimal(n.toString()).movePointLeft(2);
    }

    // Returns a random BigDecimal for mocking weight values
    private BigDecimal randomWeightValue() {
        Number n = (Number) (r.nextInt(50) + 750);
        return new BigDecimal(n.toString()).movePointLeft(1);
    }

    // Prints each entry in the provided list to console in format:
    // <DateTime>: <HealthDataType>, <Value>
    private void printEntries(List<HealthDataEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Nothing to print.");
            return;
        }
        for (HealthDataEntry e : entries) {
            System.out.println(e.getInstant().toDateTime().toString() + ": " + e.getType().toString() + ", " + e.getValue().toString());
        }
    }
}
