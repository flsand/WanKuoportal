package com.example.administrator.wankuoportal.fragment.order;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_order_huiyuan;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_rencai;
import com.example.administrator.wankuoportal.beans.Order_huiyuan;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.会员待发订单
 */

public class OrderDaiFa_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private ListView baselist;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private View toumingdu;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_order_huiyuan mRecyclerViewAdapter;
    private int mCount = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.order_main_layout, container, false);//关联布局文件
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_order_huiyuan(getActivity());
        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_rencai.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO: 2017/10/31 0031
            }
        });
        getData();
        return rootView;
    }

    List<Order_huiyuan.DatasBean> list  = new ArrayList<>();
    private void getData() {
        String time = new Date().getTime() + "";
        String token = new UserService(getActivity()).gettoken();
        String accountId = new UserService(getActivity()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getorder + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("state", "2")
                .addParams("page", mCount + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Order_huiyuan order = gson.fromJson(response,Order_huiyuan.class);
                        if (order.getCode() == 0) {
                            if (order.getDatas().size() < 10&&order.getDatas().size()>0) {
                                //设置是否可以上拉刷新
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                for (int i = 0;i<order.getDatas().size();i++){
                                    list.add(order.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(list);
                            } else if (order.getDatas().size()==0){
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                            }else {
                                for (int i = 0;i<order.getDatas().size();i++){
                                    list.add(order.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(list);
                                mCount++;
                            }
                            pullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        } else if (order.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(order.getMsg());
                            new UserService(getActivity()).setislogin("1");
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

    @Override
    public void onPause() {
        super.onPause();
        list.clear();
        mCount =1;
    }


}
