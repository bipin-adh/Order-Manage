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
import android.widget.TextView;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.TodaysSpecialAdapter;
import com.example.bpn8adh.ordermanage.interfaces.CartToolbarCountListener;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;
import com.example.bpn8adh.ordermanage.utils.GeneralUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodaysSpecialFragment extends Fragment {
    private static final String MSG_ADD_TO_CART_SUCCESS = "Items added to cart succesfully";
    private static final String MSG_ADD_TO_CART_FAIL = "Please select item to add";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.add_to_cart)
//    TextView addToCartBtn;

    private Context mContext;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private TodaysSpecialAdapter todaysSpecialAdapter;

    private ArrayList<FoodDetails> todaysSpecialDetailList = new ArrayList<>();
    private ArrayList<FoodDetails> oldCartDetailList = new ArrayList<>();

    private CartToolbarCountListener cartToolbarCountListener;

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
        setItemDetails();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (AppSettings.getInstance().isEditCart()) {
//            addToCartBtn.setText("Update Cart");
//        }
//    }

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
////                this.cartToolbarCountListener = (CartToolbarCountListener) mContext;
////                AppSettings.getInstance().setCartListInPref(AppSettings.getInstance().getCartDetailsLists());
////                if (GeneralUtils.getTotalCount() != 0) {
////                    OrderManageApplication.getInstance().showToast(MSG_ADD_TO_CART_SUCCESS);
////                } else {
////                    OrderManageApplication.getInstance().showToast(MSG_ADD_TO_CART_FAIL);
////                }
////                cartToolbarCountListener.updateCartToolbarCount();
//
//                break;
//        }
//    }


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
                    newFoodDetail.setFoodQuantity(preFoodDetail.getFoodQuantity());
                }
            }
        }

        AppSettings.getInstance().setCartDetailsLists(todaysSpecialDetailList);
        populateList();
    }

    private void populateList() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        todaysSpecialAdapter = new TodaysSpecialAdapter(mContext, todaysSpecialDetailList);
        recyclerView.setAdapter(todaysSpecialAdapter);
        todaysSpecialAdapter.notifyDataSetChanged();
    }
}