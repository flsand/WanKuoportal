package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Getmyinvitation;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class TuiJian_Adapter extends BaseAdapter {
    private Context context;
    private List<Getmyinvitation.DataBean.ListBean> list;


    public TuiJian_Adapter(Context context, List<Getmyinvitation.DataBean.ListBean> list) {
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
            convertView = myInflater.inflate(R.layout.tuijian_list, null);
            viewHolde.phone = (TextView) convertView.findViewById(R.id.phone);
            viewHolde.zhuce = (TextView) convertView.findViewById(R.id.zhuce);
            viewHolde.dati = (TextView) convertView.findViewById(R.id.dati);
            viewHolde.xiaofei = (TextView) convertView.findViewById(R.id.xiaofei);
            viewHolde.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }

        String phonenum = list.get(position).getPhone();
//        phonenum = phonenum.substring(0,7)+"****";
        String a  = phonenum.substring(0,3);
        String b  = phonenum.substring(phonenum.length()-4);
        viewHolde.phone.setText(a+"****"+b);
        viewHolde.zhuce.setText(list.get(position).getInvitationGold()+"");
        viewHolde.dati.setText(list.get(position).getPrizeGold()+"");
        viewHolde.xiaofei.setText(list.get(position).getConsumptionAmount()+"");
        viewHolde.time.setText(list.get(position).getInvitationDay()+"");


        return convertView;
    }


    static class ViewHolde {

        TextView zhuce;
        TextView dati;
        TextView phone;
        TextView time;
        TextView xiaofei;


    }
}
