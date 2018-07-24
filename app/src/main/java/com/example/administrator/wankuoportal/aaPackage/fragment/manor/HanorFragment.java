package com.example.administrator.wankuoportal.aaPackage.fragment.manor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.manor.ManorListActivity;
import com.example.administrator.wankuoportal.aaPackage.adapter.HanorHomeAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.TestBean;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.adapter.HeaderAndFooterWrapper;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.listener.OnItemButtonClickListener;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;
import com.flysand.mylibrary.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/11.
 *     desc    : 庄园主页
 * </pre>
 */
public class HanorFragment extends MyBaseFragment implements RecyclerOnScrollListener.OnScrollListener, View.OnClickListener, OnItemButtonClickListener {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    HanorHomeAdapter mAdapter;
    HeaderAndFooterWrapper mWrapper;
    List<TestBean> beanList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manor_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        ProjectUtil.initRecyclerView(mRecyclerView, this);
        beanList.addAll(Utils.getTestList(20, new TestBean()));
        mAdapter = new HanorHomeAdapter(getActivity(), beanList);
        mWrapper = new HeaderAndFooterWrapper(mAdapter);
        View topView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_manor_home_top, null, false);
        topView.findViewById(R.id.agricultural_tv).setOnClickListener(this);
        topView.findViewById(R.id.forestry_tv).setOnClickListener(this);
        topView.findViewById(R.id.husbandry_tv).setOnClickListener(this);
        topView.findViewById(R.id.fisheries_tv).setOnClickListener(this);

        mWrapper.addHeaderView(topView);
        mRecyclerView.setAdapter(mWrapper);

        mAdapter.setListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadNextPage(View view) {

    }

    @Override
    public void onClick(View v) {
        int index = -1;
        switch (v.getId()) {
            case R.id.agricultural_tv://农业
                index = 0;
                break;
            case R.id.forestry_tv://林业
                index = 1;
                break;
            case R.id.husbandry_tv://牧业
                index = 2;
                break;
            case R.id.fisheries_tv://渔业
                index = 3;
                break;
        }
        if (index != -1) {
            Intent intent = new Intent(getActivity(), ManorListActivity.class);
            intent.putExtra(Constant.IntentKey.INDEX, index);
            startActivity(intent);
        }
    }

    @Override
    public void onButtonClick(BaseAdapter baseAdapter, View view, int i, int i1) {
        switch (i) {
            case 0:
                Utils.print("认购");
                break;
            case 1:
                Utils.print("拼购");
                break;
        }
    }
}
