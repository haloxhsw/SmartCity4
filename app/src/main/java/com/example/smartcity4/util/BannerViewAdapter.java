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
    private List<String> list; //存储图片地址的列表

    //构造方法，参数：datas是图片地址的列表
    public BannerViewAdapter(List<String> datas) {
        super(datas); //调用父类的构造方法，传入datas作为参数
        this.list = datas; //将datas赋值给list
    }

    @Override
    public ViewHolder onCreateHolder(ViewGroup viewGroup, int i) {
        //重写onCreateHolder方法，用于创建ViewHolder对象
        ImageView imageView = new ImageView(viewGroup.getContext()); //创建一个ImageView对象，传入viewGroup的上下文作为参数
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)); //设置ImageView的布局参数，宽度和高度都为MATCH_PARENT
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); //设置ImageView的缩放类型为CENTER_CROP
        return new ViewHolder(imageView); //返回一个ViewHolder对象，传入imageView作为参数
    }

    @Override
    public void onBindView(ViewHolder viewHolder, String s, int i, int i1) {
        //重写onBindView方法，用于绑定数据到视图上
        Glide.with(viewHolder.imageView) //使用Glide库加载图片，传入viewHolder中的imageView作为参数
                .load("http://124.93.196.45:10001/"+s) //设置图片的加载地址，s是图片地址的一部分，需要拼接完整的url
                .into(viewHolder.imageView); //将图片显示在viewHolder中的imageView上
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView; //ImageView对象

        public ViewHolder(@NonNull View itemView) {
            super(itemView); //调用父类的构造方法，传入itemView作为参数
            this.imageView = (ImageView) itemView; //将itemView强制转换成ImageView对象，并赋值给imageView
        }
    }
}

