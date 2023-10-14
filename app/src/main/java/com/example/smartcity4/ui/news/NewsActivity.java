package com.example.smartcity4.ui.news;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smartcity4.R;
import com.example.smartcity4.pojo.News;
import com.example.smartcity4.pojo.NewsData;
import com.example.smartcity4.pojo.Pl;
import com.example.smartcity4.util.AppUtil;
import com.example.smartcity4.util.HttpUtil;
import com.example.smartcity4.util.MyAdapter;
import com.example.smartcity4.util.MyApp;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView web_view;
    private ListView list_view;
    private EditText input_pl;
    private HttpUtil httpUtil = new HttpUtil();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private News news;
    private Pl pl;
    private TextView title_pl;
    private ListView list_vew_pl;
    private Button btn;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initView();
        id = getIntent().getStringExtra("newsid");
        Log.d("TAG", "onCreate: " + id);
        initNewsData(id);
        initNews();
        initPl(id);
    }

    private void initView() {
        web_view = (WebView) findViewById(R.id.web_view);
        list_view = (ListView) findViewById(R.id.list_view);
        input_pl = (EditText) findViewById(R.id.input_pl);
        title_pl = (TextView) findViewById(R.id.title_pl);
        list_vew_pl = (ListView) findViewById(R.id.list_vew_pl);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String pl = input_pl.getText().toString().trim();

        // TODO validate success, do something
    }

    private void initNewsData(String id) {
        httpUtil.get("/prod-api/press/press/" + id, client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final NewsData newsData = gson.fromJson(response.body().string(), NewsData.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getNewsData(newsData);
                    }
                });
            }
        });
    }

    private void getNewsData(NewsData newsData) {
        String text = newsData.getData().getContent();
        text = text.replaceAll("src=\\\"/", "src=\\\"http://124.93.196.45:10001/");
        web_view.loadData(text, "text/html;charset=utf-8", null);
    }

    private void initNews() {
        httpUtil.get("/prod-api/press/press/list", client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                news = gson.fromJson(response.body().string(), News.class);
                while (news.getRows().size() > 3)
                    news.getRows().remove(news.getRows().size() - 1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getNews(news);
                    }
                });
            }
        });
    }

    private void getNews(final News news) {
        MyAdapter myAdapter = new MyAdapter(news.getRows(), R.layout.news_list_item, MyApp.getContext(), new MyAdapter.Callback() {
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
                text.setText(news.getRows().get(position).getContent());
                read.setText(news.getRows().get(position).getReadNum() + "");
                like.setText(news.getRows().get(position).getLikeNum() + "");
            }
        });
        list_view.setAdapter(myAdapter);

    }

    private void initPl(String id) {
        httpUtil.get("/prod-api/press/comments/list?newsId=" + id, client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                pl = gson.fromJson(response.body().string(), Pl.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getPl(pl);
                    }
                });
            }
        });
    }

    private void getPl(Pl pl) {
        MyAdapter myAdapter = new MyAdapter(pl.getRows(), R.layout.pl_list_item, MyApp.getContext(), new MyAdapter.Callback() {
            @Override
            public void itemAdapter(MyAdapter.ViewHolder holder, Object data, int position) {
                ImageView imageView = holder.getImageView(R.id.item_image);
                TextView nick = holder.getTextView(R.id.item_nick);
                TextView text = holder.getTextView(R.id.item_text);
                TextView time = holder.getTextView(R.id.item_time);
                imageView.setImageResource(R.drawable.usericon);
                nick.setText(pl.getRows().get(position).getUserName());
                text.setText(pl.getRows().get(position).getContent());
                time.setText(pl.getRows().get(position).getCommentDate());
            }
        });
        list_vew_pl.setAdapter(myAdapter);
        if (!pl.getTotal().equals("0")) {
            View item = myAdapter.getView(0, null, list_vew_pl);
            item.measure(0, 0);
            //myAdapter.notifyDataSetChanged();
            //Log.d("TAG", "getPl: "+item.getMeasuredHeight());
            ViewGroup.LayoutParams params = list_vew_pl.getLayoutParams();
            params.height = item.getMeasuredHeight() * Integer.parseInt(pl.getTotal());
            list_vew_pl.setLayoutParams(params);
        }
        title_pl.setText("评论数:" + pl.getTotal());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("newsId", id);
                    jsonObject.put("content", input_pl.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                upPl(jsonObject);
                break;
        }
    }

    class Body {

        /**
         * code : 200
         * msg : 操作成功
         */

        @SerializedName("code")
        private int code;
        @SerializedName("msg")
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
    private void upPl(JSONObject jsonObject) {
        httpUtil.postAddHand(jsonObject, "/prod-api/press/pressComment", AppUtil.getToken(), client, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Body body = gson.fromJson(response.body().string(), Body.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        initPl(id);
                    }
                });
            }
        });
    }
}