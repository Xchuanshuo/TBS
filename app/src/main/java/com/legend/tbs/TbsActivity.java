package com.legend.tbs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.legend.tbs.common.adapter.MainFragmentAdapter;
import com.legend.tbs.common.utils.Calculate;

import static com.legend.tbs.common.MyWebViewClient.COOKIE;
import static com.legend.tbs.common.MyWebViewClient.SKEY;

/**
 * @author HP
 */
public class TbsActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private MainFragmentAdapter adapter;
    private Toolbar mToolbar;
    public static String token;
    public static String cookie;
    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.mViewPager);
        mViewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager,true);
        Intent intent = getIntent();
        token = intent.getStringExtra(SKEY);
        cookie = intent.getStringExtra(COOKIE);
        dialog = Calculate.createLoadingDialog(this,"正在努力加载中");
    }
}
