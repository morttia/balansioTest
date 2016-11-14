package com.quattrofolia.balansiosmart.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Discipline extends RealmObject implements AutoIncrementable {

    @PrimaryKey
    private int id;
    private int frequency;
    private String monitoringPeriod;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public MonitoringPeriod getMonitoringPeriod() {
        return MonitoringPeriod.valueOf(monitoringPeriod);
    }

    public void setMonitoringPeriod(MonitoringPeriod monitoringPeriod) {
        this.monitoringPeriod = monitoringPeriod.toString();
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        this.id = primaryKey;
    }

    @Override
    public int getNextPrimaryKey(Realm realm) {
        return realm.where(Discipline.class).max("id").intValue() + 1;
    }
}
