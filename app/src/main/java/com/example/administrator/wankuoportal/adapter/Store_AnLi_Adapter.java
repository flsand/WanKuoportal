package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lv 首页四宫格adapter
 */

public class Store_AnLi_Adapter extends BaseAdapter {

    private List<String> mNameList = new ArrayList<String>();
    private LayoutInflater mInflater;
    private Context mContext;


    public Store_AnLi_Adapter(List<String> mNameList, Context mContext) {
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
            view = mInflater.inflate(R.layout.store_anli_adapter_layout, null);
            viewHolder.imaService = (ImageView) view.findViewById(R.id.ima_service);
            viewHolder.txTitle = (TextView) view.findViewById(R.id.tx_title);
            viewHolder.txPrice = (TextView) view.findViewById(R.id.tx_price);
            viewHolder.txName = (TextView) view.findViewById(R.id.tx_name);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.txTitle.setText(mNameList.get(i));
//        viewHolder.txPrice.setText(mbody.get(i));
//        viewHolder.txPosion.setText(mbody.get(i));
//        viewHolder.txName.setText(mbody.get(i));
//        viewHolder.imaService.setImageResource(mDrawableList.get(i));

        return view;
    }

    static class ViewHolder {

        ImageView imaService;

        TextView txTitle;

        TextView txPrice;

        TextView txName;


    }
}
