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
 * Created by Administrator on 2017/2/17.
 */

public class Store_Rencai_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public Store_Rencai_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.store_rencai_list, null);
            viewHolde.name = (TextView) convertView.findViewById(R.id.name);
            viewHolde.zhiye = (TextView) convertView.findViewById(R.id.zhiye);
            viewHolde.body = (TextView) convertView.findViewById(R.id.body);
            viewHolde.ima = (ImageView) convertView.findViewById(R.id.ima);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }
        viewHolde.name.setText("吕道欣");
        viewHolde.zhiye.setText("18153527447");
        viewHolde.body.setText("山东省烟台市福山区宁海佳苑19号楼二单元903");

        return convertView;
    }




    static class ViewHolde {
        ImageView ima;
        TextView name;
        TextView zhiye;
        TextView body;

    }
}
