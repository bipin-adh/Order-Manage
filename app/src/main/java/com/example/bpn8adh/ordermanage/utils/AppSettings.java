package com.example.bpn8adh.ordermanage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by bpn8adh on 03/03/18.
 */

public class AppSettings {

    public static final String TAG = AppSettings.class.getSimpleName();
    private static final String SP_NAME = "OrderManage";
    private static final String SP_CART_LIST = "CartList";
    private static AppSettings appSettings;
    private static Context context;
    private List<FoodDetails> cartDetailsList;
    private SharedPreferences sharedPreference;

    public static AppSettings getInstance() {
        if (appSettings == null) {
            synchronized (AppSettings.class) {
                appSettings = new AppSettings();
                context = OrderManageApplication.getContext();
            }
        }
        return appSettings;
    }

    public SharedPreferences getSharedPreference() {
        if (sharedPreference == null) {
            synchronized (AppSettings.class) {
                sharedPreference = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
            }
        }
        return sharedPreference;
    }

    public SharedPreferences.Editor getSharedPreferenceEditor() {
        return getSharedPreference().edit();
    }

    public List<FoodDetails> getCartDetailsLists() {
        return cartDetailsList;
    }

    public void setCartDetailsLists(List<FoodDetails> cartDetailsLists) {
        this.cartDetailsList = cartDetailsLists;
    }

    public void setCartListInPref(List<FoodDetails> notificationDetailsList) {
        Gson gson = new Gson();
        String json = gson.toJson(notificationDetailsList);
        getSharedPreferenceEditor().putString(SP_CART_LIST, json).commit();
    }

    public List<FoodDetails> getCartListFromPref() {
        String json = getSharedPreference().getString(SP_CART_LIST, "");
        Type type = new TypeToken<List<FoodDetails>>() {
        }.getType();

        List<FoodDetails> objects = new ArrayList<>();
        if ((new Gson().fromJson(json, type)) != null) {
            objects.addAll((Collection<? extends FoodDetails>) new Gson().fromJson(json, type));
        }
        return objects;
    }
}
