package com.example.smartcity4.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class BannerViewAdapter extends BannerAdapter<String,BannerViewAdapter.ViewHolder> {
    private List<String> list;

    public BannerViewAdapter(List<String> datas) {
        super(datas);
        this.list = datas;
    }

    @Override
    public ViewHolder onCreateHolder(ViewGroup viewGroup, int i) {
        ImageView imageView = new ImageView(viewGroup.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindView(ViewHolder viewHolder, String s, int i, int i1) {
        Glide.with(viewHolder.imageView)
                .load("http://124.93.196.45:10001/"+s)
                .into(viewHolder.imageView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView;
        }
    }
}
