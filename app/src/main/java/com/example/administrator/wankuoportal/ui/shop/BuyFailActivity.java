package com.example.administrator.wankuoportal.ui.shop;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.global.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyFailActivity extends MyBaseActivity {

    @BindView(R.id.cause_failure_tv)
    TextView causeFailureTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_fail);
        ButterKnife.bind(this);
        try {
            causeFailureTv.setText(getIntent().getStringExtra(Constant.IntentKey.STRING));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.bug_fail_back_btn)
    public void onViewClicked() {
        finish();
    }
}
