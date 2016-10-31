package com.quattrofolia.balansiosmart.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Measurement extends Accomplishment implements Serializable {
    private final Unit unit;
    private final BigDecimal value;
    public Measurement(Unit unit, BigDecimal value) {
        super(new Date());
        this.unit = unit;
        this.value = value;
    }
    public Measurement(Unit unit, BigDecimal value, Date date) {
        super(date);
        this.unit = unit;
        this.value = value;
    }
}