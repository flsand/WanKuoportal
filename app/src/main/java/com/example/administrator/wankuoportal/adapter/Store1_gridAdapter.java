package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.InitShop;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.List;


/**
 * Created by lv 帮助中心adapter
 */

public class Store1_gridAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<InitShop.DataBean.AGoodListBean> list;


    public Store1_gridAdapter( Context mContext,List<InitShop.DataBean.AGoodListBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            view = mInflater.inflate(R.layout.store1_grid, null);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            viewHolder.ima = (ImageView) view.findViewById(R.id.ima);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.price.setText(list.get(i).getPirce()+"元");
        viewHolder.title.setText(list.get(i).getName());
        Glide.with(mContext).load(Apis.Baseima+list.get(i).getImg()).into(viewHolder.ima);
        return view;
    }


    static class ViewHolder {
        ImageView ima;
        TextView title;
        TextView price;
    }
}
