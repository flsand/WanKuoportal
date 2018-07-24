package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
/**
 * 账户激活
 * */

public class ZhangHuJiHuoActivity extends AppCompatActivity {

    private TextView jihuo_sendcode;
    private android.widget.ImageView back;
    private TextView informationtitle;
    private TextView textView3;
    private TextView jihuosendcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_hu_ji_huo);
        this.jihuosendcode = (TextView) findViewById(R.id.jihuo_sendcode);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        jihuo_sendcode = (TextView) findViewById(R.id.jihuo_sendcode);
        initHandler();
        jihuo_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                djs();
            }
        });
    }

    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                jihuo_sendcode.setText(sec + "");
                sec--;
                if (sec > 0) {
                    jihuo_sendcode.setEnabled(false);
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    jihuo_sendcode.setText("获取验证码");
                    jihuo_sendcode.setEnabled(true);
                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 60;

    public void djs() {
        sec = 60;
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
