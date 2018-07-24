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
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.ShopExampleDetails;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lv 首页四宫格adapter
 */

public class Store_ChanPin_Adapter extends BaseAdapter {

    private List<ShopExampleDetails.DatasBean> mNameList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;


    public Store_ChanPin_Adapter(List<ShopExampleDetails.DatasBean> mNameList, Context mContext) {
        this.mNameList = mNameList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mNameList.size();
    }

    @Override
    public Object getItem(int i) {
        return mNameList.get(i);
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
            viewHolder.ima = (ImageView) view.findViewById(R.id.ima);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.price = (TextView) view.findViewById(R.id.price);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(mNameList.get(i).getName()+"");
        viewHolder.price.setText(mNameList.get(i).getImg1()+"/"+mNameList.get(i).getImg2());
        Glide.with(MyApplication.context).load(Apis.Baseima+mNameList.get(i).getImg()).into(viewHolder.ima);
        return view;
    }

    static  class ViewHolder {
        ImageView ima;
        TextView title;
        TextView price;

    }
}
