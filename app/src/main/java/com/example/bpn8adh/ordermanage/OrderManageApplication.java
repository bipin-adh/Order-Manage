package com.example.bpn8adh.ordermanage;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.bpn8adh.ordermanage.utils.AppSettings;

/**
 * Created by Bipin Adhikari on 03/03/18.
 */

public class OrderManageApplication extends Application {
    private static OrderManageApplication mInstance;
    private static AppSettings settings;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // initialize settings class
        settings = AppSettings.getInstance();
        initializeFirebase();
    }
    public void initializeFirebase() {
        settings.initializeFirebase();
    }

    public static AppSettings getSettings() {
        return settings;
    }

    public static Context getContext() {
        return mInstance;
    }

    public static synchronized OrderManageApplication getInstance() {
        return mInstance;
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
