package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.SearchFuWu;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by lv 首页四宫格adapter
 */

public class ServiceAdapter extends BaseAdapter {

    private List<SearchFuWu.DatasBean> dataList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;


    public ServiceAdapter(List<SearchFuWu.DatasBean> mNameList,Context mContext) {
        this.dataList = mNameList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        mInflater = LayoutInflater.from(mContext);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.service_adapter_layout, null);
            viewHolder.imaService = (ImageView) view.findViewById(R.id.ima_service);
            viewHolder.txTitle = (TextView) view.findViewById(R.id.tx_title);
            viewHolder.txPrice = (TextView) view.findViewById(R.id.tx_price);
            viewHolder.txPosion = (TextView) view.findViewById(R.id.tx_posion);
            viewHolder.txName = (TextView) view.findViewById(R.id.tx_name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

//        viewHolder.txName.setText(dataList.get(position).getAgood().getName());
        viewHolder.txPosion.setText(dataList.get(position).getAgood().getSalesVolume()+"人购买");
        viewHolder.txPrice.setText(dataList.get(position).getAgood().getPirce()+dataList.get(position).getAgood().getUnit()+"");
        viewHolder.txTitle.setText(dataList.get(position).getAgood().getName());
        Glide.with(mContext).load(dataList.get(position).getAgood().getImg()).into(viewHolder.imaService);

        return view;
    }

    static class ViewHolder {

        ImageView imaService;

        TextView txTitle;

        TextView txPrice;

        TextView txPosion;

        TextView txName;


    }
}
