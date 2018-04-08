package com.legend.tbs.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * @author Legend
 * @data by on 2018/1/27.
 * @description
 */
abstract public class BaseFragment extends Fragment {

    private View mView;
    private Context mContext;
    private XRecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setResourceLayoutId(),container,false);
        mContext = getContext();
        initView();
        mRecyclerView = $(setRecyclerViewId());
        if (mRecyclerView != null) {
            init();
        }
        initListener();
        refreshData();
        return mView;
    }
    private void init() {
        mRecyclerView.setFootViewText("正在玩命加载中...⌇●﹏●⌇","亲(o~.~o) 我也是有底线的哦");
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotateMultiple);
        mRecyclerView.getFootView().setMinimumHeight(200);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mView.getContext());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public abstract int setResourceLayoutId();

    public abstract int setRecyclerViewId();

    public abstract void initView();

    public abstract void initListener();

    public abstract void refreshData();



    public <T extends View> T $(int id) {
        return (T)mView.findViewById(id);
    }

    public View getmView() {
        return mView;
    }

    public XRecyclerView getmRecyclerView() {
        return mRecyclerView;
    }
    public Context getmContext() {
        return mContext;
    }
}
