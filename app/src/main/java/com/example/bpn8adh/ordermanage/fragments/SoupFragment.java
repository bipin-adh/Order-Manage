package com.example.bpn8adh.ordermanage.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.SoupAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoupFragment extends Fragment {
    Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SoupAdapter soupAdapter;
    private ArrayList<FoodDetails> foodDetailList = new ArrayList<>();

    public SoupFragment() {
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

        if (foodDetailList.size() != 0) {
            foodDetailList.clear();
        }
        setItemDetails();
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        soupAdapter = new SoupAdapter(mContext, foodDetailList);
        recyclerView.setAdapter(soupAdapter);
        soupAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    private void setItemDetails() {
        FoodDetails foodDetails = new FoodDetails();
        foodDetails.setFoodName("Chicken Ball And Spinach Soup");
        foodDetails.setFoodPreparationTime("25 mins");
        foodDetails.setFoodPrice("Rs 120");
        foodDetails.setFoodQuantity(0);
        foodDetails.setFoodImage("https://www.wholefoodsmarket.com/sites/default/files/styles/header_recipe/public/media/3438-1.jpg?itok=WicsKlIK");
        foodDetailList.add(foodDetails);

        FoodDetails foodDetails1 = new FoodDetails();
        foodDetails1.setFoodName("Roast Pumpkin Soup");
        foodDetails1.setFoodPreparationTime("15 mins");
        foodDetails1.setFoodPrice("Rs 250");
        foodDetails1.setFoodQuantity(0);
        foodDetails1.setFoodImage("http://img.taste.com.au/g_FqQjcv/taste/2016/11/roasted-pumpkin-soup-93906-1.jpeg");
        foodDetailList.add(foodDetails1);

        FoodDetails foodDetails2 = new FoodDetails();
        foodDetails2.setFoodName("Charred Eggplant Soup");
        foodDetails2.setFoodPreparationTime("30 mins");
        foodDetails2.setFoodPrice("Rs 590");
        foodDetails2.setFoodQuantity(0);
        foodDetails2.setFoodImage("https://food.fnr.sndimg.com/content/dam/images/food/fullset/2010/8/30/4/FNM_100110-NICA-012_s4x3.jpg.rend.hgtvcom.616.462.suffix/1379693798550.jpeg");
        foodDetailList.add(foodDetails2);

    }
}