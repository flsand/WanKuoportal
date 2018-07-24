package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.bean.ServiceProviderBean;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.base.BaseFragment;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/2.
 *     desc    :
 * </pre>
 */
public class MainProviderAapter extends BaseAdapter {

    public MainProviderAapter(BaseFragment baseFragment, List<ServiceProviderBean> list) {
        super(baseFragment, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.fuwushang_search;
    }

    @Override
    public void onBindViewHolder(int viewType, View view, int i1) throws Exception {
        ServiceProviderBean bean = (ServiceProviderBean) getObjcet(i1);

        ImageView ima = view.findViewById(R.id.ima);
        TextView shopname = view.findViewById(R.id.shopname);
        TextView shoparea = view.findViewById(R.id.shoparea);
        TextView shoplevel = view.findViewById(R.id.shoplevel);
        TextView shopfuwu = view.findViewById(R.id.shopfuwu);
        TextView isbaozhang = view.findViewById(R.id.isbaozhang);
        TextView type = view.findViewById(R.id.type);


        shopname.setText(bean.getAshopData().getShopName());
        shoparea.setText(bean.getAshopData().getProvince() + "" + bean.getAshopData().getCity());

        String fuwufanwei = "";
        for (int i = 0; i < bean.getLabellist().size(); i++) {
            if (fuwufanwei.isEmpty()) {
                fuwufanwei = fuwufanwei + bean.getLabellist().get(i).getLabelName();
            } else {
                fuwufanwei = fuwufanwei + " " + bean.getLabellist().get(i).getLabelName();
            }
        }
        shopfuwu.setText(fuwufanwei);
        if (bean.getAshopData().getType() == 1) {
            type.setText("个人");
        } else if (bean.getAshopData().getType() == 2) {
            type.setText("工作室");
        } else if (bean.getAshopData().getType() == 3) {
            type.setText("公司");
        }

        if (bean.getAshopData().getLevel() == 0) {
            shoplevel.setText("级别：普通会员  保证金：0  好评率：" + bean.getAshopData().getEvaluate() + "%");
            isbaozhang.setVisibility(View.VISIBLE);
        } else if (bean.getAshopData().getLevel() == 1) {
            isbaozhang.setVisibility(View.VISIBLE);
            shoplevel.setText("级别：白金会员  保证金：5000  好评率：" + bean.getAshopData().getEvaluate() + "%");
        } else if (bean.getAshopData().getLevel() == 2) {
            isbaozhang.setVisibility(View.VISIBLE);
            shoplevel.setText("级别：钻石会员  保证金：10000  好评率：" + bean.getAshopData().getEvaluate() + "%");
        } else if (bean.getAshopData().getLevel() == 3) {
            isbaozhang.setVisibility(View.VISIBLE);
            shoplevel.setText("级别：皇冠会员  保证金：20000  好评率：" + bean.getAshopData().getEvaluate() + "%");
        } else if (bean.getAshopData().getLevel() == 4) {
            isbaozhang.setVisibility(View.VISIBLE);
            shoplevel.setText("级别：尊贵会员  保证金：69800  好评率：" + bean.getAshopData().getEvaluate() + "%");
        } else if (bean.getAshopData().getLevel() == 5) {
            isbaozhang.setVisibility(View.VISIBLE);
            shoplevel.setText("级别：至尊会员  保证金：109800  好评率：" + bean.getAshopData().getEvaluate() + "%");
        }
        if (bean.getInfo().getImg1().isEmpty()) {
            ProjectUtil.loadLocalhostImage(context, R.drawable.shopnone, ima);
        } else {
            ProjectUtil.loadRemoteImage(context, bean.getInfo().getImg1(), ima);
        }
    }
}
