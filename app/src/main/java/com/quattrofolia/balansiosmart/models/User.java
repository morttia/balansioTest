package com.quattrofolia.balansiosmart.models;

import java.util.ArrayList;

import io.realm.RealmObject;

public class User extends RealmObject {
    public ArrayList<Goal> goals;
    public User() {
        this.goals = new ArrayList<Goal>();
    }
}
