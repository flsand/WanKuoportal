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
 * Created by lv on 2017/9/14 普通商品列表.
 */

public class XinziAdapter extends BaseAdapter{
    private Context context;
    private List<String> list;
    TextView kind;

    private int clickTemp = -1;

    // 标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }

    public XinziAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_xinzi, null);
            kind = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(kind);
        } else {
            kind = (TextView) convertView.getTag();
        }
        kind.setText(list.get(position));
        kind.setTextColor(context.getResources().getColor(R.color.all_textc));
        if (clickTemp == position) {
            kind.setBackgroundResource(R.color.grey);
        } else {
            kind.setBackgroundResource(R.color.white);
        }
        return convertView;
    }
}
