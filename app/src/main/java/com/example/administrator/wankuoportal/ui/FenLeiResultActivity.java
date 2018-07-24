package com.example.administrator.wankuoportal.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class FenLeiResultActivity extends BaseActivity {

    private android.widget.FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_lei_result);
        this.framelayout = (FrameLayout) findViewById(R.id.framelayout);
    }
}
