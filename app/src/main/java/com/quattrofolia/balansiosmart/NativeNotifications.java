package com.quattrofolia.balansiosmart;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Mortti on 23.11.2016.
 */
public class NativeNotifications {
    private static NativeNotifications ourInstance = new NativeNotifications();

    public static NativeNotifications getInstance() {
        return ourInstance;
    }

    private NativeNotifications() {

    }

}
