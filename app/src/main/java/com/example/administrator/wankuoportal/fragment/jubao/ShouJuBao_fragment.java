package com.example.administrator.wankuoportal.fragment.jubao;


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
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_jubao;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv on 2017/9/8 搜索服务结果.
 */

public class ShouJuBao_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {


    int price;

    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private int mCount = 1;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_jubao mRecyclerViewAdapter;
    List<String> title = new ArrayList<>();

    private View grey;
    private TextView tvshijian;
    private ImageView shijianima;
    private LinearLayout shijian;
    private TextView tvchulizhaungtai;
    private ImageView chulizhaungtaiima;
    private LinearLayout chulizhaungtai;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.jubao, container, false);//关联布局文件
        this.chulizhaungtai = (LinearLayout) rootView.findViewById(R.id.chulizhaungtai);
        this.chulizhaungtaiima = (ImageView) rootView.findViewById(R.id.chulizhaungtai_ima);
        this.tvchulizhaungtai = (TextView) rootView.findViewById(R.id.tv_chulizhaungtai);
        this.shijian = (LinearLayout) rootView.findViewById(R.id.shijian);
        this.shijianima = (ImageView) rootView.findViewById(R.id.shijian_ima);
        this.tvshijian = (TextView) rootView.findViewById(R.id.tv_shijian);
        this.grey = (View) rootView.findViewById(R.id.grey);
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_jubao(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);


        price = 0;
        getData();

        //发起时间点击
        shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvshijian.setTextColor(getResources().getColor(R.color.colorPrimary));
                shijianima.setImageResource(R.drawable.shangla);
                initkaipiaofangpop();
            }
        });
        chulizhaungtai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvchulizhaungtai.setTextColor(getResources().getColor(R.color.colorPrimary));
                chulizhaungtaiima.setImageResource(R.drawable.shangla);
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

    private RelativeLayout re;

    //开票方点击现实的popwindow
    private void initkaipiaofangpop() {
        final List<String> listxinzi;
        listxinzi = new ArrayList<String>();

        listxinzi.add("不限");
        listxinzi.add("由远到近");
        listxinzi.add("由近到远");


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
                tvshijian.setText(listxinzi.get(position));
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
        listxinzi.add("举证协商期");
        listxinzi.add("判定期");
        listxinzi.add("判断失败");
        listxinzi.add("判定成立");


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
                tvchulizhaungtai.setText(listxinzi.get(position));
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
        shijianima.setImageResource(R.drawable.sanjiao);
        chulizhaungtaiima.setImageResource(R.drawable.sanjiao);
        tvshijian.setTextColor(getResources().getColor(R.color.henhui));
        tvchulizhaungtai.setTextColor(getResources().getColor(R.color.henhui));
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
