package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Address;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Address_Adapter extends BaseAdapter {
    private Context context;
    private List<Address.DatasBean> list;


    public Address_Adapter(Context context, List<Address.DatasBean> list) {
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
            convertView = myInflater.inflate(R.layout.address_adapter, null);
            viewHolde.name = (TextView) convertView.findViewById(R.id.name);
            viewHolde.phone = (TextView) convertView.findViewById(R.id.phone);
            viewHolde.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }
        if (position == 0) {
            viewHolde.name.setText(list.get(position).getName());
            viewHolde.phone.setText(list.get(position).getPhone());
            viewHolde.address.setText(Html.fromHtml("<font color=\"#ff4200\">[默认地址]</font><font color=\"#000000\">"
                    + list.get(position).getProvince()
                    + list.get(position).getCity()
                    + list.get(position).getArea()
                    + list.get(position).getAddress()
                    + "</font>"));
        } else {
            viewHolde.name.setText(list.get(position).getName());
            viewHolde.phone.setText(list.get(position).getPhone());
            viewHolde.address.setText(list.get(position).getProvince()
                    + list.get(position).getCity()
                    + list.get(position).getArea()
                    + list.get(position).getAddress()
            );
        }


        return convertView;
    }


    public class ViewHolde {
        TextView name;
        TextView phone;
        TextView address;

    }
}
