package com.example.administrator.wankuoportal.ui.notify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.util.LogUtils;

/**
 * 消息详情(Web)
 * <p>
 * 修改时间:2018年3月2日11:48:45
 *
 * @author JakeChen
 */
public class NotifyWebDetailActivity extends AppCompatActivity {

    public static final String NOTIFY_TITLE = "title";
    public static final String NOTIFY_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_web_detail);
        initView();
    }

    /**
     * 初始化View的方法
     */
    private void initView() {
        ImageView mBack = (ImageView) findViewById(R.id.back);
        TextView mTitle = (TextView) findViewById(R.id.information_title);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebView mWebView = (WebView) findViewById(R.id.wb_notify_web_content);
        WebSettings settings = mWebView.getSettings();
        //设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new WebViewClient());
        //初始化变量的方法
        initVariables(mTitle,mWebView);
    }

    /**
     * 初始化变量的方法
     * @param mTitle
     * @param mWebView
     */
    private void initVariables(TextView mTitle, WebView mWebView) {
        Intent mIntent = getIntent();
        String mTitleValue = mIntent.getStringExtra(NOTIFY_TITLE);
        String mUrlValue = mIntent.getStringExtra(NOTIFY_URL);
        LogUtils.e("消息URL:",mUrlValue);
        mTitle.setText(mTitleValue);
        mWebView.loadUrl(mUrlValue);
    }
}
