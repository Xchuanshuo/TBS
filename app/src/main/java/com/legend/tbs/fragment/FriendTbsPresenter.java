package com.legend.tbs.fragment;

import android.util.Log;

import com.legend.tbs.common.adapter.TbsAdapter;
import com.legend.tbs.common.model.TbsBean;
import com.legend.tbs.contract.BaseContract;

import java.util.ArrayList;
import java.util.List;

import static com.legend.tbs.fragment.BaseModel.finished;

/**
 * @author Legend
 * @data by on 2018/4/7.
 * @description
 */

public class FriendTbsPresenter implements BaseContract.Presenter {


    private BaseContract.View view;
    private BaseContract.Model model;
    public static TbsAdapter mFriendAdapter;
    private List<TbsBean> tbsBeanList;

    public FriendTbsPresenter(BaseContract.View view, BaseContract.Model model) {
        this.view = view;
        this.model = model;
        tbsBeanList = new ArrayList<>();
        mFriendAdapter = new TbsAdapter(tbsBeanList);
    }

    @Override
    public void sendRequest(String url, String token, String cookie) {

        model.Request(url, token, cookie,1, new BaseContract.Callback() {
            @Override
            public void onStart() {
                view.showLoading();
            }

            @Override
            public void onSuccess(List list) {
                if (finished == 0) {
                    tbsBeanList.addAll(list);
                }
                Log.d("SIZE", String.valueOf(mFriendAdapter.getItemCount()));
            }

            @Override
            public void onFailure() {
            }

            @Override
            public void onFinished() {
                mFriendAdapter.notifyDataSetChanged();
                view.hideLoading();
            }
        });
    }
}
