package com.example.administrator.wankuoportal.ui.SetUp;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;

public class QuesitionDetailActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private WebView helpAnswerApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesition_detail);
        this.helpAnswerApp = (WebView) findViewById(R.id.helpAnswerApp);

        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);

        WebSettings settings = helpAnswerApp.getSettings();
        //设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        String id = getIntent().getStringExtra("id");
        L.d(Apis.Base+"/api/helpcenter/gethelpcontentanswer?contentid="+id);
        helpAnswerApp.loadUrl(Apis.Base+"/api/helpcenter/gethelpcontentanswer?contentid="+id);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
