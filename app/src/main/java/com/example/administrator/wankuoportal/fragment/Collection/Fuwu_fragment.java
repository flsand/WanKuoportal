package com.example.administrator.wankuoportal.fragment.collection;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_fuwu;
import com.example.administrator.wankuoportal.beans.MyDaTi;
import com.example.administrator.wankuoportal.beans.SearchFuWu;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

import okhttp3.Call;

/**
 * Created by lv on 2017/9/8 收藏服务.
 */

public class Fuwu_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {



    int price;
    Gson gson = new Gson();
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private int mCount = 1;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_fuwu mRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.collection_layout, container, false);//关联布局文件
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_fuwu(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);



        price = 0;
        getData();
        return rootView;
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
    //获取数据
    public void getData() {

        String time = new Date().getTime() + "";
        String token = new UserService(getActivity()).gettoken();
        String content = new UserService(getActivity()).getText();
        token = MD5Util.md5(time + token);

        String url = Apis.Base + Apis.searchserve;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("content", content)
                .addParams("type", type + "")
                .addParams("shoptype", shoptype + "")
                .addParams("level", level + "")
                .addParams("page", mCount + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        SearchFuWu searchFuWu = gson.fromJson(response, SearchFuWu.class);
                        MyDaTi m = gson.fromJson(response, MyDaTi.class);
                        if (m.getCode() == 0) {
                            if (m.getDatas().size() < 15) {
                                //设置是否可以上拉刷新
                                mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
//                                mRecyclerViewAdapter.addAllData(searchFuWu.getDatas());
                            } else {
//                                mRecyclerViewAdapter.addAllData(searchFuWu.getDatas());
                                mCount++;
                            }
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        } else if (m.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(m.getMsg());
                            new UserService(getActivity()).setislogin("1");
                        }

                    }
                });

    }
}
