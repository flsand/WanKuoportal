package com.example.administrator.wankuoportal.aaPackage.fragment.manor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.HanorRecordAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.TestBean;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.util.ProjectUtil;
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
 *     desc    : 庄园记录
 * </pre>
 */
public class HanorRecordFragment extends MyBaseFragment implements RecyclerOnScrollListener.OnScrollListener {

    HanorRecordAdapter mAdapter;

    List<TestBean> beanList = new ArrayList<>();
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manor_record, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

        beanList.addAll(Utils.getTestList(20, new TestBean()));
        mAdapter = new HanorRecordAdapter(getActivity(), beanList);
        ProjectUtil.initRecyclerView(mRecyclerView,this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadNextPage(View view) {

    }
}
