package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.listener.RecyclerViewItemClickListener;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/18.
 *     desc    :
 * </pre>
 */
public class MerchantsSubDetailAdapter extends BaseAdapter {

    Fragment fragment;

    public MerchantsSubDetailAdapter(Fragment context, List<?> list) {
        super(context.getActivity(), list);
        this.fragment = context;
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_merchants_tv;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {

        NodeBean bean = (NodeBean) getObjcet(i1);
        TextView tv = view.findViewById(R.id.item_name_tv);
        View line = view.findViewById(R.id.item_line_tv);
        if (!TextUtils.isEmpty(bean.getLabelName()))
            tv.setText(bean.getLabelName());
        if ((i1 + 1) % 3 == 0) {
            line.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
        }

        if (fragment instanceof RecyclerViewItemClickListener) {
            view.setOnClickListener(v -> {
                ((RecyclerViewItemClickListener) fragment).onRecyclerViewItemClick(MerchantsSubDetailAdapter.this, view, i1);
            });
        }
    }
}
