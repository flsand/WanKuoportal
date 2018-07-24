package com.example.administrator.wankuoportal.ui.YXXY;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.MarketingAdapter;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.MarketingBean;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.adapter.HeaderAndFooterWrapper;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.JSONUtil;
import com.youth.banner.Banner;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 营销学院
 */
public class MarketingActivity extends MyBaseActivity implements RecyclerOnScrollListener.OnScrollListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    List<MarketingBean> beanList = new ArrayList<>();
    HeaderAndFooterWrapper mWrapperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing);
        ButterKnife.bind(this);
        afterViews();
        initData();
    }

    protected void afterViews() {
        MarketingAdapter marketingAdapter = new MarketingAdapter(this, beanList);
        mWrapperAdapter = new HeaderAndFooterWrapper(marketingAdapter);
        Banner bannerView = (Banner) LayoutInflater.from(this).inflate(R.layout.banner_layout, new LinearLayout(this), false);
        bannerView.setImages((List<Integer>) Arrays.asList(R.drawable.dbl, R.drawable.dl));
        initBanner(bannerView);
        mWrapperAdapter.addHeaderView(bannerView);
        mRecyclerView.setAdapter(mWrapperAdapter);

        ProjectUtil.initRecyclerView(mRecyclerView, this);
    }

    private void initData() {
        pagingHelper.init();
        loadData();
    }

    private void loadData() {
        if (pagingHelper.isLoading)
            return;
        RequestParams params = new RequestParams();
        params.put("type", "1");
        params.put("pages", pagingHelper.page);
        httpGet("marketingData", params, Apis.second);
        pagingHelper.setLoading();
    }

    @Override
    public void onLoadNextPage(View view) {
        if (pagingHelper.canLoadMore)
            loadData();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean)throws Exception {
        if ("marketingData".equals(type)) {
            beanList.addAll(JSONUtil.toJavaBeanList(MarketingBean.class, datas));
            mWrapperAdapter.notifyDataSetChanged();
            pagingHelper.canLoadMore = !pageInfoBean.isLast();
            pagingHelper.loadCompleted();

        }
        super.onHttpSuccess(type, datas, pageInfoBean);
    }
}
