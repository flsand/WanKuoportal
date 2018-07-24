package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lv 万哥对我的评价adapter
 */

public class EvaluationAdapter extends BaseAdapter {

    private List<String> mNameList = new ArrayList<String>();
    private List<String> mbody = new ArrayList<String>();
    private List<Integer> mDrawableList = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;


    public EvaluationAdapter(List<String> mNameList, List<String> mbody, List<Integer> mDrawableList, Context mContext) {
        this.mNameList = mNameList;
        this.mbody = mbody;
        this.mDrawableList = mDrawableList;
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
            view = mInflater.inflate(R.layout.pj_towan_adapter, null);

            viewHolder.qd_jifen = (TextView) view.findViewById(R.id.qd_jifen);
            viewHolder.qd_time = (TextView) view.findViewById(R.id.qd_time);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.qd_jifen.setText(mNameList.get(i));
        viewHolder.qd_time.setText(mbody.get(i));

        return view;
    }

    static  class ViewHolder {

        TextView qd_jifen;

        TextView qd_time;




    }
}
