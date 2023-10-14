package com.example.smartcity4.util;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    public void get(String url, OkHttpClient client, Callback callback){
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public void getAddHand(String url,String token, OkHttpClient client, Callback callback){
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .addHeader("Authorization",token)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public void post(JSONObject jsonObject, String url, OkHttpClient client, Callback callback){
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public void postAddHand(JSONObject jsonObject, String url,String token, OkHttpClient client, Callback callback){
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        Request request = new Request.Builder()
                .url("http://124.93.196.45:10001/"+url)
                .post(body)
                .addHeader("Authorization",token)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
