package com.example.administrator.wankuoportal.aaPackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.flysand.mylibrary.listener.OnItemButtonClickListener;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/11.
 *     desc    :
 * </pre>
 */
public class HanorHomeAdapter extends com.flysand.mylibrary.base.BaseAdapter {

    OnItemButtonClickListener listener;

    public HanorHomeAdapter(Context context, List<?> list) {
        super(context, list);
    }

    public void setListener(OnItemButtonClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int onCreateViewLayoutID(int i) {
        return R.layout.item_manor_home;
    }

    @Override
    public void onBindViewHolder(int i, View view, int i1) throws Exception {
        //认购
        TextView subscribeBtn = view.findViewById(R.id.subscribe_btn);
        //拼购
        TextView spellBtn = view.findViewById(R.id.spell_btn);

        if (listener != null) {
            subscribeBtn.setOnClickListener(v -> listener.onButtonClick(this, v, 0, i1));
            spellBtn.setOnClickListener(v -> listener.onButtonClick(this, v, 1, i1));
        }

    }
}
