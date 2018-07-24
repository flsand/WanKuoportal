package com.example.administrator.wankuoportal.fragment.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_fuwu;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.util.JSONUtil;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lv on 2017/9/8 搜索服务结果.
 */

public class Search_fuwu_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {


    private android.widget.TextView sxzonghe;
    private android.widget.ImageView sxpriceima;
    private android.widget.LinearLayout sxprice;
    private android.widget.TextView sxxiaoliang;
    private android.widget.LinearLayout sxsx;
    int price;
    Gson gson = new Gson();
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private int mCount = 1;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_fuwu mRecyclerViewAdapter;
    private TextView sxjiage;
    private TextView sxsxtx;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    /**
     * :
     select a.pirce,a.name,a.img,a.salesVolume,b.shopName,b.province,b.city,a.id
      价格， 名称，图片，卖出数量，店铺名称， 省，市， ID
     */
    List<String[]> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fuwu_layout, container, false);//关联布局文件
//        this.sxsxtx = (TextView) rootView.findViewById(R.id.sxsx_tx);
        this.sxjiage = (TextView) rootView.findViewById(R.id.sx_jiage);
        this.toumingdu = (View) rootView.findViewById(R.id.toumingdu);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.pullLoadMoreRecyclerView);
        this.sxsx = (LinearLayout) rootView.findViewById(R.id.sx_sx);
        this.sxxiaoliang = (TextView) rootView.findViewById(R.id.sx_xiaoliang);
        this.sxprice = (LinearLayout) rootView.findViewById(R.id.sx_price);
        this.sxpriceima = (ImageView) rootView.findViewById(R.id.sx_price_ima);
        this.sxzonghe = (TextView) rootView.findViewById(R.id.sx_zonghe);
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_fuwu(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);


        price = 0;
        sxprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                sxjiage.setTextColor(getResources().getColor(R.color.colorPrimary));
                if (price == 0) {
                    sxpriceima.setImageResource(R.drawable.shang);
                    price = 1;
                } else if (price == 1) {
                    sxpriceima.setImageResource(R.drawable.xia);
                    price = 2;
                } else {
                    sxpriceima.setImageResource(R.drawable.shangxia);
                    price = 0;
                }
            }
        });
        sxzonghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                list.clear();
                mRecyclerViewAdapter.clearData();
                type = 0;
                getData();
            }
        });
        sxxiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                sxxiaoliang.setTextColor(getResources().getColor(R.color.colorPrimary));
                list.clear();
                mRecyclerViewAdapter.clearData();
                type = 0;
                getData();
            }
        });

        getData();
        return rootView;
    }

    private void initview() {
        sxxiaoliang.setTextColor(getResources().getColor(R.color.henhui));
        sxsxtx.setTextColor(getResources().getColor(R.color.henhui));
        sxzonghe.setTextColor(getResources().getColor(R.color.henhui));
        sxjiage.setTextColor(getResources().getColor(R.color.henhui));
        sxpriceima.setImageResource(R.drawable.shangxia);
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

    int shoptype = 0;
    int type = 0;
    int level = 0;
    public static String labelId = "";

    //获取数据
    public void getData() {

        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String content = new UserService(MyApplication.context).getText();
        token = MD5Util.md5(time + token);

        String url = Apis.Base + Apis.getGoodShop;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("content", content)
                .addParams("labelId", labelId)
                .addParams("shoptype", shoptype + "")
                .addParams("sort", level + "")
                .addParams("sortShop", level + "")
                .addParams("page", mCount + "")
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {

                        list.clear();
                        list.addAll(JSONUtil.toJavaBeanList(String[].class, datas));

                        Utils.print("list = " + list.size());

                        mRecyclerViewAdapter.addAllData(list);
                    }
//                    @Override
//                    public void onHttpResponse(String response, int id) throws Exception {
//                        L.d(response);
//                        SearchFuWu searchFuWu = gson.fromJson(response, SearchFuWu.class);
//                        if (searchFuWu.getCode() == 0) {
//                            if (searchFuWu.getDatas().size() < 15 && searchFuWu.getDatas().size() > 0) {
//                                //设置是否可以上拉刷新
//                                mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
//                                for (int i = 0; i < searchFuWu.getDatas().size(); i++) {
//                                    list.add(searchFuWu.getDatas().get(i));
//                                }
//                                mRecyclerViewAdapter.addAllData(list);
//                            } else if (searchFuWu.getDatas().size() == 0) {
//                                mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
//                            } else {
//                                for (int i = 0; i < searchFuWu.getDatas().size(); i++) {
//                                    list.add(searchFuWu.getDatas().get(i));
//                                }
//                                mRecyclerViewAdapter.addAllData(list);
//                                mCount++;
//                            }
//                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
//
//                        } else if (searchFuWu.getCode() == 2) {
//                            startActivity(LoginActivity.class);
//                            showShort(searchFuWu.getMsg());
//                            new UserService(MyApplication.context).setislogin("1");
//                        }
//
//                    }
                });

    }
}
