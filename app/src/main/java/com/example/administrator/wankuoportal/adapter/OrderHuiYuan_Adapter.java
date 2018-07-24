package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.List;

/**
 * Created by 会员订单adapter on 2017/2/17.
 */

public class OrderHuiYuan_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public OrderHuiYuan_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.order_layout, null);
            viewHolde.orderNum = (TextView) convertView.findViewById(R.id.order_num);
            viewHolde.orderResult = (TextView) convertView.findViewById(R.id.order_result);
            viewHolde.orderTitle = (TextView) convertView.findViewById(R.id.order_title);
            viewHolde.orderType = (TextView) convertView.findViewById(R.id.order_type);
            viewHolde.orderPrice = (TextView) convertView.findViewById(R.id.order_price);
            viewHolde.orderGold = (TextView) convertView.findViewById(R.id.order_gold);
            viewHolde.orderDuihuan = (TextView) convertView.findViewById(R.id.order_duihuan);
            viewHolde.orderIma = (ImageView) convertView.findViewById(R.id.order_ima);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }

        return convertView;
    }


    static class ViewHolde {
        TextView orderNum;
        TextView orderResult;
        ImageView orderIma;
        TextView orderTitle;
        TextView orderType;
        TextView orderPrice;
        TextView orderGold;
        TextView orderDuihuan;


    }
}
