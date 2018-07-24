package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.QianDaoJiLu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  搜索服务adapter
 */
public class RecyclerViewAdapter_qiandao extends RecyclerView.Adapter<RecyclerViewAdapter_qiandao.ViewHolder> {

    private Context mContext;
    private List<QianDaoJiLu.DatasBean> dataList = new ArrayList<>();


    public void addAllData(List<QianDaoJiLu.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_qiandao(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView qdtime;
        private TextView qdjifen;

        public ViewHolder(View v) {
            super(v);
            this.qdjifen = (TextView) v.findViewById(R.id.qd_jifen);
            this.qdtime = (TextView) v.findViewById(R.id.qd_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.qiandaojilu_adapter, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.qdjifen.setText("+"+dataList.get(position).getGold()+"金币");
        holder.qdtime.setText(dataList.get(position).getSignDay() + "");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}