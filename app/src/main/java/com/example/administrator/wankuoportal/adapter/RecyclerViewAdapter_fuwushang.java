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
import com.example.administrator.wankuoportal.beans.SearchFuwushang;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  搜索服务商adapter
 */
public class RecyclerViewAdapter_fuwushang extends RecyclerView.Adapter<RecyclerViewAdapter_fuwushang.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<SearchFuwushang.DatasBean> dataList = new ArrayList<>();
    private RecyclerViewAdapter_shop.OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<SearchFuwushang.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_fuwushang(Context context) {
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
        private ImageView ima;
        private TextView shopname;
        private TextView type;
        private TextView isbaozhang;
        private TextView shoparea;
        private TextView shoplevel;
        private TextView shopfuwu;

        public ViewHolder(View v) {
            super(v);
            this.shopfuwu = (TextView) v.findViewById(R.id.shopfuwu);
            this.shoplevel = (TextView) v.findViewById(R.id.shoplevel);
            this.shoparea = (TextView) v.findViewById(R.id.shoparea);
            this.isbaozhang = (TextView) v.findViewById(R.id.isbaozhang);
            this.type = (TextView) v.findViewById(R.id.type);
            this.shopname = (TextView) v.findViewById(R.id.shopname);
            this.ima = (ImageView) v.findViewById(R.id.ima);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fuwushang_search, parent, false);


        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String fuwufanwei = "";
        for (int i = 0;i<dataList.get(position).getLabellist().size();i++){
            if (fuwufanwei.isEmpty()){
                fuwufanwei = fuwufanwei+dataList.get(position).getLabellist().get(i).getLabelName();
            }else {
                fuwufanwei = fuwufanwei+" "+dataList.get(position).getLabellist().get(i).getLabelName();
            }
        }
        holder.shopfuwu.setText(fuwufanwei);
        if (dataList.get(position).getAshopData().getType()==1){
            holder.type.setText("个人");
        }else if (dataList.get(position).getAshopData().getType()==2){
            holder.type.setText("工作室");
        }else if (dataList.get(position).getAshopData().getType()==3){
            holder.type.setText("公司");
        }
        holder.itemView.setTag(position);


        if (dataList.get(position).getAshopData().getLevel() == 0) {
            holder.shoplevel.setText("级别：普通会员  保证金：4000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
            holder.isbaozhang.setVisibility(View.VISIBLE);
        } else if (dataList.get(position).getAshopData().getLevel() == 1) {
            holder. isbaozhang.setVisibility(View.VISIBLE);
            holder.shoplevel.setText("级别：白金会员  保证金：4000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 2) {
            holder.isbaozhang.setVisibility(View.VISIBLE);
            holder.shoplevel.setText("级别：钻石会员  保证金：4000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 3) {
            holder.isbaozhang.setVisibility(View.VISIBLE);
            holder.shoplevel.setText("级别：皇冠会员  保证金：4000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 4) {
            holder.isbaozhang.setVisibility(View.VISIBLE);
            holder.shoplevel.setText("级别：尊贵会员  保证金：4000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 5) {
            holder.isbaozhang.setVisibility(View.VISIBLE);
            holder.shoplevel.setText("级别：至尊会员  保证金：4000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        }
        
        holder.shoparea.setText(dataList.get(position).getAshopData().getProvince() +" "+ dataList.get(position).getAshopData().getCity());
        holder.shopname.setText(dataList.get(position).getAshopData().getShopName() + "");
        if (dataList.get(position).getInfo().getImg1().isEmpty()){
            Glide.with(mContext).load(R.drawable.shopnone).into(holder.ima);
        }else {
            String imaurl = Apis.Baseima+dataList.get(position).getInfo().getImg1();
            Glide.with(mContext).load(imaurl).into(holder.ima);
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

    public void setOnItemClickListener(RecyclerViewAdapter_shop.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}