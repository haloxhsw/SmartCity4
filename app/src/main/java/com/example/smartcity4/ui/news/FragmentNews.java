package com.example.smartcity4.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.smartcity4.R;
import com.example.smartcity4.pojo.BannerPojo;
import com.example.smartcity4.pojo.News;
import com.example.smartcity4.util.BannerViewAdapter;
import com.example.smartcity4.util.HttpUtil;
import com.example.smartcity4.util.MyAdapter;
import com.example.smartcity4.util.MyApp;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class FragmentNews extends Fragment {
    private View view;
    private Banner banner_view;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private ListView list_view;
    private HttpUtil httpUtil = new HttpUtil();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private News news;
    private BannerPojo bannerPojo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initId();
        initBanner();
        initNews();
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyApp.getContext(),NewsActivity.class);
                intent.putExtra("newsid",news.getRows().get(i).getId()+"");
                startActivity(intent);
            }
        });
        return view;
    }
    private void initId(){
        banner_view = view.findViewById(R.id.banner_view);
        list_view = view.findViewById(R.id.list_view);
    }
    private void initBanner(){
        httpUtil.get("/prod-api/api/rotation/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bannerPojo = gson.fromJson(response.body().string(),BannerPojo.class);
                final List<String> url = new ArrayList<>();
                for (BannerPojo.RowsDTO row : bannerPojo.getRows()) {
                    url.add(row.getAdvImg());
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getBanner(url);
                    }
                });
            }
        });
    }
    private void getBanner(List<String> url){
        banner_view.setAdapter(new BannerViewAdapter(url))
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(getActivity()))
        .setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object o, int i) {
                Intent intent = new Intent(MyApp.getContext(),NewsActivity.class);
                intent.putExtra("newsid",bannerPojo.getRows().get(i).getTargetId()+"");
                startActivity(intent);
            }
        });
    }
    private void initNews(){
        httpUtil.get("/prod-api/press/press/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                news = gson.fromJson(response.body().string(),News.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getNews(news);
                    }
                });
            }
        });
    }
    private void getNews(final News news){
        MyAdapter myAdapter = new MyAdapter(news.getRows(), R.layout.news_list_item, getActivity(), new MyAdapter.Callback() {
            @Override
            public void itemAdapter(MyAdapter.ViewHolder holder, Object data, int position) {
                ImageView imageView = holder.getImageView(R.id.item_image);
                TextView title = holder.getTextView(R.id.item_title);
                TextView text = holder.getTextView(R.id.item_text);
                TextView read = holder.getTextView(R.id.item_read);
                TextView like = holder.getTextView(R.id.item_like);
                Glide.with(imageView)
                        .load("http://124.93.196.45:10001/"+news.getRows().get(position).getCover())
                        .into(imageView);
                title.setText(news.getRows().get(position).getTitle());
                text.setText(news.getRows().get(position).getContent());
                read.setText(news.getRows().get(position).getReadNum()+"");
                like.setText(news.getRows().get(position).getLikeNum()+"");
            }
        });
        list_view.setAdapter(myAdapter);

    }

}
