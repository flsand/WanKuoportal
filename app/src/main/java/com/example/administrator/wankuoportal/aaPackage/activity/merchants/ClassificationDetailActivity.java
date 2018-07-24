package com.example.administrator.wankuoportal.aaPackage.activity.merchants;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.fragment.merchants.ServiceFragment;
import com.example.administrator.wankuoportal.aaPackage.fragment.merchants.ServiceShopsFragment;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.global.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/24.
 *     desc    : 分类详情
 * </pre>
 */
public class ClassificationDetailActivity extends MyBaseActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;

    private String[] mTitles = new String[]{"服务", "服务商店铺"};

    ServiceFragment serviceFragment;
    ServiceShopsFragment serviceShopsFragment;

    private String labelId = "";
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_viewpager);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra(Constant.IntentKey.TITLE);
        labelId = getIntent().getStringExtra(Constant.IntentKey.LABEL_ID);
        setTitleText(title);
        initViewPager();
    }

    private void initViewPager() {

        serviceFragment = new ServiceFragment();
        serviceFragment.labelId = labelId;
        serviceShopsFragment = new ServiceShopsFragment();
        serviceShopsFragment.labelId = labelId;

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return serviceFragment;
                } else {
                    return serviceShopsFragment;
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
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
