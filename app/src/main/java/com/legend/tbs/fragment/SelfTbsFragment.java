package com.legend.tbs.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.legend.tbs.R;
import com.legend.tbs.common.BaseFragment;
import com.legend.tbs.contract.BaseContract;

import java.lang.ref.WeakReference;

import static com.legend.tbs.TbsActivity.cookie;
import static com.legend.tbs.TbsActivity.dialog;
import static com.legend.tbs.TbsActivity.token;
import static com.legend.tbs.fragment.SelfTbsPresenter.mSelfAdapter;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class SelfTbsFragment extends BaseFragment implements BaseContract.View {

    private BaseContract.Model model;
    private BaseContract.Presenter presenter;
    private XRecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;

    public static final String SELF_URL = "https://ti.qq.com/cgi-node/honest-say/receive/mine?/cgi-node/honest-say/activity/choose?_client_version=0.0.7&_t=1522927164526&token=";


    @Override
    public int setResourceLayoutId() {
        return R.layout.fragments_self;
    }

    @Override
    public int setRecyclerViewId() {
        return R.id.mRecyclerView;
    }

    @Override
    public void initView() {
        model = new BaseModel();
        WeakReference<SelfTbsFragment> reference = new WeakReference<>(this);
        presenter = new SelfTbsPresenter(reference.get(),model);
    }

    @Override
    public void initListener() {
        mRecyclerView = getmRecyclerView();
        mRecyclerView.setAdapter(mSelfAdapter);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    public void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.sendRequest(SELF_URL,token,cookie);
            }
        },800);

    }


    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        mRecyclerView.refreshComplete();
        dialog.dismiss();
    }
}
