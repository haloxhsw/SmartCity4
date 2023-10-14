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
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TitleLayout);
        TextView textView = findViewById(R.id.title_text);
        Button button = findViewById(R.id.back_btn);
        String s = array.getNonResourceString(R.styleable.TitleLayout_titleText);
        boolean f = array.getBoolean(R.styleable.TitleLayout_showBtn,true);
        textView.setText(s+"");
        if (!f){
            button.setVisibility(GONE);
        }
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
