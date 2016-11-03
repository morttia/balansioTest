package com.quattrofolia.balansiosmart.models;

import org.joda.time.Instant;

import java.io.Serializable;
import java.math.BigDecimal;

public class HealthDataEntry implements Serializable {
    private final HealthDataType type;
    private final BigDecimal value;
    private final Instant instant;

    public HealthDataType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Instant getInstant() {
        return instant;
    }

    public HealthDataEntry(HealthDataType type, BigDecimal value) {
        this(type, value, new Instant());
    }

    public HealthDataEntry(HealthDataType type, BigDecimal value, Instant instant) {
        this.type = type;
        this.value = value;
        this.instant = instant;
    }
}