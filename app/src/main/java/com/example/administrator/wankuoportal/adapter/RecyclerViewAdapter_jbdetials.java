package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.JBdetial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  搜索服务adapter
 */
public class RecyclerViewAdapter_jbdetials extends RecyclerView.Adapter<RecyclerViewAdapter_jbdetials.ViewHolder> {

    private Context mContext;
    private List<JBdetial.DatasBean> dataList = new ArrayList<>();



    public void addAllData(List<JBdetial.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_jbdetials(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView detialtime;
        private TextView detailtype;
        private TextView detailposition;

        public ViewHolder(View v) {
            super(v);
            this.detailposition = (TextView) v.findViewById(R.id.detail_position);
            this.detailtype = (TextView) v.findViewById(R.id.detail_type);
            this.detialtime = (TextView) v.findViewById(R.id.detial_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jbdetial_adapter, parent, false);



        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        if (dataList.get(position).getAmount()>0){
            holder.detailposition.setText("+"+dataList.get(position).getAmount());
        }else {
            holder.detailposition.setText(dataList.get(position).getAmount()+ "");
        }
        if (dataList.get(position).getConsumptionType()==11){
            holder.detailtype.setText("答题有奖");
        }else if (dataList.get(position).getConsumptionType()==12){
            holder.detailtype.setText("推荐人答题有奖");
        }else if (dataList.get(position).getConsumptionType()==13){
            holder.detailtype.setText("签到");
        }else if (dataList.get(position).getConsumptionType()==14){
            holder.detailtype.setText("区域内答题");
        }else if (dataList.get(position).getConsumptionType()==15){
            holder.detailtype.setText("区域内广告商");
        }else if (dataList.get(position).getConsumptionType()==16){
            holder.detailtype.setText("钱包兑换的");
        }else if (dataList.get(position).getConsumptionType()==21){
            holder.detailtype.setText("广告扣除");
        }else if (dataList.get(position).getConsumptionType()==22){
            holder.detailtype.setText("兑换商城消费");
        }else if (dataList.get(position).getConsumptionType()==23){
            holder.detailtype.setText("转换成钱包余额");
        }else if (dataList.get(position).getConsumptionType()==17){
            holder.detailtype.setText("注册获得");
        }else if (dataList.get(position).getConsumptionType()==18){
            holder.detailtype.setText("推荐人注册获得");
        }else if (dataList.get(position).getConsumptionType()==24){
            holder.detailtype.setText("兑换商城消费的钻石");
        }else {
            holder.detailtype.setText("");
        }

        holder.detialtime.setText(dataList.get(position).getCreateTime() + "");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}