package com.legend.tbs.common.net;

import java.io.File;
import java.util.Map;

/**
 * @author Legend
 * @data by on 2018/1/3.
 * @description
 */

public interface IHttpClient {

    IResponse get(IRequest request);

    /**
     *  json格式的post
     * @param request
     * @return
     */
    IResponse post(IRequest request);

    /**
     *  表单类型的post
     * @param request
     * @param map
     * @param file
     * @return
     */
    IResponse upload_image_post(IRequest request, Map<String, Object> map, File file);
    IResponse delete(IRequest request);
    IResponse put(IRequest request);
}
