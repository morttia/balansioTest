package com.quattrofolia.balansiosmart.models;

import org.joda.time.Period;

/* This enumeration defines the supported monitoring periods.
* Monitoring periods are used by Discipline objects for
* evaluating goal accomplishments.
*
* Each value is assigned with a Joda Period object and
* accessed via toPeriod() method. */

public enum MonitoringPeriod {
    DAY(new Period(0, 0, 0, 1, 0, 0, 0, 0)),
    WEEK(new Period(0, 0, 1, 0, 0, 0, 0, 0)),
    MONTH(new Period(0, 1, 0, 0, 0, 0, 0, 0)),
    YEAR(new Period(1, 0, 0, 0, 0, 0, 0, 0));
    private final Period period;

    private MonitoringPeriod(final Period period) {
        this.period = period;
    }

    public Period toPeriod() {
        return period;
    }
}
