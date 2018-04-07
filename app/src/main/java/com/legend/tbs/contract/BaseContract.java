package com.legend.tbs.contract;

import java.util.List;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public interface BaseContract {

    interface Model {
        /**
         *  请求获取数据
         * @param url
         * @param token
         * @param cookie
         * @param type 是好友还是自己的的
         * @param callback  回调
         */
        void Request(String url, String token, String cookie, int type,Callback callback);
    }

    interface View {

        void showLoading();

        void hideLoading();
    }

    /**
     *  发起请求
     */
    interface Presenter {
        void sendRequest(String url, String token, String cookie);
    }

    /**
     *  结果回调的接口
     * @param <T>
     */
    interface Callback<T> {

        void onStart();

        void onSuccess(List<T> tList);

        void onFailure();

        void onFinished();
    }
}
