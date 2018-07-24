package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  举报adapter
 */
public class RecyclerViewAdapter_jubao extends RecyclerView.Adapter<RecyclerViewAdapter_jubao.ViewHolder> {

    private Context mContext;
    private List<String> dataList = new ArrayList<>();



    public void addAllData(List<String> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_jubao(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bianhao;
        private TextView zhaungtai;
        private TextView renwuhao;
        private TextView time;
        private TextView price;
        private TextView from;
        private TextView people;
        private TextView shenqingkaipiao;
        private TextView zanbukaipiao;
        public ViewHolder(View v) {
            super(v);
            this.zanbukaipiao = (TextView) v.findViewById(R.id.zanbukaipiao);
            this.shenqingkaipiao = (TextView) v.findViewById(R.id.shenqingkaipiao);
            this.people = (TextView) v.findViewById(R.id.people);
            this.from = (TextView) v.findViewById(R.id.from);
            this.price = (TextView) v.findViewById(R.id.price);
            this.time = (TextView) v.findViewById(R.id.time);
            this.renwuhao = (TextView) v.findViewById(R.id.renwuhao);
            this.zhaungtai = (TextView) v.findViewById(R.id.zhaungtai);
            this.bianhao = (TextView) v.findViewById(R.id.bianhao);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jubao_adapter, parent, false);



        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.txname.setText(dataList.get(position).getAshopData().getShopName() + "");
//        holder.txposion.setText(dataList.get(position).getAgood().getSalesVolume() + "");
//        holder.txprice.setText(dataList.get(position).getAgood().getPirce() + dataList.get(position).getAgood().getUnit() + "");
//        holder.txtitle.setText(dataList.get(position).getAgood().getName() + "");
//        Glide.with(mContext).load(dataList.get(position).getAgood().getImg()).into(holder.imaservice);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}