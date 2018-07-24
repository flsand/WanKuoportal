package com.example.administrator.wankuoportal.ui.team;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.MineTeamProfitAdapter;
import com.example.administrator.wankuoportal.adapter.MineTeamRewardAdapter;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.MineTeamBean;
import com.example.administrator.wankuoportal.beans.MyTeamStatusAllBean;
import com.example.administrator.wankuoportal.beans.StatisticsBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BuildConfig;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.OkHttpUtil;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.customView.MyRadioGroup;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 我的团队
 */
@EActivity(R.layout.activity_mine_team)
public class MineTeamActivity extends MyBaseActivity implements MyRadioGroup.OnCheckedChangeListener {

    @ViewById
    MyRadioGroup mineTeamRRadioGroup;
    @ViewById
    ImageView mineTeamAllCornerIv;//对号
    @ViewById
    TextView mineTeamOneTv;
    @ViewById
    TextView mineTeamTwoTv;
    @ViewById
    TextView mineTeamThreeTv;
    @ViewById
    TextView mineTeamFourTv;
    @ViewById
    TextView mineTeamTotalAmountTv;//奖励总金额
    @ViewById
    TextView mineTeamTotalAmountOfTv;//奖励总额
    @ViewById
    TextView mineTeamTotalRevenueTv;//收益总额
    @ViewById
    TextView mineTeamTotalPeopleTv;//推荐总人数
    @ViewById
    TextView mineTeamRecommendedAwardsTv;//推荐奖励
    @ViewById
    TextView mineTeamManagerAwardsTv;//管理奖励
    @ViewById
    TextView mineTeamProfitTv;//推荐收益
    @ViewById
    TextView mineTeamManagerProfitTv;//管理收益

    @ViewById
    RecyclerView mRecyclerView;
    //奖励
    MineTeamRewardAdapter mRewardAdapter;
    //收益
    MineTeamProfitAdapter mProfitAdapter;
    List<MineTeamBean> rewardBeanList = new ArrayList<>();
    List<MineTeamBean> profitBeanList = new ArrayList<>();

    private String type = "";
    private String pageType = "";
    //http://192.168.1.118:8080/wankuoportal/img/downloadimage/9c4382b6d51f4c35b8d755b95824d7bc

    /**
     * 初始化View的方法
     */
    @AfterViews
    protected void afterViews() {
        mineTeamRRadioGroup.setOnCheckedChangeListener(this);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
        mProfitAdapter = new MineTeamProfitAdapter(this, profitBeanList);
        mRewardAdapter = new MineTeamRewardAdapter(this, rewardBeanList);
        addItemDecoration(mRecyclerView);
        findViewById(R.id.mineTeamAllRb).performClick();
    }

    /**
     * 获取统计数据
     */
    private void getStatisticsData() {
        /*RequestParams params = new RequestParams();
        params.put("type", type);
        params.put("pageType", pageType);
        httpPost("mineTeamStatsUrl", params, Apis.mineTeamStatsUrl);*/

        String time = new Date().getTime() + "";
        String token = new UserService(getApplicationContext()).gettoken();
        final String accountId = new UserService(this).getaccountid();
        token = MD5Util.md5(time + token);

        String url;
        url = Apis.Base;
        url = url + Apis.mineTeamStatsUrl
                + "?token=" + accountId + "," + time + "," + token
                + "&accountId=" + Integer.valueOf(accountId)
                + "&type=" + type
                + "&pageType=" + pageType;

        Log.d("qzw", "url = " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                MyTeamStatusAllBean bean = gson.fromJson(result, MyTeamStatusAllBean.class);

                MineTeamActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mineTeamTotalAmountTv.setText(bean.getData().getJlze());
                        mineTeamTotalAmountOfTv.setText(bean.getData().getSyze());
                        mineTeamTotalRevenueTv.setText(bean.getData().getTjzr());
                        mineTeamTotalPeopleTv.setText(bean.getData().getGlzr());
                    }
                });
            }
        });
    }

    private void getRecommendData() {
        Log.d("qzw", "getRecommendData");
        RequestParams params = new RequestParams();
        params.put("type", type);
        params.put("pageType", pageType);
        httpPost("mineTeamListUrl", params, Apis.mineTeamListUrl);

        String time = new Date().getTime() + "";
        String token = new UserService(getApplicationContext()).gettoken();
        final String accountId = new UserService(this).getaccountid();
        token = MD5Util.md5(time + token);

        String url;
        url = Apis.Base;

        url = url + Apis.mineTeamListUrl
                + "?token=" + accountId + "," + time + "," + token
                + "&accountId=" + Integer.valueOf(accountId)
                + "&type=" + type
                + "&pageType=" + pageType;

        Log.d("qzw", "url = " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d("qzw", "result = " + result);
                Gson gson = new Gson();
            }
        });
    }


    @Override
    public void onCheckedChanged(MyRadioGroup myRadioGroup, int i) {
        Log.d("qzw", "onCheckedChanged");
        profitBeanList.clear();
        mRecyclerView.setAdapter(mProfitAdapter);
        mProfitAdapter.notifyDataSetChanged();
        switch (myRadioGroup.getCheckedRadioButtonId()) {
            case R.id.mineTeamAllRb://全部
                type = "2";
                pageType = "0";
                changeAllState();
                break;
            case R.id.mineTeamRecommendedAwardsRb://推荐奖励
                type = "0";
                pageType = "1";
                changeRecommendedAwards();
                break;
            case R.id.mineTeamManagerAwardsRb://管理奖励
                type = "1";
                pageType = "1";
                changeManagerAwards();
                break;
            case R.id.mineTeamProfitRb://推荐收益
                type = "0";
                pageType = "2";
                changeProfit();
                break;
            case R.id.mineTeamManagerProfitRb://管理收益
                type = "1";
                pageType = "2";
                changeManagerProfit();
                break;
        }
        getStatisticsData();
        getRecommendData();
    }

    /**
     * 切换全部
     */
    private void changeAllState() {
        mineTeamAllCornerIv.setVisibility(View.VISIBLE);
        mineTeamOneTv.setText("奖励总金额");
        mineTeamTwoTv.setText("收益总额");
        mineTeamThreeTv.setText("推荐人数");
        mineTeamFourTv.setText("管理人数");
    }

    /**
     * 推荐奖励
     */
    private void changeRecommendedAwards() {
        mineTeamOneTv.setText("奖励总金额");
        mineTeamTwoTv.setText("推荐奖励");
        mineTeamThreeTv.setText("管理奖励");
        mineTeamFourTv.setText("推荐人数");
    }

    /**
     * 管理奖励
     */
    private void changeManagerAwards() {
        mineTeamOneTv.setText("奖励总金额");
        mineTeamTwoTv.setText("推荐奖励");
        mineTeamThreeTv.setText("管理奖励");
        mineTeamFourTv.setText("管理人数");
    }

    /**
     * 推荐收益
     */
    private void changeProfit() {
        mineTeamOneTv.setText("店铺总数");
        mineTeamTwoTv.setText("总体收益");
        mineTeamThreeTv.setText("推荐收益");
        mineTeamFourTv.setText("推荐人数");
    }

    /**
     * 管理收益
     */
    private void changeManagerProfit() {
        mineTeamOneTv.setText("店铺总数");
        mineTeamTwoTv.setText("总体收益");
        mineTeamThreeTv.setText("管理收益");
        mineTeamFourTv.setText("管理人数");
    }

    private void setStatisticsData(StatisticsBean bean) {

        switch (mineTeamRRadioGroup.getCheckedRadioButtonId()) {
            case R.id.mineTeamAllRb://全部
                mineTeamTotalAmountTv.setText(bean.getJlze() + "元");//奖励总金额
                mineTeamTotalAmountOfTv.setText(bean.getTjje() + "元");//推荐奖励
                mineTeamTotalRevenueTv.setText(bean.getGlje() + "元");//管理奖励
                mineTeamTotalPeopleTv.setText(bean.getTjzr() + "人");//推荐人数
                break;
            case R.id.mineTeamRecommendedAwardsRb://推荐奖励
                mineTeamTotalAmountTv.setText(bean.getTjzr() + "元");//奖励总金额
                mineTeamTotalAmountOfTv.setText(bean.getTjzr() + "元");//奖励总额
                mineTeamTotalRevenueTv.setText(bean.getTjzr() + "元");//收益总额
                mineTeamTotalPeopleTv.setText(bean.getTjzr() + "人");//推荐总人数
                break;
            case R.id.mineTeamManagerAwardsRb://管理奖励
                mineTeamTotalAmountTv.setText(bean.getJlze() + "元");//奖励总金额
                mineTeamTotalAmountOfTv.setText(bean.getTjje() + "元");//推荐奖励
                mineTeamTotalRevenueTv.setText(bean.getGlje() + "元");//管理奖励
                mineTeamTotalPeopleTv.setText(bean.getGlzr() + "人");//管理人数
                break;
            case R.id.mineTeamProfitRb://推荐收益
                mineTeamTotalAmountTv.setText(bean.getDpzs());//店铺总数
                mineTeamTotalAmountOfTv.setText(bean.getZtsy() + "元");//总体收益
                mineTeamTotalRevenueTv.setText(bean.getTjsy() + "元");//推荐收益
                mineTeamTotalPeopleTv.setText(bean.getTjzr() + "人");//推荐人数
                break;
            case R.id.mineTeamManagerProfitRb://管理收益
                mineTeamTotalAmountTv.setText(bean.getDpzs());//店铺总数
                mineTeamTotalAmountOfTv.setText(bean.getZtsy() + "元");//总体收益
                mineTeamTotalRevenueTv.setText(bean.getGlsy() + "元");//管理收益
                mineTeamTotalPeopleTv.setText(bean.getGlzr());//管理人数
                break;
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) {
        super.onHttpSuccess(type, jsonObject);
        if ("mineTeamStatsUrl".equals(type)) {
            StatisticsBean bean = JSONUtil.toJavaBean(StatisticsBean.class, jsonObject);
            setStatisticsData(bean);
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int page, int size, int count) {
        super.onHttpSuccess(type, jsonArray, page, size, count);
        if ("mineTeamListUrl".equals(type)) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
            switch (mineTeamRRadioGroup.getCheckedRadioButtonId()) {
                case R.id.mineTeamAllRb://全部
                case R.id.mineTeamRecommendedAwardsRb://推荐奖励
                case R.id.mineTeamManagerAwardsRb://管理奖励
                    rewardBeanList.clear();
                    rewardBeanList.addAll(JSONUtil.toJavaBeanList(MineTeamBean.class, jsonArray));
                    mRecyclerView.setAdapter(mRewardAdapter);
                    break;
                case R.id.mineTeamProfitRb://推荐收益
                case R.id.mineTeamManagerProfitRb://管理收益
                    profitBeanList.clear();
                    profitBeanList.addAll(JSONUtil.toJavaBeanList(MineTeamBean.class, jsonArray));
                    mRecyclerView.setAdapter(mProfitAdapter);
                    break;
            }
        }
    }

}
