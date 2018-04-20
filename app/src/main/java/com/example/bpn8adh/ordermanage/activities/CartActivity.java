package com.example.bpn8adh.ordermanage.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.CartAdapter;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;
import com.example.bpn8adh.ordermanage.utils.DialogUtils;

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
    private static final String TXT_DIALOG_OK = "OK";
    private static final String TITLE_DELETE_CART_ALL = "Clear Cart";
    private static final String MSG_CLEAR_CART = "Clear cart list ?";
    private static final String POSITIVE_BTN_TEXT = "YES";
    private static final String NEGATIVE_BTN_TEXT = "NO";
    private static final String TITLE_CONFIRM_ORDER = "Confirm Order !!!";
    private static final String MSG_CONFIRM_ORDER = "Do you want to proceed ?";

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
    private List<FoodDetails> startersCartDetailsList = new ArrayList<>();
    private List<FoodDetails> todaySpecialcartDetailsList = new ArrayList<>();
    private List<FoodDetails> soupCartDetailsList = new ArrayList<>();

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        startersCartDetailsList.clear();
        todaySpecialcartDetailsList.clear();
        cartDetailsList.clear();
        Log.d(TAG, "onCreate: size:" + startersCartDetailsList.size() +
                todaySpecialcartDetailsList.size() +
                +cartDetailsList.size());
        if (OrderManageApplication.getSettings().getStartersListFromPref() != null && !OrderManageApplication.getSettings().getStartersListFromPref().isEmpty()) {
            for (FoodDetails cartDetails : OrderManageApplication.getSettings().getStartersListFromPref()) {
                if (cartDetails.getFoodQuantity() != 0) {
                    Log.d(TAG, "onCreate: starter ===>" + cartDetails.getFoodName());
                    Log.d(TAG, "onCreate: starter ===>" + cartDetails.getFoodQuantity());
                    startersCartDetailsList.add(cartDetails);
                    totalPrice = totalPrice + cartDetails.getFoodPrice() * cartDetails.getFoodQuantity();
                }
            }
        }
        if (OrderManageApplication.getSettings().getCartListFromPref() != null && !OrderManageApplication.getSettings().getCartListFromPref().isEmpty()) {
            for (FoodDetails cartDetails : OrderManageApplication.getSettings().getCartListFromPref()) {
                if (cartDetails.getFoodQuantity() != 0) {
                    Log.d(TAG, "onCreate: todays ===>" + cartDetails.getFoodName());
                    Log.d(TAG, "onCreate: todays ===>" + cartDetails.getFoodQuantity());
                    todaySpecialcartDetailsList.add(cartDetails);
                    totalPrice = totalPrice + cartDetails.getFoodPrice() * cartDetails.getFoodQuantity();
                }
            }
        }
        if (OrderManageApplication.getSettings().getSoupListFromPref() != null && !OrderManageApplication.getSettings().getSoupListFromPref().isEmpty()) {
            for (FoodDetails cartDetails : OrderManageApplication.getSettings().getSoupListFromPref()) {
                if (cartDetails.getFoodQuantity() != 0) {
                    Log.d(TAG, "onCreate: todays ===>" + cartDetails.getFoodName());
                    Log.d(TAG, "onCreate: todays ===>" + cartDetails.getFoodQuantity());
                    soupCartDetailsList.add(cartDetails);
                    totalPrice = totalPrice + cartDetails.getFoodPrice() * cartDetails.getFoodQuantity();
                }
            }
        }
        cartDetailsList.addAll(startersCartDetailsList);
        cartDetailsList.addAll(todaySpecialcartDetailsList);
        cartDetailsList.addAll(soupCartDetailsList);

        cartTotalPrice.setText("" + totalPrice);
        OrderManageApplication.getSettings().setCartListInPref(todaySpecialcartDetailsList);
        OrderManageApplication.getSettings().setStartersListInPref(startersCartDetailsList);
        OrderManageApplication.getSettings().setSoupListInPref(soupCartDetailsList);

        cartAdapter = new CartAdapter(CartActivity.this, cartDetailsList);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppSettings.getInstance().setEditCartState(true);
    }

    @OnClick({R.id.btn_cart_order, R.id.iv_edit_cart, R.id.iv_delete_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cart_order:
                placeOrderConfirmDialog();
                break;

            case R.id.iv_edit_cart:
                onBackPressed();
                break;
            case R.id.iv_delete_all:
                deleteDialog();
                break;
        }

    }

    private void placeOrderConfirmDialog() {
        DialogInterface.OnClickListener positiveDialogListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                AppSettings.getInstance().setOrderHistoryinPref(cartDetailsList);
                cartDetailsList.clear();
                startersCartDetailsList.clear();
                todaySpecialcartDetailsList.clear();
                soupCartDetailsList.clear();
//                AppSettings.getInstance().setCartToolbarCountInPref(0);
                AppSettings.getInstance().setCartListInPref(todaySpecialcartDetailsList);
                AppSettings.getInstance().setStartersListInPref(startersCartDetailsList);
                AppSettings.getInstance().setSoupListInPref(soupCartDetailsList);

                cartAdapter.notifyDataSetChanged();
                finish();
                OrderManageApplication.getInstance().showToast(MSG_ORDER_SUCCESS);
                Intent launchHomeIntent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(launchHomeIntent);
            }
        };
        DialogInterface.OnClickListener negativeDialogListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        DialogUtils.alertDialog(this, 0, TITLE_CONFIRM_ORDER,
                MSG_CONFIRM_ORDER, null,
                POSITIVE_BTN_TEXT, positiveDialogListener, NEGATIVE_BTN_TEXT, negativeDialogListener);

    }

    private void deleteDialog() {
        DialogInterface.OnClickListener positiveDialogListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartDetailsList.clear();
                startersCartDetailsList.clear();
                todaySpecialcartDetailsList.clear();
                soupCartDetailsList.clear();
                AppSettings.getInstance().setCartListInPref(todaySpecialcartDetailsList);
                AppSettings.getInstance().setStartersListInPref(startersCartDetailsList);
                AppSettings.getInstance().setSoupListInPref(soupCartDetailsList);

                cartAdapter.notifyDataSetChanged();
                dialog.dismiss();
                MainActivity.launchActivity(CartActivity.this);
            }
        };
        DialogInterface.OnClickListener negativeDialogListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        DialogUtils.alertDialog(this, 0, TITLE_DELETE_CART_ALL,
                MSG_CLEAR_CART, null,
                POSITIVE_BTN_TEXT, positiveDialogListener, NEGATIVE_BTN_TEXT, negativeDialogListener);

    }

}
