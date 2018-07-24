package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.SellGoldBean;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.listener.OnAdapterBtnClickListener;
import com.flysand.mylibrary.util.Utils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/15.
 *     desc    :
 * </pre>
 */
public class TransactionOrderSellAdapter extends BaseAdapter {

    OnAdapterBtnClickListener listener;

    DecimalFormat decimalFormat = new DecimalFormat("############0.00");

    public TransactionOrderSellAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_treansaction_sell;
    }

    @Override
    public void onBindViewHolder(int i, View view, int position) throws Exception {

        SellGoldBean bean = (SellGoldBean) getObjcet(position);

        ImageView circleIv = view.findViewById(R.id.circleImageView);
        TextView timeTv = view.findViewById(R.id.item_time_tv);
        //出售数量
        TextView transactionCountTv = view.findViewById(R.id.item_sell_count_tv);
        //出售单价
        TextView priceTv = view.findViewById(R.id.item_sell_price_tv);
        //出售总价
        TextView totalPriceTv = view.findViewById(R.id.item_sell_total_money_tv);
        if (!TextUtils.isEmpty(bean.getHead_icon()))
            ProjectUtil.loadRemoteImage(MyApplication.getInstance(), bean.getHead_icon(), circleIv);

        timeTv.setText(bean.getCreateTimeStr());
        transactionCountTv.setText(bean.getMoneyCount());
        priceTv.setText("￥" + bean.getUnitPrice());
        try {
            double price = Double.parseDouble(bean.getUnitPrice());
            double count = Double.parseDouble(bean.getMoneyCount());
            totalPriceTv.setText("￥" + Utils.replaceDoubleZero(decimalFormat.format(price * count)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView changePrice = view.findViewById(R.id.item_transaction_change_price_tv);
        changePrice.setText("修改价格");
        changePrice.setOnClickListener(v -> {
            if (listener != null)
                listener.click(v.getId(), position);
        });
    }

    public void setOnAdapterBtnClickListener(OnAdapterBtnClickListener listener) {
        this.listener = listener;
    }
}
