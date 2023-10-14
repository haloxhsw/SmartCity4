package com.example.smartcity4.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.smartcity4.R;
import com.example.smartcity4.pojo.BannerPojo;
import com.example.smartcity4.pojo.News;
import com.example.smartcity4.pojo.NewsType;
import com.example.smartcity4.pojo.Service;
import com.example.smartcity4.ui.news.NewsActivity;
import com.example.smartcity4.util.BannerViewAdapter;
import com.example.smartcity4.util.HttpUtil;
import com.example.smartcity4.util.MyAdapter;
import com.example.smartcity4.util.MyApp;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class FragmentMain extends Fragment implements View.OnClickListener {
    private View view;
    private Banner banner_view;
    private GridView grid_view;
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
    private NewsType newsType;
    private BottomNavigationView news_type;
    private BannerPojo bannerPojo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        initId();
        initBanner();
        initGrid();
        initNews();
        getNewsType();
        //setNav();
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyApp.getContext(), NewsActivity.class);
                intent.putExtra("newsid", news.getRows().get(i).getId() + "");
                startActivity(intent);
            }
        });
        news_type.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        switchNews(newsType.getData().get(0).getId());
                        return true;
                    case R.id.item2:
                        switchNews(newsType.getData().get(1).getId());
                        return true;
                    case R.id.item3:
                        switchNews(newsType.getData().get(2).getId());
                        return true;
                    case R.id.item4:
                        switchNews(newsType.getData().get(3).getId());
                        return true;
                    case R.id.item5:
                        switchNews(newsType.getData().get(4).getId());
                        return true;
                }
                return false;
            }
        });
        return view;
    }

    private void initId() {
        banner_view = view.findViewById(R.id.banner_view);
        grid_view = view.findViewById(R.id.grid_view);
        list_view = view.findViewById(R.id.list_view);
        news_type = view.findViewById(R.id.news_type);
        /*btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn1.setOnClickListener(this::onClick);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);*/
    }

    private void initBanner() {
        httpUtil.get("/prod-api/api/rotation/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bannerPojo = gson.fromJson(response.body().string(), BannerPojo.class);
                final List<String> url = new ArrayList<>();
                for (BannerPojo.RowsDTO row : bannerPojo.getRows()) {
                    url.add(row.getAdvImg());
                }
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getBanner(url);
                        }
                    });
            }
        });
    }

    private void getBanner(List<String> url) {
        banner_view.setAdapter(new BannerViewAdapter(url))
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(getActivity())).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object o, int i) {
                Intent intent = new Intent(MyApp.getContext(),NewsActivity.class);
                intent.putExtra("newsid",bannerPojo.getRows().get(i).getTargetId()+"");
                startActivity(intent);
            }
        });
    }

    private void initGrid() {
        httpUtil.get("/prod-api/api/service/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Service service = gson.fromJson(response.body().string(), Service.class);
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            while (service.getRows().size() > 10)
                                service.getRows().remove(service.getRows().size() - 1);
                            getGrid(service);
                        }
                    });
            }
        });
    }

    private void getGrid(final Service service) {
        MyAdapter myAdapter = new MyAdapter(service.getRows(), R.layout.grid_item, getActivity(), new MyAdapter.Callback() {
            @Override
            public void itemAdapter(MyAdapter.ViewHolder holder, Object data, int position) {
                ImageView imageView = holder.getImageView(R.id.grid_item_image);
                TextView textView = holder.getTextView(R.id.grid_item_text);
                if (position <= 8) {
                    Glide.with(imageView)
                            .load("http://124.93.196.45:10001/" + service.getRows().get(position).getImgUrl())
                            .into(imageView);
                    textView.setText(service.getRows().get(position).getServiceName());
                } else if (position == 9) {
                    imageView.setImageResource(R.drawable.nav1);
                    textView.setText("更多服务");
                }
            }
        });
        grid_view.setAdapter(myAdapter);
    }

    private void initNews() {
        httpUtil.get("/prod-api/press/press/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                news = gson.fromJson(response.body().string(), News.class);
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getNews(news);
                        }
                    });
            }
        });
    }

    private void getNews(final News news) {
        MyAdapter myAdapter = new MyAdapter(news.getRows(), R.layout.news_list_item, getActivity(), new MyAdapter.Callback() {
            @Override
            public void itemAdapter(MyAdapter.ViewHolder holder, Object data, int position) {
                ImageView imageView = holder.getImageView(R.id.item_image);
                TextView title = holder.getTextView(R.id.item_title);
                TextView text = holder.getTextView(R.id.item_text);
                TextView read = holder.getTextView(R.id.item_read);
                TextView like = holder.getTextView(R.id.item_like);
                Glide.with(imageView)
                        .load("http://124.93.196.45:10001/" + news.getRows().get(position).getCover())
                        .into(imageView);
                title.setText(news.getRows().get(position).getTitle());
                text.setText(Html.fromHtml(news.getRows().get(position).getContent()));
                read.setText(news.getRows().get(position).getReadNum() + "");
                like.setText(news.getRows().get(position).getLikeNum() + "");
            }
        });
        list_view.setAdapter(myAdapter);
        if (news.getRows().size() >0 ){
            View item = myAdapter.getView(0, null, list_view);
            item.measure(0,0);
            ViewGroup.LayoutParams params = list_view.getLayoutParams();
            params.height = news.getRows().size()*item.getMeasuredHeight()+100;
            list_view.setLayoutParams(params);
        }
    }

    private void getNewsType() {
        httpUtil.get("/prod-api/press/category/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                newsType = gson.fromJson(response.body().string(), NewsType.class);
            }
        });
    }

    private void switchNews(int id) {
        httpUtil.get("/prod-api/press/press/list?type=" + id, client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                news = gson.fromJson(response.body().string(), News.class);
                //Log.d("TAG", "onResponse: "+response.body().string());
                if (getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getNews(news);
                        }
                    });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (getActivity() != null)
            switch (view.getId()) {
                case R.id.btn1:
                    switchNews(newsType.getData().get(0).getId());
                    break;
                case R.id.btn2:
                    switchNews(newsType.getData().get(1).getId());
                    break;
                case R.id.btn3:
                    switchNews(newsType.getData().get(2).getId());
                    break;
                case R.id.btn4:
                    switchNews(newsType.getData().get(3).getId());
                    break;
                case R.id.btn5:
                    switchNews(newsType.getData().get(4).getId());
                    break;
            }
    }

    private void setNav(){
        Class navClass = news_type.getClass();
        Field[] fields =  navClass.getDeclaredFields();
        for (int i = 0; i <fields.length ; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Log.d("TAG", "setNav: "+field.getName());
            if (field.getName().equals("menuView")){
                try {
                    BottomNavigationMenuView layout = (BottomNavigationMenuView) field.get(news_type);
                    for (int j = 0; j < layout.getChildCount(); j++) {
                        View view = layout.getChildAt(j);
                        FrameLayout frameLayout = view.findViewById(view.getId());
                        for (int k = 0; k <frameLayout.getChildCount() ; k++) {
                            View v = frameLayout.getChildAt(k);
                            if (v instanceof ImageView){
                                //((ImageView) v).setImageResource(R.drawable.like);
                                v.setVisibility(View.GONE);
                                Log.d("TAG", "setNav: GONE");
                            } else {
                                //v.setVisibility(View.GONE);
                            }
                            Log.d("TAG", "setNav: fragment "+v.getAccessibilityClassName());
                        }
                        Log.d("TAG", "setNav: "+view.getId()+" "+view.getAccessibilityClassName());

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
