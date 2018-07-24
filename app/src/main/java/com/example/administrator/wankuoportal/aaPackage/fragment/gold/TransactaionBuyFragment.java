package com.example.administrator.wankuoportal.aaPackage.fragment.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.TransactionOrderBuyAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.OrderBuyBean;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.base.MyV4BaseFragment;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
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
 *     time    : 2018/6/15.
 *     desc    :
 * </pre>
 */
public class TransactaionBuyFragment extends MyV4BaseFragment implements RecyclerOnScrollListener.OnScrollListener {


    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.no_data_tv)
    TextView noDataTv;

    Unbinder unbinder;

    TransactionOrderBuyAdapter mAdapter;
    List<OrderBuyBean> beanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Utils.print("onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_transaction_buy_layout, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    private void initView() {
        ProjectUtil.initRecyclerView(mRecyclerView, this);

        mAdapter = new TransactionOrderBuyAdapter(getActivity(), beanList);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        com.flysand.mylibrary.util.Utils.print("onViewStateRestored");
    }

    public void initData(){
        try {
            pagingHelper.init();
            loadData();
            if (noDataTv != null)
            noDataTv.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        if (pagingHelper.isLoading)
            return;
        pagingHelper.setLoading();

        RequestParams params = new RequestParams();
        params.put("number", pagingHelper.page);
        params.put("size", Define.PAGE_SIZE);
        params.put("type", 1);
        httpPostNoDialog("orderBuy", params, Apis.Transaction.getDealInfo);
    }

    @Override
    public void onUpdate(int var1, Intent var2) {
        if (Constant.UpdateCode.BUY_SUCCESS == var1) {
            pagingHelper.init();
            loadData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadNextPage(View view) {
        if (pagingHelper.canLoadMore)
            loadData();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {
        super.onHttpSuccess(type, datas, pageInfoBean);
        if ("orderBuy".equals(type)) {
            if (pageInfoBean.isFirst())
                beanList.clear();
            beanList.addAll(JSONUtil.toJavaBeanList(OrderBuyBean.class, datas));
            pagingHelper.loadCompleted();
            pagingHelper.canLoadMore = !pageInfoBean.isLast();
            mAdapter.notifyDataSetChanged();
            noDataTv.setVisibility(beanList.size() == 0 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
//        super.onHttpFailure(type, s1);
        if ("orderBuy".equals(type)) {
            dismissHttpDialog();
            pagingHelper.loadFailure();
            noDataTv.setVisibility(beanList.size() == 0 ? View.VISIBLE : View.GONE);
            return;
        }
    }
}
