package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  搜索服务adapter
 */
public class RecyclerViewAdapter_fuwu extends RecyclerView.Adapter<RecyclerViewAdapter_fuwu.ViewHolder> {

    private Context mContext;
    private List<String[]> dataList = new ArrayList<>();


    public void addAllData(List<String[]> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_fuwu(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.ImageView imaservice;
        private TextView txtitle;
        private TextView txprice;
        private TextView txposion;
        private TextView txname;

        public ViewHolder(View v) {
            super(v);
            this.txname = (TextView) v.findViewById(R.id.tx_name);
            this.txposion = (TextView) v.findViewById(R.id.tx_posion);
            this.txprice = (TextView) v.findViewById(R.id.tx_price);
            this.txtitle = (TextView) v.findViewById(R.id.tx_title);
            this.imaservice = (ImageView) v.findViewById(R.id.ima_service);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_adapter_layout, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (dataList.size() == 0) {

        } else {
            /**
             * select a.pirce,a.name,a.img,a.salesVolume,b.shopName,b.province,b.city,a.id
             价格， 名称，图片，卖出数量，店铺名称， 省，市， ID
             */
//            holder.txname.setText(dataList.get(position).getAgood().getName() + "");
//            holder.txposion.setText(dataList.get(position).getAgood().getSalesVolume() + "");
//            holder.txprice.setText(dataList.get(position).getAgood().getPirce() + dataList.get(position).getAgood().getUnit() + "");
//            holder.txtitle.setText(dataList.get(position).getAgood().getName() + "");
//            Glide.with(mContext).load(dataList.get(position).getAgood().getImg()).into(holder.imaservice);

        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}