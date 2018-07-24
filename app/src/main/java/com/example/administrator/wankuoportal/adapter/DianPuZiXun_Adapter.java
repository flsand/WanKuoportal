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
import com.example.administrator.wankuoportal.beans.InitShop;
import com.example.administrator.wankuoportal.global.Apis;

import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class DianPuZiXun_Adapter extends BaseAdapter {
    private Context context;
    private List<InitShop.DataBean.InformationListBean> list;


    public DianPuZiXun_Adapter(Context context, List<InitShop.DataBean.InformationListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolde viewHolde = null;
        LayoutInflater myInflater = LayoutInflater.from(context);

        if (convertView == null) {
            viewHolde = new ViewHolde();
            convertView = myInflater.inflate(R.layout.dianpuzixun_layout, null);
            viewHolde.title = (TextView) convertView.findViewById(R.id.title);
            viewHolde.body = (TextView) convertView.findViewById(R.id.body);
            viewHolde.end = (TextView) convertView.findViewById(R.id.end);
            viewHolde.ima = (ImageView) convertView.findViewById(R.id.ima);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolde) convertView.getTag();
        }
        viewHolde.title.setText(list.get(position).getTitle());
        viewHolde.body.setText(list.get(position).getSummary());
        viewHolde.end.setText("来源:万阔平台 浏览量:"+list.get(position).getBrowseCount());
        Glide.with(context).load(Apis.Baseima+list.get(position).getPictureId()).into(viewHolde.ima);


        return convertView;
    }


    public class ViewHolde {
        ImageView ima;
        TextView title;
        TextView body;
        TextView end;

    }
}
