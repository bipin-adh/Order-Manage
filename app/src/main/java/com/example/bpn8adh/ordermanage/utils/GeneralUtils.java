package com.example.bpn8adh.ordermanage.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.example.bpn8adh.ordermanage.models.FoodDetails;

/**
 * Created by Bipin Adhikari on 06/03/18.
 */

public class GeneralUtils {
    public static final String TAG = GeneralUtils.class.getSimpleName();

    private static final String FONT_PATH = "fonts/Montserrat-Regular.ttf";

    public static Typeface getTypeface(final Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), FONT_PATH);
        return typeface;
    }

    public static int getTotalCountTodaysSpecial() {
        int totalCount = 0;
        if (AppSettings.getInstance().getCartListFromPref() != null && !AppSettings.getInstance().getCartListFromPref().isEmpty()) {
            for (FoodDetails foodDetails : AppSettings.getInstance().getCartListFromPref()) {
                totalCount = totalCount + foodDetails.getFoodQuantity();
            }
        }
        return totalCount;
    }

    public static int getTotalCountStarters() {
        int totalCount = 0;
        if (AppSettings.getInstance().getStartersListFromPref() != null && !AppSettings.getInstance().getStartersListFromPref().isEmpty()) {
            for (FoodDetails foodDetails : AppSettings.getInstance().getStartersListFromPref()) {
                totalCount = totalCount + foodDetails.getFoodQuantity();
            }
        }
        return (totalCount);
    }

    public static int getTotalCountSoup() {
        int totalCount = 0;
        if (AppSettings.getInstance().getSoupListFromPref() != null && !AppSettings.getInstance().getSoupListFromPref().isEmpty()) {
            for (FoodDetails foodDetails : AppSettings.getInstance().getSoupListFromPref()) {
                totalCount = totalCount + foodDetails.getFoodQuantity();
            }
        }
        return (totalCount);
    }
}