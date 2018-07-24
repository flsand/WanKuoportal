package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.SmallStoreBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * Created by FlySand on 2018/3/18.
 */

public class SmallStoreAdapter extends BaseAdapter {

    public SmallStoreAdapter(Context var1, List<?> var2) {
        super(var1, var2);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_small_store;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

        SmallStoreBean bean = (SmallStoreBean) getObjcet(i1);

        ImageView img = (ImageView) view.findViewById(R.id.item_store_img);
        TextView titleTv = (TextView) view.findViewById(R.id.item_store_title_tv);
        TextView priceTv = (TextView) view.findViewById(R.id.item_store_price_tv);
        TextView priceTipTv = (TextView) view.findViewById(R.id.item_store_price_tip_tv);
        priceTipTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        ProjectUtil.loadRemoteImage(context,bean.getImg(),img);
        titleTv.setText(bean.getName());
        priceTv.setText(bean.getPirce());
        priceTipTv.setText(bean.getPhoneDiscount());


    }
}
