package com.example.administrator.wankuoportal.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Address;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/17.
 */

public class AddressAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    LayoutInflater layoutInflater;
    private List<Address.DatasBean> list = new ArrayList<>();
    private InnerItemOnclickListener mListener;

    public AddressAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public AddressAdapter(Context context, List<Address.DatasBean> list) {
        this(context);
        setData(list);
    }

    public void setData(List<Address.DatasBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Address.DatasBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.address_item, null);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.addressName = (TextView) convertView.findViewById(R.id.address_name);
        viewHolder.addressPhone = (TextView) convertView.findViewById(R.id.address_phone);
        viewHolder.addressAddress = (TextView) convertView.findViewById(R.id.address_address);
        viewHolder.addressDel = (TextView) convertView.findViewById(R.id.address_del);
        viewHolder.addressSet = (TextView) convertView.findViewById(R.id.address_set);
        viewHolder.sehzhimoren = (CheckBox) convertView.findViewById(R.id.sehzhimoren);

        viewHolder.addressName.setText(list.get(position).getName());
        viewHolder.addressPhone.setText(list.get(position).getPhone());
        viewHolder.addressAddress.setText(list.get(position).getProvince() +
                list.get(position).getCity() + list.get(position).getArea() + list.get(position).getAddress());
//        if (list.get(position).getDefaultFlay()==1){
        if (position==0){
            viewHolder.sehzhimoren.setChecked(true);
        }else {
            viewHolder.sehzhimoren.setChecked(false);
        }


        viewHolder.addressDel.setOnClickListener(this);
        viewHolder.addressSet.setOnClickListener(this);
        viewHolder.sehzhimoren.setOnClickListener(this);
        viewHolder.addressDel.setTag(position);
        viewHolder.addressSet.setTag(position);
        viewHolder.sehzhimoren.setTag(position);
        return convertView;
    }


    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }

    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener) {
        this.mListener = listener;
    }

    static class ViewHolder {

        TextView addressName;

        TextView addressPhone;

        TextView addressAddress;

        TextView addressDel;

        TextView addressSet;
        CheckBox sehzhimoren;


    }
}
