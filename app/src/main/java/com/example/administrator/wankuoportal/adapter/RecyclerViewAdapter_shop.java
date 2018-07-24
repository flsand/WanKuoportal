
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
import com.example.administrator.wankuoportal.beans.ShopGoods;
import com.example.administrator.wankuoportal.global.Apis;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  兑换商城首页adapter
 */
public class RecyclerViewAdapter_shop extends RecyclerView.Adapter<RecyclerViewAdapter_shop.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<ShopGoods.DatasBean> dataList = new ArrayList<>();
    private String[] mVals = new String[]
            {"Hello", "Android"};
    private OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<ShopGoods.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_shop(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.ImageView ima;
        private TextView title;
        private TextView price;
        private TextView priceMoneyTv;
        private TextView manposition;
        private TextView gold_mall_goods_tv_label;


        public ViewHolder(View v) {
            super(v);
            this.manposition = (TextView) v.findViewById(R.id.man_position);
            this.price = (TextView) v.findViewById(R.id.price);
            this.title = (TextView) v.findViewById(R.id.title);
            this.ima = (ImageView) v.findViewById(R.id.ima);
            this.priceMoneyTv = (TextView) v.findViewById(R.id.priceMoneyTv);
            this.gold_mall_goods_tv_label = (TextView) v.findViewById(R.id.gold_mall_goods_tv_label);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopgrid_adapter, parent, false);


        v.setOnClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        try {
            DecimalFormat df = new DecimalFormat("###########0.0000");
            holder.manposition.setText(dataList.get(position).getAproductExchangeMall().getSalesVolume() + "人付款");
            holder.price.setText("金币:"+com.flysand.mylibrary.util.Utils.replaceDoubleZero(df.format(dataList.get(position).getAproductExchangeMall().getPriceShop() * 100d)) );
            holder.title.setText(dataList.get(position).getAgood().getName() + "");
            String imaurl = Apis.Baseima + dataList.get(position).getAgood().getImg();
            Glide.with(mContext).load(imaurl).into(holder.ima);
            holder.itemView.setTag(position);
            holder.priceMoneyTv.setText(com.flysand.mylibrary.util.Utils.replaceDoubleZero(df.format(dataList.get(position).getAproductExchangeMall().getPriceShop())));
            holder.gold_mall_goods_tv_label.setText(dataList.get(position).getAgood().getLabelShop());
        } catch (Exception E) {

        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}