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
 * on 2017/9/26.  企业招聘adapter
 */
public class RecyclerViewAdapter_zhaopin extends RecyclerView.Adapter<RecyclerViewAdapter_zhaopin.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<String> dataList = new ArrayList<>();
    private String[] mVals = new String[]
            {"Hello", "Android"};
    private OnItemClickListener mOnItemClickListener = null;


    public void addAllData(List<String> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    public RecyclerViewAdapter_zhaopin(Context context) {
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
        private TextView title;
        private TextView price;
        private TextView num;
        private TextView look;
        private TextView companyname;
        private TextView time;

        public ViewHolder(View v) {
            super(v);
//            this.idflowlayoutremen = (TagFlowLayout) v.findViewById(R.id.id_flowlayout_remen);
            this.time = (TextView) v.findViewById(R.id.time);
            this.companyname = (TextView) v.findViewById(R.id.company_name);
            this.look = (TextView) v.findViewById(R.id.look);
            this.num = (TextView) v.findViewById(R.id.num);
            this.price = (TextView) v.findViewById(R.id.price);
            this.title = (TextView) v.findViewById(R.id.title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recruitment_adapter, parent, false);

        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.txname.setText(dataList.get(position).getAshopData().getShopName()+"");
//        holder.txposion.setText(dataList.get(position).getAgood().getSalesVolume()+"");
//        holder.txprice.setText(dataList.get(position).getAgood().getPirce()+dataList.get(position).getAgood().getUnit()+"");
//        holder.txtitle.setText(dataList.get(position).getAgood().getName()+"");
//        Glide.with(mContext).load(dataList.get(position).getAgood().getImg()).into(holder.imaservice);
        holder.itemView.setTag(position);
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
//        holder.idflowlayoutremen.setAdapter(new TagAdapter<String>(mVals) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                TextView tv = (TextView) mInflater.inflate(R.layout.tv, holder.idflowlayoutremen, false);
//                tv.setText(s);
//                return tv;
//            }
//        });
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