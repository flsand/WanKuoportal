package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class PushSetupActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_setup);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
