package com.legend.tbs.common.net.impl;


import com.legend.tbs.common.net.IResponse;

/**
 * @author Legend
 * @data by on 2017/11/23.
 * @description
 */

public class ResponseImpl implements IResponse {
    public static final int STATE_UNKNOW_ERROR = 100001;
    public static int STATE_OK = 200;
    private int code;
    private String data;

    @Override
    public String getData() {
        return data;
    }
    @Override
    public int getCode () {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setData(String data) {
        this.data = data;
    }

}
