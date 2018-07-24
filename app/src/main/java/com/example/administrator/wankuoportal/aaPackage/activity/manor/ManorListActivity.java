package com.example.administrator.wankuoportal.aaPackage.activity.manor;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.ManorListAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.TestBean;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : @date 2018-7-21 11:08:50
 *     desc    :
 * </pre>
 */

public class ManorListActivity extends MyBaseActivity implements RecyclerOnScrollListener.OnScrollListener {

    @BindView(R.id.mamor_tip_tv)
    TextView tipTv;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    ManorListAdapter mAdapter;
    List<TestBean> beanList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manor_list);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        beanList.addAll(Utils.getTestList(20, new TestBean()));
        ProjectUtil.initRecyclerView(mRecyclerView, this);
        int index = getIntent().getIntExtra(Constant.IntentKey.INDEX, -1);
        switch (index) {
            case 0://农业
                tipTv.setText("农业|丰富农业尽在与此");
                setTitleText("农业");
                mAdapter = new ManorListAdapter(this, beanList, "亩");
                break;
            case 1://林业
                tipTv.setText("林业|丰富林业尽在与此");
                setTitleText("林业");
                mAdapter = new ManorListAdapter(this, beanList, "颗");
                break;
            case 2://牧业
                tipTv.setText("牧业|丰富牧业尽在与此");
                setTitleText("牧业");
                mAdapter = new ManorListAdapter(this, beanList, "只");
                break;
            case 3://渔业
                tipTv.setText("渔业|丰富渔业尽在与此");
                setTitleText("渔业");
                mAdapter = new ManorListAdapter(this, beanList, "颗");
                break;
            default:
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setBackgroundResource(R.color.white);
    }

    @Override
    public void onLoadNextPage(View view) {

    }
}
