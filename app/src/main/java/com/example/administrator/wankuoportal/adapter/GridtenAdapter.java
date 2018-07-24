package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.NodeBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lv 首页十宫格adapter
 */

public class GridtenAdapter extends BaseAdapter {

    private List<NodeBean> mNameList = new ArrayList<>();
    private List<Integer> mDrawableList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;


    public GridtenAdapter(List<NodeBean> mNameList, List<Integer> mDrawableList, Context mContext) {
        this.mNameList = mNameList;
        this.mDrawableList = mDrawableList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mNameList.size();
    }

    @Override
    public Object getItem(int i) {
        return mNameList.get(i);
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
            view = mInflater.inflate(R.layout.item_button, null);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.name.setText(mNameList.get(i).getLabelName());
        viewHolder.icon.setImageResource(mDrawableList.get(i));
        return view;
    }

    static class ViewHolder {


        TextView name;

        ImageView icon;


    }
}
