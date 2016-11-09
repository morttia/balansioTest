package com.quattrofolia.balansiosmart.models;

import org.joda.time.Instant;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HealthDataEntry extends RealmObject {

    @PrimaryKey
    private long id;
    private String type;
    private String value;
    private long instant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(HealthDataType type) {
        this.type = type.toString();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setInstant(Instant instant) {
        this.instant = instant.getMillis();
    }

    public HealthDataType getType() {
        return HealthDataType.valueOf(type);
    }

    public String getValue() {
        return value;
    }

    public Instant getInstant() {
        return new Instant(instant);
    }
}