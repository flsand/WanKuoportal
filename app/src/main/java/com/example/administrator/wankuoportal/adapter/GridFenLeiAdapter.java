package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Three_fenlei;


/**
 * Created by lv 第三级分类adapter
 *
 */

public class GridFenLeiAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private Context mContext;
    private Three_fenlei three;


    public GridFenLeiAdapter(Three_fenlei three_fenlei, Context mContext) {
        this.three = three_fenlei;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return three.getDatas().size();
    }

    @Override
    public Object getItem(int i) {
        return three.getDatas().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        mInflater = LayoutInflater.from(mContext);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_fenlei, null);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (i==1||i==4||i==7||i==10||i==13||i==16||i==19){
            view.setBackgroundResource(R.drawable.sb);
        }
        viewHolder.name.setText(three.getDatas().get(i).getLabelName());
        return view;
    }

    static class ViewHolder {
        TextView name;
    }
}
