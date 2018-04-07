package com.legend.tbs.fragment;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.legend.tbs.common.model.TbsBean;
import com.legend.tbs.common.net.IHttpClient;
import com.legend.tbs.common.net.IRequest;
import com.legend.tbs.common.net.IResponse;
import com.legend.tbs.common.net.impl.OkHttpClientImpl;
import com.legend.tbs.common.net.impl.RequestImpl;
import com.legend.tbs.contract.BaseContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.legend.tbs.common.adapter.TbsAdapter.GET_NICK;

/**
 * @author Legend
 * @data by on 2018/4/5.
 * @description
 */

public class BaseModel implements BaseContract.Model {

    public static final int RESULT_OK = 200;
    private BaseContract.Callback<TbsBean> callback;
    private BaseContract.Callback<String> stringCallback;
    private int type;
    private final String regex = "\"(.*?)\"";
    private List<String> list = new ArrayList<>();
    private static String cookie="";
    public static int finished = 0;

    @Override
    public void Request(String url, String token,String cookie, int type, BaseContract.Callback callback) {
        this.callback = callback;
        this.type = type;
        new TbsAsyncTask().execute(url,token,cookie);
    }



    class TbsAsyncTask extends AsyncTask<String,Void,IResponse> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callback.onStart();
        }

        @Override
        protected IResponse doInBackground(String... strings) {
            IRequest request;
            if (type == 0) {
                request = new RequestImpl(strings[0]+strings[1]);
            } else {
                request = new RequestImpl(strings[0]+strings[1]+"&cookie="+cookie);
            }
            request.setHeader("cookie",strings[2]);
            IHttpClient mHttpClient = new OkHttpClientImpl();
            IResponse response = mHttpClient.get(request);
            Log.d("ResponseData",response.getData().toString());
            return response;
        }

        @Override
        protected void onPostExecute(IResponse iResponse) {
            super.onPostExecute(iResponse);
            Gson gson = new Gson();
            if (iResponse.getCode() == RESULT_OK) {
                String result = iResponse.getData().toString();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String data = jsonObject.getString("data");
                    JSONObject jsonObject1 = new JSONObject(data);
                    cookie = jsonObject1.getString("cookie");
                    finished = Integer.parseInt(jsonObject1.getString("finish"));
                    JSONArray jsonArray;
                    synchronized (this) {
                        if (type == 0) {
                            jsonArray = jsonObject1.getJSONArray("list");
                        } else {
                            jsonArray = jsonObject1.getJSONArray("confesses");
                        }
                    }
                    List<TbsBean> tbsBeanList =
                            gson.fromJson(jsonArray.toString(), new TypeToken<List<TbsBean>>() {
                            }.getType());
                    Log.d("ResponseData",tbsBeanList.toString());
                    callback.onSuccess(tbsBeanList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                callback.onFailure();
            }
            callback.onFinished();
        }
    }
}
