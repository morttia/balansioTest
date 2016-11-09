package com.quattrofolia.balansiosmart.models;

import io.realm.RealmObject;

public class Discipline extends RealmObject {

    private int frequency;
    private String monitoringPeriod;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getMonitoringPeriod() {
        return monitoringPeriod;
    }

    public void setMonitoringPeriod(String monitoringPeriod) {
        this.monitoringPeriod = monitoringPeriod;
    }
}
