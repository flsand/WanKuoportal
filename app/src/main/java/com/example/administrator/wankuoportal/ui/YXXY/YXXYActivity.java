package com.example.administrator.wankuoportal.ui.YXXY;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_qiyerenzheng;
import com.example.administrator.wankuoportal.adapter.RecyclerViewAdapter_rencai;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.QYRZ;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.QYRZ.InformationActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class YXXYActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.ListView list;
    private Integer[] banner_tu = {R.drawable.dbl, R.drawable.dl};
    private List<Integer> banner_list;
    private List<QYRZ.DatasBean> data_list = new ArrayList<>();
    private com.youth.banner.Banner banner;

    private com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private RecyclerView mRecyclerView;
    private int mCount = 1;
    RecyclerViewAdapter_qiyerenzheng mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yxxy);
        this.banner = (Banner) findViewById(R.id.banner);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);

        this.pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
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

        mRecyclerViewAdapter = new RecyclerViewAdapter_qiyerenzheng(MyApplication.context);
        pullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter_rencai.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(InformationActivity.class,"id",data_list.get(position).getId()+"","title",data_list.get(position).getTitle());
            }
        });

        getdata();
        banner_list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            banner_list.add(banner_tu[i]);
        }
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(banner_list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    //获取数据
    private void getdata() {

        String url = Apis.Base + Apis.second;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("type", "1")
                .addParams("pages", mCount + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        QYRZ s = gson.fromJson(response, QYRZ.class);
                        if (s.getCode() == 0) {

                            if (s.getPageInfo().getNumberOfElements() > 0) {
                                for (int i = 0; i < s.getDatas().size(); i++) {
                                    data_list.add(s.getDatas().get(i));
                                }
                                mRecyclerViewAdapter.clearData();
                                mRecyclerViewAdapter.addAllData(data_list);

                            }
                            if (s.getPageInfo().isLast()) {
                                pullLoadMoreRecyclerView.setPushRefreshEnable(false);
                            } else {
                                pullLoadMoreRecyclerView.setPushRefreshEnable(true);
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

        getdata();
    }
}
