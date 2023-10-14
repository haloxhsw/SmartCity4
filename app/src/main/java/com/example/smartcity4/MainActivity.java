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
    private BottomNavigationView nav_view;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        initView();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentMain()).commit();
        swipe_view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                swipe_view.setRefreshing(false);
                swipe_view.setEnabled(false);
            }
        });
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.fragment_main:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentMain()).commit();
                        return true;
                    case R.id.fragment_sevice:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentService()).commit();
                        return true;
                    case R.id.fragment_news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FragmentNews()).commit();
                        return true;
                    case R.id.fragment_home:
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