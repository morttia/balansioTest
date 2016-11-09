package com.quattrofolia.balansiosmart.models;

import java.math.BigDecimal;

import io.realm.RealmObject;

public class Range extends RealmObject {

    private String low;
    private String high;

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }


    public boolean contains(BigDecimal number) {
        return (number.compareTo(new BigDecimal(low)) >= 0 && number.compareTo(new BigDecimal(high)) <= 0);
    }
}
