package com.example.bpn8adh.ordermanage.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bpn8adh.ordermanage.OrderManageApplication;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.TodaysSpecialAdapter;
import com.example.bpn8adh.ordermanage.database.FirebaseManager;
import com.example.bpn8adh.ordermanage.database.callbacks.TodaysSpecialCallback;
import com.example.bpn8adh.ordermanage.interfaces.CartToolbarCountListener;
import com.example.bpn8adh.ordermanage.interfaces.UiUpdateListener;
import com.example.bpn8adh.ordermanage.models.FoodDetails;
import com.example.bpn8adh.ordermanage.utils.AppSettings;
import com.example.bpn8adh.ordermanage.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodaysSpecialFragment extends Fragment implements UiUpdateListener {
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

    private List<FoodDetails> todaysSpecialDetailList = new ArrayList<>();
    private List<FoodDetails> oldCartDetailList = new ArrayList<>();

    private CartToolbarCountListener cartToolbarCountListener;
    private ProgressDialog progressDialog;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = DialogUtils.showProgressDialog(getActivity(), null, getString(R.string.msg_progress_fetching_data), true, false);
        FirebaseManager.getInstance().getTodaysSpecialList(new TodaysSpecialCallback(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tabs, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void setItemDetails() {

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

    @Override
    public void success() {
        progressDialog.dismiss();
        todaysSpecialDetailList = AppSettings.getInstance().getCartDetailsLists();
        setItemDetails();
    }

    @Override
    public void failure(String msg) {
        progressDialog.dismiss();
        OrderManageApplication.getInstance().showToast(msg);
    }
}