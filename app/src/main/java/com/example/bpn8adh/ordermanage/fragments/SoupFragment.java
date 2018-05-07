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
import com.example.bpn8adh.ordermanage.adapters.SoupAdapter;
import com.example.bpn8adh.ordermanage.database.FirebaseManager;
import com.example.bpn8adh.ordermanage.database.callbacks.SoupCallback;
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
public class SoupFragment extends Fragment implements UiUpdateListener {
    public static final String TAG = SoupFragment.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Context mContext;
    private LinearLayoutManager linearLayoutManager;
    private SoupAdapter soupAdapter;
    private List<FoodDetails> soupDetailList = new ArrayList<>();
    private View view;
    private ArrayList<FoodDetails> cartDetailList = new ArrayList<>();
    private ArrayList<FoodDetails> oldCartDetailList = new ArrayList<>();
    private ProgressDialog progressDialog;

    public SoupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = DialogUtils.showProgressDialog(getActivity(), null, getString(R.string.msg_progress_fetching_data), true, false);
        FirebaseManager.getInstance().getSoupList(new SoupCallback(this));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        soupDetailList = AppSettings.getInstance().getSoupDetailsLists();
        oldCartDetailList.addAll(AppSettings.getInstance().getSoupListFromPref());

        for (FoodDetails newFoodDetail : soupDetailList) {
            for (FoodDetails preFoodDetail : oldCartDetailList) {
                if (preFoodDetail.getFoodName().equals(newFoodDetail.getFoodName())) {
                    newFoodDetail.setFoodQuantity(preFoodDetail.getFoodQuantity());
                }
            }
        }

        AppSettings.getInstance().setSoupDetailsLists(soupDetailList);
        populateList();
    }

    private void populateList() {
        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        soupAdapter = new SoupAdapter(mContext, soupDetailList);
        recyclerView.setAdapter(soupAdapter);
        soupAdapter.notifyDataSetChanged();
    }

    @Override
    public void success() {
        progressDialog.dismiss();
        setItemDetails();
    }

    @Override
    public void failure(String msg) {
        progressDialog.dismiss();
        OrderManageApplication.getInstance().showToast(msg);
    }
}