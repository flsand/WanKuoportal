package com.example.administrator.wankuoportal.ui.GuZhuWoDe;

import android.os.Bundle;
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
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_tuikuan;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRefundActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    int price;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private int mCount = 1;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_tuikuan mRecyclerViewAdapter;
    List<String> title = new ArrayList<>();
    private View grey;
    private TextView tvshijian;
    private ImageView shijianima;
    private LinearLayout shijian;
    private TextView tvchulizhaungtai;
    private ImageView chulizhaungtaiima;
    private LinearLayout chulizhaungtai;
    private ImageView back;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_refund);
        this.back = (ImageView) findViewById(R.id.back);
        this.chulizhaungtai = (LinearLayout) findViewById(R.id.chulizhaungtai);
        this.chulizhaungtaiima = (ImageView) findViewById(R.id.chulizhaungtai_ima);
        this.tvchulizhaungtai = (TextView) findViewById(R.id.tv_chulizhaungtai);
        this.shijian = (LinearLayout) findViewById(R.id.shijian);
        this.shijianima = (ImageView) findViewById(R.id.shijian_ima);
        this.tvshijian = (TextView) findViewById(R.id.tv_shijian);
        this.grey = (View) findViewById(R.id.grey);
        this.toumingdu = (View) findViewById(R.id.toumingdu);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        mRecyclerViewAdapter = new RecyclerViewAdapter_tuikuan(this);
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


        View contentView = LayoutInflater.from(this).inflate(R.layout.one_list, null);
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        re.setVisibility(View.GONE);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        XinziAdapter x = new XinziAdapter(this, listxinzi);
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
        listxinzi.add("待确定退款");
        listxinzi.add("退款成功");
        listxinzi.add("退款关闭");
        listxinzi.add("已拒绝");
        listxinzi.add("已撤销");
        listxinzi.add("退款处理中");


        View contentView = LayoutInflater.from(this).inflate(R.layout.one_list, null);
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        re.setVisibility(View.GONE);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        ListView listView = (ListView) contentView.findViewById(R.id.listView);
        XinziAdapter x = new XinziAdapter(this, listxinzi);
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
