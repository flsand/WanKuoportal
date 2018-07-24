package com.example.administrator.wankuoportal.ui.XWGG;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.util.Utils;

public class WebActivity extends BaseActivity {

    private android.widget.ImageView back;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        this.webView = (WebView) findViewById(R.id.webView);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String url = getIntent().getStringExtra("url");

        if (TextUtils.isEmpty(url)) {
            finish();
        } else {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                Utils.print("正确url");
            } else {
                url = "http://" + url;
            }
        }
        Utils.print("url = " + url);
        WebSettings settings = webView.getSettings();
        //设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
