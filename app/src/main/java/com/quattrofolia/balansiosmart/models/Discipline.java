package com.quattrofolia.balansiosmart.models;

import java.io.Serializable;

public class Discipline implements Serializable {
    private HealthDataType dataType;
    private MonitoringPeriod monitoringPeriod;
    private 
    public Discipline(MonitoringPeriod monitoringPeriod) {
        this.monitoringPeriod = monitoringPeriod;
    }
}
