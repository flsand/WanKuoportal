package com.example.administrator.wankuoportal.ui.shop;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_shop;
import com.example.administrator.wankuoportal.beans.ShopGoods;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ShopActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.TextView sxzonghe;
    private android.widget.ImageView dhpriceima;
    private android.widget.LinearLayout sxprice;
    private android.widget.ImageView dhnewima;
    private android.widget.LinearLayout sxnew;
    private RecyclerView mRecyclerView;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    private RecyclerViewAdapter_shop mRecyclerViewAdapter;
    private View mo;
    int page = 0;
    int type = 0;
    List<ShopGoods.DatasBean> list = new ArrayList<>();
    private TextView paihang;
    private TextView txprice;
    private TextView txnew;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        this.txnew = (TextView) findViewById(R.id.tx_new);
        this.txprice = (TextView) findViewById(R.id.tx_price);
        this.paihang = (TextView) findViewById(R.id.paihang);
        this.sxnew = (LinearLayout) findViewById(R.id.sx_new);
        this.sxprice = (LinearLayout) findViewById(R.id.sx_price);
        this.dhpriceima = (ImageView) findViewById(R.id.dh_price_ima);
        this.sxzonghe = (TextView) findViewById(R.id.sx_zonghe);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        this.mo = (View) findViewById(R.id.mo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));

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
        mPullLoadMoreRecyclerView.setGridLayout(2);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerViewAdapter = new RecyclerViewAdapter_shop(getBaseContext());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_shop.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = list.get(position).getAproductExchangeMall().getId() + "";
                startActivity(ShopDetailActivity.class, "id", id);
            }
        });
        page = 1;
        type = 0;
        getData();


        //综合点击
        sxzonghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chushihuadingbu();
                clearData();
                type = 0;
                page = 1;
                getData();
                sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        //金币点击
        sxprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                if (type != 1 && type != 2) {
                    chushihuadingbu();
                    clearData();
                    type = 1;
                    getData();
                    txprice.setTextColor(getResources().getColor(R.color.colorPrimary));
                    dhpriceima.setImageResource(R.drawable.shang);
                } else if (type == 1) {
                    chushihuadingbu();
                    clearData();
                    type = 2;
                    getData();
                    txprice.setTextColor(getResources().getColor(R.color.colorPrimary));
                    dhpriceima.setImageResource(R.drawable.xia);
                } else if (type == 2) {
                    chushihuadingbu();
                    clearData();
                    type = 0;
                    getData();
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                }

            }
        });
        //新品点击
        sxnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                if (type != 3) {
                    chushihuadingbu();
                    clearData();
                    type = 3;
                    getData();
                    txnew.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    chushihuadingbu();
                    clearData();
                    type = 0;
                    getData();
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                }

            }
        });
        //排行点击
        paihang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                if (type != 4) {
                    chushihuadingbu();
                    clearData();
                    type = 4;
                    getData();
                    paihang.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    chushihuadingbu();
                    clearData();
                    type = 0;
                    getData();
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                }

            }
        });
    }

    //初始化顶部筛选栏
    private void chushihuadingbu() {

        sxzonghe.setTextColor(getResources().getColor(R.color.henhui));
        txnew.setTextColor(getResources().getColor(R.color.henhui));
        txprice.setTextColor(getResources().getColor(R.color.henhui));
        sxzonghe.setTextColor(getResources().getColor(R.color.henhui));
        paihang.setTextColor(getResources().getColor(R.color.henhui));
        dhpriceima.setImageResource(R.drawable.shangxia);
        //设置是否可以上拉刷新
        mPullLoadMoreRecyclerView.setPushRefreshEnable(true);
        list.clear();
    }


    private void getData() {

        String url = Apis.Base + Apis.searchexchangegoods;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("type", type + "")
                .addParams("page", page + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        try {
                            ShopGoods s = gson.fromJson(response, ShopGoods.class);
                            Log.d("qzw", "size = " + s.getDatas().size());
                            if (s.getCode() == 0) {

                                if (s.getDatas().size() < 10) {
                                    mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                    mRecyclerViewAdapter.addAllData(s.getDatas());
                                } else {
                                    mRecyclerViewAdapter.addAllData(s.getDatas());
                                    page++;
                                }
                                for (int i = 0; i < s.getDatas().size(); i++) {
                                    list.add(s.getDatas().get(i));
                                }


                            } else {
                                mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
                            }
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        getData();

    }

    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }


}
