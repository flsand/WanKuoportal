package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.QYRZ;
import com.example.administrator.wankuoportal.beans.ShopExample;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  企业认证界面
 */
public class RecyclerViewAdapter_qiyerenzheng extends RecyclerView.Adapter<RecyclerViewAdapter_qiyerenzheng.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<QYRZ.DatasBean> dataList = new ArrayList<>();
    private RecyclerViewAdapter_rencai.OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<QYRZ.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_qiyerenzheng(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(RecyclerViewAdapter_rencai.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ima;
        private TextView title;
        private TextView body;
        private TextView end;

        public ViewHolder(View v) {
            super(v);
            this.end = (TextView) v.findViewById(R.id.end);
            this.body = (TextView) v.findViewById(R.id.body);
            this.title = (TextView) v.findViewById(R.id.title);
            this.ima = (ImageView) v.findViewById(R.id.ima);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dianpuzixun_layout, parent, false);

        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setTag(position);
        holder.body.setText(dataList.get(position).getSummary()+"");
        holder.title.setText(dataList.get(position).getTitle()+"");
//        holder.end.setText(dataList.get(position).getContent());
        Glide.with(mContext).load(dataList.get(position).getPictureId()).into(holder.ima);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}