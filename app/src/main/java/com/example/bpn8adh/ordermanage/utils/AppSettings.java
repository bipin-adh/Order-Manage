package com.example.bpn8adh.ordermanage.utils;

import android.content.Context;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.models.FoodDetails;

import java.util.ArrayList;

/**
 * Created by bpn8adh on 03/03/18.
 */

public class AppSettings {

    public static final String TAG = AppSettings.class.getSimpleName();
    private static AppSettings appSettings;
    private static Context context;
    private ArrayList<FoodDetails> cartDetailsList;

    public static AppSettings getInstance() {
        if (appSettings == null) {
            synchronized (AppSettings.class) {
                appSettings = new AppSettings();
                context = OrderManageApplication.getContext();
            }
        }
        return appSettings;
    }

    public ArrayList<FoodDetails> getCartDetailsLists() {
        return cartDetailsList;
    }

    public void setCartDetailsLists(ArrayList<FoodDetails> cartDetailsLists) {
        this.cartDetailsList = cartDetailsLists;
    }
}
