package com.example.bpn8adh.ordermanage.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by Bipin on 28/02/18.
 */

public class SoupAdapter extends RecyclerView.Adapter<SoupAdapter.MyViewHolder>{

    private int DEFAULT_ITEM_QUANTITY_VALUE = 0;
    private Context context;
    private MyViewHolder myViewHolder;

    private ArrayList<FoodDetails> foodDetailList = new ArrayList<>();
    private String foodName;
    private int foodPrice;
    private String foodPrepTime;
    private String foodImage;

    public SoupAdapter(Context context, ArrayList<FoodDetails> foodDetailList) {
        this.context = context;
        this.foodDetailList = foodDetailList;
    }

    @Override
    public SoupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        myViewHolder = new SoupAdapter.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final SoupAdapter.MyViewHolder holder, final int position) {
        holder.progressBar.setVisibility(View.VISIBLE);
        holder.textViewQuantityTotal.setText("" + DEFAULT_ITEM_QUANTITY_VALUE);
        final FoodDetails foodDetails = foodDetailList.get(position);
        foodName = foodDetails.getFoodName();
        foodPrice = foodDetails.getFoodPrice();
        foodImage = foodDetails.getFoodImage();
        foodPrepTime = foodDetails.getFoodPreparationTime();

        Glide.with(context)
                .load(foodImage)
                .asBitmap()
                .error(R.drawable.error_no_preview)
                .centerCrop()
                .into(new BitmapImageViewTarget(holder.imageViewItem) {
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
        holder.textViewPrice.setText(String.format(context.getString(R.string.item_price),foodPrice));

        holder.imageViewQuantityIncrease.setTag(position);
        holder.imageViewQuantityIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textViewQuantityTotal.setTextColor(context.getResources().getColor(R.color.colorAccent));
                foodDetails.setFoodQuantity(foodDetails.getFoodQuantity() + 1);
                holder.textViewQuantityTotal.setText("" + foodDetails.getFoodQuantity());
            }
        });
        holder.imageViewQuantityDecrease.setTag(position);
        holder.imageViewQuantityDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodDetails.getFoodQuantity() > 0) {
                    holder.textViewQuantityTotal.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    foodDetails.setFoodQuantity(foodDetails.getFoodQuantity() - 1);
                    holder.textViewQuantityTotal.setText("" + foodDetails.getFoodQuantity());
                    if (foodDetails.getFoodQuantity() == 0) {
                        holder.textViewQuantityTotal.setTextColor(context.getResources().getColor(R.color.gray_color_dark));
                    }
                } else {
                    holder.textViewQuantityTotal.setTextColor(context.getResources().getColor(R.color.gray_color_dark));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDetailList.size();
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

