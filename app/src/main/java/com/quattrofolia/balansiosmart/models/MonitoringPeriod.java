package com.quattrofolia.balansiosmart.models;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;

/* This enumeration defines the supported monitoring periods.
* Monitoring periods are used by Discipline objects for
* evaluating progress_view_goal_item accomplishments.
*
* Each value is assigned with a function that returns a Joda
* Interval object.  */

public enum MonitoringPeriod {
    day ("day") {
        @Override
        public Interval quantizedInterval(Instant pointer, int transposition) {
            if (pointer == null) {
                pointer = new Instant();
            }
            DateTime midnight = this.midnight(pointer.toDateTime());
            DateTime dateStarting = midnight.plusDays(transposition);
            DateTime dateEnding = dateStarting.toDateTime().plusDays(1);
            return new Interval(dateStarting, dateEnding);
        }
    },
    week ("week") {
        @Override
        public Interval quantizedInterval(Instant pointer, int transposition) {
            if (pointer == null) {
                pointer = new Instant();
            }
            int dayOfWeek = pointer.toDateTime().getDayOfWeek();
            DateTime midnight = this.midnight(pointer.toDateTime());
            DateTime mondayMidnight = midnight.toDateTime().minusDays(dayOfWeek-1);
            DateTime dateStarting = mondayMidnight.plusWeeks(transposition);
            DateTime dateEnding = dateStarting.toDateTime().plusWeeks(1);
            return new Interval(dateStarting, dateEnding);
        }
    },
    month ("month") {
        @Override
        public Interval quantizedInterval(Instant pointer, int transposition) {
            if (pointer == null) {
                pointer = new Instant();
            }
            int dayOfMonth = pointer.toDateTime().getDayOfMonth();
            DateTime midnight = this.midnight(pointer.toDateTime());
            DateTime firstDayOfMonth = midnight.toDateTime().minusDays(dayOfMonth-1);
            DateTime dateStarting = firstDayOfMonth.plusMonths(transposition);
            DateTime dateEnding = dateStarting.toDateTime().plusMonths(1);
            return new Interval(dateStarting, dateEnding);
        }
    },
    year ("year") {
        @Override
        public Interval quantizedInterval(Instant pointer, int transposition) {
            if (pointer == null) {
                pointer = new Instant();
            }
            int dayOfYear = pointer.toDateTime().getDayOfYear();
            DateTime midnight = this.midnight(pointer.toDateTime());
            DateTime firstDayOfYear = midnight.toDateTime().minusDays(dayOfYear-1);
            DateTime dateStarting = firstDayOfYear.plusYears(transposition);
            DateTime dateEnding = dateStarting.toDateTime().plusWeeks(1);
            return new Interval(dateStarting, dateEnding);
        }
    };

    private final String name;
    MonitoringPeriod(String name) {
        this.name = name;
    }
    public String toString() {
        return this.name;
    }

    public abstract Interval quantizedInterval(Instant pointer, int transposition);
    DateTime midnight(DateTime ref) {
        return new DateTime(
                ref.getYear(),
                ref.getMonthOfYear(),
                ref.getDayOfMonth(),
                0,
                0);
    }
}
