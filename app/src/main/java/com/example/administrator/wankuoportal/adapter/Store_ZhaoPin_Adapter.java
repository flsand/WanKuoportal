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
 * Created by Administrator on 2017/2/17.
 */

public class Store_ZhaoPin_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public Store_ZhaoPin_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.store_zhappin, null);
            viewHolde.zhiwei = (TextView) convertView.findViewById(R.id.zhiwei);
            viewHolde.xinzi = (TextView) convertView.findViewById(R.id.xinzi);
            viewHolde.renshu = (TextView) convertView.findViewById(R.id.renshu);
            viewHolde.shijian = (TextView) convertView.findViewById(R.id.shijian);

            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }


        return convertView;
    }


    static class ViewHolde {
        TextView zhiwei;
        TextView xinzi;
        TextView renshu;
        TextView shijian;


    }
}
