package com.example.bpn8adh.ordermanage.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.database.FirebaseManager;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Bipin Adhikari on 03/03/18.
 */

public class AppSettings {

    public static final String TAG = AppSettings.class.getSimpleName();
    private static final String SP_NAME = "orderManage";
    private static final String SP_CART_LIST = "cartList";
    private static final String SP_STARTERS_CART_LIST = "startersCartList";
    private static final String SP_SOUP_CART_LIST = "soupCartList";
    private static final String SP_MAIN_COURSE_CART_LIST = "mainCourseCartList";

    public static final String SP_ORDER_HISTORY_CART_LIST = "orderHistoryCartList";

    private static AppSettings appSettings;
    private static Context context;
    private List<FoodDetails> cartDetailsList = new ArrayList<>();
    private List<FoodDetails> startersDetailsLists = new ArrayList<>();
    private List<FoodDetails> soupDetailsLists = new ArrayList<>();
    private List<FoodDetails> mainCourseDetailsLists = new ArrayList<>();

    private SharedPreferences sharedPreference;
    private boolean isEditCart;
    private FirebaseManager firebaseInstance;

    public static AppSettings getInstance() {
        if (appSettings == null) {
            synchronized (AppSettings.class) {
                appSettings = new AppSettings();
                context = OrderManageApplication.getContext();
            }
        }
        return appSettings;
    }

    public void initializeFirebase() {
        firebaseInstance = FirebaseManager.getInstance();
    }


    public List<FoodDetails> getCartDetailsLists() {
        return cartDetailsList;
    }

    public void setCartDetailsLists(List<FoodDetails> cartDetailsLists) {
        this.cartDetailsList = cartDetailsLists;
    }

    public void setStartersDetailsLists(List<FoodDetails> startersDetailsLists) {
        this.startersDetailsLists = startersDetailsLists;
    }

    public List<FoodDetails> getStartersDetailsLists() {
        return startersDetailsLists;
    }

    public void setSoupDetailsLists(List<FoodDetails> soupDetailsLists) {
        this.soupDetailsLists = soupDetailsLists;
    }

    public List<FoodDetails> getMainCourseDetailsLists() {
        return mainCourseDetailsLists;
    }


    public void setMainCourseDetailsLists(List<FoodDetails> mainCourseDetailsLists) {
        this.mainCourseDetailsLists = mainCourseDetailsLists;
    }

    public List<FoodDetails> getSoupDetailsLists() {
        return soupDetailsLists;
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

    public void setOrderHistoryinPref(List<FoodDetails> foodDetailsList) {
        Gson gson = new Gson();
        String json = gson.toJson(foodDetailsList);
        getSharedPreferenceEditor().putString(SP_ORDER_HISTORY_CART_LIST, json).commit();
    }

    public List<FoodDetails> getOrderHistoryFromPref() {
        String json = getSharedPreference().getString(SP_ORDER_HISTORY_CART_LIST, "");
        Type type = new TypeToken<List<FoodDetails>>() {
        }.getType();

        List<FoodDetails> objects = new ArrayList<>();
        if ((new Gson().fromJson(json, type)) != null) {
            objects.addAll((Collection<? extends FoodDetails>) new Gson().fromJson(json, type));
        }
        return objects;
    }

    public void setCartListInPref(List<FoodDetails> foodDetailsList) {
        Gson gson = new Gson();
        String json = gson.toJson(foodDetailsList);
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

    public void setStartersListInPref(List<FoodDetails> foodDetailsList) {
        Gson gson = new Gson();
        String json = gson.toJson(foodDetailsList);
        getSharedPreferenceEditor().putString(SP_STARTERS_CART_LIST, json).commit();
    }

    public List<FoodDetails> getStartersListFromPref() {
        String json = getSharedPreference().getString(SP_STARTERS_CART_LIST, "");
        Type type = new TypeToken<List<FoodDetails>>() {
        }.getType();

        List<FoodDetails> objects = new ArrayList<>();
        if ((new Gson().fromJson(json, type)) != null) {
            objects.addAll((Collection<? extends FoodDetails>) new Gson().fromJson(json, type));
        }
        return objects;
    }

    public void setSoupListInPref(List<FoodDetails> foodDetailsList) {
        Gson gson = new Gson();
        String json = gson.toJson(foodDetailsList);
        getSharedPreferenceEditor().putString(SP_SOUP_CART_LIST, json).commit();
    }

    public List<FoodDetails> getSoupListFromPref() {
        String json = getSharedPreference().getString(SP_SOUP_CART_LIST, "");
        Type type = new TypeToken<List<FoodDetails>>() {
        }.getType();

        List<FoodDetails> objects = new ArrayList<>();
        if ((new Gson().fromJson(json, type)) != null) {
            objects.addAll((Collection<? extends FoodDetails>) new Gson().fromJson(json, type));
        }
        return objects;
    }

    public void setMainCourseListInPref(List<FoodDetails> foodDetailsList) {
        Gson gson = new Gson();
        String json = gson.toJson(foodDetailsList);
        getSharedPreferenceEditor().putString(SP_MAIN_COURSE_CART_LIST, json).commit();
    }

    public List<FoodDetails> getMainCourseListFromPref() {
        String json = getSharedPreference().getString(SP_MAIN_COURSE_CART_LIST, "");
        Type type = new TypeToken<List<FoodDetails>>() {
        }.getType();

        List<FoodDetails> objects = new ArrayList<>();
        if ((new Gson().fromJson(json, type)) != null) {
            objects.addAll((Collection<? extends FoodDetails>) new Gson().fromJson(json, type));
        }
        return objects;
    }

    public void setEditCartState(boolean isEditCart) {
        this.isEditCart = isEditCart;
    }
}