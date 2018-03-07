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
import android.widget.Button;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.activities.CartActivity;
import com.example.bpn8adh.ordermanage.adapters.StartersAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartersFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.add_to_cart)
//    Button addToCartBtn;

    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private StartersAdapter startersAdapter;
    private ArrayList<FoodDetails> startersDetailList = new ArrayList<>();
    private View view;
    private ArrayList<FoodDetails> cartDetailList = new ArrayList<>();

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

        if (startersDetailList.size() != 0) {
            startersDetailList.clear();
        }
        setItemDetails();
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        startersAdapter = new StartersAdapter(mContext, startersDetailList);
        recyclerView.setAdapter(startersAdapter);
        startersAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_tabs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

//    @OnClick({R.id.add_to_cart})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.add_to_cart:
//                break;
//        }
//    }

    private void setItemDetails() {
        FoodDetails foodDetails = new FoodDetails();
        foodDetails.setFoodName("Chicken Biryani");
        foodDetails.setFoodPreparationTime("20 mins");
        foodDetails.setFoodPrice(300);
        foodDetails.setFoodQuantity(0);
        foodDetails.setFoodImage("https://cdn5.norecipes.com/wp-content/uploads/2017/05/05021751/chicken-biryani-12-1290x1934.jpg");
        startersDetailList.add(foodDetails);

        FoodDetails foodDetails1 = new FoodDetails();
        foodDetails1.setFoodName("Jhol momo");
        foodDetails1.setFoodPreparationTime("10 mins");
        foodDetails1.setFoodPrice(150);
        foodDetails1.setFoodQuantity(0);
        foodDetails1.setFoodImage("http://3.bp.blogspot.com/-lJKqWMQ1INk/VnIa8oF2pKI/AAAAAAAABJ0/C8pZkTbcH6k/s1600/IMG_4360_E.JPG");
        startersDetailList.add(foodDetails1);

        FoodDetails foodDetails2 = new FoodDetails();
        foodDetails2.setFoodName("Fried Rice");
        foodDetails2.setFoodPreparationTime("25 mins");
        foodDetails2.setFoodPrice(190);
        foodDetails2.setFoodQuantity(0);
        foodDetails2.setFoodImage("http://www.foodpost.ca/wp-content/uploads/2017/06/chinese-fried-rice-780x520.jpg");
        startersDetailList.add(foodDetails2);

        FoodDetails foodDetails3 = new FoodDetails();
        foodDetails3.setFoodName("Chopsey");
        foodDetails3.setFoodPreparationTime("15 mins");
        foodDetails3.setFoodPrice(280);
        foodDetails3.setFoodQuantity(0);
        foodDetails3.setFoodImage("https://media-cdn.tripadvisor.com/media/photo-s/06/84/09/c6/bhanchha.jpg");
        startersDetailList.add(foodDetails3);

        if (cartDetailList.size() != 0) {
            cartDetailList.clear();
        }
        cartDetailList.addAll(startersDetailList);
        if (AppSettings.getInstance().getCartDetailsLists() != null &&
                !AppSettings.getInstance().getCartDetailsLists().isEmpty()) {
            cartDetailList.addAll(AppSettings.getInstance().getCartDetailsLists());
        }
        AppSettings.getInstance().setCartDetailsLists(cartDetailList);
    }
}