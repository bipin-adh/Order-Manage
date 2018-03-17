package com.example.bpn8adh.ordermanage.database.callbacks;

import android.util.Log;

import com.example.bpn8adh.ordermanage.interfaces.UiUpdateListener;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bpn8adh on 16/03/18.
 */

public class TodaysSpecialCallback implements ValueEventListener {

    public static final String TAG = TodaysSpecialCallback.class.getSimpleName();
    private final UiUpdateListener uiUpdateListener;
    private List<FoodDetails> foodDetailsList = new ArrayList<>();

    public TodaysSpecialCallback(UiUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d(TAG, "onDataChange: data : " + dataSnapshot);
        if (dataSnapshot != null) {
            Map<String, Object> objectMap = (HashMap<String, Object>) dataSnapshot.getValue();
            for (String foodCategory : objectMap.keySet()) {
                FoodDetails foodDetails = new FoodDetails();
                Map<String, String> objectMapSpecific = (HashMap<String, String>) objectMap.get(foodCategory);

                foodDetails.setFoodName(objectMapSpecific.get("foodName") == null ? "" : objectMapSpecific.get("foodName"));
                foodDetails.setFoodPrice(objectMapSpecific.get("foodPrice") == null ? 0 : Integer.parseInt(String.valueOf((objectMapSpecific.get("foodPrice")))));
                foodDetails.setFoodImage(objectMapSpecific.get("foodImage") == null ? "" : objectMapSpecific.get("foodImage"));
                foodDetails.setFoodQuantity(objectMapSpecific.get("foodQuantity") == null ? 0 : Integer.parseInt(String.valueOf(objectMapSpecific.get("foodQuantity"))));
                foodDetails.setFoodPreparationTime(objectMapSpecific.get("foodPreparationTime") == null ? "" : objectMapSpecific.get("foodPreparationTime"));

                foodDetailsList.add(foodDetails);
            }
            AppSettings.getInstance().setCartDetailsLists(foodDetailsList);
            if (uiUpdateListener != null) {
                uiUpdateListener.success();
            }

        } else {
            if (uiUpdateListener != null) {
                uiUpdateListener.failure("failed to load data");
            }
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "onCancelled: error : " + databaseError.getMessage());
    }
}
