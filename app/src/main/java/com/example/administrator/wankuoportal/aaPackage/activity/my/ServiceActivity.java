package com.example.administrator.wankuoportal.aaPackage.activity.my;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.ui.KaiDianActivity;
import com.example.administrator.wankuoportal.util.ProjectUtil;

public class ServiceActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.invoice_layout:
            case R.id.my_report_layout:
            case R.id.my_refund_layout:
            case R.id.my_activist_layout:
            case R.id.my_all_order_layout:
            case R.id.my_evaluation_layout:
                ProjectUtil.showDevelopmentMessage();
                break;
            case R.id.download_layout:
                startActivity(KaiDianActivity.class);
                break;
        }
    }
}
