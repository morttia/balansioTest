package com.quattrofolia.balansiosmart;

import com.quattrofolia.balansiosmart.models.Discipline;
import com.quattrofolia.balansiosmart.models.HealthDataType;
import com.quattrofolia.balansiosmart.models.Range;

import java.util.Observable;

public class GoalComposer extends Observable {
    private static GoalComposer ourInstance = new GoalComposer();

    private HealthDataType type;
    private Discipline discipline;
    private Range range;

    public void setType(HealthDataType type) {
        this.type = type;
        setChanged();
        notifyObservers(type);
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
        setChanged();
        notifyObservers(discipline);
    }

    public void setRange(Range range) {
        this.range = range;
        setChanged();
        notifyObservers(range);
    }

    public static GoalComposer getInstance() {
        return ourInstance;
    }

    private GoalComposer() {
    }
}
