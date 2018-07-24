package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.flysand.mylibrary.base.BaseAdapter;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/23.
 *     desc    :
 * </pre>
 */
public class ManorListAdapter extends BaseAdapter {

    private String unit;

    public ManorListAdapter(Context context, List<?> list, String unit) {
        super(context, list);
        this.unit = unit;
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_manor_list;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {
        TextView priceTv = view.findViewById(R.id.item_manor_price_tv);
        priceTv.setText("￥3000/" + unit);

    }
}
