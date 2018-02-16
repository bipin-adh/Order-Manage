package com.example.bpn8adh.ordermanage.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.CustomAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodaysSpecialFragment extends Fragment {

    //    GridView gridview;
    Context mContext;
    private static String[] app_name = {
            "Camera",
            "Chrome",
            "Clock", "Clsfas"};
    private static int[] app_icon = {
            R.drawable.food,
            R.drawable.food,
            R.drawable.food,
            R.drawable.food};
    private RecyclerView recyclerView;

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
        // casting xml GridView into javacode
        recyclerView = view.findViewById(R.id.recyclerView);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        // setting up Adapter tp GridView
        recyclerView.setAdapter(new CustomAdapter(mContext, app_name, app_icon, true));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }
}
