package com.example.bpn8adh.ordermanage.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.models.FoodDetails;

import java.util.ArrayList;

/**
 * Created by bpn8adh on 02/03/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private final Context context;
    private ArrayList<FoodDetails> foodDetailsList = new ArrayList<>();
    private MyViewHolder myViewHolder;
    public static final String TAG = CartAdapter.class.getSimpleName();

    public CartAdapter(Context context, ArrayList<FoodDetails> foodDetailsList) {
        this.context = context;
        this.foodDetailsList = foodDetailsList;
    }

    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list, parent, false);

        myViewHolder = new CartAdapter.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CartAdapter.MyViewHolder holder, int position) {
        final FoodDetails foodDetails = foodDetailsList.get(position);
        holder.textViewItemName.setText(String.valueOf(foodDetails.getFoodName()));
        holder.textViewCost.setText(String.valueOf(foodDetails.getFoodPrice()));
        holder.textViewQty.setText(String.valueOf(foodDetails.getFoodQuantity()));
        holder.textViewTotalCost.setText(String.valueOf(foodDetails.getFoodQuantity() * foodDetails.getFoodPrice()));
    }

    @Override
    public int getItemCount() {
        return foodDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        TextView textViewCost;
        TextView textViewQty;
        TextView textViewTotalCost;

        public MyViewHolder(View view) {
            super(view);
            textViewItemName = view.findViewById(R.id.tv_cart_item_name);
            textViewCost = view.findViewById(R.id.cart_tv_cost_amount);
            textViewQty = view.findViewById(R.id.cart_tv_qty);
            textViewTotalCost = view.findViewById(R.id.cart_tv_total_amount);
        }
    }
}