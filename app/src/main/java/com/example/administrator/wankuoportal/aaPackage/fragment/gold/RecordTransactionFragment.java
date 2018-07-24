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
import com.example.administrator.wankuoportal.aaPackage.adapter.RecordTransactionAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.OrderBuyBean;
import com.example.administrator.wankuoportal.aaPackage.bean.RecordBean;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

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
 *     desc    : 统计记录
 * </pre>
 */
public class RecordTransactionFragment extends MyBaseFragment implements RecyclerOnScrollListener.OnScrollListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.no_data_tv)
    TextView noDataTv;
    Unbinder unbinder;

    RecordTransactionAdapter mAdapter;

    List<OrderBuyBean> beanList = new ArrayList<>();
    @BindView(R.id.all_sell_gold_tv)
    TextView allSellGoldTv;
    @BindView(R.id.all_buy_gold_tv)
    TextView allBuyGoldTv;
    @BindView(R.id.in_money_tv)
    TextView inMoneyTv;
    @BindView(R.id.out_money_tv)
    TextView outMoneyTv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transaction_record, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        loadData();
        return rootView;
    }

    private void initView() {
        ProjectUtil.initRecyclerView(mRecyclerView, this);
//        beanList.addAll(Utils.getTestList(20, new OrderBuyBean()));
        mAdapter = new RecordTransactionAdapter(this, beanList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getReportData();
    }

    private void loadData() {
        if (pagingHelper.isLoading)
            return;
        pagingHelper.setLoading();
        RequestParams params = new RequestParams();
        params.put("size", Define.PAGE_SIZE);
        params.put("type", 0);
        params.put("number", pagingHelper.page);
        httpPost("getRecordsList", params, Apis.Transaction.getDealInfo);
    }

    public void getReportData() {
        httpPostNoDialog("getDealReport", new RequestParams(), Apis.Transaction.getDealReport);
    }

    @Override
    public void onUpdate(int var1, Intent var2) {
        if (var1 == Constant.UpdateCode.BUY_SUCCESS || var1 == Constant.UpdateCode.SELL_SUCCESS) {
            getReportData();
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
//        if (pagingHelper.canLoadMore)
//            loadData();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) throws Exception {
        super.onHttpSuccess(type, datas, pageInfoBean);
        if ("getRecordsList".equals(type)) {

            if (pageInfoBean.isFirst())
                beanList.clear();

            beanList.addAll(JSONUtil.toJavaBeanList(OrderBuyBean.class, datas));
            mAdapter.notifyDataSetChanged();

            pagingHelper.loadCompleted();
            pagingHelper.canLoadMore = !pageInfoBean.isLast();
            noDataTv.setVisibility(beanList.size() == 0 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        if ("getRecordsList".equals(type)) {
            dismissHttpDialog();
            noDataTv.setVisibility(beanList.size() == 0 ? View.VISIBLE : View.GONE);
        }
//        super.onHttpFailure(type, s1);
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        if ("getDealReport".equals(type)) {
            RecordBean bean = JSONUtil.toJavaBean(RecordBean.class, jsonObject.getString("dealReports"));

            allBuyGoldTv.setText(Utils.replaceDoubleZero(bean.getBuyGold() + ""));
            allSellGoldTv.setText(Utils.replaceDoubleZero(bean.getSellGold() + ""));
            inMoneyTv.setText(Utils.replaceDoubleZero(bean.getInMoney() + ""));
            outMoneyTv.setText(Utils.replaceDoubleZero(bean.getOutMoney() + ""));
        }
    }
}
