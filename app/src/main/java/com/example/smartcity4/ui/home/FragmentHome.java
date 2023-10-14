package com.example.smartcity4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcity4.R;
import com.example.smartcity4.pojo.User;
import com.example.smartcity4.util.AppUtil;
import com.example.smartcity4.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class FragmentHome extends Fragment {
    private View view;
    private HttpUtil httpUtil = new HttpUtil();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private ImageView user_icon;
    private TextView user_nick;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initId();
        initData();
        return view;
    }
    private void initId(){
        user_nick = view.findViewById(R.id.user_nick);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
    }
    private void initData(){
        httpUtil.getAddHand("/prod-api/api/common/user/getInfo", AppUtil.getToken(), client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                User user = gson.fromJson(response.body().string(),User.class);
                user_nick.setText(user.getUser().getNickName());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }
}
