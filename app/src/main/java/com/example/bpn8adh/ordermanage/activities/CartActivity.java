package com.example.bpn8adh.ordermanage.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.CartAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;

import java.util.ArrayList;

/**
 * Created by bpn8adh on 02/03/18.
 */

public class CartActivity extends AppCompatActivity {

    public static final String TAG = CartActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<FoodDetails> cartDetailsList = new ArrayList<>();
    private Toolbar toolbar;
    private TextView cartTotalPrice;
    private int totalPrice = 0;

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, CartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        recyclerView = findViewById(R.id.recycler_view_cart);
        cartTotalPrice = findViewById(R.id.cart_total_price);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (AppSettings.getInstance().getCartDetailsLists() != null && !AppSettings.getInstance().getCartDetailsLists().isEmpty()) {
            for (FoodDetails cartDetails : AppSettings.getInstance().getCartDetailsLists()) {
                if (cartDetails.getFoodQuantity() != 0) {
                    cartDetailsList.add(cartDetails);
                    totalPrice = totalPrice + cartDetails.getFoodPrice() * cartDetails.getFoodQuantity();
                }
            }
        }
        cartTotalPrice.setText("" + totalPrice);
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this, cartDetailsList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }
}
