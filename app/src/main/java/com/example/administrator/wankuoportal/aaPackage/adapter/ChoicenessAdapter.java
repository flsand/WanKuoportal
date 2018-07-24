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
 *     time    : 2018/7/11.
 *     desc    :
 * </pre>
 */
public class ChoicenessAdapter extends BaseAdapter {

    public ChoicenessAdapter(Context context, List<?> list) {
        super(context, list);
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_choiceness;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

    }
}
