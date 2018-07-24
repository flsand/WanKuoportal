package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_jbdetials;
import com.example.administrator.wankuoportal.beans.JBdetial;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;

public class DetailJBActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private android.widget.TextView informationtitle;
    private android.widget.ListView jbdetiallist;
    private android.widget.ImageView back;
    private android.widget.TextView title;
    private RecyclerView mRecyclerView;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerViewAdapter_jbdetials mRecyclerViewAdapter;
    private int mCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jb);
        this.mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initview();
    }

    private void initview() {

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
        mRecyclerViewAdapter = new RecyclerViewAdapter_jbdetials(getBaseContext());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        getData();
    }

    //下拉刷新
    @Override
    public void onRefresh() {

    }

    //上拉加载
    @Override
    public void onLoadMore() {
        getData();
    }

    //获取数据
    public void getData() {

        String time = new Date().getTime() + "";
        String token = new UserService(DetailJBActivity.this).gettoken();
        String accountId = new UserService(DetailJBActivity.this).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getmygoldpage + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("page", mCount + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        JBdetial m = gson.fromJson(response, JBdetial.class);
                        if (m.getCode() == 0) {


                            if (m.getDatas().size() < 15) {
                                //设置是否可以上拉刷新
                                mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                mRecyclerViewAdapter.addAllData(m.getDatas());
                            } else {
                                mRecyclerViewAdapter.addAllData(m.getDatas());
                                mCount++;
                            }
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        } else if (m.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(m.getMsg());
                            new UserService(DetailJBActivity.this).setislogin("1");
                        }else {
                            mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
                        }

                    }
                });

    }
}
