package com.example.bpn8adh.ordermanage.adapters;

/**
 * Created by Bipin on 07/02/18.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.bpn8adh.ordermanage.R;
import com.example.bpn8adh.ordermanage.models.FoodDetails;

import java.util.ArrayList;

public class StartersAdapter extends RecyclerView.Adapter<StartersAdapter.MyViewHolder>{

    public static final String TAG = StartersAdapter.class.getSimpleName();
    private Context context;
    private MyViewHolder myViewHolder;
    private ArrayList<FoodDetails> foodDetailList = new ArrayList<>();

    private String foodName;
    private String foodPrice;
    private String foodPrepTime;
    private String foodImage;

    private int quantityCount = 0;

    public StartersAdapter(Context context, ArrayList<FoodDetails> foodDetailList) {

        this.context = context;
        this.foodDetailList = foodDetailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.progressBar.setVisibility(View.VISIBLE);
        foodName = foodDetailList.get(position).getFoodName();
        foodPrice = foodDetailList.get(position).getFoodPrice();
        foodImage = foodDetailList.get(position).getFoodImage();
        foodPrepTime = foodDetailList.get(position).getFoodPreparationTime();

        Glide.with(context).load(foodImage).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imageViewItem) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                circularBitmapDrawable.setCornerRadius(16);
                holder.imageViewItem.setImageDrawable(circularBitmapDrawable);
                if (holder.progressBar != null) {
                    holder.progressBar.setVisibility(View.GONE);
                }
            }
        });
        holder.textViewItemName.setText(foodName);
        holder.textViewPrepTime.setText(foodPrepTime);
        holder.textViewPrice.setText(foodPrice);

        holder.imageViewQuantityIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addCount = increaseQuantityCount();
                holder.textViewQuantityTotal.setText(""+addCount);
            }
        });
        holder.imageViewQuantityDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             int decreaseCount = decreaseQuantityCount();
             holder.textViewQuantityTotal.setText(""+decreaseCount);
            }
        });

//        holder.textViewQuantityTotal.setText(String.valueOf(quantityCount));
    }

    @Override
    public int getItemCount() {
        return foodDetailList.size();
    }

    private int decreaseQuantityCount() {
        if (quantityCount > 0) {
            quantityCount--;
        }
        Log.d(TAG, "aaaa decreaseQuantityCount: - --->" + quantityCount);
        return quantityCount;
    }

    private int increaseQuantityCount() {
        quantityCount++;
        Log.d(TAG, "aaaa increaseQuantityCount: + ---> " + quantityCount);
        return quantityCount;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        TextView textViewPrepTime;
        TextView textViewQuantityTotal;
        TextView textViewPrice;

        ProgressBar progressBar;
        ImageView imageViewItem;
        ImageView imageViewQuantityIncrease;
        ImageView imageViewQuantityDecrease;

        public MyViewHolder(View view) {
            super(view);
            textViewItemName = view.findViewById(R.id.tv_item_name);
            textViewPrepTime = view.findViewById(R.id.tv_time);
            textViewPrice = view.findViewById(R.id.tv_price);
            progressBar = view.findViewById(R.id.item_image_progress_bar);

            imageViewItem = view.findViewById(R.id.iv_item_logo);
            imageViewQuantityIncrease = view.findViewById(R.id.iv_quantity_increase);
            imageViewQuantityDecrease = view.findViewById(R.id.iv_quantity_decrease);
            textViewQuantityTotal = view.findViewById(R.id.tv_item_quantity);
        }
    }
}