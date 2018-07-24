package com.example.administrator.wankuoportal.aaPackage.mainFragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.MainRankingAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.RankingBean;
import com.example.administrator.wankuoportal.aaPackage.coustom.WaveView;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.ui.DaTi.DTPaiHangActivity;
import com.example.administrator.wankuoportal.ui.DaTi.DaTi_WenActivity;
import com.example.administrator.wankuoportal.ui.DaTi.ShuoMingActivity;
import com.example.administrator.wankuoportal.ui.DaTi.WoDeDTActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyJinBiActivity;
import com.example.administrator.wankuoportal.util.ClickHelper;
import com.example.administrator.wankuoportal.util.HttpDataManager;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/17.
 *     desc    :
 * </pre>
 */
public class AnswerFragment extends MyBaseFragment {

    @BindView(R.id.wave_view_1)
    WaveView waveView1;

    @BindView(R.id.wave_view_2)
    WaveView waveView2;

    @BindView(R.id.mBanner)
    Banner mBanner;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    MainRankingAdapter mAdapter;

    List<Integer> bannerList = Arrays.asList(R.drawable.xianzhuan);
    List<RankingBean> rankingBeans = new ArrayList<>();
    RankingBean myRankingBean;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_answer, container, false);//关联布局文件
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    private void initData() {
        mAdapter = new MainRankingAdapter(getActivity(), rankingBeans);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 0));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        httpGetNoDialog("getRankingAll", new RequestParams(), Apis.getrankingall);
    }

    private void initView() {

        waveView1.setInitialRadius(Utils.dip2px(getActivity(), 20));
        waveView1.setStyle(Paint.Style.FILL);
        waveView1.start();
        waveView2.setInitialRadius(Utils.dip2px(getActivity(), 20));
        waveView2.setStyle(Paint.Style.FILL);
        waveView2.start();

        initBanner(mBanner, bannerList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.memory_answer_layout, R.id.memory_gold_layout, R.id.memory_start_layout, R.id.memory_explanin_layout, R.id.memory_ranking_layout, R.id.guess_answer_layout, R.id.guess_glod_layout, R.id.guess_start_layout, R.id.guess_explain_layout, R.id.guess_ranking_layout})
    public void onViewClicked(View view) {
        if (ClickHelper.isHandle())
            return;
        if (ProjectUtil.isLogin())
            return;
        switch (view.getId()) {
            case R.id.memory_answer_layout://记忆 -> 我的答题
                startActivity(WoDeDTActivity.class, "type", "JY");
                break;
            case R.id.memory_gold_layout://记忆 -> 我的金币
                startActivity(MyJinBiActivity.class);
                break;
            case R.id.memory_explanin_layout://记忆 -> 答题说明
                startActivity(ShuoMingActivity.class, "type", "JY");
                break;
            case R.id.memory_ranking_layout://记忆 -> 答题排行
                startActivity(DTPaiHangActivity.class, "type", "wen");
                break;
            case R.id.guess_answer_layout://猜题 -> 我的答题
                startActivity(WoDeDTActivity.class, "type", "SC");
                break;
            case R.id.guess_glod_layout://猜题 -> 我的金币
                startActivity(MyJinBiActivity.class);
                break;
            case R.id.guess_explain_layout://猜题 -> 答题说明
                startActivity(ShuoMingActivity.class, "type", "SC");
                break;
            case R.id.guess_ranking_layout://猜题 -> 答题排行
                startActivity(DTPaiHangActivity.class, "type", "sc");
                break;
            case R.id.memory_start_layout://记忆赢大奖开始
                checkAnswerPermission("JY", Constant.HttpType.MEMORY);
                break;
            case R.id.guess_start_layout://猜题赢大奖开始
                checkAnswerPermission("SC", Constant.HttpType.ANSWER);
                break;
        }
    }

    private void checkAnswerPermission(String type, String answerType) {
        HttpDataManager.checkAnswerPermission(getActivity(), answerType, new HttpDataManager.onResultListenr() {
            @Override
            public void onStart() {
                showHttpDialog();
            }

            @Override
            public void onSuccess(Object s) {
                dismissHttpDialog();
                Intent intent = new Intent(getActivity(), DaTi_WenActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
            }

            @Override
            public void onFail(Object s) {
                dismissHttpDialog();
            }

        });
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
        if ("getRankingAll".equals(type)) {
            rankingBeans.clear();
            rankingBeans.addAll(JSONUtil.toJavaBeanList(RankingBean.class, jsonObject.getJSONArray("list")));
            myRankingBean = JSONUtil.toJavaBean(RankingBean.class, jsonObject.getString("apiPrizeRecordRanking"));
            mAdapter.notifyDataSetChanged();
        }
    }
}
