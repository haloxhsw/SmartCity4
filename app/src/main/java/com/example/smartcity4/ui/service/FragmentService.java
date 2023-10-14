package com.example.smartcity4.ui.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.smartcity4.R;
import com.example.smartcity4.pojo.Service;
import com.example.smartcity4.util.HttpUtil;
import com.example.smartcity4.util.MyAdapter;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class FragmentService extends Fragment {
    private View view;
    private SearchView search_view;
    private GridView grid_view;
    private HttpUtil httpUtil = new HttpUtil();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);
        initId();
        initGrid();
        return view;
    }
    private void initId(){
        search_view = view.findViewById(R.id.search_view);
        grid_view = view.findViewById(R.id.grid_view);
    }
    private void initGrid(){
        httpUtil.get("/prod-api/api/service/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Service service = gson.fromJson(response.body().string(),Service.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getGrid(service);
                    }
                });
            }
        });
    }
    private void getGrid(final Service service){
        MyAdapter myAdapter = new MyAdapter(service.getRows(), R.layout.grid_item, getActivity(), new MyAdapter.Callback() {
            @Override
            public void itemAdapter(MyAdapter.ViewHolder holder, Object data, int position) {
                ImageView imageView = holder.getImageView(R.id.grid_item_image);
                TextView textView = holder.getTextView(R.id.grid_item_text);
                    Glide.with(imageView)
                            .load("http://124.93.196.45:10001/"+service.getRows().get(position).getImgUrl())
                            .into(imageView);
                    textView.setText(service.getRows().get(position).getServiceName());
            }
        });
        grid_view.setAdapter(myAdapter);
    }
}
