package com.legend.tbs.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Legend
 * @data by on 2018/4/6.
 * @description
 */

public class MyWebViewClient extends WebViewClient {

    public static final String SKEY = "skey";
    public static final String COOKIE = "cookie";
    private static String skey;
    private static String cookie;
    private static Context context;

    @Override
    public boolean shouldOverrideUrlLoading(WebView webview, String url) {
        webview.loadUrl(url);
        context = webview.getContext();
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookie = cookieManager.getCookie(url);
        Log.i("Cookies", "Cookies = " + cookie);
        getSkey();
        super.onPageFinished(view, url);
    }

    public static String getCookie() {
        return cookie;
    }

    // 从返回的cookie中正则提取skey
    public static String getSkey() {

        String regex = "skey=(.*?);";
        Pattern pattern = Pattern.compile(regex);
        if (!TextUtils.isEmpty(cookie)) {
            Matcher matcher = pattern.matcher(cookie);
            if (matcher.find()) {
                skey = matcher.group(1);
                Log.d("SKEYSKEY",skey);
            }
        } else {
            skey = "";
        }
        return skey;
    }
}