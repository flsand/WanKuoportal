package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.PaiHang;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by lv 答题排行横向listviewadapter
 */

public class DatipaihangAdapter extends BaseAdapter {

    private List<PaiHang.DataBean.ListBean> list = new ArrayList<PaiHang.DataBean.ListBean>();
    private List<String> mbody = new ArrayList<String>();
    private List<Integer> mDrawableList = new ArrayList<Integer>();
    private LayoutInflater mInflater;
    private Context mContext;
    LinearLayout.LayoutParams params;


    public DatipaihangAdapter(List<PaiHang.DataBean.ListBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            view = mInflater.inflate(R.layout.datipaihang_layout, null);
            viewHolder.touxiang = (CircleImageView) view.findViewById(R.id.touxiang);
            viewHolder.wangming = (TextView) view.findViewById(R.id.wangming);
            viewHolder.jinbishu = (TextView) view.findViewById(R.id.jinbishu);
            viewHolder.mingci = (TextView) view.findViewById(R.id.mingci);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.wangming.setText(list.get(i).getNickname());
        viewHolder.jinbishu.setText(list.get(i).getGold()+"");
        viewHolder.mingci.setText("第"+list.get(i).getRanking()+"名");
        if (list.get(i).getHeadimg().isEmpty()){
            Glide.with(mContext).load(R.drawable.tou).into(viewHolder.touxiang);
        }else {
            String imaurl = Apis.Baseima+list.get(i).getHeadimg();
            Glide.with(mContext).load(imaurl).into(viewHolder.touxiang);
        }


        return view;
    }


    static class ViewHolder {

        de.hdodenhof.circleimageview.CircleImageView touxiang;

        TextView wangming;

        TextView jinbishu;

        TextView mingci;

    }
}
