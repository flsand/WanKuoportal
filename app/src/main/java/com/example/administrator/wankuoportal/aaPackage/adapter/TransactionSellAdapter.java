package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.SellGoldBean;
import com.example.administrator.wankuoportal.aaPackage.coustom.TimeTextView;
import com.example.administrator.wankuoportal.aaPackage.utils.TimeManager;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.base.BaseFragment;
import com.flysand.mylibrary.listener.OnAdapterBtnClickListener;
import com.flysand.mylibrary.util.Utils;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/14.
 *     desc    :
 * </pre>
 */
public class TransactionSellAdapter extends BaseAdapter {

    public TransactionSellAdapter(BaseFragment baseFragment, List<?> list) {
        super(baseFragment, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_treansaction;
    }

    @Override
    public void onBindViewHolder(int i, View view, int position) throws Exception {

        SellGoldBean bean = (SellGoldBean) getObjcet(position);

        ((TextView) view.findViewById(R.id.item_transaction_count_tv)).setText("收购数量");
        ((TextView) view.findViewById(R.id.item_transaction_price_tv)).setText("收购单价");
        ((TextView) view.findViewById(R.id.item_transaction_total_price_tv)).setText("剩余时间");

        TextView buyGold = view.findViewById(R.id.item_transaction_buy_gold_tv);
        TextView phoneTv = view.findViewById(R.id.item_reansaction_phone_tv);
        TextView timeTv = view.findViewById(R.id.item_reansaction_time_tv);
        TextView countTv = view.findViewById(R.id.transaction_count_tv);
        TextView priceTv = view.findViewById(R.id.transaction_price_tv);
        TimeTextView remainingTimeTv = view.findViewById(R.id.transaction_total_price_tv);
        TextView tradingCountTv = view.findViewById(R.id.item_trading_count_tv);

        remainingTimeTv.setText("");
        buyGold.setText(context.getString(R.string.sell));
        phoneTv.setText(ProjectUtil.conductPhoneNumber(bean.getUserPhone()));
        timeTv.setText(bean.getCreateTimeStr());
        priceTv.setText(bean.getUnitPrice());
        countTv.setText(Long.parseLong(bean.getMoneyCount()) - Long.parseLong(bean.getSuccessCount()) + "");

        remainingTimeTv.setListener(() -> {
            remainingTimeTv.setText("已结束");
            buyGold.setEnabled(false);
            try {
                if (fragment instanceof OnAdapterBtnClickListener)
                    ((OnAdapterBtnClickListener) fragment).click(-2, position);
            } catch (Exception E) {
                E.printStackTrace();
            }
        });
        //计算剩余时间
        long remaining = bean.getForceTime() - TimeManager.getInstance().getServiceTime();
        Utils.print("剩余时间 =" + remaining);
        remainingTimeTv.setTimes(remaining);
        tradingCountTv.setText("交易:" + bean.getDealAllCount() + context.getString(R.string.sell_cumulative) + bean.getDealAllMoney());

        buyGold.setOnClickListener((v) -> {
            if (fragment instanceof OnAdapterBtnClickListener)
                ((OnAdapterBtnClickListener) fragment).click(v.getId(), position);
        });

    }
}
