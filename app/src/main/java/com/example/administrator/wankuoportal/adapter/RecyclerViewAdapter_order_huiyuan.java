package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.Order_huiyuan;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  订单界面
 */
public class RecyclerViewAdapter_order_huiyuan extends RecyclerView.Adapter<RecyclerViewAdapter_order_huiyuan.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<Order_huiyuan.DatasBean> dataList = new ArrayList<>();
    private RecyclerViewAdapter_rencai.OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<Order_huiyuan.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_order_huiyuan(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ordernum;
        private TextView orderresult;
        private ImageView orderima;
        private TextView ordertitle;
        private TextView ordertype;
        private TextView orderprice;
        private TextView ordergold;
        private TextView orderduihuan;
        private LinearLayout duihuan_lin;

        public ViewHolder(View v) {
            super(v);
            this.orderduihuan = (TextView) v.findViewById(R.id.order_duihuan);
            this.ordergold = (TextView) v.findViewById(R.id.order_gold);
            this.orderprice = (TextView) v.findViewById(R.id.order_price);
            this.ordertype = (TextView) v.findViewById(R.id.order_type);
            this.ordertitle = (TextView) v.findViewById(R.id.order_title);
            this.orderima = (ImageView) v.findViewById(R.id.order_ima);
            this.orderresult = (TextView) v.findViewById(R.id.order_result);
            this.ordernum = (TextView) v.findViewById(R.id.order_num);
            this.duihuan_lin = (LinearLayout) v.findViewById(R.id.duihuan_lin);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout, parent, false);

        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.ordernum.setText("订单号:" + dataList.get(position).getAexchangeOrder().getOrderId() + "");
        int state = dataList.get(position).getAexchangeOrder().getState();
        if (state == 1) {
            holder.orderresult.setText("待付款");
            holder.duihuan_lin.setVisibility(View.VISIBLE);
        } else if (state == 2) {
            holder.orderresult.setText("待发货");
        } else if (state == 3) {
            holder.orderresult.setText("待收货");
        } else if (state == 4) {
            holder.orderresult.setText("已完成");
        }
        holder.itemView.setTag(position);
        holder.ordertitle.setText(dataList.get(position).getAgood().getName() + "");

        holder.ordertype.setText(dataList.get(position).getAexchangeOrder().getRemark() + "     x"+dataList.get(position).getAexchangeOrder().getNumber());

        holder.orderprice.setText("¥ " + dataList.get(position).getAgood().getPirce() + "");

        holder.ordergold.setText("合计:" + dataList.get(position).getAexchangeOrder().getAmount() + "个金币");

        Glide.with(mContext).load(Apis.Baseima + dataList.get(position).getAgood().getImg()).into(holder.orderima);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(RecyclerViewAdapter_rencai.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}