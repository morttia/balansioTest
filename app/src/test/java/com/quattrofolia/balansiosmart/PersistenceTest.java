package com.quattrofolia.balansiosmart;

import com.quattrofolia.balansiosmart.models.HealthDataEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;

public class PersistenceTest {
    private Realm realm;

    @Before
    public void init() throws Exception {
        realm = Realm.getDefaultInstance();
    }

    @Test
    public void transactions() throws Exception {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HealthDataEntry entry = realm.createObject(HealthDataEntry.class);
                //
            }
        });
    }

    @After
    public void destroy() throws Exception {
        realm.close();
    }
}
