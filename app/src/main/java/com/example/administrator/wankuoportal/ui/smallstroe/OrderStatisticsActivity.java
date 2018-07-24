package com.example.administrator.wankuoportal.ui.smallstroe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.base.MyBaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 订单统计
 */
@EActivity(R.layout.activity_order_statistics)
public class OrderStatisticsActivity extends MyBaseActivity implements RadioGroup.OnCheckedChangeListener {

    @ViewById
    RadioGroup orderStatisticsRadioGroup;
    @ViewById
    ImageView orderStatisticsSpan1;
    @ViewById
    ImageView orderStatisticsSpan2;
    @ViewById
    ImageView orderStatisticsSpan3;
    @ViewById
    ImageView orderStatisticsSpan4;
    @ViewById
    TextView orderStatisticsDateTv;//时间
    @ViewById
    TextView orderStatisticsDateNumTv;//几天
    @ViewById
    TextView orderStatisticsLeftNumTv;//左边数量
    @ViewById
    TextView orderStatisticsLeftTipTv;
    @ViewById
    TextView orderStatisticsRightNumTv;//右边数量
    @ViewById
    TextView orderStatisticsRightTipTv;


    @AfterViews
    protected void afterViews() {
        super.afterViews();
        orderStatisticsRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.orderStatisticsOrderNumRb://订单数
                switchSpan(0);
                break;
            case R.id.orderStatisticsSalesVolumeRb://销售额
                switchSpan(1);
                break;
            case R.id.orderStatisticsVermicelliRb://粉丝数
                switchSpan(2);
                break;
            case R.id.orderStatisticsCallerNumRb://访客数
                switchSpan(3);
                break;
        }
    }

    private void switchSpan(int index) {
        invisibleSpan();
        int color = getResources().getColor(R.color.order_orange_color);
        switch (index) {
            case 0:
                orderStatisticsSpan1.setVisibility(View.VISIBLE);
                break;
            case 1:
                orderStatisticsSpan2.setVisibility(View.VISIBLE);
                color = getResources().getColor(R.color.order_red_color);
                break;
            case 2:
                orderStatisticsSpan3.setVisibility(View.VISIBLE);
                color = getResources().getColor(R.color.order_green_color);
                break;
            case 3:
                orderStatisticsSpan4.setVisibility(View.VISIBLE);
                color = getResources().getColor(R.color.order_blue_color);
                break;
            default:
                break;
        }
        orderStatisticsLeftNumTv.setTextColor(color);
        orderStatisticsRightNumTv.setTextColor(color);
    }

    private void invisibleSpan() {
        orderStatisticsSpan1.setVisibility(View.INVISIBLE);
        orderStatisticsSpan2.setVisibility(View.INVISIBLE);
        orderStatisticsSpan3.setVisibility(View.INVISIBLE);
        orderStatisticsSpan4.setVisibility(View.INVISIBLE);
    }
}
