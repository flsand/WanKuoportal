package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.order.OrderAll_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderDaiDui_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderDaiFa_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderDaiShow_fragment;
import com.example.administrator.wankuoportal.fragment.order.OrderFinish_fragment;

public class ExchangeOrderActivity extends AppCompatActivity {
    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.support.design.widget.TabLayout tabLayout;
    private android.support.v4.view.ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_order);
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

            private String[] mTitles = new String[]{"全部", "待付款", "待发货", "待收货", "已完成"};

            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new OrderAll_fragment();
                    case 1:
                        return new OrderDaiDui_fragment();
                    case 2:
                        return new OrderDaiFa_fragment();
                    case 3:
                        return new OrderDaiShow_fragment();
                    case 4:
                        return new OrderFinish_fragment();
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
