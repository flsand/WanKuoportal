package com.example.administrator.wankuoportal.ui.CityHeHuo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.citypartner.CityPartner_ima_fragment;
import com.example.administrator.wankuoportal.fragment.citypartner.CityPartner_login_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;

import butterknife.ButterKnife;

public class CityPartnerActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.support.design.widget.TabLayout logintab;
    private android.support.v4.view.ViewPager viewPagerlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_partner);

        this.viewPagerlogin = (ViewPager) findViewById(R.id.viewPager_login);
        this.logintab = (TabLayout) findViewById(R.id.login_tab);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
    }


    private void initView() {
        viewPagerlogin.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"合伙收益", "申请加入"};

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new CityPartner_ima_fragment();
                } else {
                    return new CityPartner_login_fragment();
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

        logintab.setupWithViewPager(viewPagerlogin);
    }

    public void gotoPartnerLogin() {
        viewPagerlogin.setCurrentItem(1);
    }
}
