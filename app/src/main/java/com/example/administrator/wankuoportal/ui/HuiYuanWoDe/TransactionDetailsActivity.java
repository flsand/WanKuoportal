package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_jiaoyixiangqiong;
import com.example.administrator.wankuoportal.beans.Getmywalletpage;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
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

public class TransactionDetailsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener{

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.RelativeLayout basetoolbar;
    private List<String> topname;//顶部导航名称
    private ArrayList<Fragment> topFragments;
    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private View toumingdu;
    private int mCount = 1;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter_jiaoyixiangqiong mRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);
        this.toumingdu = (View) findViewById(R.id.toumingdu);
        this.pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        this.basetoolbar = (RelativeLayout) findViewById(R.id.base_toolbar);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        //返回按钮点击
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        mRecyclerViewAdapter = new RecyclerViewAdapter_jiaoyixiangqiong(TransactionDetailsActivity.this);
        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

        getData();

    }
    List<Getmywalletpage.DatasBean> list  = new ArrayList<>();
    private void getData() {


        String time = new Date().getTime()+"";
        String token = new UserService(TransactionDetailsActivity.this).gettoken();
        String accountId = new UserService(TransactionDetailsActivity.this).getaccountid();
        token = MD5Util.md5(time+token);
        String url = Apis.Base + Apis.getmywalletpage+"?token=" +accountId+","+time+","+token;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("page", mCount+"")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Getmywalletpage s = gson.fromJson(response,Getmywalletpage.class);
                        if (s.getCode() == 0) {
                            if (s.getDatas().size() < 15&&s.getDatas().size()>0) {
                                //设置是否可以上拉刷新
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                                for (int i = 0;i<s.getDatas().size();i++){
                                    list.add(s.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(list);
                            } else if (s.getDatas().size()==0){
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                            }else {
                                for (int i = 0;i<s.getDatas().size();i++){
                                    list.add(s.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(list);
                                mCount++;
                            }
                            pullLoadMoreRecyclerView.setPullLoadMoreCompleted();

                        } else if (s.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            showShort(s.getMsg());
                            new UserService(TransactionDetailsActivity.this).setislogin("1");
                        }
                    }
                });

    }

    public void clearData() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
