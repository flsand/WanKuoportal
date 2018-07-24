package com.example.administrator.wankuoportal.ui.GuZhuWoDe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.collection.Fuwu_fragment;
import com.example.administrator.wankuoportal.fragment.collection.Fuwushang_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;


//我的收藏
public class MyCollectionActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.support.design.widget.TabLayout logintab;
    private android.support.v4.view.ViewPager viewPagerlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        this.viewPagerlogin = (ViewPager) findViewById(R.id.viewPager_login);
        this.logintab = (TabLayout) findViewById(R.id.login_tab);
        this.title = (TextView) findViewById(R.id.title);
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

            private String[] mTitles = new String[]{"服务", "服务商店铺"};
            @Override
            public Fragment getItem(int position) {

                if (position == 1) {
                    return new Fuwushang_fragment();
                }
                return new Fuwu_fragment();
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
