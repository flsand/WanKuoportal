package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.RecommendedServiceBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseFragment;

import java.util.List;


/**
 * 首页
 */

public class MainServiceAdapter extends com.flysand.mylibrary.base.BaseAdapter {



    public MainServiceAdapter(BaseFragment baseFragment, List<RecommendedServiceBean> list) {
        super(baseFragment, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.service_adapter_layout;
    }


    @Override
    public void onBindViewHolder(int viewType, View view, int i1) throws Exception {

            RecommendedServiceBean bean = (RecommendedServiceBean) getObjcet(i1);

            ImageView imaService = view.findViewById(R.id.ima_service);
            TextView txTitle = view.findViewById(R.id.tx_title);
            TextView txPrice = view.findViewById(R.id.tx_price);
            TextView txPosion = view.findViewById(R.id.tx_posion);
            TextView txName = view.findViewById(R.id.tx_name);

            txName.setText(bean.getAshopData().getShopName() + "/" + bean.getAshopData().getProvince() + bean.getAshopData().getCity());
            txPosion.setText(bean.getAgood().getSalesVolume() + "人购买");
            txPrice.setText(bean.getAgood().getPirce() + bean.getAgood().getUnit() + "");
            txTitle.setText(bean.getAgood().getName());
            ProjectUtil.loadHostImage(context, bean.getAgood().getImg(), imaService);

    }
}
