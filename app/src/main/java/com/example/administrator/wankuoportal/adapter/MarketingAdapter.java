package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.MarketingBean;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/18.
 *     desc    : 营销学院
 * </pre>
 */
public class MarketingAdapter extends BaseAdapter {

    public MarketingAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.dianpuzixun_layout;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {
        MarketingBean bean = (MarketingBean) getObjcet(i1);
        TextView end = view.findViewById(R.id.end);
        TextView body = view.findViewById(R.id.body);
        TextView title = view.findViewById(R.id.title);
        ImageView ima = view.findViewById(R.id.ima);
        body.setText(bean.getSummary() + "");
        title.setText(bean.getTitle() + "");
        Glide.with(MyApplication.context).load(bean.getPictureId()).into(ima);
    }
}