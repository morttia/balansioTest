package com.quattrofolia.balansiosmart.models;

import io.realm.Realm;

public interface AutoIncrementable {
    public void setPrimaryKey(int primaryKey);
    public int getNextPrimaryKey(Realm realm);
}
