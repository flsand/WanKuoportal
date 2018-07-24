package com.example.administrator.wankuoportal.ui.DaTi;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.QuersitionBean;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class Wzy_TZ_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wzy__tz_);
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
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    Intent intent = new Intent(Wzy_TZ_Activity.this,DaTi_CountdownActivity.class);
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
    private int sec = 2;

    public void djs() {
        sec = 2;
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
            handler = null;
        }
    }
}
