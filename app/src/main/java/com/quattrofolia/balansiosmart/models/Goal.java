package com.quattrofolia.balansiosmart.models;

import io.realm.RealmObject;

public class Goal extends RealmObject {
    private final HealthDataType type;
    private final Discipline discipline;
    private final Range targetRange;

    public Goal(HealthDataType type, Discipline discipline, Range targetRange) {
        this.type = type;
        this.discipline = discipline;
        this.targetRange = targetRange;
    }

    public HealthDataType getType() {
        return type;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Range getTargetRange() {
        return targetRange;
    }
}
