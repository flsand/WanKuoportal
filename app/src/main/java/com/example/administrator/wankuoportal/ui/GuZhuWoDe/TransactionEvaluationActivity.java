package com.example.administrator.wankuoportal.ui.GuZhuWoDe;

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
import com.example.administrator.wankuoportal.fragment.gzpingjia.PingJiaGZToFW_fragment;
import com.example.administrator.wankuoportal.fragment.gzpingjia.PingJiaFWToGZ_fragment;

/**
 * 雇主评价
 * */

public class TransactionEvaluationActivity extends AppCompatActivity {
    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.support.design.widget.TabLayout tabLayout;
    private android.support.v4.view.ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_evaluation);
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

            private String[] mTitles = new String[]{"我对服务商的评价", "服务商对我的评价"};

            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new PingJiaGZToFW_fragment();
                    case 1:
                        return new PingJiaFWToGZ_fragment();
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
    }
}
