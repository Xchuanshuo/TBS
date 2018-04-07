package com.legend.tbs.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.legend.tbs.R;
import com.legend.tbs.common.BaseFragment;
import com.legend.tbs.contract.BaseContract;

import java.lang.ref.WeakReference;

import static com.legend.tbs.TbsActivity.cookie;
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
    private RecyclerView mRecyclerView;
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
        mSwipeRefresh = $(R.id.mSwipeRefresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
    }

    @Override
    public void initListener() {
        mRecyclerView = getmRecyclerView();
        mRecyclerView.setAdapter(mSelfAdapter);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefresh.setRefreshing(true);
                refreshData();
            }
        });
    }

    @Override
    public void refreshData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.sendRequest(SELF_URL,token,cookie);
                mSwipeRefresh.setRefreshing(false);
            }
        },800);

    }


    @Override
    public void showLoading() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefresh.setRefreshing(false);
    }
}
