package com.example.administrator.wankuoportal.ui.XWGG;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;

public class XinXiDetailsActivity extends BaseActivity {

    private ImageView back;
    private WebView webView;
    private android.widget.TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        this.title = (TextView) findViewById(R.id.title);
        this.webView = (WebView) findViewById(R.id.webView);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String type = getIntent().getStringExtra("type");
        title.setText(getIntent().getStringExtra("title"));
        WebSettings settings = webView.getSettings();
        //设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient());
        L.d(type);
        if (type.equals("0")) {
            String url = getIntent().getStringExtra("linkurl");
            L.d(url);
            webView.loadUrl(url);
        } else {
            String content = getIntent().getStringExtra("content");
            L.d(content);
            webView.getSettings().setDefaultTextEncodingName("UTF-8");
            webView.loadData(content, "text/html", "UTF-8");
        }
    }
}
