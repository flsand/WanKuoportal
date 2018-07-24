package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.GetXinWen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv
 * on 2017/9/26.  发票adapter
 */
public class RecyclerViewAdapter_gexinwen extends RecyclerView.Adapter<RecyclerViewAdapter_gexinwen.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<GetXinWen.DatasBean> dataList = new ArrayList<>();
    private RecyclerViewAdapter_shop.OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<GetXinWen.DatasBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_gexinwen(Context context) {
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
        private TextView tx;

        public ViewHolder(View v) {
            super(v);
            this.tx = (TextView) v.findViewById(R.id.tx);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.help_adapter, parent, false);
        v.setOnClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.tx.setText(dataList.get(position).getNacigationQuestion());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(RecyclerViewAdapter_shop.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}