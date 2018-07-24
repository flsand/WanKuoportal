package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.StoreGoods;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Store4_yiji_Adapter extends BaseAdapter {
    private Context context;
    private List<StoreGoods.DataBean.LabellistBean> list;

    private int clickTemp = -1;

    // 标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }

    public Store4_yiji_Adapter(Context context, List<StoreGoods.DataBean.LabellistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolde viewHolde = null;
        LayoutInflater myInflater = LayoutInflater.from(context);

        if (convertView == null) {
            viewHolde = new ViewHolde();
            convertView = myInflater.inflate(R.layout.item_store_yiji, null);
            viewHolde.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }
        viewHolde.name.setText(list.get(position).getLabelName());
        if (clickTemp == position) {
            viewHolde.name.setBackgroundResource(R.drawable.xuanzhong);
        } else {
            viewHolde.name.setBackgroundResource(R.drawable.bg_kuang_write);
        }
        return convertView;
    }


    public class ViewHolde {
        TextView name;

    }
}
