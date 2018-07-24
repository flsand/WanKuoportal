package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.wankuoportal.R;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/13.
 *     desc    :
 * </pre>
 */
public class HanorRecordAdapter extends BaseAdapter {
    public HanorRecordAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_hanor_record;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

    }
}
