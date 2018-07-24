package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Gethelpcenter;




/**
 * Created by lv 帮助中心adapter
 */

public class HelpAdapter extends BaseAdapter {


    private LayoutInflater mInflater;
    private Context mContext;
    private Gethelpcenter gethelpcenter;


    public HelpAdapter(Gethelpcenter getHelpcenter, Context mContext) {
        this.gethelpcenter = getHelpcenter;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return gethelpcenter.getDatas().size();
    }

    @Override
    public Object getItem(int i) {
        return gethelpcenter.getDatas().get(i);
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
            view = mInflater.inflate(R.layout.help_adapter, null);
            viewHolder.tx = (TextView) view.findViewById(R.id.tx);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tx.setText(gethelpcenter.getDatas().get(i).getHelpTypeName());

        return view;
    }


    static  class ViewHolder {
        TextView tx;
    }
}
