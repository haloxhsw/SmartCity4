package com.example.smartcity4.util;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static Context context; //定义一个静态的Context变量，用于存储全局的上下文对象

    @Override
    public void onCreate() {
        super.onCreate(); //调用父类的onCreate方法
        context = getApplicationContext(); //获取应用程序的上下文对象，并赋值给context变量
    }

    public static Context getContext() {
        return context; //定义一个静态的方法，用于返回context变量
    }
}

