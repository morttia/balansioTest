package com.quattrofolia.balansiosmart.models;

/* This enum defines the supported health data types.
* All values are assigned with a Unit object that
* can be accessed via unit() method. */

public enum HealthDataType {

    BLOOD_GLUCOSE("Blood Glucose", "BG", Unit.MMOL_PER_L),
    WEIGHT("Weight", "W", Unit.KG);


    private final String longName;
    private final String shortName;
    private final Unit unit;

    HealthDataType(String longName, String shortName, Unit unit) {
        this.longName = longName;
        this.shortName = shortName;
        this.unit = unit;
    }

    public String getLongName() { return longName; }
    public String getShortName() { return shortName; }
    public Unit getUnit() {
        return unit;
    }
}
