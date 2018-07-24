package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.ServiceShopsBean;
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
public class ServiceShopAdapter extends BaseAdapter {

    Fragment fragment;

    public ServiceShopAdapter(Context context, List<?> list) {
        super(context, list);
    }
    public ServiceShopAdapter(Fragment context, List<?> list) {
        super(context.getContext(), list);
        this.fragment = context;
    }

    @Override
    protected void onItemClick(View view, int i) {
        if (fragment instanceof RecyclerViewItemClickListener)
            ((RecyclerViewItemClickListener) fragment).onRecyclerViewItemClick(this, null, i);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_service_shop;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {
        /**
         * ability : 程序UI设计
         * city : 烟台市
         * evaluate : 0
         * headIcon : be39bb88312f4d3e91a78881634f38c3
         * id : 20
         * level : 钻石服务商
         * nomoney : 3.0
         * province : 山东省
         * shopName : 鼎信科技
         * shopType : 公司
         */
        ServiceShopsBean bean = (ServiceShopsBean) getObjcet(i1);

        ImageView img = view.findViewById(R.id.item_ima);
        TextView shopNameTv = view.findViewById(R.id.item_shopname_tv);
        TextView typeTv = view.findViewById(R.id.item_type_tv);
        //地区
        TextView addressTv = view.findViewById(R.id.item_address_tv);
        //级别
        TextView hoplevelTv = view.findViewById(R.id.item_shoplevel_tv);
        //服务范围
        TextView scopeTv = view.findViewById(R.id.item_shop_scope_tv);


        ProjectUtil.loadRemoteImage(context, bean.getHeadIcon(), img);
        shopNameTv.setText(bean.getShopName());
        typeTv.setText(bean.getLevel());
        addressTv.setText("地区：" + bean.getProvince());
        hoplevelTv.setText("级别：" + bean.getLevel());
        scopeTv.setText("服务范围：" + bean.getAbility());

    }
}
