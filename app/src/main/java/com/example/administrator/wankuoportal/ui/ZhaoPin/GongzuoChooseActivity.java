package com.example.administrator.wankuoportal.ui.ZhaoPin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecruitmentAdapter;
import com.example.administrator.wankuoportal.global.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class GongzuoChooseActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.ImageView add;
    private android.widget.ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongzuo_choose);
        this.list = (ListView) findViewById(R.id.list);
        this.add = (ImageView) findViewById(R.id.add);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> listxinzi = new ArrayList<String>();
        listxinzi.add("销售");
        listxinzi.add("客服");
        RecruitmentAdapter r = new RecruitmentAdapter(listxinzi, GongzuoChooseActivity.this);
        list.setAdapter(r);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gongzuoActivity.class);
            }
        });


    }
}
