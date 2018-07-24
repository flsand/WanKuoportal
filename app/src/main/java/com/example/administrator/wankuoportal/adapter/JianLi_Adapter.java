package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/2/17.
 */

public class JianLi_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public JianLi_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.jianli_adapter, null);
            viewHolde.name = (TextView) convertView.findViewById(R.id.name);
            viewHolde.sex = (TextView) convertView.findViewById(R.id.sex);
            viewHolde.zhiye = (TextView) convertView.findViewById(R.id.zhiye);
            viewHolde.didian = (TextView) convertView.findViewById(R.id.didian);
            viewHolde.biaoqian1 = (TextView) convertView.findViewById(R.id.biaoqian1);
            viewHolde.biaoqian2 = (TextView) convertView.findViewById(R.id.biaoqian2);
            viewHolde.biaoqian3 = (TextView) convertView.findViewById(R.id.biaoqian3);
            viewHolde.biaoqian4 = (TextView) convertView.findViewById(R.id.biaoqian4);
            viewHolde.bianji = (TextView) convertView.findViewById(R.id.bianji);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }


        return convertView;
    }


    public static class ViewHolde {
        CircleImageView ima;
        TextView name;
        TextView sex;
        TextView zhiye;
        TextView didian;
        TextView biaoqian1;
        TextView biaoqian2;
        TextView biaoqian3;
        TextView biaoqian4;
        TextView bianji;

    }
}
