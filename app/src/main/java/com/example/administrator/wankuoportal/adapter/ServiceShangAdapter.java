package com.example.administrator.wankuoportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.SearchFuwushang;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lv 首页底部服务商adapter
 */

public class ServiceShangAdapter extends BaseAdapter {

    private List<SearchFuwushang.DatasBean> dataList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;


    public ServiceShangAdapter(List<SearchFuwushang.DatasBean> mNameList, Context mContext) {
        this.dataList = mNameList;

        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        mInflater = LayoutInflater.from(mContext);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.fuwushang_search, null);
            viewHolder.ima = (ImageView) view.findViewById(R.id.ima);
            viewHolder.shopname = (TextView) view.findViewById(R.id.shopname);
            viewHolder.shoparea = (TextView) view.findViewById(R.id.shoparea);
            viewHolder.shoplevel = (TextView) view.findViewById(R.id.shoplevel);
            viewHolder.shopfuwu = (TextView) view.findViewById(R.id.shopfuwu);
            viewHolder.isbaozhang = (TextView) view.findViewById(R.id.isbaozhang);
            viewHolder.type = (TextView) view.findViewById(R.id.type);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.shopname.setText(dataList.get(position).getAshopData().getShopName());
        viewHolder.shoparea.setText(dataList.get(position).getAshopData().getProvince()+""+dataList.get(position).getAshopData().getCity());

        String fuwufanwei = "";
        for (int i = 0;i<dataList.get(position).getLabellist().size();i++){
            if (fuwufanwei.isEmpty()){
                fuwufanwei = fuwufanwei+dataList.get(position).getLabellist().get(i).getLabelName();
            }else {
                fuwufanwei = fuwufanwei+" "+dataList.get(position).getLabellist().get(i).getLabelName();
            }
        }
        viewHolder.shopfuwu.setText(fuwufanwei);
        if (dataList.get(position).getAshopData().getType()==1){
            viewHolder.type.setText("个人");
        }else if (dataList.get(position).getAshopData().getType()==2){
            viewHolder.type.setText("工作室");
        }else if (dataList.get(position).getAshopData().getType()==3){
            viewHolder.type.setText("公司");
        }


        if (dataList.get(position).getAshopData().getLevel() == 0) {
            viewHolder.shoplevel.setText("级别：普通会员  保证金：0  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
            viewHolder.isbaozhang.setVisibility(View.VISIBLE);
        } else if (dataList.get(position).getAshopData().getLevel() == 1) {
            viewHolder. isbaozhang.setVisibility(View.VISIBLE);
            viewHolder.shoplevel.setText("级别：白金会员  保证金：5000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 2) {
            viewHolder.isbaozhang.setVisibility(View.VISIBLE);
            viewHolder.shoplevel.setText("级别：钻石会员  保证金：10000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 3) {
            viewHolder.isbaozhang.setVisibility(View.VISIBLE);
            viewHolder.shoplevel.setText("级别：皇冠会员  保证金：20000  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 4) {
            viewHolder.isbaozhang.setVisibility(View.VISIBLE);
            viewHolder.shoplevel.setText("级别：尊贵会员  保证金：69800  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        } else if (dataList.get(position).getAshopData().getLevel() == 5) {
            viewHolder.isbaozhang.setVisibility(View.VISIBLE);
            viewHolder.shoplevel.setText("级别：至尊会员  保证金：109800  好评率：" + dataList.get(position).getAshopData().getEvaluate() + "%");
        }
        if (dataList.get(position).getInfo().getImg1().isEmpty()){
            Glide.with(mContext).load(R.drawable.shopnone).into(viewHolder.ima);
        }else {
            String imaurl = Apis.Baseima+dataList.get(position).getInfo().getImg1();
            Glide.with(mContext).load(imaurl).into(viewHolder.ima);
        }


        return view;
    }

    static   class ViewHolder {
        ImageView ima;
        TextView shopname;
        TextView shoparea;
        TextView shoplevel;
        TextView shopfuwu;
        TextView isbaozhang;
        TextView type;

    }
}
