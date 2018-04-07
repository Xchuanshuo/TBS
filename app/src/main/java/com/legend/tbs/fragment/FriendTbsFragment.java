package com.legend.tbs.fragment;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.legend.tbs.R;
import com.legend.tbs.common.BaseFragment;
import com.legend.tbs.contract.BaseContract;

import java.lang.ref.WeakReference;

import static com.legend.tbs.TbsActivity.cookie;
import static com.legend.tbs.TbsActivity.dialog;
import static com.legend.tbs.TbsActivity.token;
import static com.legend.tbs.fragment.BaseModel.finished;
import static com.legend.tbs.fragment.FriendTbsPresenter.mFriendAdapter;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class FriendTbsFragment extends BaseFragment implements BaseContract.View {

    private BaseContract.Model model;
    private BaseContract.Presenter presenter;
    private RecyclerView mRecyclerView;

    public static final String FRIEND_URL = "https://ti.qq.com/cgi-node/honest-say/receive/friends?_client_version=0.0.7&token=";

    @Override
    public int setResourceLayoutId() {
        return R.layout.fragment_friend;
    }

    @Override
    public int setRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    public void initView() {
        model = new BaseModel();
        WeakReference<FriendTbsFragment> reference = new WeakReference<>(this);
        presenter = new FriendTbsPresenter(reference.get(),model);
    }

    @Override
    public void initListener() {
        mRecyclerView = getmRecyclerView();
        mRecyclerView.setAdapter(mFriendAdapter);
    }

    @Override
    public void refreshData() {
        dialog.show();
        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 2000);
                if (finished == 1) {
                    handler.removeCallbacks(this);
                    hideLoading();
                }
                presenter.sendRequest(FRIEND_URL,token,cookie);
            }
        };
        handler.postDelayed(runnable,1000);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }
}
