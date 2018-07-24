package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lv 首页四宫格adapter
 */

public class RecruitmentAdapter extends BaseAdapter {

    private List<String> mNameList = new ArrayList<String>();
    private LayoutInflater mInflater;
    private Context mContext;


    public RecruitmentAdapter(List<String> mNameList, Context mContext) {
        this.mNameList = mNameList;

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
            view = mInflater.inflate(R.layout.recruitment_adapter, null);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        return view;
    }


    static class ViewHolder {
        TextView title;
        TextView price;
        TextView num;
        TextView look;
        TextView companyName;
        TextView time;

    }
}
