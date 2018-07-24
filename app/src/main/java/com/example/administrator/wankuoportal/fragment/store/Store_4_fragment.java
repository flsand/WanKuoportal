package com.example.administrator.wankuoportal.fragment.store;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_rencai;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_shop_chanpin;
import com.example.administrator.wankuoportal.adapter.Store4_yiji_Adapter;
import com.example.administrator.wankuoportal.beans.StoreGoods;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.Store.StoreChanPinActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class Store_4_fragment extends BaseFragment implements PullLoadMoreRecyclerView.PullLoadMoreListener{

    private ListView baselist;
    private ImageView cityima;
    private ListView yijilist;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;
    private int mCount = 1;
    private RecyclerViewAdapter_shop_chanpin mRecyclerViewAdapter;
    private String labelId = "";
    Store4_yiji_Adapter store4_yiji_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store_fragment4, container, false);//关联布局文件
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_shop_chanpin(getActivity());
        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        this.yijilist = (ListView) rootView.findViewById(R.id.yiji_list);
        yijilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                store4_yiji_adapter.setSeclection(position);
                store4_yiji_adapter.notifyDataSetChanged();
                labelId = labellist.get(position).getId()+"";
                mRecyclerViewAdapter.clearData();
                examplelist.clear();
                pullLoadMoreRecyclerView.setPushRefreshEnable(true);
                mCount=1;
                getshuju2();

            }
        });

        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_rencai.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(StoreChanPinActivity.class,"id",examplelist.get(position).getId()+"");
            }
        });

        getshuju();


        return rootView;
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        getshuju2();
    }



    private List<StoreGoods.DataBean.LabellistBean> labellist = new ArrayList<>();
    private List<StoreGoods.DataBean.AGoodListBean> examplelist = new ArrayList<>();

    //查询带id的数据
    private void getshuju2() {

        String id = new UserService(getActivity()).getstoreid();
        String url = Apis.Base + Apis.shopgoods;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("labelId", labelId)
                .addParams("page", mCount + "")
                .addParams("id", id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        StoreGoods s = gson.fromJson(response, StoreGoods.class);
                        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        if (s.getCode() == 0) {
                            if (s.getData().getAGoodList().size()>0&&s.getData().getAGoodList().size()<15){
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                for (int i = 0; i < s.getData().getAGoodList().size(); i++) {
                                    examplelist.add(s.getData().getAGoodList().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(examplelist);
                            }else if (s.getData().getAGoodList().size()==0){
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                mRecyclerViewAdapter.addAllData(examplelist);
                            }else {
                                for (int i = 0; i < s.getData().getAGoodList().size(); i++) {
                                    examplelist.add(s.getData().getAGoodList().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(examplelist);
                                mCount++;
                            }

                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });

    }

    //获取数据
    private void getshuju() {
        String id = new UserService(getActivity()).getstoreid();
        String url = Apis.Base + Apis.shopgoods;
        labellist = new ArrayList<>();
        examplelist = new ArrayList<>();
        labelId = "";
        mCount=1;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("labelId", labelId)
                .addParams("page", mCount + "")
                .addParams("id", id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        StoreGoods s = gson.fromJson(response, StoreGoods.class);
                        if (s.getCode() == 0) {
                            labelId = s.getData().getLabellist().get(0).getId()+"";
//                            showShort(s.getMsg());
                            for (int i = 0; i < s.getData().getLabellist().size(); i++) {
                                labellist.add(s.getData().getLabellist().get(i));
                            }

                            store4_yiji_adapter = new Store4_yiji_Adapter(getActivity(), labellist);
                            yijilist.setAdapter(store4_yiji_adapter);
                            store4_yiji_adapter.setSeclection(0);
                            store4_yiji_adapter.notifyDataSetChanged();
                            if (s.getData().getAGoodList().size()>0&&s.getData().getAGoodList().size()<15){
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                for (int i = 0; i < s.getData().getAGoodList().size(); i++) {
                                    examplelist.add(s.getData().getAGoodList().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(examplelist);
                            }else if (s.getData().getAGoodList().size()==0){
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(examplelist);
                            }else {
                                for (int i = 0; i < s.getData().getAGoodList().size(); i++) {
                                    examplelist.add(s.getData().getAGoodList().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(examplelist);
                                mCount++;
                            }
                        } else {
                            showShort(s.getMsg());
                        }
                    }
                });

    }
}
