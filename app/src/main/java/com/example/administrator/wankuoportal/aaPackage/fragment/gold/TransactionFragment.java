package com.example.administrator.wankuoportal.aaPackage.fragment.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.aaPackage.adapter.TransactionBuyAdapter;
import com.example.administrator.wankuoportal.aaPackage.adapter.TransactionSellAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.BuyGoldBean;
import com.example.administrator.wankuoportal.aaPackage.bean.DateGOldBean;
import com.example.administrator.wankuoportal.aaPackage.bean.OrderBuyBean;
import com.example.administrator.wankuoportal.aaPackage.bean.SellGoldBean;
import com.example.administrator.wankuoportal.aaPackage.bean.TransactionPriceBean;
import com.example.administrator.wankuoportal.aaPackage.coomon.Define;
import com.example.administrator.wankuoportal.aaPackage.coustom.ToggleRadioButton;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.BuyGoldDialog;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.PayGoldDialog;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.SellGoldDialog;
import com.example.administrator.wankuoportal.aaPackage.utils.TimeManager;
import com.example.administrator.wankuoportal.base.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.beans.GetMyGold;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.ui.Pay.ChongZhiActivity;
import com.example.administrator.wankuoportal.util.PagingHelper;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.OnAdapterBtnClickListener;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;
import com.oushangfeng.marqueelayout.MarqueeLayout;
import com.oushangfeng.marqueelayout.MarqueeLayoutAdapter;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/14.
 *     desc    : 交易
 * </pre>
 */
public class TransactionFragment extends MyBaseFragment implements RecyclerOnScrollListener.OnScrollListener,
        RadioGroup.OnCheckedChangeListener, ToggleRadioButton.OnToggleChangeListener, OnAdapterBtnClickListener {

    @BindView(R.id.mBanner)
    Banner mBanner;

    @BindView(R.id.transaction_marquee_layout)
    MarqueeLayout transactionMarqueeLayout;

    @BindView(R.id.transaction_sort_radioGroup)
    RadioGroup sortRadioGroup;

    @BindView(R.id.sort_price_rb)
    ToggleRadioButton sortPriceRb;

    @BindView(R.id.sort_count_rb)
    ToggleRadioButton sortCountRb;

    @BindView(R.id.sort_time_rb)
    ToggleRadioButton sortTimeRb;

    @BindView(R.id.shor_defult_rb)
    RadioButton shorDefultRb;
    //最新单价
    @BindView(R.id.latest_price_tv)
    TextView latestPriceTv;

    @BindView(R.id.no_data_tv)
    Button noDataTv;

    @BindView(R.id.commercial_group)
    RadioGroup commercialGroup;

    @BindView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.ommercial_buy_rb)
    RadioButton ommercialBuyRb;
    Unbinder unbinder;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    List<OrderBuyBean> messageBeanList = new ArrayList<>();

    TransactionBuyAdapter buyAdapter;
    TransactionSellAdapter sellAdapter;

    List<SellGoldBean> sellBeanList = new ArrayList<>();
    List<BuyGoldBean> buyBeanList = new ArrayList<>();

    PagingHelper buyPageHelper = new PagingHelper();
    PagingHelper sellPageHelper = new PagingHelper();

    private String column = "orderNo";
    //    顺序
    private String sequence = "desc";
    GetMyGold.DataBean goldBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transaction, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    public void initData() {
        try {
            httpPostNoDialog("getLatestPrice", new RequestParams(), Apis.Transaction.getLatestPrice);
            httpGetNoDialog("getmygold", new RequestParams(), Apis.getmygold);

            RequestParams params = new RequestParams();
            params.put("size", 3);
            params.put("type", 0);
            params.put("number", 1);
            httpPostNoDialog("messageList", params, Apis.Transaction.getDealInfo);

            buyPageHelper.init();
            sellPageHelper.init();
            loadBuyData();
            loadSellData();
            noDataTv.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSellData() {
        if (sellPageHelper.isLoading)
            return;
        sellPageHelper.setLoading();
        RequestParams params = new RequestParams();
        params.put("column", column);
        params.put("order", sequence);
        params.put("number", sellPageHelper.page);
        params.put("size", Define.PAGE_SIZE);

        HttpUtil.getInstance(this).httpPost("loadSellData", params, Apis.Transaction.getBuyData);
    }

    private void loadBuyData() {
        if (buyPageHelper.isLoading)
            return;
        buyPageHelper.setLoading();

        RequestParams params = new RequestParams();
        params.put("column", column);
        params.put("order", sequence);
        params.put("number", buyPageHelper.page);
        params.put("size", Define.PAGE_SIZE);

        HttpUtil.getInstance(this).httpPost("loadBuyData", params, Apis.Transaction.getSellData);
    }

    private void initView() {

        buyAdapter = new TransactionBuyAdapter(this, buyBeanList);
        sellAdapter = new TransactionSellAdapter(this, sellBeanList);

        ProjectUtil.initRecyclerView(mRecyclerView, this);
//        防止更新item时防止抖动
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        sortRadioGroup.setOnCheckedChangeListener(this);
        commercialGroup.setOnCheckedChangeListener(this);
        sortPriceRb.setOnToggleChangeListener(this);
        sortCountRb.setOnToggleChangeListener(this);
        sortTimeRb.setOnToggleChangeListener(this);
        shorDefultRb.performClick();
        ommercialBuyRb.performClick();
//        startTime();
    }

    @Override
    public void onResume() {
        super.onResume();
        HttpUtil.getInstance(this).httpPost("getServerTime", new RequestParams(), Apis.Transaction.getServerTime);
    }

    public void initSwitcher() {

//        MessageBean msg = new MessageBean();
//        msg.setNacigationQuestion("北京东墨");
//        messageBeanList.add(msg);
//        MessageBean msg2 = new MessageBean();
//        msg2.setNacigationQuestion("山东 墨摸");
//        messageBeanList.add(msg2);
        MarqueeLayoutAdapter mSrcAdapter = new MarqueeLayoutAdapter<OrderBuyBean>(messageBeanList) {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_simple_text;
            }

            @Override
            public void initView(View view, int position, OrderBuyBean item) {
                try {
                    String text = "<font color=\"" + getResources().getColor(R.color.text_gray) + "\"> 恭喜 </font>" +
                            "<font color=\"" + getResources().getColor(R.color.text_orange) + "\">%1$s</font>" +
                            "<font color=\"" + getResources().getColor(R.color.text_gray) + "\"> "+getResources().getString(R.string.success_buy)+" </font>" +
                            "<font color=\"" + getResources().getColor(R.color.text_orange) + "\">%2$s</font>";
                    ((TextView) view).setText(Html.fromHtml(String.format(text, ProjectUtil.conductPhoneNumber(item.getBuyUserName()), ProjectUtil.formatDouble(item.getBuyCount()))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
//        mSrcAdapter.setItemClickListener(listener);
        transactionMarqueeLayout.setAdapter(mSrcAdapter);
        transactionMarqueeLayout.start();
    }


//    private void startTime() {
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
////                Utils.print("TimerTask run ...");
//                if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_sell_rb)
//                    for (int i = 0; i < sellBeanList.size(); i++) {
//                        SellGoldBean bean = sellBeanList.get(i);
//                        //计算剩余时间
//                        long remaining = bean.getForceTime() - TimeManager.getInstance().getServiceTime();
////                        Utils.print("计算剩余时间");
//                        bean.setRemainingTime(remaining);
//                        bean.subtractRemainingTime();
//                        int finalI = i;
////                        getActivity().runOnUiThread(() -> sellAdapter.notifyItemChanged(finalI));
//
//                    }
//                getActivity().runOnUiThread(() -> sellAdapter.notifyItemRangeChanged(0, sellBeanList.size()));
//            }
//        }, 0, 1000);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Utils.print("onDestroyView");
        unbinder.unbind();
//        if (timer != null)
//            timer.cancel();
//        timer = null;

    }

    @Override
    public void onLoadNextPage(View view) {
        if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_buy_rb) {
            Utils.print("我要买");
            if (buyPageHelper.canLoadMore)
                loadBuyData();
        } else {
            Utils.print("我要卖");
            if (sellPageHelper.canLoadMore)
                loadSellData();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.shor_defult_rb:
                Utils.print("默认排序");
                column = "orderNo";
                sequence = "desc";
                if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_buy_rb) {
                    buyPageHelper.init();
                    loadBuyData();
                } else {
                    sellPageHelper.init();
                    loadSellData();
                }
                break;
            case R.id.ommercial_buy_rb:
                Utils.print("我要买");
                shorDefultRb.performClick();
                buyPageHelper.init();
                loadBuyData();
                mRecyclerView.setAdapter(buyAdapter);
                noDataTv.setVisibility(View.GONE);
                break;
            case R.id.ommercial_sell_rb:
                shorDefultRb.performClick();
                Utils.print("我要卖");
                sellPageHelper.init();
                loadSellData();
                mRecyclerView.setAdapter(sellAdapter);
                noDataTv.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isToggle) {
        switch (buttonView.getId()) {
            case R.id.sort_price_rb:
                Utils.print("金币单价");
                column = "unitPrice";
                break;
            case R.id.sort_count_rb:
                Utils.print("金币数量");
                column = "moneyCount";
                break;
            case R.id.sort_time_rb:
                Utils.print("发布时间");
                column = "createTime";
                break;
        }
        if (isToggle) {
            Utils.print("升序");
            sequence = "desc";
        } else {
            Utils.print("降序");
            sequence = "asc";
        }
        if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_buy_rb) {
            buyBeanList.clear();
            buyAdapter.notifyDataSetChanged();
            buyPageHelper.init();
            loadBuyData();
        } else {
            sellBeanList.clear();
            sellAdapter.notifyDataSetChanged();
            sellPageHelper.init();
            loadSellData();
        }
    }

    public void onUpdate(int var1, Intent var2) {
        if (var1 == Constant.UpdateCode.ADD_SELL) {
            buyPageHelper.init();
            loadBuyData();
        } else if (var1 == Constant.UpdateCode.ADD_BUY) {
            sellPageHelper.init();
            loadSellData();
        }
    }

    @Override
    public void click(int clickId, int position) {
        if (ProjectUtil.isLogin())
            return;
        switch (clickId) {
            case R.id.item_transaction_buy_gold_tv:
                if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_buy_rb) {//购买金币
                    Utils.print("购买金币");
                    if (TransactionSalaActivity.canTransaction) {
                        showBuyDialog(position);
                    } else {
                        toast.setText(getResources().getString(R.string.transction_end));
                    }
                } else {
                    Utils.print("出售金币");
//                    showSellDiaog(position);
                    this.position = position;
                    if (TransactionSalaActivity.canTransaction) {
                        getDateGOldData();
                    } else {
                        toast.setText(getResources().getString(R.string.transction_end));
                    }
                }
                break;
            case -2://倒计时结束
                Utils.print("倒计时结束  " + position);
//                getActivity().runOnUiThread(() -> {
//                    Utils.print("sellBeanList =" + sellBeanList.size());
//                    sellBeanList.remove(position);
//                    Utils.print("sellBeanList 2  =" + sellBeanList.size());
////                    sellAdapter.notifyItemRangeChanged(0, position);
//                    sellAdapter.notifyDataSetChanged();
//                });
                break;
        }
    }

    private void getDateGOldData() {
        RequestParams params = new RequestParams();
        params.put("member", MyApplication.getInstance().getUser().getAAccountAuthorize().getIsOrdinaryMember());
        httpPost("getDateGOld", params, Apis.Transaction.getDateGOld);
    }

    SellGoldDialog sellGoldDialog;

    private void showSellDiaog(DateGOldBean detaGoldBean) {

        SellGoldBean bean = sellBeanList.get(position);

        sellGoldDialog = new SellGoldDialog(getActivity(), R.style.DialogStyle) {
            EditText countEt;
            TextView receiveAmountTv;
            TextView serviceFeeTv;
            TextView identityTv;
            TextView balanceTv;
            TextView limitTv;

            @Override
            public void afterView(View view) {
                countEt = view.findViewById(R.id.dialog_count_et);
                receiveAmountTv = view.findViewById(R.id.receive_amount_tv);
                identityTv = view.findViewById(R.id.identity_tv);
                serviceFeeTv = view.findViewById(R.id.service_fee_tv);
                balanceTv = view.findViewById(R.id.balance_tv);
                limitTv = view.findViewById(R.id.limit_tv);
                countEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            String countStr = s.toString();
                            count = Long.parseLong(countStr);
                            long moneyCount = Long.parseLong(bean.getMoneyCount()) - Long.parseLong(bean.getSuccessCount());
                            if (count > moneyCount) {
                                countEt.setText(moneyCount + "");
                                countEt.setSelection(countEt.getText().toString().length());
                                return;
                            }

                            double totalMoney = Double.parseDouble(bean.getUnitPrice()) * count;
                            receiveAmountTv.setText("将获得出售金额：￥" + ProjectUtil.formatDouble(totalMoney * 0.8));
                            serviceFeeTv.setText("平台将收取20%技术服务费：" + ProjectUtil.formatDouble(totalMoney * 0.2));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                identityTv.setText("身份：" + Constant.getMemberGradeInfo(MyApplication.getInstance().getUser().getAAccountAuthorize().getIsOrdinaryMember()));
                balanceTv.setText("金币余额：" + goldBean.getNolimitGold());
                limitTv.setText("每日限额：" + detaGoldBean.getGoldCount());
            }

            @Override
            public void confirm() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);
                Utils.print("connt = " + countEt.getText().toString());
                if (TextUtils.isEmpty(countEt.getText().toString())) {
                    toast.setText("请输入出售数量");
                    return;
                }
                try {
                    if (count < 100) {
                        toast.setText("至少购买100枚金币");
                        return;
                    }
                    if (count % 100 != 0) {
                        toast.setText("购买金币必须是100的倍数");
                        return;
                    }

                    long surplus = (detaGoldBean.getGoldCount() - detaGoldBean.getScuessCount());
                    if (count > surplus) {
                        if (surplus > 0) {
                            toast.setText("根据您的会员等级,当日剩余可交易金币限额为" + surplus);
                        } else {
                            toast.setText("根据您的会员等级,您当日的交易限额已用完");
                        }
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                RequestParams params = new RequestParams();
                params.put("buyId", bean.getId());
                params.put("sellCount", countEt.getText().toString());
                params.put("member", MyApplication.getInstance().getUser().getAAccountAuthorize().getIsOrdinaryMember());
                httpPost("addBuyInfo", params, Apis.Transaction.addBuyInfo);
//                super.confirm();
//                showPayDialog();
            }

            @Override
            public void cancel() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);
                super.cancel();
            }
        };
        sellGoldDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
        sellGoldDialog.show();
    }


    private int position;
    private long count;
    private double pirce;

    private void showBuyDialog(int position) {
        BuyGoldDialog buyGoldDialog = new BuyGoldDialog(getActivity(), R.style.DialogStyle) {
            EditText countEt;

            @Override
            public void afterView(View view) {
                countEt = view.findViewById(R.id.dialog_count_et);
                TextView amountTv = view.findViewById(R.id.dialog_amount_tv);
                pirce = Double.parseDouble(buyBeanList.get(position).getUnitPrice());
                //剩余数量
                long surplus = buyBeanList.get(position).getMoneyCount() - buyBeanList.get(position).getSuccessCount();
                countEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String countStr = s.toString();
                        if (!TextUtils.isEmpty(countStr)) {
                            count = Long.parseLong(countStr);
                            if (count > surplus) {
                                countEt.setText(surplus + "");
                                countEt.setSelection(countEt.getText().toString().length());
                                return;
                            }
                            amountTv.setText("需要支金额：￥" + ProjectUtil.formatDouble(pirce * count));
                        } else {
                            amountTv.setText("需要支金额：￥0.0");
                        }
                    }
                });

            }

            @Override
            public void confirm() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);
//                super.confirm();
                String countStr = countEt.getText().toString();
                Utils.print("connt = " + countStr);
                if (TextUtils.isEmpty(countStr)) {
                    toast.setText("请输入购买数量");
                    return;
                }

                try {
                    if (count < 100) {
                        toast.setText("至少购买100枚金币");
                        return;
                    }
                    if (count % 100 != 0) {
                        toast.setText("购买金币必须是100的倍数");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                TransactionFragment.this.position = position;
                super.confirm();
                showPayDialog();
            }

            @Override
            public void cancel() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);
                super.cancel();
            }
        };
        buyGoldDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
        buyGoldDialog.show();
    }

    PayGoldDialog payGoldDialog;

    private void showPayDialog() {
        //判断余额够不够
        Utils.print("getAAccountAuthorize = " + MyApplication.getInstance().getUser().getAAccountAuthorize());
        double balance = MyApplication.getInstance().getUser().getAAccountAuthorize().getMoney();

        payGoldDialog = new PayGoldDialog(getActivity(), R.style.DialogStyle) {

            @Override
            public void afterView(View view) {
                TextView balanceMoneyTv = view.findViewById(R.id.balance_money_tv);
                TextView balancePaymentTv = view.findViewById(R.id.balance_payment_tv);
                TextView balancePaymentTipTv = view.findViewById(R.id.balance_payment_tip_tv);
                ImageView balancePaymentIv = view.findViewById(R.id.balance_payment_iv);
                ImageView checkMarkIv = view.findViewById(R.id.check_mark_iv);
                TextView confirm = view.findViewById(R.id.ok);

                if (pirce * count > balance) {
                    balanceMoneyTv.setText("余额不足");
                    balanceMoneyTv.setTextColor(getResources().getColor(R.color.text_gray));
                    balancePaymentTv.setTextColor(getResources().getColor(R.color.text_gray));
                    balancePaymentTipTv.setTextColor(getResources().getColor(R.color.text_gray));
                    balancePaymentIv.setImageResource(R.drawable.wallet_gary_icon);
                    confirm.setText("前去充值");
                    confirm.setBackground(getResources().getDrawable(R.drawable.green_btn));
                    checkMarkIv.setImageResource(R.drawable.yes_gray_icon);
                } else {
                    balanceMoneyTv.setText("余额：" + balance);
                }
            }

            @Override
            public void confirm() {
                if (pirce * count > balance) {
                    Intent intent = new Intent(getActivity(), ChongZhiActivity.class);
                    intent.putExtra(Constant.IntentKey.DOUBLE, pirce * count);
                    startActivity(intent);
                    super.confirm();
                } else {
                    RequestParams params = new RequestParams();
                    params.put("sellId", buyBeanList.get(position).getId());
                    params.put("buyCount", count);

                    httpPost("buyGold", params, Apis.Transaction.addSellInfo);
                }
            }

            @Override
            public void cancel() {
                super.cancel();
            }
        };
        payGoldDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
        payGoldDialog.show();
    }


    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) throws Exception {
        super.onHttpSuccess(type, datas, pageInfoBean);
        if ("loadBuyData".equals(type)) {//我要买
            if (pageInfoBean.isFirst())
                buyBeanList.clear();
            buyBeanList.addAll(JSONUtil.toJavaBeanList(BuyGoldBean.class, datas));
            buyPageHelper.canLoadMore = !pageInfoBean.isLast();
            buyAdapter.notifyDataSetChanged();
            if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_buy_rb) {
                noDataTv.setVisibility(buyBeanList.size() == 0 ? View.VISIBLE : View.GONE);
                if (buyBeanList.size() < 2) {
                    scrollToTop();
                }
            }
            buyPageHelper.loadCompleted();
        } else if ("loadSellData".equals(type)) {//我要卖
            if (pageInfoBean.isFirst())
                sellBeanList.clear();
            sellBeanList.addAll(JSONUtil.toJavaBeanList(SellGoldBean.class, datas));
            sellPageHelper.canLoadMore = !pageInfoBean.isLast();
            sellAdapter.notifyDataSetChanged();

            if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_sell_rb) {
                noDataTv.setVisibility(sellBeanList.size() == 0 ? View.VISIBLE : View.GONE);
                if (sellBeanList.size() < 2) {
                    scrollToTop();
                }
            }
            sellPageHelper.loadCompleted();
        } else if ("messageList".equals(type)) {
            messageBeanList.clear();
            messageBeanList.addAll(JSONUtil.toJavaBeanList(OrderBuyBean.class, datas));
            initSwitcher();
        }
    }

    private void scrollToTop() {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
            }
        }
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {

        if ("loadBuyData".equals(type)) {//我要买
            buyPageHelper.init();
            buyBeanList.clear();
            buyAdapter.notifyDataSetChanged();
            if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_buy_rb) {
                noDataTv.setVisibility(buyBeanList.size() == 0 ? View.VISIBLE : View.GONE);
                if (buyBeanList.size() < 2) {
                    scrollToTop();
                }
            }
            dismissHttpDialog();
        } else if ("loadSellData".equals(type)) {//我要卖
            sellPageHelper.init();
            sellBeanList.clear();
            sellAdapter.notifyDataSetChanged();
            if (commercialGroup.getCheckedRadioButtonId() == R.id.ommercial_sell_rb) {
                noDataTv.setVisibility(sellBeanList.size() == 0 ? View.VISIBLE : View.GONE);
                if (sellBeanList.size() < 2) {
                    scrollToTop();
                }
            }
            dismissHttpDialog();
        } else {
            super.onHttpFailure(type, s1);
        }
    }

    @Override
    public void onHttpResult(String type, String code, String msg) throws Exception {
        super.onHttpResult(type, code, msg);
        if ("buyGold".equals(type)) {//购买金币
            payGoldDialog.dismiss();
            toast.setText(msg);
            buyBeanList.clear();
            buyAdapter.notifyDataSetChanged();
            buyPageHelper.init();
            loadBuyData();
//            BuyGoldBean bean = buyBeanList.get(position);
//            bean.setSuccessCount(bean.getSuccessCount() + count);
//            if (bean.getDealAllCount() - bean.getSuccessCount() > 0) {
//                buyAdapter.notifyItemChanged(position);
//            } else {
//                buyBeanList.clear();
//                buyAdapter.notifyDataSetChanged();
//                buyPageHelper.init();
//                loadBuyData();
////                buyBeanList.remove(position);
////                buyAdapter.notifyItemRemoved(position);
////                if (buyBeanList.size() == 0) {
////                    noDataTv.setVisibility(View.VISIBLE);
////                }
//            }

            sendUpdateMessage(Constant.UpdateCode.BUY_SUCCESS, null);
        } else if ("addBuyInfo".equals(type)) {//出售金币
            sellGoldDialog.dismiss();
            toast.setText(msg);
            sendUpdateMessage(Constant.UpdateCode.SELL_SUCCESS, null);
            sellBeanList.clear();
            sellAdapter.notifyDataSetChanged();
            sellPageHelper.init();
            loadSellData();
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if ("getLatestPrice".equals(type)) {
            TransactionSalaActivity.transactionPriceBean = JSONUtil.toJavaBean(TransactionPriceBean.class, jsonObject.getString("newPrice"));
            latestPriceTv.setText("￥" + TransactionSalaActivity.transactionPriceBean.getBeginPrice());
        } else if ("addBuyInfo".equals(type)) {
            sellGoldDialog.dismiss();
        } else if ("getServerTime".equals(type)) {
            long time = jsonObject.getLong("time");
            Utils.print("服务器时间:" + Utils.sdf.format(new Date(time)));
            TimeManager.getInstance().initServerTime(time);

            TransactionSalaActivity.canTransaction = ProjectUtil.checkTime(time);

        } else if ("getmygold".equals(type)) {
            goldBean = JSONUtil.toJavaBean(GetMyGold.DataBean.class, jsonObject);
        } else if ("getDateGOld".equals(type)) {
            DateGOldBean bean = JSONUtil.toJavaBean(DateGOldBean.class, jsonObject.getString("dealUser"));
            showSellDiaog(bean);
        }
    }
}
