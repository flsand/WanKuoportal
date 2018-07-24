package com.example.administrator.wankuoportal.fragment.collection;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_fuwushang;
import com.example.administrator.wankuoportal.beans.SearchFuwushang;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;

/**
 * Created by lv on 2017/9/8 收藏服务商.
 */

public class Fuwushang_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {



    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private View toumingdu;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_fuwushang mRecyclerViewAdapter;
    private int mCount = 1;

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
        mRecyclerViewAdapter = new RecyclerViewAdapter_fuwushang(getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
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

        String url = Apis.Base + Apis.searchserveprovider;
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
                        L.d("fuwushang",response);
                        SearchFuwushang searchFuwushang = gson.fromJson(response,SearchFuwushang.class);
                        if (searchFuwushang.getCode() == 0) {
                            if (searchFuwushang.getDatas().size() < 15) {
                                //设置是否可以上拉刷新
                                mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                mRecyclerViewAdapter.addAllData(searchFuwushang.getDatas());
                            } else {
                                mRecyclerViewAdapter.addAllData(searchFuwushang.getDatas());
                                mCount++;
                            }
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        } else if (searchFuwushang.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(searchFuwushang.getMsg());
                            new UserService(getActivity()).setislogin("1");
                        }

                    }
                });

    }
}
