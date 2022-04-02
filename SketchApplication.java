/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.Application
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Process
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Thread$UncaughtExceptionHandler
 *  java.lang.Throwable
 */
package com.asBros.converter.PDF;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.asBros.converter.PDF.DebugActivity;

public class SketchApplication
extends Application {
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public void onCreate() {
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new Thread.UncaughtExceptionHandler(){

            public void uncaughtException(Thread thread, Throwable throwable) {
                Intent intent = new Intent(SketchApplication.this.getApplicationContext(), DebugActivity.class);
                intent.setFlags(32768);
                intent.putExtra("error", Log.getStackTraceString((Throwable)throwable));
                PendingIntent pendingIntent = PendingIntent.getActivity((Context)SketchApplication.this.getApplicationContext(), (int)11111, (Intent)intent, (int)1073741824);
                ((AlarmManager)SketchApplication.this.getSystemService("alarm")).set(2, 1000L, pendingIntent);
                Process.killProcess((int)Process.myPid());
                System.exit((int)1);
                SketchApplication.this.uncaughtExceptionHandler.uncaughtException(thread, throwable);
            }
        });
        super.onCreate();
    }

}

