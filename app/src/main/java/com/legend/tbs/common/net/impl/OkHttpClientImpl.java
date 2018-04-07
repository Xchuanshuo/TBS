package com.legend.tbs.common.net.impl;


import com.legend.tbs.common.net.IHttpClient;
import com.legend.tbs.common.net.IRequest;
import com.legend.tbs.common.net.IResponse;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * @author Legend
 * @data by on 2017/11/23.
 * @description
 */

public class OkHttpClientImpl implements IHttpClient {
    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .build();

    @Override
    public IResponse get(IRequest request) {
        /**
         *  解析业务参数
         */
        // 指定请求方式
        request.setMethod(IRequest.GET);
        Map<String,String> header = request.getHeader();

        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key,header.get(key));
        }
        builder.url(request.getUrl()
        ).get();
        Request okRequest = builder.build();
        return execute(okRequest);
    }

    @Override
    public IResponse post(IRequest request) {
        request.setMethod(IRequest.POST);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType,request.getBody().toString());

        Map<String,String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key,header.get(key));
        }
        builder.url(request.getUrl())
                .post(requestBody);
        Request okRequest = builder.build();
        return execute(okRequest);
    }

    @Override
    public IResponse upload_image_post(IRequest request,Map<String,Object> map,File file) {
        /**
         *  实现文件上传
         */
        request.setMethod(IRequest.POST);
        MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");
        MultipartBody.Builder requestBody = new MultipartBody
                .Builder().setType(MultipartBody.FORM);
        if (file != null) {
            requestBody.addFormDataPart("image",file.getName(),
                    RequestBody.create(MEDIA_TYPE_IMAGE,file));
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Map<String,String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key,header.get(key));
        }
        builder.url(request.getUrl())
                .post(requestBody.build());
        Request okRequest = builder.build();
        return execute(okRequest);
    }

    @Override
    public IResponse delete(IRequest request) {
        request.setMethod(IRequest.DELETE);
        Map<String,String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key,header.get(key));
        }
        builder.url(request.getUrl())
                .delete(null);
        Request okRequest = builder.build();
        return execute(okRequest);
    }

    @Override
    public IResponse put(IRequest request) {
        request.setMethod(IRequest.PUT);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType,request.getBody().toString());

        Map<String,String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key : header.keySet()) {
            builder.header(key,header.get(key));
        }
        builder.url(request.getUrl())
                .put(requestBody);
        Request okRequest = builder.build();
        return execute(okRequest);
    }

    private IResponse execute(Request request) {
        ResponseImpl commonResponse = new ResponseImpl();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            // 设置状态码
            commonResponse.setCode(response.code());
            String body = response.body().string();
            // 设置响应数据
            commonResponse.setData(body);
        } catch (IOException e) {
            e.printStackTrace();
            commonResponse.setCode(ResponseImpl.STATE_UNKNOW_ERROR);
            commonResponse.setData(e.getMessage());
        }
        return commonResponse;
    }
}
