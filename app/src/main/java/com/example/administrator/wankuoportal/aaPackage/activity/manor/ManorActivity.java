package com.example.administrator.wankuoportal.aaPackage.activity.manor;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 万阔庄园
 */
public class ManorActivity extends MyBaseActivity {

    @BindView(R.id.manor_title_tv)
    TextView manorTitleTv;
    @BindView(R.id.manor_title_layout)
    RelativeLayout titleLayout;
    @BindView(R.id.manor_buyer_layout)
    RelativeLayout buyerLayout;
    @BindView(R.id.manor_back_layout)
    LinearLayout manorBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ProjectUtil.setTransparent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manor);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        manorTitleTv.setText(getTitle());
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams titleLayoutParams = (LinearLayout.LayoutParams) titleLayout.getLayoutParams();
            titleLayoutParams.setMargins(0, com.flysand.mylibrary.util.Utils.getStatusHeight(this), 0, 0);
            LinearLayout.LayoutParams manorBackLayoutParams = (LinearLayout.LayoutParams) manorBackLayout.getLayoutParams();
            manorBackLayoutParams.setMargins(0, 0, 0, ProjectUtil.getBottomStatusHeight(this));
        } else {
            LinearLayout.LayoutParams buyerLayoutParams = (LinearLayout.LayoutParams) buyerLayout.getLayoutParams();
            buyerLayoutParams.setMargins(com.flysand.mylibrary.util.Utils.dip2px(this, 30), com.flysand.mylibrary.util.Utils.dip2px(this, 200), com.flysand.mylibrary.util.Utils.dip2px(this, 30), 0);
        }
    }

    @Override
    protected boolean showTitle() {
        return false;
    }

    @OnClick({R.id.manor_back_iv, R.id.manor_buyer_layout, R.id.manor_owner_layout, R.id.manor_back_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manor_back_iv:
            case R.id.manor_back_layout:
                finish();
                break;
            case R.id.manor_buyer_layout:
                Utils.print("我是买家");
                startActivity(ManorHomeActivity.class);
                break;
            case R.id.manor_owner_layout:
                Utils.print("我是庄园主");
                break;
        }
    }
}
