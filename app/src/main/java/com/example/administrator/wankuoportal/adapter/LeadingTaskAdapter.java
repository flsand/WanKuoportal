package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.wankuoportal.R;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * Created by FlySand on 2018/3/19.
 */

public class LeadingTaskAdapter extends BaseAdapter {

    public LeadingTaskAdapter(Context var1, List<?> var2) {
        super(var1, var2);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_leading_task;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

    }
}
