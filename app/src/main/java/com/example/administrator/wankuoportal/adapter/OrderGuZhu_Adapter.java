package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.List;

/**
 * Created by 会员订单adapter on 2017/2/17.
 */

public class OrderGuZhu_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public OrderGuZhu_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.guzhu_orderadapter, null);
            viewHolde.ordernum = (TextView) convertView.findViewById(R.id.ordernum);
            viewHolde.ordertype = (TextView) convertView.findViewById(R.id.ordertype);
            viewHolde.name = (TextView) convertView.findViewById(R.id.name);
            viewHolde.price = (TextView) convertView.findViewById(R.id.price);
            viewHolde.type = (TextView) convertView.findViewById(R.id.type);
            viewHolde.button1 = (TextView) convertView.findViewById(R.id.button1);
            viewHolde.button2 = (TextView) convertView.findViewById(R.id.button2);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }

        return convertView;
    }


    static class ViewHolde {
        TextView ordernum;
        TextView ordertype;
        TextView name;
        TextView price;
        TextView type;
        TextView button1;
        TextView button2;
    }
}
