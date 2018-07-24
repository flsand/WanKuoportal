package com.example.administrator.wankuoportal.aaPackage.fragment.merchants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.ServiceShopAdapter;
import com.example.administrator.wankuoportal.base.MyV4BaseFragment;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.beans.ServiceShopsBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/24.
 *     desc    :
 * </pre>
 */
public class ServiceShopsFragment extends MyV4BaseFragment implements RadioGroup.OnCheckedChangeListener, RecyclerOnScrollListener.OnScrollListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fegment_service_radioGroup)
    RadioGroup radioGroup;

    @BindView(R.id.nodata_iv)
    ImageView nodataIv;
    Unbinder unbinder;


    private String sort = null;
    public String labelId = "";

    List<ServiceShopsBean> list = new ArrayList<>();

    ServiceShopAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_shops, container, false);
        unbinder = ButterKnife.bind(this, view);
        radioGroup.setOnCheckedChangeListener(this);
        serviceAdapter = new ServiceShopAdapter(this, list);
        ProjectUtil.initRecyclerView(mRecyclerView, this);
        mRecyclerView.setAdapter(serviceAdapter);
        initData();
        getNetworkData();
        return view;
    }

    public void initData() {
        list.clear();
        serviceAdapter.notifyDataSetChanged();
        pagingHelper.init();
        sort = null;
    }

    private void getNetworkData() {
        if (pagingHelper.isLoading)
            return;
        pagingHelper.isLoading = true;
        RequestParams params = new RequestParams();
        params.put("page", pagingHelper.page);
        params.put("labelId", labelId);
        if (!TextUtils.isEmpty(sort))
            params.put("sort", sort);
        httpGet("getShop", params, Apis.getShop);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        initData();
        switch (checkedId) {
            case R.id.comprehensive_tv://综合排序
                sort = "1";
                break;
            case R.id.rating_tv://好评度
                sort = "3";
                break;
            case R.id.sales_tv://销量优先
                sort = "2";
                break;
            case R.id.level_tv://商家等级
                break;
        }
        getNetworkData();
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        super.onHttpFailure(type, s1);
        nodataIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {
        super.onHttpSuccess(type, datas, pageInfoBean);
        try {
            if ("getShop".equals(type)) {
                if (datas.length() > 0) {
                    list.addAll(JSONUtil.toJavaBeanList(ServiceShopsBean.class, datas));
                    nodataIv.setVisibility(View.GONE);
                } else {
                    list.clear();
                    nodataIv.setVisibility(View.VISIBLE);
                }
                serviceAdapter.notifyDataSetChanged();
                pagingHelper.loadCompleted();
                pagingHelper.canLoadMore = !pageInfoBean.isLast();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoadNextPage(View view) {
        if (pagingHelper.canLoadMore)
            getNetworkData();
    }
}
