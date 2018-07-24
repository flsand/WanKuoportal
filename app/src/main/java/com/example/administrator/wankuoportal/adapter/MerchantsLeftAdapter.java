package com.example.administrator.wankuoportal.adapter;

import android.support.v4.app.Fragment;
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
public class MerchantsLeftAdapter extends BaseAdapter {
    int clickPosition;
    Fragment fragment;

    public MerchantsLeftAdapter(Fragment context, List<?> list, int clickPosition) {
        super(context.getContext(), list);
        this.fragment = context;
    }

    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_merchants_left;
    }

    @Override
    public void onBindViewHolder(int i, View view, int poistion) throws Exception {

        NodeBean bean = (NodeBean) getObjcet(poistion);

        TextView textView = view.findViewById(R.id.item_merchant_rb);

        if (!TextUtils.isEmpty(bean.getLabelName())){
            textView.setText(bean.getLabelName());
        }

        textView.setEnabled(poistion != clickPosition);

        if (fragment instanceof RecyclerViewItemClickListener)
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPosition = poistion;
                    notifyDataSetChanged();
                    try {
                        ((RecyclerViewItemClickListener) fragment).onRecyclerViewItemClick(MerchantsLeftAdapter.this, null, poistion);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}
