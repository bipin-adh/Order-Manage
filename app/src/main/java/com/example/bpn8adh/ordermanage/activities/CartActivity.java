package com.example.bpn8adh.ordermanage.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.CartAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bpn8adh on 02/03/18.
 */

public class CartActivity extends AppCompatActivity {

    private static final String MSG_ORDER_SUCCESS = "You order has been succesfully registered !!!";

    public static final String TAG = CartActivity.class.getSimpleName();
    @BindView(R.id.recycler_view_cart)
    RecyclerView recyclerView;
    @BindView(R.id.cart_toolbar)
    Toolbar toolbar;
    @BindView(R.id.cart_total_price)
    TextView cartTotalPrice;
    @BindView(R.id.btn_cart_order)
    Button btnCartOrder;

    private LinearLayoutManager linearLayoutManager;
    private List<FoodDetails> cartDetailsList = new ArrayList<>();
    private int totalPrice = 0;
    private CartAdapter cartAdapter;

    public static void launchActivity(Activity activity) {
        Intent intent = new Intent(activity, CartActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        cartDetailsList.clear();
//        cartDetailsList = OrderManageApplication.getSettings().getCartListFromPref();
        if (OrderManageApplication.getSettings().getCartListFromPref() != null && !OrderManageApplication.getSettings().getCartListFromPref().isEmpty()) {
            for (FoodDetails cartDetails : OrderManageApplication.getSettings().getCartListFromPref()) {
                if (cartDetails.getFoodQuantity() != 0) {
                    cartDetailsList.add(cartDetails);
                    totalPrice = totalPrice + cartDetails.getFoodPrice() * cartDetails.getFoodQuantity();
                }
            }
        }
        cartTotalPrice.setText("" + totalPrice);
        OrderManageApplication.getSettings().setCartListInPref(cartDetailsList);
        cartAdapter = new CartAdapter(CartActivity.this, cartDetailsList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_cart_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cart_order:
                cartDetailsList.clear();
                AppSettings.getInstance().setCartToolbarCountInPref(0);
                AppSettings.getInstance().setCartListInPref(cartDetailsList);
                OrderManageApplication.getInstance().showToast(MSG_ORDER_SUCCESS);
                cartAdapter.notifyDataSetChanged();
                finish();
                Intent launchHomeIntent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(launchHomeIntent);
                break;
        }
    }

}
