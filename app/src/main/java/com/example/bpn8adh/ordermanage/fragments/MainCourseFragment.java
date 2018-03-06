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
import com.example.bpn8adh.ordermanage.adapters.MainCourseAdapter;
import com.example.bpn8adh.ordermanage.adapters.TodaysSpecialAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCourseFragment extends Fragment {
    private Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MainCourseAdapter mainCourseAdapter;
    private ArrayList<FoodDetails> foodDetailList = new ArrayList<>();
    private View view;

    public MainCourseFragment() {
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
        mainCourseAdapter = new MainCourseAdapter(mContext, foodDetailList);
        recyclerView.setAdapter(mainCourseAdapter);
        mainCourseAdapter.notifyDataSetChanged();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tabs, container, false);
        return view;
    }

    private void setItemDetails() {
        FoodDetails foodDetails = new FoodDetails();
        foodDetails.setFoodName("Chicken Corma");
        foodDetails.setFoodPreparationTime("25 mins");
        foodDetails.setFoodPrice(300);
        foodDetails.setFoodQuantity(0);
        foodDetails.setFoodImage("http://www.ektaindianrestaurant.com/fishtown/br/wp-content/uploads/sites/5/2017/02/Chicken-korma.jpg");
        foodDetailList.add(foodDetails);

        FoodDetails foodDetails1 = new FoodDetails();
        foodDetails1.setFoodName("Rice noodles with prawns");
        foodDetails1.setFoodPreparationTime("15 mins");
        foodDetails1.setFoodPrice(170);
        foodDetails1.setFoodQuantity(0);
        foodDetails1.setFoodImage("https://realfood.tesco.com/media/images/171961-rice-vermicellis-with-gambas-onions-and-peppers-HERO-1cdc243b-ec72-4f23-9c7f-61dedbe861b9-0-472x310.jpg");
        foodDetailList.add(foodDetails1);

        FoodDetails foodDetails2 = new FoodDetails();
        foodDetails2.setFoodName("Chilli bean soup with cheesy garlic bread");
        foodDetails2.setFoodPreparationTime("30 mins");
        foodDetails2.setFoodPrice(390);
        foodDetails2.setFoodQuantity(0);
        foodDetails2.setFoodImage("https://realfood.tesco.com/media/images/Chilli-bean-soup-with-cheesy-garlic-bread--HERO-a4c3e012-c012-4d6f-9aa0-e7c1916b6d7d-0-472x310.jpg");
        foodDetailList.add(foodDetails2);

        AppSettings.getInstance().setCartDetailsLists(foodDetailList);
    }
}