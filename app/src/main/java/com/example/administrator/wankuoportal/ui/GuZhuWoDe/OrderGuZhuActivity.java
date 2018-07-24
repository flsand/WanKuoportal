package com.example.administrator.wankuoportal.ui.GuZhuWoDe;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.order.OrderGZ1_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderGZ2_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderGZ3_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderGZ4_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderGZ5_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;
/**
 * 雇主订单
 * */

public class OrderGuZhuActivity extends BaseActivity {
    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.support.design.widget.TabLayout tabLayout;
    private android.support.v4.view.ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_gu_zhu);
        initview();
    }
    private void initview() {

        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"所有订单", "待托管赏金", "进行中", "待确定付款", "待评价"};

            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new OrderGZ1_fragment();
                    case 1:
                        return new OrderGZ2_fragment();
                    case 2:
                        return new OrderGZ3_fragment();
                    case 3:
                        return new OrderGZ4_fragment();
                    case 4:
                        return new OrderGZ5_fragment();
                    default:
                        return null;
                }
            }


            @Override
            public int getCount() {
                return mTitles.length;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);

        String page = getIntent().getStringExtra("page");
        if (page.equals("0")){
            viewPager.setCurrentItem(0);
        }else if (page.equals("1")){
            viewPager.setCurrentItem(1);
        }else if (page.equals("2")){
            viewPager.setCurrentItem(2);
        }else if (page.equals("3")){
            viewPager.setCurrentItem(3);
        }else {
            viewPager.setCurrentItem(4);
        }
    }
}
