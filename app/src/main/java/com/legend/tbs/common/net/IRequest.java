package com.legend.tbs.common.net;

import java.util.Map;

/**
 * @author Legend
 * @data by on 2018/1/3.
 * @description
 */

public interface IRequest {
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String DELETE = "DELETE";
    public static final String PUT = "PUT";

    /**
     *  请求方式
     * @param method
     */
    void setMethod(String method);

    /**
     *  指定请求头
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

    /**
     *  指定请求信息
     * @param key
     * @param value
     */
    void setBody(String key, Object value);

    /**
     * 提供给执行库请求行URL
     * @return
     */
    String getUrl();

    /**
     * 提供给执行库请求行URL
     * @return
     */
    Map<String,String> getHeader();

    /**
     *  请求体
     * @return
     */
    String getBody();

}
