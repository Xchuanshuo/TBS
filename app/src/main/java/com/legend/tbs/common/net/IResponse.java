package com.legend.tbs.common.net;

/**
 * @author Legend
 * @data by on 2018/1/3.
 * @description
 */

public interface IResponse {

    /**
     *  响应码
     * @return
     */
    int getCode();

    /**
     *  返回的数据
     * @return
     */
    String getData();
}
