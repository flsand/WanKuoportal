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

public class Store_Youshi_Adapter extends BaseAdapter {
    private Context context;
    private List<String> list;


    public Store_Youshi_Adapter(Context context, List<String> list) {
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
            convertView = myInflater.inflate(R.layout.store_youshi_list, null);
            viewHolde.text = (TextView) convertView.findViewById(R.id.text);
            viewHolde.ima = (ImageView) convertView.findViewById(R.id.ima);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }

        return convertView;
    }




    static class ViewHolde {
        ImageView ima;
        TextView text;


    }
}
