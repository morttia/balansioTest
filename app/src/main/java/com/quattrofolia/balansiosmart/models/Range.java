package com.quattrofolia.balansiosmart.models;

import java.math.BigDecimal;

import io.realm.RealmObject;

public class Range extends RealmObject {
    private final BigDecimal low;
    private final BigDecimal high;
    public Range(BigDecimal low, BigDecimal high) {
        this.low = low;
        this.high = high;
    }

    public Range(double low, double high) {
        this.low = BigDecimal.valueOf(low);
        this.high = BigDecimal.valueOf(high);
    }

    public boolean contains(BigDecimal number) {
        return (number.compareTo(low) >= 0 && number.compareTo(high) <= 0);
    }
}
