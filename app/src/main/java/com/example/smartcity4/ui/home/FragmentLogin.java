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
    private HttpUtil httpUtil = new HttpUtil();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initId();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("username",input_name.getText());
                    jsonObject.put("password",input_pwd.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    private void login(JSONObject jsonObject){
        httpUtil.post(jsonObject, "/prod-api/api/login", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Login data = gson.fromJson(response.body().string(),Login.class);
                if (data.getToken()!=null){
                    AppUtil.setToken(data.getToken());
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment,new FragmentHome()).commit();
                } else {
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
