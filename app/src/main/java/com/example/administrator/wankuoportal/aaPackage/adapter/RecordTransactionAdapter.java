package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.OrderBuyBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.base.BaseFragment;
import com.flysand.mylibrary.util.Utils;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/15.
 *     desc    : 我的订单 -> 我是买家
 * </pre>
 */
public class RecordTransactionAdapter extends BaseAdapter {


    public RecordTransactionAdapter(BaseFragment baseFragment, List<?> list) {
        super(baseFragment, list);
    }


    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_treansaction_record;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

        OrderBuyBean bean = (OrderBuyBean) getObjcet(i1);

        TextView buyPhoneTv = view.findViewById(R.id.item_buy_phone_tv);
        TextView sellPhoneTv = view.findViewById(R.id.item_sell_phone_tv);
        TextView priceTv = view.findViewById(R.id.item_price_tv);
        TextView countTv = view.findViewById(R.id.item_count_tv);
        TextView timeTv = view.findViewById(R.id.item_time_tv);

        buyPhoneTv.setText(ProjectUtil.conductPhoneNumber(bean.getBuyUserName()));
        sellPhoneTv.setText(ProjectUtil.conductPhoneNumber(bean.getSellUserName()));
        priceTv.setText("￥" + bean.getUnitPrice());
        countTv.setText(bean.getBuyCount() + "");
        timeTv.setText(Utils.sdf.format(new Date(bean.getCreateTime())));

    }
}
