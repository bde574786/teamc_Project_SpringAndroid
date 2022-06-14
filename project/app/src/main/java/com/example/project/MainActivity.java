package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.project.adapter.FragmentAdapter;
import com.example.project.adapter.StoreAdapter;
import com.example.project.interfaces.OnIntentCallback;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;

    public static Context context;
    public static final int TAB_COUNT = 4;
    private static final String TAB_NAME[] = {"랜덤 가게 뽑기", "카테고리 별 메뉴 보기", "가까운 가게 보기", "정보 수정"};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private BackPressCloseHandler backPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backPress = new BackPressCloseHandler(this);
        init();
        addTab();
        addEventListener();
        chain();
    }

    /////////////////////////////////////////////////////////////
    // 초기화
    private void init() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout  = findViewById(R.id.tabLayout);
    }

    // 탭 추가
    private void addTab() {
        for (String name : TAB_NAME) {
            tabLayout.addTab(tabLayout.newTab().setText(name));
        }
        adapter = new FragmentAdapter(getSupportFragmentManager(), TAB_COUNT);
    }

    // 이벤트 리스너
    private void addEventListener() {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    // 뷰 페이저, 탭 레이아웃 연동
    private void chain() {
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onBackPressed() {

        int i = tabLayout.getSelectedTabPosition();
        switch (i){
            case 0:
                backPress.onBackPressed();
                break;
            default:
                viewPager.setCurrentItem(i-1);
        }

    }

}