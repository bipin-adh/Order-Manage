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
import com.example.bpn8adh.ordermanage.adapters.StartersAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartersFragment extends Fragment {
    Context mContext;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StartersAdapter startersAdapter;
    private ArrayList<FoodDetails> foodDetailList = new ArrayList<>();

    public StartersFragment() {
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

        setItemDetails();
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        startersAdapter = new StartersAdapter(mContext, foodDetailList);
        recyclerView.setAdapter(startersAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    private void setItemDetails() {
        FoodDetails foodDetails = new FoodDetails();
        foodDetails.setFoodName("Chicken Biryani");
        foodDetails.setFoodPreparationTime("20 mins");
        foodDetails.setFoodPrice("Rs 300");
        foodDetails.setFoodQuantity(0);
        foodDetails.setFoodImage("https://cdn5.norecipes.com/wp-content/uploads/2017/05/05021751/chicken-biryani-12-1290x1934.jpg");
        foodDetailList.add(foodDetails);

        FoodDetails foodDetails1 = new FoodDetails();
        foodDetails1.setFoodName("Jhol momo");
        foodDetails1.setFoodPreparationTime("10 mins");
        foodDetails1.setFoodPrice("Rs 150");
        foodDetails1.setFoodQuantity(0);
        foodDetails1.setFoodImage("http://3.bp.blogspot.com/-lJKqWMQ1INk/VnIa8oF2pKI/AAAAAAAABJ0/C8pZkTbcH6k/s1600/IMG_4360_E.JPG");
        foodDetailList.add(foodDetails1);

        FoodDetails foodDetails2 = new FoodDetails();
        foodDetails2.setFoodName("Fried Rice");
        foodDetails2.setFoodPreparationTime("25 mins");
        foodDetails2.setFoodPrice("Rs 190");
        foodDetails2.setFoodQuantity(0);
        foodDetails2.setFoodImage("http://www.foodpost.ca/wp-content/uploads/2017/06/chinese-fried-rice-780x520.jpg");
        foodDetailList.add(foodDetails2);

        FoodDetails foodDetails3 = new FoodDetails();
        foodDetails3.setFoodName("Chopsey");
        foodDetails3.setFoodPreparationTime("15 mins");
        foodDetails3.setFoodPrice("Rs 280");
        foodDetails3.setFoodQuantity(0);
        foodDetails3.setFoodImage("https://media-cdn.tripadvisor.com/media/photo-s/06/84/09/c6/bhanchha.jpg");
        foodDetailList.add(foodDetails3);
    }
}