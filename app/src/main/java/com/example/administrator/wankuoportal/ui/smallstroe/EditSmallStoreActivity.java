package com.example.administrator.wankuoportal.ui.smallstroe;

import android.view.View;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * 编辑微店
 */
@EActivity(R.layout.activity_edit_small_store)
public class EditSmallStoreActivity extends MyBaseActivity {

    @AfterViews
    protected void afterViews() {
        super.afterViews();

    }

    @Click({R.id.addGoodsTv, R.id.addRecommendTv, R.id.commodityManagementTv})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.addGoodsTv://添加平台商品
                break;
            case R.id.addRecommendTv://添加店主推荐
                break;
            case R.id.commodityManagementTv://商品管理
                PlatformGoodsActivity_.intent(this).start();
                break;
        }
    }
}
