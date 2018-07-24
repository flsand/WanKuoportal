package com.example.administrator.wankuoportal.ui.GuZhuWoDe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.jubao.FaJuBao_fragment;
import com.example.administrator.wankuoportal.fragment.jubao.ShouJuBao_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class MyReportActivity extends BaseActivity {

    private ImageView back;
    private TabLayout logintab;
    private ViewPager viewPagerlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        this.viewPagerlogin = (ViewPager) findViewById(R.id.viewPager_login);
        this.logintab = (TabLayout) findViewById(R.id.login_tab);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initview();
    }

    private void initview() {


        viewPagerlogin.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"我发起的举报", "我收到的举报"};

            @Override
            public Fragment getItem(int position) {

                if (position == 1) {
                    return new ShouJuBao_fragment();
                }
                return new FaJuBao_fragment();
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

        logintab.setupWithViewPager(viewPagerlogin);


    }
}
