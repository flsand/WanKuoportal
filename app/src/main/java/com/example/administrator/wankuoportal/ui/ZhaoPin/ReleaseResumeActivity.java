package com.example.administrator.wankuoportal.ui.ZhaoPin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;


/**
 * 发布简历界面  2017-09-16  by lv
 */

public class ReleaseResumeActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.LinearLayout jibenqingkuang;
    private android.widget.LinearLayout jiaoyuqingkuang;
    private android.widget.LinearLayout gongzuoqingkuang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_resume);
        this.gongzuoqingkuang = (LinearLayout) findViewById(R.id.gongzuoqingkuang);
        this.jiaoyuqingkuang = (LinearLayout) findViewById(R.id.jiaoyuqingkuang);
        this.jibenqingkuang = (LinearLayout) findViewById(R.id.jibenqingkuang);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        jibenqingkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gerenxinxiActivity.class);
            }
        });
        gongzuoqingkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GongzuoChooseActivity.class);
            }
        });
        jiaoyuqingkuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(jiaoyuActivity.class);
            }
        });

    }
}
