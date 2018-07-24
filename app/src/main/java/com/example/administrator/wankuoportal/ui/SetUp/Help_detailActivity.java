package com.example.administrator.wankuoportal.ui.SetUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.HelpcontentAdapter;
import com.example.administrator.wankuoportal.beans.Gethelpcontent;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import okhttp3.Call;

public class Help_detailActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.ListView list;
    Gethelpcontent gethelpcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        this.list = (ListView) findViewById(R.id.list);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText(getIntent().getStringExtra("title"));
        String url = Apis.Base + Apis.gethelpcontent+getIntent().getStringExtra("id");
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        gethelpcontent = gson.fromJson(response, Gethelpcontent.class);
                        if (gethelpcontent.getCode() == 0) {
                            HelpcontentAdapter helpAdapter = new HelpcontentAdapter(gethelpcontent, Help_detailActivity.this);
                            list.setAdapter(helpAdapter);
                        }
                    }
                });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Help_detailActivity.this,QuesitionDetailActivity.class);
                it.putExtra("id",gethelpcontent.getDatas().get(position).getId()+"");
                startActivity(it);
            }
        });

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
