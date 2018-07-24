package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/12.
 *     desc    :
 * </pre>
 */
public class ActivityAdapter extends BaseAdapter {

    private int clickPosition = -1;


    public ActivityAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        return position == clickPosition ? 1 : 0;
    }

    public BaseAdapter.RVHolder onCreateViewHolder(ViewGroup var1, int var2) {
        if (var2 == 0) {
            View var3 = LayoutInflater.from(this.context).inflate(this.onCreateViewLayoutID(var2), var1, false);
            return new BaseAdapter.RVHolder(var3, var2);
        } else {
            View var3 = LayoutInflater.from(this.context).inflate(R.layout.item_activity_detail, var1, false);
            return new BaseAdapter.RVHolder(var3, var2);
        }
    }

    @Override
    protected void onItemClick(View view, int i) {
        super.onItemClick(view, i);
        if (i == clickPosition) {
            clickPosition = -1;
        } else {
            clickPosition = i;
        }
        notifyDataSetChanged();
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_activity;
    }

    @Override
    public void onBindViewHolder(int viewType, View view, int position) throws Exception {
        view.setOnClickListener(v->onItemClick(view,position));

        if (viewType ==0){

        }
    }
}
