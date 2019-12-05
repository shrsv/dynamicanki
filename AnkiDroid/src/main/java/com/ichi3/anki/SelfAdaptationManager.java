package com.ichi3.anki;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import java.util.Calendar;

public class SelfAdaptationManager extends Service {
    private static String LOG_TAG = "SelfAdaptationManager";
    private IBinder mBinder = new MyBinder();
    private Chronometer mChronometer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "in onCreate");
        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
        mChronometer.stop();
    }

    public boolean ifNightEnableNightMode() {
        SharedPreferences preferences = AnkiDroidApp.getSharedPrefs(getBaseContext());
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 16 && timeOfDay <= 6) {
            preferences.edit().putBoolean("invertedColors", true).apply();
            return true;
        } else {
            preferences.edit().putBoolean("invertedColors", false).apply();
        }
        return true;
    }

    @TargetApi( 21 )
    @SuppressWarnings( "deprecation" )
    public boolean isBatteryOKforSync(){
        String DEBUG_TAG = "SYNCMSG";
        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        Log.d(DEBUG_TAG, "Battery level is: " + batLevel);

        if(batLevel > 20) {
            return true;
        } else {
            return false;
        }
    }

    @TargetApi( 21 )
    @SuppressWarnings( "deprecation" )
    public boolean isNetworkOKforSync() {
        String DEBUG_TAG = "SYNCMSG";

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn = true;
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn = true;
            }
        }
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);

        if (isWifiConn){
            return true;
        } else {
            return false;
        }

    }


    public boolean getSyncPermission() {
        Context context = getApplicationContext();

        if (isBatteryOKforSync() && isNetworkOKforSync()) {
            return true;
        } else {
            return false;
        }

    }

    public class MyBinder extends Binder {
        SelfAdaptationManager getService() {
            return SelfAdaptationManager.this;
        }
    }
}
