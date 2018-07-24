package com.example.administrator.wankuoportal.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.MyInfoAssetBean;
import com.example.administrator.wankuoportal.beans.MyInfoCommonBean;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyInfoRecyclerAdapter extends RecyclerView.Adapter<MyInfoRecyclerAdapter.ViewHolder> {

    private MyInfoActivity myInfoActivity;

    private List<MyInfoAssetBean.DatasBean> mAssetList;
    private List<MyInfoCommonBean.DatasBean> mCommonList;

    private OnItemClickListener mListener;

    public MyInfoRecyclerAdapter(MyInfoActivity activity, List<MyInfoAssetBean.DatasBean> assetList, List<MyInfoCommonBean.DatasBean> commonList) {
        myInfoActivity = activity;
        mAssetList = assetList;
        mCommonList = commonList;
    }

    public void setDataList(List<MyInfoAssetBean.DatasBean> assetList, List<MyInfoCommonBean.DatasBean> commonList) {
        mAssetList = assetList;
        mCommonList = commonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(myInfoActivity).inflate(R.layout.my_info_recycler_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });

        if (position < mAssetList.size()) {
            //显示Asset消息
            MyInfoAssetBean.DatasBean assetDataBean = mAssetList.get(position);
            if (myInfoActivity.isInfoRead(assetDataBean.getId())) {
                holder.my_info_item_iv_red_dot.setVisibility(View.INVISIBLE);
            } else {
                holder.my_info_item_iv_red_dot.setVisibility(View.VISIBLE);
            }
            String title = "店长分红到账";
            String content = "您的每日分红到账啦！！";
            switch (assetDataBean.getConsumptionType()) {
                case 96:
                    title = "管理奖励到账";
                    content = "您的管理奖励到账啦！！";
                    break;
                case 97:
                    title = "升级店长成功";
                    content = "恭喜您，已成功升级店长！！";
                    break;
                case 98:
                    title = "推荐奖励到账";
                    content = "您的推荐奖励到账啦！！";
                    break;
                case 99:
                    title = "店长分红到账";
                    content = "您的每日分红到账啦！！";
                    break;
                case 100:
                    title = "升级店长成功";
                    content = "恭喜您，已成功升级店长！！";
                    break;
                case 101:
                    title = "用户余额充值";
                    content = "恭喜您，已经成功充值！";
                    break;
            }
            holder.my_info_item_tv_title.setText(title);
            holder.my_info_item_tv_content.setText(content);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(assetDataBean.getCreateTime());
                holder.my_info_item_tv_date.setText(getShowDate(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            //显示Common消息
            MyInfoCommonBean.DatasBean commonDataBean = mCommonList.get(position - mAssetList.size());
            if (myInfoActivity.isInfoRead(commonDataBean.getAPushinfoId())) {
                holder.my_info_item_iv_red_dot.setVisibility(View.INVISIBLE);
            } else {
                holder.my_info_item_iv_red_dot.setVisibility(View.VISIBLE);
            }
            holder.my_info_item_tv_title.setText(commonDataBean.getInfoTitle());
            holder.my_info_item_tv_content.setText(commonDataBean.getContent());

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(commonDataBean.getCreateTime());
                holder.my_info_item_tv_date.setText(getShowDate(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mAssetList.size() + mCommonList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    private String getShowDate(Date date) {
        long dif = System.currentTimeMillis() - date.getTime();
        if (dif < 60 * 1000) {
            return myInfoActivity.getString(R.string.my_info_date_just_now);
        } else if (dif < 60 * 60 * 1000) {
            return myInfoActivity.getString(R.string.my_info_date_minutes, dif / 60 / 1000);
        } else if (dif < 24 * 60 * 60 * 1000) {
            return myInfoActivity.getString(R.string.my_info_date_hours, dif / 60 / 60 / 1000);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM" + myInfoActivity.getString(R.string.month)
                    + "dd" + myInfoActivity.getString(R.string.day));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                return dateFormat.format(date);
            } else {
                return calendar.get(Calendar.YEAR) + myInfoActivity.getString(R.string.year);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView my_info_item_iv_red_dot;
        private TextView my_info_item_tv_title;
        private TextView my_info_item_tv_content;
        private TextView my_info_item_tv_date;

        public ViewHolder(View v) {
            super(v);
            my_info_item_iv_red_dot = (ImageView) v.findViewById(R.id.my_info_item_iv_red_dot);
            my_info_item_tv_title = (TextView) v.findViewById(R.id.my_info_item_tv_title);
            my_info_item_tv_content = (TextView) v.findViewById(R.id.my_info_item_tv_content);
            my_info_item_tv_date = (TextView) v.findViewById(R.id.my_info_item_tv_date);
        }
    }
}