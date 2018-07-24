package com.example.administrator.wankuoportal.ui.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.fragment.search.Search_fuwu_fragment;
import com.example.administrator.wankuoportal.fragment.search.Search_fuwushang_fragment;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.flysand.mylibrary.util.MyHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResult_Activity extends BaseActivity {

    private android.widget.ImageView back;
    private android.support.design.widget.TabLayout logintab;
    private android.support.v4.view.ViewPager viewPagerlogin;
    @BindView(R.id.tv_search_result)
    TextView tv_search_result;
    @BindView(R.id.ll_activity_result)
    LinearLayout ll_activity_result;
    private String labelId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_);
        ButterKnife.bind(this);
        this.viewPagerlogin = (ViewPager) findViewById(R.id.viewPager_login);
        this.logintab = (TabLayout) findViewById(R.id.login_tab);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        labelId = getIntent().getStringExtra(Constant.IntentKey.LABEL_ID);
        initView();
        new MyHandler(2000) {
            @Override
            public void run() {
                finish();
            }
        };
    }

    private void initView() {
        viewPagerlogin.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"服务", "服务商店铺"};

            @Override
            public Fragment getItem(int position) {

                if (position == 1) {
                    Search_fuwushang_fragment search_fuwushang_fragment = new Search_fuwushang_fragment();
                    search_fuwushang_fragment.labelId = labelId;
                    return search_fuwushang_fragment;
                }
                Search_fuwu_fragment searchFuwuFragment = new Search_fuwu_fragment();
                searchFuwuFragment.labelId = labelId;
                return searchFuwuFragment;
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
        String type = getIntent().getStringExtra("type");
        if (type.equals("0")) {
            viewPagerlogin.setCurrentItem(0);
        } else {
            viewPagerlogin.setCurrentItem(1);
        }

        String search = getIntent().getExtras().getString("search");
        if (search != null) {
            tv_search_result.setText(search);
        }

        ll_activity_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
