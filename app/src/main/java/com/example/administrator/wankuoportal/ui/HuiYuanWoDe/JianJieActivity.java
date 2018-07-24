package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Getnavigation;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.ui.SetUp.TenXinTiaoActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import okhttp3.Call;

public class JianJieActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.TextView text;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_jie);
        this.webView = (WebView) findViewById(R.id.webView);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.text = (TextView) findViewById(R.id.text);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webView.loadUrl(Apis.Base + Apis.getnavigation+"?name=平台简介");
        //设置Web视图
        webView.setWebViewClient(new JianJieActivity.webViewClient());

//        String url = Apis.Base + Apis.getnavigation;
//        OkHttpUtils
//                .get()
//                .url(url)
//                .build()
//                .execute(new MyStringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onHttpResponse(String response, int id) throws Exception {
//                        L.d(response);
//                        Getnavigation baseResult = gson.fromJson(response, Getnavigation.class);
//                        if (baseResult.getCode() == 0) {
//                            text.setText(Html.fromHtml(baseResult.getData()));
//                        } else {
//                            showShort(baseResult.getMsg());
//                        }
//
//                    }
//                });
    }
    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
