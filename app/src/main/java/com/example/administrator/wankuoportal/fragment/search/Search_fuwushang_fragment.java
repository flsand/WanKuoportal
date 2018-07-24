package com.example.administrator.wankuoportal.fragment.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_fuwushang;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_shop;
import com.example.administrator.wankuoportal.adapter.XinziAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.SearchFuwushang;
import com.example.administrator.wankuoportal.beans.SearchListBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.Store.StoreMainActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.util.JSONUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by lv on 2017/9/8 搜索服务结果.
 */

public class Search_fuwushang_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {


    private android.widget.TextView sxzonghe;
    private android.widget.TextView sxhaoping;
    private android.widget.TextView sxxiaoliang;
    private android.widget.TextView sxdengji;
    private android.widget.LinearLayout sxsx;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_fuwushang mRecyclerViewAdapter;
    private int mCount = 1;
    List<SearchFuwushang.DatasBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fuwushang_layout, container, false);//关联布局文件
        this.toumingdu = (View) rootView.findViewById(R.id.toumingdu);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.pullLoadMoreRecyclerView);
        this.sxsx = (LinearLayout) rootView.findViewById(R.id.sx_sx);
        this.sxdengji = (TextView) rootView.findViewById(R.id.sx_dengji);
        this.sxxiaoliang = (TextView) rootView.findViewById(R.id.sx_xiaoliang);
        this.sxhaoping = (TextView) rootView.findViewById(R.id.sx_haoping);
        this.sxzonghe = (TextView) rootView.findViewById(R.id.sx_zonghe);
        this.grey = (View) rootView.findViewById(R.id.grey);
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_fuwushang(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));

        //按销量排行
        sxxiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                sxxiaoliang.setTextColor(getResources().getColor(R.color.colorPrimary));
                list.clear();
                if (type == 2) {
                    type = 0;
                    mRecyclerViewAdapter.clearData();
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    type = 2;
                    mRecyclerViewAdapter.clearData();
                }
                getData();

            }
        });
        //按综合排行
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
        //按好评度筛选
        sxhaoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                sxhaoping.setTextColor(getResources().getColor(R.color.colorPrimary));
                list.clear();
                mRecyclerViewAdapter.clearData();
                if (type == 1) {
                    type = 0;
                    mRecyclerViewAdapter.clearData();
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    type = 1;
                    mRecyclerViewAdapter.clearData();
                }
                getData();

            }
        });

        //按商家登记排序
        sxdengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                sxdengji.setTextColor(getResources().getColor(R.color.colorPrimary));
                list.clear();
                mRecyclerViewAdapter.clearData();
                if (type == 3) {
                    type = 0;
                    mRecyclerViewAdapter.clearData();
                    sxzonghe.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    type = 3;
                    mRecyclerViewAdapter.clearData();
                }
                getData();

            }
        });


        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_shop.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                new UserService(MyApplication.context).setstoreposition("0");
                new UserService(MyApplication.context).setstoreid(list.get(position).getAshopData().getId() + "");
                startActivity(StoreMainActivity.class);
            }
        });

        getData();
        return rootView;
    }


    private TextView backpop;
    private ListView listView2;
    private ListView listView;
    private android.widget.RelativeLayout re;
    private TextView jingyantx;
    private LinearLayout linjingyan;
    private TextView xuelitx;
    private LinearLayout linxueli;
    private TextView queding;
    String shoptypetx = "不限";
    String leveltx = "不限";
    private TextView xuanxiang1;
    private TextView xuanxiang2;
    private View grey;

    //更多点击现实的popwindow
    private void initmorepop() {


        View contentView = LayoutInflater.from(MyApplication.context).inflate(R.layout.shaixuan_more_layout, null);
        this.xuanxiang2 = (TextView) contentView.findViewById(R.id.xuanxiang2);
        this.xuanxiang1 = (TextView) contentView.findViewById(R.id.xuanxiang1);
        this.queding = (TextView) contentView.findViewById(R.id.queding);
        this.linxueli = (LinearLayout) contentView.findViewById(R.id.lin_xueli);
        this.xuelitx = (TextView) contentView.findViewById(R.id.xueli_tx);
        this.linjingyan = (LinearLayout) contentView.findViewById(R.id.lin_jingyan);
        this.jingyantx = (TextView) contentView.findViewById(R.id.jingyan_tx);
        xuanxiang1.setText("商铺等级");
        xuanxiang2.setText("商铺类型");
        xuelitx.setText(shoptypetx + "");
        jingyantx.setText(leveltx + "");
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        toumingdu.setBackgroundResource(R.drawable.bg_transparent);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                list.clear();
                mRecyclerViewAdapter.clearData();
                getData();
            }
        });
        linjingyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listxinzi = new ArrayList<String>();
                listxinzi.add("不限");
                listxinzi.add("普通会员");
                listxinzi.add("白金会员");
                listxinzi.add("钻石会员");
                listxinzi.add("皇冠会员");
                listxinzi.add("尊贵会员");
                listxinzi.add("至尊会员");
                popupWindow.dismiss();
                initmoreclickpop(listxinzi, "jingyan");
            }
        });

        linxueli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listxinzi = new ArrayList<String>();
                listxinzi.add("不限");
                listxinzi.add("个人");
                listxinzi.add("工作室");
                listxinzi.add("公司");
                popupWindow.dismiss();
                initmoreclickpop(listxinzi, "xueli");
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                initview();
                toumingdu.setBackgroundResource(R.drawable.touming);
            }
        });

    }

    //更多点击内部内容时显示的popwindow
    private void initmoreclickpop(final List<String> listxinzi, final String type) {

        View contentView = LayoutInflater.from(MyApplication.context).inflate(R.layout.one_list, null);
        this.backpop = (TextView) contentView.findViewById(R.id.backpop);
        this.listView = (ListView) contentView.findViewById(R.id.listView);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);//点击外部收起
        this.re = (RelativeLayout) contentView.findViewById(R.id.re);
        XinziAdapter x = new XinziAdapter(MyApplication.context, listxinzi);
        listView.setAdapter(x);
        toumingdu.setBackgroundResource(R.drawable.bg_transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                initview();
                if (type.equals("xueli")) {
                    shoptypetx = listxinzi.get(position);
                    shoptype = position;
                } else {
                    leveltx = listxinzi.get(position);
                    level = position;
                }
                popupWindow.dismiss();
                initmorepop();
            }
        });
        backpop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                initmorepop();
            }
        });

        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(grey);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {

//                initview();
                toumingdu.setBackgroundResource(R.drawable.touming);

            }
        });

    }


    //初始化筛选条件
    private void initview() {
        sxdengji.setTextColor(getResources().getColor(R.color.henhui));
        sxhaoping.setTextColor(getResources().getColor(R.color.henhui));
        sxxiaoliang.setTextColor(getResources().getColor(R.color.henhui));
        sxzonghe.setTextColor(getResources().getColor(R.color.henhui));
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
    public String labelId = "";

    //获取数据
    public void getData() {

        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String content = new UserService(MyApplication.context).getText();
        token = MD5Util.md5(time + token);

        Utils.print("content = " + content);
        String url = Apis.Base + Apis.getGood;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("content", content)
                .addParams("type", type + "")
                .addParams("shoptype", shoptype + "")
                .addParams("level", level + "")
                .addParams("labelId", labelId + "")
                .addParams("page", mCount + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception {

                        SearchListBean searchListBean = JSONUtil.toJavaBean(SearchListBean.class,body);
//                        //设置是否可以上拉刷新
//                        mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
//                        mRecyclerViewAdapter.addAllData(searchListBean.getDatas());
//                        for (int i = 0; i < searchFuwushang.getDatas().size(); i++) {
//                            list.add(searchFuwushang.getDatas().get(i));
//                        }
//                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        return true;
                    }

                });

    }
}
