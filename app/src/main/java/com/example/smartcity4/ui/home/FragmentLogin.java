package com.example.smartcity4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity4.R;
import com.example.smartcity4.pojo.Login;
import com.example.smartcity4.util.AppUtil;
import com.example.smartcity4.util.HttpUtil;
import com.example.smartcity4.util.MyApp;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class FragmentLogin extends Fragment {
    private View view;
    private EditText input_name;
    private EditText input_pwd;
    private Button btn_login;
    //网络请求用的 httpUtil OkHttpClient
    //使用HttpUtil和OkHttpClient类进行网络请求
    private HttpUtil httpUtil = new HttpUtil();
    private OkHttpClient client = new OkHttpClient();
    //json object 转换
    //使用Gson类进行json对象的转换
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //登录页面布局
        //加载登录页面的布局文件
        view = inflater.inflate(R.layout.fragment_login, container, false);
        //初始化id
        //获取登录页面的各个控件的id
        initId();
        //登录按钮事件
        //设置登录按钮的点击事件监听器
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    //获取输入的账号和密码
                    //将输入的账号和密码封装成json对象
                    jsonObject.put("username",input_name.getText());
                    jsonObject.put("password",input_pwd.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //登录方法
                //调用登录方法，传入json对象作为参数
                login(jsonObject);
            }
        });
        return view;
    }

    private void initId(){
        input_name = view.findViewById(R.id.input_name);
        input_pwd = view.findViewById(R.id.input_pwd);
        btn_login = view.findViewById(R.id.btn_login);
    }

    /**
     * 登录方法
     * @param jsonObject 登录信息json
     */
    private void login(JSONObject jsonObject){
        //post 请求 类型见api文档
        //使用httpUtil的post方法，向服务器发送登录信息，client作为回调参数，接收服务器的响应
        httpUtil.post(jsonObject, "/prod-api/api/login", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //发生错误的后的回调方法
                //如果请求失败，执行此方法，处理错误信息
            }
            //成功请求的回调方法
            //如果请求成功，执行此方法，处理响应数据
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Login 是用GsonFormat插件生成的
                //使用GsonFormat插件生成的Login类，将响应数据转换成Login对象
                final Login data = gson.fromJson(response.body().string(),Login.class);
                //token 不为空 存入AppUtil的Token里 并跳到个人中心
                if (data.getToken()!=null){
                    AppUtil.setToken(data.getToken());
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment,new FragmentHome()).commit();
                } else {
                    //为空 发一个toast
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MyApp.getContext(),data.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

