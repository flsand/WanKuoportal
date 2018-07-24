package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.BusinessBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lv 首页四宫格adapter
 */

public class GridFourAdapter extends BaseAdapter {

    private List<BusinessBean> beanList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;


    public GridFourAdapter(List<BusinessBean> beanList, Context mContext) {
        this.beanList = beanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        mInflater = LayoutInflater.from(mContext);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.gridfour_layout, null);
            viewHolder.gridfourTitle = (TextView) view.findViewById(R.id.gridfour_title);
            viewHolder.gridfourBody = (TextView) view.findViewById(R.id.gridfour_body);
            viewHolder.businessTv = (TextView) view.findViewById(R.id.gridfour_business_tv);
            viewHolder.businessNumTv = (TextView) view.findViewById(R.id.gridfour_business_num_tv);
            viewHolder.unitTv = (TextView) view.findViewById(R.id.gridfour_business_unit_tv);
            viewHolder.gridfourIma = (ImageView) view.findViewById(R.id.gridfour_ima);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        BusinessBean bean = beanList.get(i);

        viewHolder.gridfourTitle.setText(bean.getTitle());
        viewHolder.gridfourBody.setText(bean.getBody());
        viewHolder.gridfourIma.setImageResource(bean.getImg());
        viewHolder.businessTv.setText(bean.getNothing());
        viewHolder.businessNumTv.setText(bean.getNum());
        viewHolder.unitTv.setText("/" + bean.getUnit());
        if (i == 0) {
            viewHolder.gridfourBody.setTextColor(mContext.getResources().getColor(R.color.green));
        } else if (i == 1) {
            viewHolder.gridfourBody.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else if (i == 2) {
            viewHolder.gridfourBody.setTextColor(mContext.getResources().getColor(R.color.juse));
        } else if (i == 3) {
            viewHolder.gridfourBody.setTextColor(mContext.getResources().getColor(R.color.blue));
        }

        return view;
    }

    static class ViewHolder {
        TextView gridfourTitle;
        TextView businessNumTv;
        TextView unitTv;
        TextView businessTv;
        TextView gridfourBody;
        ImageView gridfourIma;
    }
}
