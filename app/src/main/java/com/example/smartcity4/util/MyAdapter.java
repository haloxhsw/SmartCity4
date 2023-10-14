package com.example.smartcity4.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter<T> extends BaseAdapter {
    List<T> list; //存储数据的列表
    int id; //布局文件的id
    Context context; //上下文对象
    LayoutInflater layoutInflater; //布局加载器
    Callback callback; //回调接口

    //构造方法，参数：list是数据列表，id是布局文件的id，context是上下文对象，callback是回调接口
    public MyAdapter(List<T> list, int id, Context context, Callback callback) {
        this.list = list;
        this.id = id;
        this.context = context;
        this.callback = callback;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size(); //返回数据列表的大小
    }

    @Override
    public Object getItem(int i) {
        return list.get(i); //返回数据列表中指定位置的元素
    }

    @Override
    public long getItemId(int i) {
        return i; //返回指定位置的id，这里直接返回位置索引
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = layoutInflater.inflate(id,null); //如果view为空，就从布局文件中加载视图
            holder = new ViewHolder(view); //创建一个ViewHolder对象，传入视图作为参数
            view.setTag(holder); //将ViewHolder对象设置为视图的标签
        } else {
            holder = (ViewHolder) view.getTag(); //如果view不为空，就从视图中获取ViewHolder对象
        }
        callback.itemAdapter(holder,list.get(i),i); //调用回调接口的方法，传入ViewHolder对象，数据元素和位置索引作为参数
        return view; //返回视图对象
    }

    //定义一个回调接口，用于在外部实现数据绑定的逻辑
    public interface Callback<T>{
        void itemAdapter(ViewHolder holder,Object data,int position); //定义一个方法，参数：holder是ViewHolder对象，data是数据元素，position是位置索引
    }

    //定义一个静态内部类ViewHolder，用于缓存视图中的控件对象，避免重复查找控件id
    public static class ViewHolder{
        View view; //视图对象

        public ViewHolder(View view) {
            this.view = view;
        }
        public ImageView getImageView(int id){
            return view.findViewById(id); //根据id获取视图中的ImageView控件对象，并返回
        }
        public TextView getTextView(int id){
            return view.findViewById(id); //根据id获取视图中的TextView控件对象，并返回
        }
    }
}
