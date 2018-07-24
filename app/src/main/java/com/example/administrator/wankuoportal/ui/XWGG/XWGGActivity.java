package com.example.administrator.wankuoportal.ui.XWGG;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.xwgg.GG_fragment;
import com.example.administrator.wankuoportal.fragment.xwgg.GZ_fragment;
import com.example.administrator.wankuoportal.fragment.xwgg.XW_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;

public class XWGGActivity extends BaseActivity {

    private ImageView back;
    private TextView informationtitle;
    private TabLayout logintab;
    private ViewPager viewPagerlogin;

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
        informationtitle.setText("新闻公告");
        initview();
    }


    private void initview() {


        viewPagerlogin.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"公告", "新闻", "规则"};

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return new GG_fragment();
                } else if (position == 1) {
                    return new XW_fragment();
                } else {
                    return new GZ_fragment();
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
}
