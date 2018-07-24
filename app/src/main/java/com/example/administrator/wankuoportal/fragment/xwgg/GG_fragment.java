package com.example.administrator.wankuoportal.fragment.xwgg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.google.gson.Gson;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_gexinwen;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_shop;
import com.example.administrator.wankuoportal.beans.GetXinWen;
import com.example.administrator.wankuoportal.beans.Gethelpcenter;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.ui.XWGG.WebActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class GG_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private ListView baselist;
    Gson gson = new Gson();
    Gethelpcenter gethelpcenter;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private View toumingdu;
    private RecyclerView mRecyclerView;
    private int mCount = 1;
    RecyclerViewAdapter_gexinwen mRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.base_recleview, container, false);//关联布局文件
        this.toumingdu = (View) rootView.findViewById(R.id.toumingdu);
        this.pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.pullLoadMoreRecyclerView);
        //获取mRecyclerView对象
        mRecyclerView = pullLoadMoreRecyclerView.getRecyclerView();
        //代码设置scrollbar无效？未解决！
        mRecyclerView.setVerticalScrollBarEnabled(true);
        //设置是否可以下拉刷新
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        //设置是否可以上拉刷新
        pullLoadMoreRecyclerView.setPushRefreshEnable(true);
        //显示下拉刷新
        pullLoadMoreRecyclerView.setRefreshing(false);
        //设置上拉刷新文字
        pullLoadMoreRecyclerView.setFooterViewText("loading");
        //设置上拉刷新文字颜色
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.white);
        //设置加载更多背景色
        pullLoadMoreRecyclerView.setFooterViewBackgroundColor(R.color.colorPrimary);
        pullLoadMoreRecyclerView.setLinearLayout();

        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mRecyclerViewAdapter = new RecyclerViewAdapter_gexinwen(getActivity());
        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        list = new ArrayList<>();
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_shop.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                try {
                    startActivity(WebActivity.class, "url", Apis.Base + Apis.getnavigationdetail + "?navigationtid=" + list.get(position).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return rootView;
    }

    private void getData() {
        String url = Apis.Base + Apis.getxinwengonggao;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("type", "8")
                .addParams("page", mCount + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        GetXinWen s = gson.fromJson(response, GetXinWen.class);
                        if (s.getCode() == 0) {
                            if (s.getDatas().size() < 20 && s.getDatas().size() > 0) {
                                //设置是否可以上拉刷新
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                for (int i = 0; i < s.getDatas().size(); i++) {
                                    list.add(s.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(list);
                            } else if (s.getDatas().size() == 0) {
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                            } else {
                                for (int i = 0; i < s.getDatas().size(); i++) {
                                    list.add(s.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(list);
                                mCount++;
                            }
                            pullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        } else {

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

    List<GetXinWen.DatasBean> list = new ArrayList<>();


    @Override
    public void onPause() {
        super.onPause();
        list.clear();
        mCount = 1;
    }
    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}
