package com.quattrofolia.balansiosmart.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Range implements Serializable {
    private final BigDecimal low;
    private final BigDecimal high;
    public Range(BigDecimal minValue, BigDecimal maxValue) {
        this.low = minValue;
        this.high = maxValue;
    }
    public boolean contains(BigDecimal number) {
        return (number.compareTo(low) >= 0 && number.compareTo(high) <= 0);
    }
}
