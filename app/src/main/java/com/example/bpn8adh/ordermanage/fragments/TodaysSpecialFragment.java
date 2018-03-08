package com.example.bpn8adh.ordermanage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.TodaysSpecialAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodaysSpecialFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.add_to_cart)
//    Button addToCartBtn;

    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private TodaysSpecialAdapter todaysSpecialAdapter;
    private ArrayList<FoodDetails> todaysSpecialDetailList = new ArrayList<>();
    private ArrayList<FoodDetails> updateTodaysSpecialDetailList = new ArrayList<>();

    private View view;
    private ArrayList<FoodDetails> oldCartDetailList = new ArrayList<>();
    private ArrayList<FoodDetails> cartDetailList = new ArrayList<>();

    public TodaysSpecialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if (todaysSpecialDetailList.size() == 0) {
//            todaysSpecialDetailList.clear();
//        }

        setItemDetails();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tabs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void setItemDetails() {

        FoodDetails foodDetails = new FoodDetails();
        foodDetails.setFoodName("Hyderabad Biryani");
        foodDetails.setFoodPreparationTime("20 mins");
        foodDetails.setFoodPrice(400);
        foodDetails.setFoodQuantity(0);
        foodDetails.setFoodImage("https://static.wixstatic.com/media/871a43_855845212afb4815a2e607fd9c4a9b76~mv2.jpg");
        todaysSpecialDetailList.add(foodDetails);

        FoodDetails foodDetails1 = new FoodDetails();
        foodDetails1.setFoodName("Masala Tea");
        foodDetails1.setFoodPreparationTime("10 mins");
        foodDetails1.setFoodPrice(70);
        foodDetails1.setFoodQuantity(0);
        foodDetails1.setFoodImage("https://assets.epicurious.com/photos/579909083a12dd9d56024018/master/pass/spiced-milk-tea-masala-chai.jpg");
        todaysSpecialDetailList.add(foodDetails1);

        FoodDetails foodDetails2 = new FoodDetails();
        foodDetails2.setFoodName("Chicken Curry");
        foodDetails2.setFoodPreparationTime("25 mins");
        foodDetails2.setFoodPrice(290);
        foodDetails2.setFoodQuantity(0);
        foodDetails2.setFoodImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZc8SEDp09gwbPaCebWEazNwlRgr860wp2oCwZR4mhCKc42hPjEw");
        todaysSpecialDetailList.add(foodDetails2);

        FoodDetails foodDetails3 = new FoodDetails();
        foodDetails3.setFoodName("Coffee");
        foodDetails3.setFoodPreparationTime("10 mins");
        foodDetails3.setFoodPrice(200);
        foodDetails3.setFoodQuantity(0);
        foodDetails3.setFoodImage("http://ninjacoffeebarrecipes.com/wp-content/uploads/2015/12/Cafe_Mocha.jpg");
        todaysSpecialDetailList.add(foodDetails3);

        oldCartDetailList.addAll(AppSettings.getInstance().getCartListFromPref());

        for (FoodDetails newFoodDetail : todaysSpecialDetailList) {
            for (FoodDetails preFoodDetail : oldCartDetailList) {
                if (preFoodDetail.getFoodName().equals(newFoodDetail.getFoodName())) {
//                    todaysSpecialDetailList.remove(newFoodDetail);
                    Log.d("aaaa", "setItemDetails: " + preFoodDetail.getFoodQuantity());
//                    todaysSpecialDetailList.add(preFoodDetail);
                    newFoodDetail.setFoodQuantity(preFoodDetail.getFoodQuantity());
                    Log.d("aaaa", "getFoodQuantity: " + newFoodDetail.getFoodQuantity());
//                    cartDetailList.add(newFoodDetail);
                }
            }
        }


//        for (FoodDetails newFoodDetail : todaysSpecialDetailList) {
//            for (FoodDetails prefFoodDetail : oldCartDetailList) {
//                if (newFoodDetail.getFoodName().equals(prefFoodDetail)) {
//                    newFoodDetail.setFoodQuantity(prefFoodDetail.getFoodQuantity());
//                    cartDetailList.add(newFoodDetail);
//                }
//            }
//        }

//        oldCartDetailList.addAll(todaysSpecialDetailList);
//
//        Set<FoodDetails> hs = new HashSet<>();
//        hs.addAll(oldCartDetailList);
//        oldCartDetailList.clear();
//        oldCartDetailList.addAll(hs);

//            oldCartDetailList.addAll(todaysSpecialDetailList);

//        if (oldCartDetailList.size() != 0) {
//            oldCartDetailList.clear();
//        }
//        if (AppSettings.getInstance().getCartDetailsLists() != null &&
//                !AppSettings.getInstance().getCartDetailsLists().isEmpty()) {
//            oldCartDetailList.addAll(AppSettings.getInstance().getCartDetailsLists());

        AppSettings.getInstance().setCartDetailsLists(todaysSpecialDetailList);
        populateList();
    }

    private void populateList() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
//        todaysSpecialAdapter = new TodaysSpecialAdapter(mContext, oldCartDetailList);
        todaysSpecialAdapter = new TodaysSpecialAdapter(mContext, todaysSpecialDetailList);
        recyclerView.setAdapter(todaysSpecialAdapter);
        todaysSpecialAdapter.notifyDataSetChanged();
    }
}