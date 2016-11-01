package com.quattrofolia.balansiosmart.models;

import java.io.Serializable;

public class Discipline implements Serializable {
    private int frequency;
    private MonitoringPeriod monitoringPeriod;

    public Discipline(int frequency, MonitoringPeriod monitoringPeriod) {
        this.frequency = frequency;
        this.monitoringPeriod = monitoringPeriod;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public MonitoringPeriod getMonitoringPeriod() {
        return monitoringPeriod;
    }

    public void setMonitoringPeriod(MonitoringPeriod monitoringPeriod) {
        this.monitoringPeriod = monitoringPeriod;
    }
}
