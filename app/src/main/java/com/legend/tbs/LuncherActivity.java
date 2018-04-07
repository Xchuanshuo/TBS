package com.legend.tbs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.legend.tbs.common.MyWebViewClient;

import static com.legend.tbs.common.MyWebViewClient.COOKIE;
import static com.legend.tbs.common.MyWebViewClient.SKEY;


/**
 * @author Legend
 * @data by on 2018/4/6.
 * @description
 */

public class LuncherActivity extends AppCompatActivity {

    private WebView mWebView;
    private static String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        initView();

    }

    private void initView() {
        mWebView = findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.loadUrl("https://ui.ptlogin2.qq.com/cgi-bin/login?pt_hide_ad=1&style=9&pt_ttype=1&appid=549000929&pt_no_auth=1&pt_wxtest=1&daid=5&s_url=https%3A%2F%2Fh5.qzone.qq.com%2Fmqzone%2Findex\n");
        mWebView.setWebViewClient(new MyWebViewClient());


        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @SuppressLint("JavascriptInterface")
            @Override
            public void run() {
                final String skey = MyWebViewClient.getSkey();
                if (!TextUtils.isEmpty(skey)&&skey.length() > 5) {
                    // 计算skey
                    final String str = "function o() {\n" +
                            "    for (var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : \"\", e = 5381, n = 0, r = t.length; n < r; ++n)\n" +
                            "        e += (e << 5) + t.charAt(n).charCodeAt();\n" +
                            "    return 2147483647 & e\n" +
                            "}" +
                            "o('"+skey+"');";
                    mWebView.evaluateJavascript(str, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            Log.d("strtr",str+""+value);
//                            Toast.makeText(LuncherActivity.this, "结果是：" + value,
//                                    Toast.LENGTH_LONG).show();
                            token = value;
                            Intent intent = new Intent();
                            intent.setClass(LuncherActivity.this, TbsActivity.class);
                            intent.putExtra(SKEY,token);
                            intent.putExtra(COOKIE,MyWebViewClient.getCookie());

                            startActivity(intent);
                        }
                    });

                    if (!TextUtils.isEmpty(token)) {
                        handler.removeCallbacks(this);
                        Intent intent = new Intent();
                        intent.setClass(LuncherActivity.this, TbsActivity.class);
                        intent.putExtra(SKEY,token);
                        intent.putExtra(COOKIE,MyWebViewClient.getCookie());

                        startActivity(intent);
                    }
                } else {
                    handler.postDelayed(this,1000);
                }
            }
        };
        handler.postDelayed(runnable,1000);
    }

}
