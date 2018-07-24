package com.example.administrator.wankuoportal.aaPackage.fragment.gold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.aaPackage.bean.DateGOldBean;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.PayGoldDialog;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.ReleaseBuyGoldDialog;
import com.example.administrator.wankuoportal.aaPackage.coustom.dialog.ReleaseSellGoldDialog;
import com.example.administrator.wankuoportal.base.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.beans.GetMyGold;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.ui.Pay.ChongZhiActivity;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/14.
 *     desc    : 发布交易
 * </pre>
 */
public class ReleaseTransactionFragment extends MyBaseFragment {

    Unbinder unbinder;

    ReleaseBuyGoldDialog releaseBuyGoldDialog;

    @BindView(R.id.release_day_tv)
    TextView releaseDayTv;

    @BindView(R.id.release_week_tv)
    TextView releaseWeekTv;

    @BindView(R.id.release_time_tv)
    TextView releaseTimeTv;

    ReleaseSellGoldDialog releaseSellGoldDialog;

    GetMyGold.DataBean goldBean;

    double minValue;
    double maxValue;
    long count;
    double prcie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transaction_release, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    private void initData() {
        httpGetNoDialog("getmygold", new RequestParams(), Apis.getmygold);
    }

    private void initView() {
        Calendar calendar = Calendar.getInstance();
        releaseDayTv.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
        releaseWeekTv.setText(ProjectUtil.getWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        releaseTimeTv.setText(sdf.format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.selling_gold_tv, R.id.buy_gold_tv, R.id.release_bg_btn})
    public void onViewClicked(View view) {
        Utils.print("onViewClicked");
        if (ProjectUtil.isLogin())
            return;
        switch (view.getId()) {
            case R.id.selling_gold_tv:
                Utils.print("出售金币");
                if (TransactionSalaActivity.canTransaction) {
                    getDateGOldData();
                } else {
                    toast.setText(getResources().getString(R.string.transction_end));
                }
                break;
            case R.id.buy_gold_tv:
                Utils.print("收购金币");
                if (TransactionSalaActivity.canTransaction) {
                    showReleseBuyDialog();
                } else {
                    toast.setText(getResources().getString(R.string.transction_end));
                }
                break;
        }
    }

    private void getDateGOldData() {
        RequestParams params = new RequestParams();
        params.put("member", MyApplication.getInstance().getUser().getAAccountAuthorize().getIsOrdinaryMember());
        httpPost("getDateGOld", params, Apis.Transaction.getDateGOld);
    }


    private void showReleseSellDiaog(DateGOldBean bean) {

        releaseSellGoldDialog = new ReleaseSellGoldDialog(getActivity(), R.style.DialogStyle) {
            EditText countEt;
            EditText priceEt;
            TextView referenceValueTv;
            TextView identityTv;
            TextView balanceTv;
            TextView limitTv;
            double price;

            @Override
            public void afterView(View view) {
                countEt = view.findViewById(R.id.dialog_count_et);
                priceEt = view.findViewById(R.id.dialog_release_price_et);
                referenceValueTv = view.findViewById(R.id.reference_value_tv);
                identityTv = view.findViewById(R.id.identity_tv);
                balanceTv = view.findViewById(R.id.balance_tv);
                limitTv = view.findViewById(R.id.sell_limit_tv);
                identityTv.setText("身份：" + Constant.getMemberGradeInfo(MyApplication.getInstance().getUser().getAAccountAuthorize().getIsOrdinaryMember()));
                minValue = TransactionSalaActivity.transactionPriceBean.getBeginPrice();
                maxValue = TransactionSalaActivity.transactionPriceBean.getEndPrice();
                referenceValueTv.setText("参考价￥：" + minValue + "~" + maxValue);
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
                            String str = s.toString();
                            price = Double.parseDouble(str);
                            if (price > maxValue) {
                                priceEt.setText(maxValue + "");
                                priceEt.setSelection((minValue + "").length());
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
                balanceTv.setText(getString(R.string.balance_of) + ProjectUtil.formatDouble(MyApplication.getInstance().getUser().getAAccountAuthorize().getAnswerGold()));
                limitTv.setText("每日限额：" + bean.getGoldCount());
            }

            @Override
            public void confirm() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);

                String countStr = countEt.getText().toString();
                String priceStr = priceEt.getText().toString();

                if (TextUtils.isEmpty(countStr)) {
                    toast.setText("请输入出售数量");
                    return;
                }
                if (TextUtils.isEmpty(priceStr)) {
                    toast.setText("请输入出售单价");
                    return;
                }


                try {

                    count = Long.parseLong(countStr);
                    prcie = Double.parseDouble(priceStr);

                    if (count < 100) {
                        toast.setText("至少出售100枚金币");
                        return;
                    }
                    if (count % 100 != 0) {
                        toast.setText("出售金币必须是100的倍数");
                        return;
                    }
                    if (price < minValue) {
                        toast.setText("出售金币单价不能低于最低价格");
                        return;
                    }
                    long surplus = (bean.getGoldCount() - bean.getScuessCount());
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
                com.flysand.mylibrary.util.Utils.print("connt = " + countEt.getText().toString());
                RequestParams params = new RequestParams();
                //购买金币数量
                params.put("moneyCount", count);
                //购买单价
                params.put("unitPrice", prcie);
                params.put("member", MyApplication.getInstance().getUser().getAAccountAuthorize().getIsOrdinaryMember());
                httpPost("addSell", params, Apis.Transaction.addSell);
            }

            @Override
            public void cancel() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);
                super.cancel();
            }
        };
        releaseSellGoldDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
        releaseSellGoldDialog.show();
    }

    @Override
    public void onHttpResult(String type, String code, String msg) throws Exception {
        super.onHttpResult(type, code, msg);
        if ("addSell".equals(type)) {
            toast.setText(msg);
            sendUpdateMessage(Constant.UpdateCode.ADD_SELL, null);
            releaseSellGoldDialog.dismiss();
        } else if ("addBuy".equals(type)) {
            payGoldDialog.dismiss();
            if ("0".equals(code)) {
                sendUpdateMessage(Constant.UpdateCode.ADD_BUY, null);
            } else if (Constant.HttpCode.PAY_MONEY.equals(code)) {
                Intent intent = new Intent(getActivity(), ChongZhiActivity.class);
                intent.putExtra(Constant.IntentKey.DOUBLE, prcie * count);
                startActivity(intent);
            }
            toast.setText(msg);
        }
    }

    private void showReleseBuyDialog() {
        releaseBuyGoldDialog = new ReleaseBuyGoldDialog(getActivity(), R.style.DialogStyle) {
            EditText countEt;
            EditText priceEt;
            TextView referenceValueTv;
            double price;

            @Override
            public void afterView(View view) {
                countEt = view.findViewById(R.id.dialog_count_et);
                priceEt = view.findViewById(R.id.dialog_release_price_et);
                referenceValueTv = view.findViewById(R.id.reference_value_tv);
                minValue = TransactionSalaActivity.transactionPriceBean.getBeginPrice();
                maxValue = TransactionSalaActivity.transactionPriceBean.getEndPrice();
                referenceValueTv.setText("参考价￥：" + minValue + "~" + maxValue);
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
                            String str = s.toString();
                            price = Double.parseDouble(str);
                            if (price > maxValue) {
                                priceEt.setText(maxValue + "");
                                priceEt.setSelection((minValue + "").length());
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
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);

                String countStr = countEt.getText().toString();
                String priceStr = priceEt.getText().toString();

                if (TextUtils.isEmpty(countStr)) {
                    toast.setText("请输入收购数量");
                    return;
                }
                if (TextUtils.isEmpty(priceStr)) {
                    toast.setText("请输入收购单价");
                    return;
                }
                try {
                    count = Long.parseLong(countStr);
                    prcie = Double.parseDouble(priceStr);

                    if (count < 100) {
                        toast.setText("至少收购100枚金币");
                        return;
                    }
                    if (count % 100 != 0) {
                        toast.setText("收购金币必须是100的倍数");
                        return;
                    }
                    if (price < minValue) {
                        toast.setText("收购单价不能低于最低价格");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                com.flysand.mylibrary.util.Utils.print("connt = " + countEt.getText().toString());
                super.confirm();
                showPayDialog();

            }

            @Override
            public void cancel() {
                ProjectUtil.hideSoftKeyboard(getActivity(), countEt);
                super.cancel();
            }
        };
        releaseBuyGoldDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉dialog的title。
        releaseBuyGoldDialog.show();
    }

    PayGoldDialog payGoldDialog;

    private void showPayDialog() {
        //判断余额够不够
        com.flysand.mylibrary.util.Utils.print("getAAccountAuthorize = " + MyApplication.getInstance().getUser().getAAccountAuthorize());
        double balance = MyApplication.getInstance().getUser().getAAccountAuthorize().getMoney();

        Utils.print("余额:" + balance);
        payGoldDialog = new PayGoldDialog(getActivity(), R.style.DialogStyle) {

            @Override
            public void afterView(View view) {
                TextView balanceMoneyTv = view.findViewById(R.id.balance_money_tv);
                TextView balancePaymentTv = view.findViewById(R.id.balance_payment_tv);
                TextView balancePaymentTipTv = view.findViewById(R.id.balance_payment_tip_tv);
                ImageView balancePaymentIv = view.findViewById(R.id.balance_payment_iv);
                ImageView checkMarkIv = view.findViewById(R.id.check_mark_iv);
                TextView confirm = view.findViewById(R.id.ok);

                if (prcie * count > balance) {
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
                if (prcie * count > balance) {
                    Intent intent = new Intent(getActivity(), ChongZhiActivity.class);
                    intent.putExtra(Constant.IntentKey.DOUBLE, prcie * count);
                    startActivity(intent);
                    super.confirm();
                } else {
                    RequestParams params = new RequestParams();
                    //购买金币数量
                    params.put("moneyCount", count);
                    //购买单价
                    params.put("unitPrice", prcie);
                    httpPost("addBuy", params, Apis.Transaction.addBuy);
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
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        if ("getmygold".equals(type)) {
            goldBean = JSONUtil.toJavaBean(GetMyGold.DataBean.class, jsonObject);
        } else if ("getDateGOld".equals(type)) {
            dismissHttpDialog();
            DateGOldBean bean = JSONUtil.toJavaBean(DateGOldBean.class, jsonObject.getString("dealUser"));
            showReleseSellDiaog(bean);
        }
    }
}
