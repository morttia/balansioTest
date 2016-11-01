package com.quattrofolia.balansiosmart.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Range implements Serializable {
    private final BigDecimal low;
    private final BigDecimal high;
    public Range(BigDecimal low, BigDecimal high) {
        this.low = low;
        this.high = high;
    }
    public boolean contains(BigDecimal number) {
        return (number.compareTo(low) >= 0 && number.compareTo(high) <= 0);
    }
}
