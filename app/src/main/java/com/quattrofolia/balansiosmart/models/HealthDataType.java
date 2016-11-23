package com.quattrofolia.balansiosmart.models;

/* This enum defines the supported health data types.
* All values are assigned with a Unit object that
* can be accessed via unit() method. */

public enum HealthDataType {

    BLOOD_GLUCOSE(Unit.MMOL_PER_L),
    WEIGHT(Unit.KG),
    SLEEP(Unit.HOUR),
    HYPOS(Unit.INSTANCE),
    MEDICATION_BOLUS(Unit.INSTANCE),
    MEDICATION_BASAL(Unit.INSTANCE),
    A1C(Unit.PERCENTAGE),
    EXERCISE(Unit.HOUR),
    BLOOD_PRESSURE_SYSTOLIC(Unit.MMHG);


    private final Unit unit;

    HealthDataType(final Unit unit) {
        this.unit = unit;
    }

    public Unit unit() {
        return unit;
    }
}
