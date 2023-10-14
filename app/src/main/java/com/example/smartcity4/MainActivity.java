package com.example.smartcity4;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.smartcity4.ui.home.FragmentHome;
import com.example.smartcity4.ui.home.FragmentLogin;
import com.example.smartcity4.ui.main.FragmentMain;
import com.example.smartcity4.ui.news.FragmentNews;
import com.example.smartcity4.ui.service.FragmentService;
import com.example.smartcity4.util.AppUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    /**
     * 菜单对应的页面都在这个布局里面
     */
    private FrameLayout fragment;
    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout swipe_view;
    /**
     * 菜单
     */
    private BottomNavigationView nav_view;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //如果自带的标题栏不为空 则隐藏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        //初始化 id
        initView();
        //先加载一下主页
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentMain()).commit();
        //下拉组件的事件
        swipe_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                swipe_view.setRefreshing(false);
                swipe_view.setEnabled(false);
            }
        });
        //底部菜单的事件
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.fragment_main:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentMain()).commit();
                        return true;//可以控制菜单的选中 即变色
                    case R.id.fragment_sevice:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentService()).commit();
                        return true;
                    case R.id.fragment_news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentNews()).commit();
                        return true;
                    case R.id.fragment_home:
                        //如果token为空 则跳到 登录页面 否则 跳到 个人中心
                        if (AppUtil.getToken() != null){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentHome()).commit();
                            return true;
                        }else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentLogin()).commit();
                            return true;
                        }
                }
                return false;
            }
        });
    }

    private void initView() {
        fragment = (FrameLayout) findViewById(R.id.fragment);
        swipe_view = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        nav_view = (BottomNavigationView) findViewById(R.id.nav_view);
    }

}