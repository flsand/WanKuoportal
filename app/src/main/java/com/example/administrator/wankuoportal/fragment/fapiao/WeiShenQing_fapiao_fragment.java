package com.example.administrator.wankuoportal.fragment.fapiao;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.google.gson.Gson;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_fapiao;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv on 2017/9/8 搜索服务结果.
 */

public class WeiShenQing_fapiao_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {


    int price;
    Gson gson = new Gson();
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private int mCount = 1;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_fapiao mRecyclerViewAdapter;
    List<String> title = new ArrayList<>();
    private ImageView kaipiaofangima;
    private LinearLayout kaipiaofang;
    private ImageView shenqingzhuangtaiima;
    private LinearLayout shenqingzhuangtai;
    private View grey;
    private android.widget.TextView tvkaipiaofang;
    private android.widget.TextView tvshenqingzhaungtai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fapiao, container, false);//关联布局文件
        this.tvshenqingzhaungtai = (TextView) rootView.findViewById(R.id.tv_shenqingzhaungtai);
        this.tvkaipiaofang = (TextView) rootView.findViewById(R.id.tv_kaipiaofang);
        this.grey = (View) rootView.findViewById(R.id.grey);
        this.shenqingzhuangtai = (LinearLayout) rootView.findViewById(R.id.shenqingzhuangtai);
        this.shenqingzhuangtaiima = (ImageView) rootView.findViewById(R.id.shenqingzhuangtai_ima);
        this.kaipiaofang = (LinearLayout) rootView.findViewById(R.id.kaipiaofang);
        this.kaipiaofangima = (ImageView) rootView.findViewById(R.id.kaipiaofang_ima);
        this.toumingdu = (View) rootView.findViewById(R.id.toumingdu);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.pullLoadMoreRecyclerView);

        //获取mRecyclerView对象
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置是否可以下拉刷新
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        //设置是否可以上拉刷新
        mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
        //显示下拉刷新
        mPullLoadMoreRecyclerView.setRefreshing(false);
        //设置上拉刷新文字
        mPullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        mPullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        mPullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorPrimary);
        mPullLoadMoreRecyclerView.setLinearLayout();

        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerViewAdapter = new RecyclerViewAdapter_fapiao(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);


        price = 0;
        getData();

        //开票方点击
        kaipiaofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvkaipiaofang.setTextColor(getResources().getColor(R.color.colorPrimary));
                kaipiaofangima.setImageResource(R.drawable.shangla);
                initkaipiaofangpop();
            }
        });
        shenqingzhuangtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvshenqingzhaungtai.setTextColor(getResources().getColor(R.color.colorPrimary));
                shenqingzhuangtaiima.setImageResource(R.drawable.shangla);
                initshenqingzhaugtaipop();
            }
        });
        return rootView;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

        getData();
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }


    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    private android.widget.RelativeLayout re;

    //开票方点击现实的popwindow
    private void initkaipiaofangpop() {
        final List<String> listxinzi;
        listxinzi = new ArrayList<String>();

        listxinzi.add("不限");
        listxinzi.add("平台自开");
        listxinzi.add("服务商");


        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.one_list, null);
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        re.setVisibility(View.GONE);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        XinziAdapter x = new XinziAdapter(getActivity(), listxinzi);
        listView.setAdapter(x);
        toumingdu.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initview();
//                xinzilast = listxinzi.get(position);
                tvkaipiaofang.setText(listxinzi.get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                initview();
                toumingdu.setBackgroundResource(R.drawable.touming);

            }
        });

    }
    //申请状态点击现实的popwindow
    private void initshenqingzhaugtaipop() {
        final List<String> listxinzi;
        listxinzi = new ArrayList<String>();

        listxinzi.add("不限");
        listxinzi.add("待申请");
        listxinzi.add("已放弃");
        listxinzi.add("已取消");
        listxinzi.add("已过期");


        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.one_list, null);
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        re.setVisibility(View.GONE);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        XinziAdapter x = new XinziAdapter(getActivity(), listxinzi);
        listView.setAdapter(x);
        toumingdu.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initview();
//                xinzilast = listxinzi.get(position);
                tvshenqingzhaungtai.setText(listxinzi.get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                initview();
                toumingdu.setBackgroundResource(R.drawable.touming);

            }
        });

    }

    private void initview() {
        kaipiaofangima.setImageResource(R.drawable.sanjiao);
        shenqingzhuangtaiima.setImageResource(R.drawable.sanjiao);
        tvkaipiaofang.setTextColor(getResources().getColor(R.color.henhui));
        tvshenqingzhaungtai.setTextColor(getResources().getColor(R.color.henhui));
    }

    int shoptype = 0;
    int type = 0;
    int level = 0;

    //获取数据
    public void getData() {

        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");
        title.add("城市合作");
        title.add("开店赚钱");
        title.add("开店赚钱");
        title.add("广告投放");
        title.add("万哥跑腿");

        mRecyclerViewAdapter.addAllData(title);


    }
}
