package com.quattrofolia.balansiosmart.models;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {
    public RealmList<Goal> goals;
}
