package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by lv 我的信息adapter
 */

public class InformationAdapter extends BaseAdapter {

    private List<String> mNameList = new ArrayList<String>();
    private List<String> mbody = new ArrayList<String>();
    private List<Integer> mDrawableList = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;


    public InformationAdapter(List<String> mNameList, List<String> mbody, List<Integer> mDrawableList, Context mContext) {
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
            view = mInflater.inflate(R.layout.information_adapter, null);
            viewHolder.isread = (ImageView) view.findViewById(R.id.isread);
            viewHolder.inforTitle = (TextView) view.findViewById(R.id.infor_title);
            viewHolder.inforTime = (TextView) view.findViewById(R.id.infor_time);
            viewHolder.inforBody = (TextView) view.findViewById(R.id.infor_body);


            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.inforTime.setText(mNameList.get(i));
        viewHolder.inforTitle.setText(mbody.get(i));
        viewHolder.inforBody.setText(mbody.get(i));
        viewHolder.isread.setImageResource(mDrawableList.get(i));

        return view;
    }

    static  class ViewHolder {

        ImageView isread;

        TextView inforTitle;

        TextView inforTime;

        TextView inforBody;


    }
}
