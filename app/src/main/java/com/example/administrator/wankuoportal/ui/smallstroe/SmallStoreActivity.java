package com.example.administrator.wankuoportal.ui.smallstroe;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.SmallStoreAdapter;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.SmallStoreBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;
import com.flysand.mylibrary.viewHelper.ImageViewAnimationHelper;
import com.youth.banner.Banner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的万店
 */
@EActivity(R.layout.activity_small_store)
public class SmallStoreActivity extends MyBaseActivity implements RadioGroup.OnCheckedChangeListener {


    @ViewById
    Banner smallStoreBanner;
    @ViewById
    ImageView smallStoreLineView;//线
    ImageViewAnimationHelper animationHelper;
    @ViewById
    RadioGroup smallStoreRadioGroup;
    @ViewById
    RecyclerView mRecyclerView;
    @ViewById
    ScrollView smallStoreScorllView;

    SmallStoreAdapter mAdapter;

    //轮播图
    List<Integer> bannerList = new ArrayList<>();
    //商城数据
    List<SmallStoreBean> storeBeanList = new ArrayList<>();


    @AfterViews
    protected void afterViews() {
        smallStoreRadioGroup.setOnCheckedChangeListener(this);
        animationHelper = new ImageViewAnimationHelper(this, smallStoreLineView, 4, 90);
        initBanner(smallStoreBanner);
        mAdapter = new SmallStoreAdapter(this, storeBeanList);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        mRecyclerView.setAdapter(mAdapter);
        findViewById(R.id.smallStoreProductRb).performClick();
    }

    @Click({R.id.smallStoreEditTv, R.id.smallStoreOrderStatisticsTv})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.smallStoreEditTv://编辑微店
                EditSmallStoreActivity_.intent(this).start();
                break;
            case R.id.smallStoreOrderStatisticsTv://订单统计
                OrderStatisticsActivity_.intent(this).start();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.smallStoreProductRb://全部
                animationHelper.startAnimation(0);
                RequestParams params = new RequestParams();
                params.put("type", "1");
                params.put("name", "1");
                httpPost("SmallStore", params, Apis.SmallStoreUrl);
                break;
            case R.id.smallStoreRecommendedRb://推荐产品
                animationHelper.startAnimation(1);
                httpPost("SmallStore", new RequestParams(), Apis.SmallStoreRecommendUrl);
                break;
            case R.id.smallStoreShoppingCartRb://购物车
                animationHelper.startAnimation(2);
                break;
            case R.id.smallStorePersonalCenterRb://个人中心
                animationHelper.startAnimation(3);
                break;
        }
    }

    private void testData() {
        bannerList.add(R.drawable.banner11);
        bannerList.add(R.drawable.banner12);
        bannerList.add(R.drawable.banner21);
        bannerList.add(R.drawable.banner22);
        smallStoreBanner.update(bannerList);
        storeBeanList.addAll(Utils.getTestList(30, new SmallStoreBean()));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int page, int size, int count) {
        super.onHttpSuccess(type, jsonArray, page, size, count);
        if ("SmallStore".equals(type)) {
            storeBeanList.clear();
            storeBeanList.addAll(JSONUtil.toJavaBeanList(SmallStoreBean.class, jsonArray));
            mAdapter.notifyDataSetChanged();
        }
    }
}
