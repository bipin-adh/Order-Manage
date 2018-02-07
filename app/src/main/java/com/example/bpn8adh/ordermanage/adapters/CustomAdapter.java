package com.example.bpn8adh.ordermanage.adapters;

/**
 * Created by bpn8adh on 07/02/18.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.bpn8adh.ordermanage.R;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private String[] app_name;
    private int[] app_icon;

    public CustomAdapter(Context context, String[] app_name, int[] app_icon) {

        this.context = context;
        this.app_icon = app_icon;
        this.app_name = app_name;
    }

    @Override
    public int getCount() {

        return app_name.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_grid, null);
        TextView name = convertView.findViewById(R.id.textView1);
        final ImageView icon = convertView.findViewById(R.id.imageView);

        Glide.with(context).load(app_icon[position]).asBitmap().centerCrop().into(new BitmapImageViewTarget(icon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
                circularBitmapDrawable.setCornerRadius(16);
                icon.setImageDrawable(circularBitmapDrawable);
            }
        });
        name.setText(app_name[position]);
//        icon.setImageResource(app_icon[position]);
        return convertView;
    }
}
