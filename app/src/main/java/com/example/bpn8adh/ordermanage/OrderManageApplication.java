package com.example.bpn8adh.ordermanage;

import android.app.Application;
import android.content.Context;

import com.example.bpn8adh.ordermanage.utils.AppSettings;

/**
 * Created by bpn8adh on 03/03/18.
 */

public class OrderManageApplication extends Application {
    private static OrderManageApplication mInstance;
    private static AppSettings settings;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        settings = AppSettings.getInstance();
    }

    public static AppSettings getSettings() {
        return settings;
    }
    public static Context getContext() {
        return mInstance;
    }
}
