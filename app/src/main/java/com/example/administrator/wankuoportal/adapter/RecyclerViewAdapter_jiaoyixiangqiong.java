package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Getmywalletpage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  交易详情adapter
 */
public class RecyclerViewAdapter_jiaoyixiangqiong extends RecyclerView.Adapter<RecyclerViewAdapter_jiaoyixiangqiong.ViewHolder> {

    private Context mContext;
    private List<Getmywalletpage.DatasBean> dataList = new ArrayList<>();



    public void addAllData(List<Getmywalletpage.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_jiaoyixiangqiong(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private TextView time;
        private TextView type;

        public ViewHolder(View v) {
            super(v);
            this.type = (TextView) v.findViewById(R.id.type);
            this.time = (TextView) v.findViewById(R.id.time);
            this.price = (TextView) v.findViewById(R.id.price);
            this.name = (TextView) v.findViewById(R.id.name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jiaoyimingxi_layout_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.time.setText(dataList.get(position).getCreateTime()+"");

        holder.price.setText(dataList.get(position).getAmount()+"");

//        if (dataList.get(position).getConsumptionType()==11){
//            holder.name.setText("充值");
//        }else if (dataList.get(position).getConsumptionType()==12){
//            holder.name.setText("下家充值钻石会员的返利");
//        }else if (dataList.get(position).getConsumptionType()==13){
//            holder.name.setText("本地区充值钻石的返利");
//        }else if (dataList.get(position).getConsumptionType()==14){
//            holder.name.setText("由金币转换");
//        }else if (dataList.get(position).getConsumptionType()==21){
//            holder.name.setText("提现");
//        }else if (dataList.get(position).getConsumptionType()==22){
//            holder.name.setText("转换成金币");
//        }else {
//            holder.name.setText("交易");
//        }
        String consumptionType = "交易";
        switch (dataList.get(position).getConsumptionType()){
            case 24:
                consumptionType = "用户余额兑换成金币";
                break;
            case 23:
                consumptionType ="金币兑换成用户余额";
                break;
            case 100:
                consumptionType ="升级店长";
                break;
            case 98:
                consumptionType ="推荐奖励";
                break;
            case 99:
                consumptionType ="每日分红";
                break;
            case 101:
                consumptionType ="用户余额充值";
                break;
        }
        holder.name.setText(consumptionType);

        holder.type.setText("交易完成");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}