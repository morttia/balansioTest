package com.quattrofolia.balansiosmart.models;

/* This enum defines the supported health data types.
* All values are assigned with a Unit object that
* can be accessed via unit() method. */

public enum HealthDataType {

    BLOOD_GLUCOSE("Blood Glucose", "BG", Unit.MMOL_PER_L),
    WEIGHT("Weight", "W", Unit.KG),
    SLEEP("Sleep","Sleep",Unit.HOUR),
    HYPOS("Hypos","Hypos",Unit.INSTANCE),
    MEDICATION_BOLUS("Medication Bolus","MedBol",Unit.INSTANCE),
    MEDICATION_BASAL("Medication Basal","MedBas",Unit.INSTANCE),
    A1C("A1C","A1C",Unit.PERCENTAGE),
    EXERCISE("Exercise","Exercise",Unit.HOUR),
    BLOOD_PRESSURE_SYSTOLIC("Blood Pressure Systolic","BPSys",Unit.MMHG);


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
