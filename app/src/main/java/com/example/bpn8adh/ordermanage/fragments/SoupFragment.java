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
import android.widget.Button;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.activities.CartActivity;
import com.example.bpn8adh.ordermanage.adapters.SoupAdapter;
import com.example.bpn8adh.ordermanage.database.FirebaseManager;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoupFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    @BindView(R.id.add_to_cart)
//    Button addToCartBtn;

    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private SoupAdapter soupAdapter;
    private ArrayList<FoodDetails> soupDetailList = new ArrayList<>();
    private View view;
    private ArrayList<FoodDetails> cartDetailList = new ArrayList<>();

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

        if (soupDetailList.size() != 0) {
            soupDetailList.clear();
        }
        setItemDetails();
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        soupAdapter = new SoupAdapter(mContext, soupDetailList);
        recyclerView.setAdapter(soupAdapter);
        soupAdapter.notifyDataSetChanged();
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
//                OrderManageApplication.getInstance().showToast("Order placed succesfully");
//                break;
//        }
//    }

    private void setItemDetails() {

        if (cartDetailList.size() != 0) {
            cartDetailList.clear();
        }
        cartDetailList.addAll(soupDetailList);
        if (AppSettings.getInstance().getCartDetailsLists() != null &&
                !AppSettings.getInstance().getCartDetailsLists().isEmpty()) {
            cartDetailList.addAll(AppSettings.getInstance().getCartDetailsLists());
        }
        AppSettings.getInstance().setCartDetailsLists(cartDetailList);

    }
}