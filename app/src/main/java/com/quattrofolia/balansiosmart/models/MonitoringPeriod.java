package com.quattrofolia.balansiosmart.models;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;

/* This enumeration defines the supported monitoring periods.
* Monitoring periods are used by Discipline objects for
* evaluating goal accomplishments.
*
* Each value is assigned with a function that returns a Joda
* Instant object.  */

public enum MonitoringPeriod {
    DAY {
        @Override
        public Interval interval(Instant refInstant) {
            DateTime midnight = this.midnight(refInstant);
            DateTime timeStarting = midnight;
            DateTime timeEnding = timeStarting.plusDays(1);
            return new Interval(timeStarting.toInstant(), timeEnding.toInstant());
        }
    },
    WEEK {
        @Override
        public Interval interval(Instant refInstant) {
            DateTime midnight = this.midnight(refInstant);
            int dayOfWeek = refInstant.toDateTime().getDayOfWeek();
            DateTime timeStarting = midnight.minusDays(dayOfWeek-1);
            DateTime timeEnding = timeStarting.plusWeeks(1);
            return new Interval(timeStarting.toInstant(), timeEnding.toInstant());
        }
    },
    MONTH {
        @Override
        public Interval interval(Instant refInstant) {
            DateTime midnight = this.midnight(refInstant);
            int dayOfMonth = refInstant.toDateTime().getDayOfMonth();
            DateTime timeStarting = midnight.minusDays(dayOfMonth-1);
            DateTime timeEnding = timeStarting.plusMonths(1);
            return new Interval(timeStarting.toInstant(), timeEnding.toInstant());
        }
    },
    YEAR {
        @Override
        public Interval interval(Instant refInstant) {
            DateTime midnight = this.midnight(refInstant);
            int dayOfYear = refInstant.toDateTime().getDayOfYear();
            DateTime timeStarting = midnight.minusDays(dayOfYear-1);
            DateTime timeEnding = timeStarting.plusYears(1);
            return new Interval(timeStarting.toInstant(), timeEnding.toInstant());
        }
    };

    public abstract Interval interval(Instant refInstant);
    DateTime midnight(Instant refInstant) {
        DateTime refInstantTime = refInstant.toDateTime();
        return new DateTime(
                refInstantTime.getYear(),
                refInstantTime.getMonthOfYear(),
                refInstantTime.getDayOfMonth(),
                0,
                0);
    }
}
