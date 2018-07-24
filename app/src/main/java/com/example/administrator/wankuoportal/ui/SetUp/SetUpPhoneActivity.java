package com.example.administrator.wankuoportal.ui.SetUp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;

/**
 * 设置手机号
 */

public class SetUpPhoneActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private TextView changphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_phone);
        this.changphone = (TextView) findViewById(R.id.changphone);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SetUpNewPhoneActivity.class);
            }
        });
    }
}
