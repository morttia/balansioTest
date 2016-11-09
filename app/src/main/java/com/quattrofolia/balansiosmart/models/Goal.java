package com.quattrofolia.balansiosmart.models;

import io.realm.RealmObject;

public class Goal extends RealmObject {

    private String type;
    private Discipline discipline;
    private Range targetRange;

    public void setType(HealthDataType type) {
        this.type = type.toString();
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setTargetRange(Range targetRange) {
        this.targetRange = targetRange;
    }

    public HealthDataType getType() {
        return HealthDataType.valueOf(type);
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Range getTargetRange() {
        return targetRange;
    }
}
