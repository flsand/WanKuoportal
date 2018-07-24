package com.example.administrator.wankuoportal.ui.Store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.InitShop;
import com.example.administrator.wankuoportal.fragment.store.Store_1_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_2_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_3_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_4_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_5_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_6_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_7_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_8_fragment;
import com.example.administrator.wankuoportal.fragment.store.Store_9_fragment;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class StoreMainActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.RelativeLayout basetoolbar;
    private android.widget.TextSwitcher tvmessage;
    private com.flyco.tablayout.SlidingTabLayout SlidingTabLayout;
    private android.support.v4.view.ViewPager mViewPager;


    private List<String> topname;//顶部导航名称
    private ArrayList<Fragment> topFragments;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_main);
        this.title = (TextView) findViewById(R.id.title);
        this.mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        this.SlidingTabLayout = (com.flyco.tablayout.SlidingTabLayout) findViewById(R.id.SlidingTabLayout);
        this.basetoolbar = (RelativeLayout) findViewById(R.id.base_toolbar);
        this.tvmessage = (TextSwitcher) findViewById(R.id.tv_message);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String initshopurl = Apis.Base + Apis.initshop;
        String id = new UserService(MyApplication.context).getstoreid();
        String accountId = new UserService(MyApplication.context).getaccountid();
        OkHttpUtils
                .get()
                .url(initshopurl)
                .addParams("id", id)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);

                        InitShop initShop = gson.fromJson(response, InitShop.class);
                        if (initShop.getCode() == 0) {
                            title.setText(initShop.getData().getInfo().getShopName());
                            zidingyi = initShop.getData().getInfo().getMobMenuName();

                            if (zidingyi.isEmpty()) {
                                zidingyi = "自定义";
                            }
                            initview();
                        } else {

                            showShort("该店铺信息维护不全,请看其他店铺");
                            finish();
                        }

                    }
                });


    }


    String zidingyi = "自定义";

    private MyPagerAdapter mAdapter;

    private void initview() {
        topFragments = new ArrayList<>();
        topname = new ArrayList<>();
        topname.add("首页");
        topname.add("关于我们");
        topname.add("店铺资质");
        topname.add("产品服务");
        topname.add("精品案例");
        topname.add("店铺资讯");
        topname.add("店铺评价");
        topname.add("人才招聘");
        topname.add(zidingyi);
        topFragments.add(new Store_1_fragment());
        topFragments.add(new Store_2_fragment());
        topFragments.add(new Store_3_fragment());
        topFragments.add(new Store_4_fragment());
        topFragments.add(new Store_5_fragment());
        topFragments.add(new Store_6_fragment());
        topFragments.add(new Store_7_fragment());
        topFragments.add(new Store_8_fragment());
        topFragments.add(new Store_9_fragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        SlidingTabLayout.clearDisappearingChildren();
        SlidingTabLayout.setViewPager(mViewPager);
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return topFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return topname.get(position);

        }

        @Override
        public Fragment getItem(int position) {
//            new UserService(StoreMainActivity.this).setstoreposition(""+(position-1));
            return topFragments.get(position);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String page = new UserService(this).getstoreposition();
        if (page.equals("0")) {
            mViewPager.setCurrentItem(0);
        } else if (page.equals("1")) {
            mViewPager.setCurrentItem(1);
        } else if (page.equals("2")) {
            mViewPager.setCurrentItem(2);
        } else if (page.equals("3")) {
            mViewPager.setCurrentItem(3);
        } else if (page.equals("4")) {
            mViewPager.setCurrentItem(4);
        } else if (page.equals("5")) {
            mViewPager.setCurrentItem(5);
        } else if (page.equals("6")) {
            mViewPager.setCurrentItem(6);
        } else if (page.equals("7")) {
            mViewPager.setCurrentItem(7);
        } else if (page.equals("8")) {
            mViewPager.setCurrentItem(8);
        }
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
