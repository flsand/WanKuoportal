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

public class GZjiaoyipPingjia_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public GZjiaoyipPingjia_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.gzjiaoyipingjia_adapter, null);
            viewHolde.name = (TextView) convertView.findViewById(R.id.name);
            viewHolde.price = (TextView) convertView.findViewById(R.id.price);
            viewHolde.pingjia = (TextView) convertView.findViewById(R.id.pingjia);
            viewHolde.ima = (ImageView) convertView.findViewById(R.id.ima);
            viewHolde.xing1 = (ImageView) convertView.findViewById(R.id.xing1);
            viewHolde.xing2 = (ImageView) convertView.findViewById(R.id.xing2);
            viewHolde.xing3 = (ImageView) convertView.findViewById(R.id.xing3);
            viewHolde.xing4 = (ImageView) convertView.findViewById(R.id.xing4);
            viewHolde.xing5 = (ImageView) convertView.findViewById(R.id.xing5);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }


        return convertView;
    }




    static class ViewHolde {
        ImageView ima;
        TextView name;
        TextView price;
        TextView pingjia;
        ImageView xing1;
        ImageView xing2;
        ImageView xing3;
        ImageView xing4;
        ImageView xing5;


    }
}
