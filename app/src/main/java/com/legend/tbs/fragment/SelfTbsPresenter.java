package com.legend.tbs.fragment;

import android.util.Log;

import com.legend.tbs.common.adapter.TbsAdapter;
import com.legend.tbs.common.model.TbsBean;
import com.legend.tbs.contract.BaseContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class SelfTbsPresenter implements BaseContract.Presenter {

    private BaseContract.View view;
    private BaseContract.Model model;
    public static TbsAdapter mSelfAdapter;
    private List<TbsBean> tbsBeanList;

    public SelfTbsPresenter(BaseContract.View view, BaseContract.Model model) {
        this.view = view;
        this.model = model;
        tbsBeanList = new ArrayList<>();
        mSelfAdapter = new TbsAdapter(tbsBeanList);
    }

    @Override
    public void sendRequest(String url, String token, String cookie) {

        model.Request(url, token, cookie, 0,new BaseContract.Callback<TbsBean>() {
            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onSuccess(List list) {
                tbsBeanList.clear();
                tbsBeanList.addAll(list);
                Log.d("SIZE", String.valueOf(mSelfAdapter.getItemCount()));
            }

            @Override
            public void onFailure() {
            }

            @Override
            public void onFinished() {
                mSelfAdapter.notifyDataSetChanged();
                view.hideLoading();
            }
        });
    }
}
