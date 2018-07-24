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
import com.example.administrator.wankuoportal.beans.ShopExample;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  商品案例界面
 */
public class RecyclerViewAdapter_shop_anli extends RecyclerView.Adapter<RecyclerViewAdapter_shop_anli.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<ShopExample.DataBean.GoodExampleListBean> dataList = new ArrayList<>();
    private RecyclerViewAdapter_rencai.OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<ShopExample.DataBean.GoodExampleListBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_shop_anli(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(RecyclerViewAdapter_rencai.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imaservice;
        private TextView txtitle;
        private TextView txprice;

        public ViewHolder(View v) {
            super(v);
            this.txprice = (TextView) v.findViewById(R.id.tx_price);
            this.txtitle = (TextView) v.findViewById(R.id.tx_title);
            this.imaservice = (ImageView) v.findViewById(R.id.ima_service);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_anli_adapter_layout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setTag(position);
        holder.txtitle.setText(dataList.get(position).getName() );
        holder.txprice.setText(dataList.get(position).getContent() );
        Glide.with(mContext).load(Apis.Baseima + dataList.get(position).getImg()).into(holder.imaservice);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}