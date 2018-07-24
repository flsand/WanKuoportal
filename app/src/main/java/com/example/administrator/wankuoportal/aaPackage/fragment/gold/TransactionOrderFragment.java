package com.example.administrator.wankuoportal.aaPackage.fragment.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.flysand.mylibrary.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/14.
 *     desc    : 交易订单
 * </pre>
 */
public class TransactionOrderFragment extends MyBaseFragment {

    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    Unbinder unbinder;

    private String[] mTitles = new String[]{"我是买家", "我是卖家"};

    TransactaionBuyFragment buyFragment;
    TransactaionSellFragment sellFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Utils.print("onCreateView()");
        View rootView = inflater.inflate(R.layout.activity_table_viewpager, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        initViewPager();
        return rootView;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Utils.print("onViewStateRestored");
    }

    public void initData() {
        buyFragment.initData();
        sellFragment.initData();
    }

    private void initViewPager() {

        buyFragment = new TransactaionBuyFragment();
        sellFragment = new TransactaionSellFragment();

        mViewPager.setAdapter(new FragmentPagerAdapter(((TransactionSalaActivity) getActivity()).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return buyFragment;
                } else {
                    return sellFragment;
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

    @Override
    public void onUpdate(int var1, Intent var2) {
        try {
            buyFragment.onUpdate(var1, var2);
            sellFragment.onUpdate(var1, var2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
