package com.quattrofolia.balansiosmart.models;

import org.joda.time.Instant;

import java.io.Serializable;
import java.math.BigDecimal;

public class Measurement implements Serializable {
    private final Unit unit;
    private final BigDecimal value;
    private final Instant instant;

    public Measurement(Unit unit, BigDecimal value) {
        this(unit, value, new Instant());
    }

    public Measurement(Unit unit, BigDecimal value, Instant instant) {
        this.unit = unit;
        this.value = value;
        this.instant = instant;
    }
}