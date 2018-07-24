package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.MyDaTi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  我的答题adapter
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<MyDaTi.DatasBean> dataList = new ArrayList<>();



    public void addAllData(List<MyDaTi.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView all;
        private TextView fault;
        private TextView right;
        private TextView jifen;
        public ViewHolder(View v) {
            super(v);
            this.jifen = (TextView) v.findViewById(R.id.jifen);
            this.right = (TextView) v.findViewById(R.id.right);
            this.fault = (TextView) v.findViewById(R.id.fault);
            this.all = (TextView) v.findViewById(R.id.all);
            this.time = (TextView) v.findViewById(R.id.time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wodedati_adapter, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.time.setText(dataList.get(position).getQuestionTime());
        holder.all.setText(dataList.get(position).getRightSum()+dataList.get(position).getWrongSum()+"");
        holder.fault.setText(dataList.get(position).getWrongSum()+"");
        holder.right.setText(dataList.get(position).getRightSum()+"");
        holder.jifen.setText(dataList.get(position).getGold()+"");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}