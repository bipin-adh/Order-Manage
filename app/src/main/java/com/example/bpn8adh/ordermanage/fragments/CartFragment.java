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
import com.example.bpn8adh.ordermanage.adapters.CartAdapter;
import com.example.bpn8adh.ordermanage.adapters.TodaysSpecialAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    private RecyclerView recyclerView;
    private Context context;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<FoodDetails> foodDetailList = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment cartFragment = new CartFragment();
        return cartFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_cart);

        setItemDetails();
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        CartAdapter cartAdapter = new CartAdapter(context, foodDetailList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    private void setItemDetails() {
        FoodDetails foodDetails = new FoodDetails();
        foodDetails.setFoodName("Hyderabad Biryani");
        foodDetails.setFoodPreparationTime("20 mins");
        foodDetails.setFoodPrice(400);
        foodDetails.setFoodQuantity(0);
        foodDetails.setFoodImage("https://static.wixstatic.com/media/871a43_855845212afb4815a2e607fd9c4a9b76~mv2.jpg");
        foodDetailList.add(foodDetails);

        FoodDetails foodDetails1 = new FoodDetails();
        foodDetails1.setFoodName("Masala Tea");
        foodDetails1.setFoodPreparationTime("10 mins");
        foodDetails1.setFoodPrice(70);
        foodDetails1.setFoodQuantity(0);
        foodDetails1.setFoodImage("https://assets.epicurious.com/photos/579909083a12dd9d56024018/master/pass/spiced-milk-tea-masala-chai.jpg");
        foodDetailList.add(foodDetails1);

        FoodDetails foodDetails2 = new FoodDetails();
        foodDetails2.setFoodName("Chicken Curry");
        foodDetails2.setFoodPreparationTime("25 mins");
        foodDetails2.setFoodPrice(290);
        foodDetails2.setFoodQuantity(0);
        foodDetails2.setFoodImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZc8SEDp09gwbPaCebWEazNwlRgr860wp2oCwZR4mhCKc42hPjEw");
        foodDetailList.add(foodDetails2);

        FoodDetails foodDetails3 = new FoodDetails();
        foodDetails3.setFoodName("Coffee");
        foodDetails3.setFoodPreparationTime("10 mins");
        foodDetails3.setFoodPrice(200);
        foodDetails3.setFoodQuantity(0);
        foodDetails3.setFoodImage("http://ninjacoffeebarrecipes.com/wp-content/uploads/2015/12/Cafe_Mocha.jpg");
        foodDetailList.add(foodDetails3);
    }

}