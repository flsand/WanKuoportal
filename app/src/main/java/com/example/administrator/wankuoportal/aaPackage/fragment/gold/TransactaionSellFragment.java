package com.example.administrator.wankuoportal.aaPackage.fragment.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.aaPackage.adapter.TransactionOrderBuyAdapter;
import com.example.administrator.wankuoportal.aaPackage.adapter.TransactionOrderSellAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.OrderBuyBean;
import com.example.administrator.wankuoportal.aaPackage.bean.SellGoldBean;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.ChangeSellGoldDialog;
import com.example.administrator.wankuoportal.base.MyV4BaseFragment;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.PagingHelper;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.OnAdapterBtnClickListener;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.JSONUtil;

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
 *     desc    :
 * </pre>
 */
public class TransactaionSellFragment extends MyV4BaseFragment implements RadioGroup.OnCheckedChangeListener,
        RecyclerOnScrollListener.OnScrollListener, OnAdapterBtnClickListener {


    @BindView(R.id.sell_radioGroup)
    RadioGroup sellRadioGroup;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.sell_nvn_layout)
    LinearLayout sellNvnLayout;

    @BindView(R.id.no_data_tv)
    TextView noDataTv;

    @BindView(R.id.in_the_sell_rb)
    RadioButton inTheSellRb;

    //已完成
    TransactionOrderBuyAdapter sellCompletedAdapter;
    //出售中
    TransactionOrderSellAdapter sellAdapter;
    List<OrderBuyBean> completeBeanList = new ArrayList<>();
    List<SellGoldBean> sellBeanList = new ArrayList<>();

    PagingHelper completePagingHelper = new PagingHelper();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Utils.print("onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_transaction_sell_layout, container, false);

        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    public void initData() {
        Utils.print("initData");
        try {
            pagingHelper.init();
            loadUserSellData();
            completePagingHelper.init();
            loadUserCompletedData();
            if (noDataTv != null)
                noDataTv.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        sellRadioGroup.setOnCheckedChangeListener(this);
        ProjectUtil.initRecyclerView(mRecyclerView, this);
//        beanList.addAll(com.flysand.mylibrary.util.Utils.getTestList(20, new OrderBuyBean()));
        sellCompletedAdapter = new TransactionOrderBuyAdapter(getActivity(), completeBeanList);
        sellAdapter = new TransactionOrderSellAdapter(getActivity(), sellBeanList);
        sellAdapter.setOnAdapterBtnClickListener(this);
        inTheSellRb.performClick();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        com.flysand.mylibrary.util.Utils.print("onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.in_the_sell_rb:
                Utils.print("出售中");
                sellNvnLayout.setVisibility(View.GONE);
                mRecyclerView.setAdapter(sellAdapter);
                if (sellBeanList.size() == 0) {
                    pagingHelper.init();
                    loadUserSellData();
                }
                break;
            case R.id.sell_completed_rb:
                Utils.print("已完成");
                sellNvnLayout.setVisibility(View.VISIBLE);
                mRecyclerView.setAdapter(sellCompletedAdapter);
                if (completeBeanList.size() == 0) {
                    completePagingHelper.init();
                    loadUserCompletedData();
                }
                break;
        }
        noDataTv.setVisibility(View.GONE);
    }

    /**
     * 出售中
     */
    private void loadUserSellData() {
        if (pagingHelper.isLoading)
            return;
        pagingHelper.setLoading();

        RequestParams params = new RequestParams();
        params.put("column", "orderNo");
        params.put("order", "desc");
        params.put("number", pagingHelper.page);
        params.put("size", Define.PAGE_SIZE);
        httpPostNoDialog("getAllSellInfo", params, Apis.Transaction.getSellData);
    }

    /**
     * 已完成
     */
    private void loadUserCompletedData() {
        if (completePagingHelper.isLoading)
            return;
        completePagingHelper.setLoading();

        RequestParams params = new RequestParams();
        params.put("number", completePagingHelper.page);
        params.put("size", Define.PAGE_SIZE);
        params.put("type", 2);
        httpPostNoDialog("loadUserSellCompleted", params, Apis.Transaction.getDealInfo);
    }

    @Override
    public void onUpdate(int var1, Intent var2) {
        if (Constant.UpdateCode.SELL_SUCCESS == var1 || Constant.UpdateCode.ADD_SELL == var1 || Constant.UpdateCode.BUY_SUCCESS == var1) {
            pagingHelper.init();
            loadUserSellData();
            noDataTv.setVisibility(View.GONE);
        }
        if (Constant.UpdateCode.BUY_SUCCESS == var1) {
            completePagingHelper.init();
            loadUserCompletedData();
            pagingHelper.init();
            loadUserSellData();
            noDataTv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadNextPage(View view) {

        switch (sellRadioGroup.getCheckedRadioButtonId()) {
            case R.id.in_the_sell_rb:
                Utils.print("出售中");
                if (pagingHelper.canLoadMore)
                    loadUserSellData();
                break;
            case R.id.sell_completed_rb:
                Utils.print("已完成");
                if (completePagingHelper.canLoadMore)
                    loadUserCompletedData();
                break;
        }
    }

    @Override
    public void click(int id, int position) {
        switch (id) {
            case R.id.item_transaction_change_price_tv:
                Utils.print("修改价格");
                showChangePriceDialog(position);
                break;
        }
    }

    double priceUnit;
    int position;
    ChangeSellGoldDialog changePriceDialog;

    private void showChangePriceDialog(int position) {

        SellGoldBean bean = sellBeanList.get(position);
        TransactaionSellFragment.this.position = position;

        changePriceDialog = new ChangeSellGoldDialog(getActivity(), R.style.DialogStyle) {
            TextView countTv;
            EditText priceEt;
            TextView referencePriceTv;

            double minPrice;
            double maxPrice;

            String price;

            @Override
            public void afterView(View view) {
                countTv = view.findViewById(R.id.dialog_count_et);
                priceEt = view.findViewById(R.id.dialog_price_et);
                referencePriceTv = view.findViewById(R.id.reference_price_tv);
                countTv.setText(bean.getMoneyCount() + "");
                minPrice = TransactionSalaActivity.transactionPriceBean.getBeginPrice();
                maxPrice = TransactionSalaActivity.transactionPriceBean.getEndPrice();

                referencePriceTv.setText("参考价格：(" + minPrice + "~" + maxPrice + ")");

                priceEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            price = s.toString();
                            priceUnit = Double.parseDouble(price);
                            if (priceUnit > maxPrice) {
                                priceEt.setText(maxPrice + "");
                                priceEt.setSelection(priceEt.getText().toString().length());
                                return;
                            }
                            /**
                             * 保留小数
                             */
                            ProjectUtil.keepDecimal(priceEt, 2);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

            @Override
            public void confirm() {
                if (TextUtils.isEmpty(price)) {
                    toast.setText("请输入出售单价");
                    return;
                }
                if (priceUnit < minPrice) {
                    toast.setText("修改后单价不能低于最低价格");
                    return;
                }
                ProjectUtil.hideSoftKeyboard(getActivity(), priceEt);

                RequestParams params = new RequestParams();
                params.put("sellId", bean.getId());
                params.put("oldPrice", bean.getUnitPrice());
                params.put("newPrice", com.flysand.mylibrary.util.Utils.replaceDoubleZero(priceUnit + ""));
                httpPost("upUnitPrice", params, Apis.Transaction.upUnitPrice);
            }

            @Override
            public void cancel() {
                ProjectUtil.hideSoftKeyboard(getActivity(), priceEt);
                super.cancel();
            }
        };
        changePriceDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
        changePriceDialog.show();
    }

    @Override
    public void onHttpResult(String type, String code, String msg) {
        super.onHttpResult(type, code, msg);
        if ("upUnitPrice".equals(type)) {
            toast.setText(msg);
            changePriceDialog.dismiss();
            sellBeanList.get(position).setUnitPrice(com.flysand.mylibrary.util.Utils.replaceDoubleZero(priceUnit + ""));
            sellAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if ("upUnitPrice".equals(type)) {
            changePriceDialog.dismiss();
            sellBeanList.get(position).setUnitPrice(priceUnit + "");
            sellAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {
        super.onHttpSuccess(type, datas, pageInfoBean);
        if ("getAllSellInfo".equals(type)) {//出售中
            Utils.print("sss");
            if (pageInfoBean.isFirst())
                sellBeanList.clear();

            sellBeanList.addAll(JSONUtil.toJavaBeanList(SellGoldBean.class, datas));
            sellAdapter.notifyDataSetChanged();

            pagingHelper.loadCompleted();
            pagingHelper.canLoadMore = !pageInfoBean.isLast();
            if (sellRadioGroup.getCheckedRadioButtonId() == R.id.in_the_sell_rb) {
                noDataTv.setVisibility(sellBeanList.size() == 0 ? View.VISIBLE : View.GONE);
            }
        } else if ("loadUserSellCompleted".equals(type)) {//已完成

            if (pageInfoBean.isFirst())
                completeBeanList.clear();

            completeBeanList.addAll(JSONUtil.toJavaBeanList(OrderBuyBean.class, datas));
            sellCompletedAdapter.notifyDataSetChanged();

            completePagingHelper.loadCompleted();
            completePagingHelper.canLoadMore = !pageInfoBean.isLast();
            if (sellRadioGroup.getCheckedRadioButtonId() == R.id.sell_completed_rb) {
                noDataTv.setVisibility(completeBeanList.size() == 0 ? View.VISIBLE : View.GONE);
            }
        }
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {

        if ("getAllSellInfo".equals(type)) {
            Utils.print("sss");
            pagingHelper.loadFailure();
            if (sellRadioGroup.getCheckedRadioButtonId() == R.id.in_the_sell_rb) {
                sellBeanList.clear();
                sellAdapter.notifyDataSetChanged();
                noDataTv.setVisibility(sellBeanList.size() == 0 ? View.VISIBLE : View.GONE);
            }
            dismissHttpDialog();
        } else if ("loadUserSellCompleted".equals(type)) {
            completePagingHelper.loadFailure();
            if (sellRadioGroup.getCheckedRadioButtonId() == R.id.sell_completed_rb) {
                completeBeanList.clear();
                sellCompletedAdapter.notifyDataSetChanged();
                noDataTv.setVisibility(completeBeanList.size() == 0 ? View.VISIBLE : View.GONE);
            }
            dismissHttpDialog();
        } else
            super.onHttpFailure(type, s1);
    }
}
