package com.example.administrator.wankuoportal.aaPackage.fragment.merchants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.ServiceAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.ServiceBean;
import com.example.administrator.wankuoportal.base.MyV4BaseFragment;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.customView.MyRadioGroup;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/24.
 *     desc    : 服务
 * </pre>
 */
public class ServiceFragment extends MyV4BaseFragment implements RecyclerOnScrollListener.OnScrollListener, MyRadioGroup.OnCheckedChangeListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.fegment_service_radioGroup)
    MyRadioGroup radioGroup;

    @BindView(R.id.price_rb)
    RadioButton priceRb;

    @BindView(R.id.price_select_iv)
    ImageView priceSelectIv;

    @BindView(R.id.nodata_iv)
    ImageView nodataIv;
    Unbinder unbinder;


    private String sort = null;
    public String labelId = "";
    public String priceSort = "2";

    List<ServiceBean> list = new ArrayList<>();

    ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        unbinder = ButterKnife.bind(this, view);
        radioGroup.setOnCheckedChangeListener(this);
        serviceAdapter = new ServiceAdapter(this, list);
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
        pagingHelper.setLoading();
        RequestParams params = new RequestParams();
        params.put("page", pagingHelper.page);
        params.put("labelId", labelId);
        if (!TextUtils.isEmpty(sort))
            params.put("sort", sort);
        httpGet("getGood", params, Apis.getGood);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.price_rb://价格
                return;
            case R.id.comprehensive_tv://综合排序
                initData();
                priceSelectIv.setImageResource(R.drawable.shangxia);
                sort = "1";
                break;
            case R.id.sales_tv://销量优先
                initData();
                priceSelectIv.setImageResource(R.drawable.shangxia);
                sort = "4";
                break;
        }
        getNetworkData();
    }

    @OnClick({R.id.price_rb})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.price_rb:
                initData();
                Utils.print("R.id.price_rb");
                if ("2".equals(priceSort)) {
                    priceSort = "3";
                    priceSelectIv.setImageResource(R.drawable.shang);
                } else {
                    priceSort = "2";
                    priceSelectIv.setImageResource(R.drawable.xia);
                }
                sort = priceSort;
                getNetworkData();
                break;
        }
    }


    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        super.onHttpFailure(type, s1);
        nodataIv.setVisibility(View.VISIBLE);
        pagingHelper.loadCompleted();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {
        super.onHttpSuccess(type, datas, pageInfoBean);
        try {
            if ("getGood".equals(type)) {
                pagingHelper.loadCompleted();
                if (datas.length() > 0) {
                    list.addAll(JSONUtil.toJavaBeanList(ServiceBean.class, datas));
                    nodataIv.setVisibility(View.GONE);
                } else {
                    list.clear();
                    nodataIv.setVisibility(View.VISIBLE);
                }
                serviceAdapter.notifyDataSetChanged();
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
