package com.example.bpn8adh.ordermanage.database;

import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

//    public void setData(ArrayList<FoodDetails> list1) {
//        getRootDbInstance().child("foodCategories").child("soup").setValue(list1);
//    }
//    public void setMainCourseData(ArrayList<FoodDetails> list1) {
//        getRootDbInstance().child("foodCategories").child("mainCourse").setValue(list1);
//    }
}
