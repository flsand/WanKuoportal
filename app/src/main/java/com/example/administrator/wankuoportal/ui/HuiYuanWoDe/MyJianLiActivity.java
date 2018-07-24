package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.jianli.JianLiFaBu_fragment;
import com.example.administrator.wankuoportal.fragment.jianli.JianLiZanTing_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.ui.ZhaoPin.ReleaseResumeActivity;

public class MyJianLiActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.support.design.widget.TabLayout tabLayout;
    private android.support.v4.view.ViewPager viewPager;
    private ImageView fabujianli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jian_li);


        initview();

    }

    private void initview() {
        this.fabujianli = (ImageView) findViewById(R.id.fabujianli);
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
        fabujianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ReleaseResumeActivity.class);
            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"发布中", "已暂停"};

            @Override
            public Fragment getItem(int position) {

                if (position == 1) {
                    return new JianLiZanTing_fragment();
                }
                return new JianLiFaBu_fragment();

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
