package com.example.bpn8adh.ordermanage.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.adapters.CustomAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartersFragment extends Fragment {
    Context mContext;
    private GridView gridview;
    private static String[] app_name = {
            "Chicken",
            "Soup",
            "Chilly"};
    private static int[] app_icon = {
            R.drawable.soup, R.drawable.soup, R.drawable.soup};


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
        // casting xml GridView into javacode
        gridview = view.findViewById(R.id.gridView);
        // setting up Adapter tp GridView
        gridview.setAdapter(new CustomAdapter(mContext, app_name, app_icon));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_offer, container, false);
    }

}
