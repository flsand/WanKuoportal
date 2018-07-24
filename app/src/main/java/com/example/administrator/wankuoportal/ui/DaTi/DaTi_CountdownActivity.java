package com.example.administrator.wankuoportal.ui.DaTi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.QuersitionBean;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class DaTi_CountdownActivity extends BaseActivity {

    private ImageButton countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_ti__countdown);
        this.countdown = (ImageButton) findViewById(R.id.countdown);
        StatusBarUtil.setTransparent(DaTi_CountdownActivity.this);
        initHandler();
        djs();
    }

    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                sec--;
                if (sec > 0) {
                    if (sec == 2) {
                        countdown.setBackgroundResource(R.drawable.djs2);
                    } else if (sec == 1) {
                        countdown.setBackgroundResource(R.drawable.djs1);
                    } else if (sec == 3) {
                        countdown.setBackgroundResource(R.drawable.djs3);
                    }
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    Intent intent = new Intent(DaTi_CountdownActivity.this,Wzy_AnswerActivity.class);
                    QuersitionBean q = (QuersitionBean) getIntent().getSerializableExtra("QuersitionBean");
                    intent.putExtra("QuersitionBean",q);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 4;

    public void djs() {
        sec = 4;
        handler.post(runnable);
    }
}
