package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.BuyGoldBean;
import com.example.administrator.wankuoportal.base.MyApplication;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.base.BaseFragment;
import com.flysand.mylibrary.listener.OnAdapterBtnClickListener;
import com.flysand.mylibrary.util.Utils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/14.
 *     desc    :
 * </pre>
 */
public class TransactionBuyAdapter extends BaseAdapter {

    DecimalFormat decimalFormat = new DecimalFormat("############.00");

    public TransactionBuyAdapter(BaseFragment baseFragment, List<?> list) {
        super(baseFragment, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_treansaction;
    }

    @Override
    public void onBindViewHolder(int i, View view, int position) throws Exception {

        BuyGoldBean bean = (BuyGoldBean) getObjcet(position);

        TextView buyGold = view.findViewById(R.id.item_transaction_buy_gold_tv);
        ImageView circleIv = view.findViewById(R.id.circleImageView);
        TextView phoneTv = view.findViewById(R.id.item_reansaction_phone_tv);
        TextView timeTv = view.findViewById(R.id.item_reansaction_time_tv);
        //交易数量
        TextView tradingCountTv = view.findViewById(R.id.item_trading_count_tv);
        //出售数量
        TextView transactionCountTv = view.findViewById(R.id.transaction_count_tv);
        //出售单价
        TextView priceTv = view.findViewById(R.id.transaction_price_tv);
        //出售总价
        TextView totalPriceTv = view.findViewById(R.id.transaction_total_price_tv);

        if (!TextUtils.isEmpty(bean.getHead_icon()))
            ProjectUtil.loadRemoteImage(MyApplication.getInstance(), bean.getHead_icon(), circleIv);

        phoneTv.setText(ProjectUtil.conductPhoneNumber(bean.getUserPhone()));
        timeTv.setText(bean.getCreateTimeStr());
        tradingCountTv.setText("交易:" + bean.getDealAllCount() + context.getString(R.string.sell_cumulative) + bean.getDealAllMoney());
        try {
            transactionCountTv.setText(bean.getMoneyCount() - bean.getSuccessCount() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        priceTv.setText("￥" + bean.getUnitPrice());
        try {
            double price = Double.parseDouble(bean.getUnitPrice());
            double count = bean.getMoneyCount();
            totalPriceTv.setText("￥" + Utils.replaceDoubleZero(decimalFormat.format(price * count)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        buyGold.setOnClickListener((v) -> {
            if (fragment instanceof OnAdapterBtnClickListener)
                ((OnAdapterBtnClickListener) fragment).click(v.getId(), position);
        });

    }
}
