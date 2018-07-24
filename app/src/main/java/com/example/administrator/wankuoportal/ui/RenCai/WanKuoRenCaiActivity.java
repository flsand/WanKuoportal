package com.example.administrator.wankuoportal.ui.RenCai;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class WanKuoRenCaiActivity extends BaseActivity {

    private ImageView back;
    private TextView title;
    private ListView list;
    private ListView manlist;
    private TextView yaoqingmianshi;
    private TextView textView6;
    private android.widget.LinearLayout callphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_detail);
        this.callphone = (LinearLayout) findViewById(R.id.callphone);
        this.textView6 = (TextView) findViewById(R.id.textView6);
        this.yaoqingmianshi = (TextView) findViewById(R.id.yaoqingmianshi);
        this.manlist = (ListView) findViewById(R.id.man_list);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //邀请面试点击
        yaoqingmianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MianShiYaoQingActivity.class);
            }
        });
    }
}
