package com.example.bpn8adh.ordermanage.database;

import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Bipin Adhikari on 15/03/18
 */
public class FirebaseManager {
    private static final String TAG = FirebaseManager.class.getSimpleName();

    private static FirebaseManager firebaseManagerInstance;
    private DatabaseReference databaseReference;

    public static FirebaseManager getInstance() {
        if (firebaseManagerInstance == null) {
            synchronized (AppSettings.class) {
                firebaseManagerInstance = new FirebaseManager();
            }
        }
        return firebaseManagerInstance;
    }

    public void clearFirebaseManagerInstance() {
        firebaseManagerInstance = null;
    }

    public DatabaseReference getRootDbInstance() {
        if (databaseReference == null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();
        }
        return databaseReference;
    }
    public void getTodaysSpecialList(ValueEventListener valueEventListener) {
        Query childRef = getRootDbInstance().child("foodCategories").child("todaysSpecial").orderByChild("foodName");
        childRef.addValueEventListener(valueEventListener);
    }
    public void getStartersList(ValueEventListener valueEventListener) {
        Query childRef = getRootDbInstance().child("foodCategories").child("starters").orderByChild("foodName");
        childRef.addValueEventListener(valueEventListener);
    }

//    public void setData(ArrayList<FoodDetails> list1) {
//        for(FoodDetails foodDetails: list1){
//            getRootDbInstance().child("foodCategories").child("todaysSpecial").push().setValue(foodDetails);
//        }
//    }
//    public void setMainData(ArrayList<FoodDetails> list1) {
//        for(FoodDetails foodDetails: list1){
//            getRootDbInstance().child("foodCategories").child("mainCourse").push().setValue(foodDetails);
//        }
//    }
//    public void setSoupData(ArrayList<FoodDetails> list1) {
//        for(FoodDetails foodDetails: list1){
//            getRootDbInstance().child("foodCategories").child("soup").push().setValue(foodDetails);
//        }
//    }
//    public void setStartersData(ArrayList<FoodDetails> list1) {
//        for(FoodDetails foodDetails: list1){
//            getRootDbInstance().child("foodCategories").child("starters").push().setValue(foodDetails);
//        }
//    }
//    public void setMainCourseData(ArrayList<FoodDetails> list1) {
//        getRootDbInstance().child("foodCategories").child("mainCourse").setValue(list1);
//    }
}
