package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.ServiceBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.listener.RecyclerViewItemClickListener;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/24.
 *     desc    :
 * </pre>
 */
public class ServiceAdapter extends BaseAdapter {

    Fragment fragment;

    public ServiceAdapter(Fragment context, List<?> list) {
        super(context.getContext(), list);
        this.fragment = context;
    }
    public ServiceAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    protected void onItemClick(View view, int i) {
        if (fragment instanceof RecyclerViewItemClickListener)
            ((RecyclerViewItemClickListener) fragment).onRecyclerViewItemClick(this, null, i);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_service;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {
        /**
         * city : 烟台市
         * goodName : 六合一建站
         * id : 115
         * img : 099e88cd0bc74d4bb12b24e7eee94748
         * price : 12800.0
         * province : 山东省
         * salesVolume : 2
         * shopName : 科信软件
         */
        ServiceBean bean = (ServiceBean) getObjcet(i1);

        ImageView img = view.findViewById(R.id.ima_service);
        TextView shopNameTv = view.findViewById(R.id.tx_title);
        TextView priceTv = view.findViewById(R.id.tx_price);
        TextView posionTv = view.findViewById(R.id.tx_posion);

        ProjectUtil.loadRemoteImage(context,bean.getImg(),img);
        shopNameTv.setText(bean.getShopName());
        priceTv.setText("¥"+bean.getPrice()+"/套");
        posionTv.setText(bean.getSalesVolume()+"人付款");
    }
}
