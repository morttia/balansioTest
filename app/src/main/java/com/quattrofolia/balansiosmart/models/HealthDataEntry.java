package com.quattrofolia.balansiosmart.models;

import org.joda.time.Instant;

import java.math.BigDecimal;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HealthDataEntry extends RealmObject implements AutoIncrementable {

    @PrimaryKey
    private int id;
    private String type;

    public HealthDataEntry() {}
    public HealthDataEntry(String type, String value, long instant) {
        this.type = type;
        this.value = value;
        this.instant = instant;
    }

    private String value;
    private long instant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(HealthDataType type) {
        this.type = type.toString();
    }

    public void setValue(BigDecimal value) {
        this.value = value.toString();
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

    @Override
    public void setPrimaryKey(int primaryKey) {
        this.id = primaryKey;
    }

    @Override
    public int getNextPrimaryKey(Realm realm) {
        return realm.where(HealthDataEntry.class).max("id").intValue() + 1;
    }
}