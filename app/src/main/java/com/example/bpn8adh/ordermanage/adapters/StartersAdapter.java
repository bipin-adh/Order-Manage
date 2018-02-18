package com.example.bpn8adh.ordermanage.adapters;

/**
 * Created by Bipin on 07/02/18.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.bpn8adh.ordermanage.R;

public class StartersAdapter extends RecyclerView.Adapter<StartersAdapter.MyViewHolder> {

    private final boolean isGridView;
    private Context context;
    private String[] app_name;
    private int[] app_icon;
    private MyViewHolder myViewHolder;

    public StartersAdapter(Context context, String[] app_name, int[] app_icon, boolean isGridView) {

        this.context = context;
        this.app_icon = app_icon;
        this.app_name = app_name;
        this.isGridView = isGridView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
//            itemView = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.custom_grid, parent, false);
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Glide.with(context).load(app_icon[position]).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imageViewItem) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                circularBitmapDrawable.setCornerRadius(16);
                holder.imageViewItem.setImageDrawable(circularBitmapDrawable);
            }
        });
        holder.textViewItemName.setText(app_name[position]);
    }

    @Override
    public int getItemCount() {
        return app_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        ImageView imageViewItem;

        public MyViewHolder(View view) {
            super(view);
            imageViewItem = view.findViewById(R.id.iv_item_logo);
            textViewItemName =view.findViewById(R.id.tv_item_name);

        }
    }
}