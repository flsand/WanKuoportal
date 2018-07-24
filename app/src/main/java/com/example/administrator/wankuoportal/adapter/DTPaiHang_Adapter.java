package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.PaiHang;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lv on 2017/2/17   答题排行竖向adapter.
 */

public class DTPaiHang_Adapter extends BaseAdapter {
    private Context context;
    private List<PaiHang.DataBean.ListBean> list;


    public DTPaiHang_Adapter(Context context, List<PaiHang.DataBean.ListBean> list) {
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
            convertView = myInflater.inflate(R.layout.paihang_list, null);
            viewHolde.ima = (CircleImageView) convertView.findViewById(R.id.ima);
            viewHolde.nickname = (TextView) convertView.findViewById(R.id.nickname);
            viewHolde.num = (TextView) convertView.findViewById(R.id.num);
            viewHolde.paiming = (TextView) convertView.findViewById(R.id.paiming);
            viewHolde.chengshi = (TextView) convertView.findViewById(R.id.chengshi);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }
        viewHolde.nickname.setText(list.get(position).getNickname() + "");
        viewHolde.num.setText(list.get(position).getGold() + "");
        viewHolde.paiming.setText(list.get(position).getRanking() + "");
        viewHolde.chengshi.setText(list.get(position).getProvince() + " " + list.get(position).getCity());
        if (list.get(position).getHeadimg().isEmpty()) {
            viewHolde.ima.setImageResource(R.drawable.tou);
        } else {
            String imaurl = Apis.Baseima + list.get(position).getHeadimg();
            Glide.with(context).load(imaurl).into(viewHolde.ima);
        }

        return convertView;
    }


    static class ViewHolde {
        de.hdodenhof.circleimageview.CircleImageView ima;
        TextView nickname;
        TextView num;
        TextView paiming;
        TextView chengshi;
    }


}
