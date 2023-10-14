package com.example.smartcity4.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.smartcity4.R;

public class TitleLayout extends ConstraintLayout {
    //构造方法，参数：context是上下文对象，attrs是自定义属性集合
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //从布局文件中加载标题栏的视图
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        //从自定义属性集合中获取自定义属性的值
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TitleLayout);
        //获取标题栏的文本控件
        TextView textView = findViewById(R.id.title_text);
        //获取标题栏的返回按钮控件
        Button button = findViewById(R.id.back_btn);
        //获取自定义属性titleText的值，即标题栏的文本内容
        String s = array.getNonResourceString(R.styleable.TitleLayout_titleText);
        //获取自定义属性showBtn的值，即是否显示返回按钮
        boolean f = array.getBoolean(R.styleable.TitleLayout_showBtn,true);
        //设置标题栏的文本为自定义属性的值
        textView.setText(s+"");
        //如果自定义属性showBtn为false，则隐藏返回按钮
        if (!f){
            button.setVisibility(GONE);
        }
        //设置返回按钮的点击事件监听器
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击返回按钮时，结束当前的活动
                ((Activity)getContext()).finish();
            }
        });
    }
}

