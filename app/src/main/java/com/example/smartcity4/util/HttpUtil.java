package com.example.smartcity4.util;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    //get方法，不带token
    //参数：url是请求的地址，client是OkHttpClient对象，callback是回调接口
    public void get(String url, OkHttpClient client, Callback callback){
        //创建一个Request对象，设置请求的url
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .build();
        //使用client对象发送请求，并将callback作为参数
        client.newCall(request).enqueue(callback);
    }
    //get方法，带token
    //参数：url是请求的地址，token是用户的身份标识，client是OkHttpClient对象，callback是回调接口
    public void getAddHand(String url,String token, OkHttpClient client, Callback callback){
        //创建一个Request对象，设置请求的url和token
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .addHeader("Authorization",token)
                .build();
        //使用client对象发送请求，并将callback作为参数
        client.newCall(request).enqueue(callback);
    }
    //post方法，不带token
    //参数：jsonObject是请求的数据，url是请求的地址，client是OkHttpClient对象，callback是回调接口
    public void post(JSONObject jsonObject, String url, OkHttpClient client, Callback callback){
        //创建一个RequestBody对象，将jsonObject转换成字符串，并设置数据类型为json
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        //创建一个Request对象，设置请求的url和body
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .post(body)
                .build();
        //使用client对象发送请求，并将callback作为参数
        client.newCall(request).enqueue(callback);
    }
    //post方法，带token
    //参数：jsonObject是请求的数据，url是请求的地址，token是用户的身份标识，client是OkHttpClient对象，callback是回调接口
    public void postAddHand(JSONObject jsonObject, String url,String token, OkHttpClient client, Callback callback){
        //创建一个RequestBody对象，将jsonObject转换成字符串，并设置数据类型为json
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        //创建一个Request对象，设置请求的url、body和token
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .post(body)
                .addHeader("Authorization",token)
                .build();
        //使用client对象发送请求，并将callback作为参数
        client.newCall(request).enqueue(callback);
    }
}

