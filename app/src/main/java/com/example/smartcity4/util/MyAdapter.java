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
    List<T> list;
    int id;
    Context context;
    LayoutInflater layoutInflater;
    Callback callback;

    public MyAdapter(List<T> list, int id, Context context, Callback callback) {
        this.list = list;
        this.id = id;
        this.context = context;
        this.callback = callback;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = layoutInflater.inflate(id,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        callback.itemAdapter(holder,list.get(i),i);
        return view;
    }

    public interface Callback<T>{
        void itemAdapter(ViewHolder holder,Object data,int position);
    }

    public static class ViewHolder{
        View view;

        public ViewHolder(View view) {
            this.view = view;
        }
        public ImageView getImageView(int id){
            return view.findViewById(id);
        }
        public TextView getTextView(int id){
            return view.findViewById(id);
        }
    }
}
