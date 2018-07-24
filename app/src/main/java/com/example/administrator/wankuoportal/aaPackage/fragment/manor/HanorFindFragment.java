package com.example.administrator.wankuoportal.aaPackage.fragment.manor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.adapter.ActivityAdapter;
import com.example.administrator.wankuoportal.aaPackage.adapter.ChoicenessAdapter;
import com.example.administrator.wankuoportal.aaPackage.bean.TestBean;
import com.example.administrator.wankuoportal.base.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.listener.RecyclerOnScrollListener;

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
 *     desc    : 庄园发现
 * </pre>
 */
public class HanorFindFragment extends MyBaseFragment implements RecyclerOnScrollListener.OnScrollListener {

    @BindView(R.id.find_loaction_tv)
    TextView findLoactionTv;
    @BindView(R.id.preferential_tv)
    TextView preferentialTv;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;

    Unbinder unbinder;
    //精选
    ChoicenessAdapter choicenessAdapter;
    //活动
    ActivityAdapter activityAdapter;

    List<TestBean> choicenessBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_manor_find, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        findLoactionTv.setText(MyApplication.getInstance().getLocation().getCity().replace("市", ""));

        TabLayout.Tab tab = mTabLayout.newTab().setText("精选");
        mTabLayout.addTab(tab, true);
        tab = mTabLayout.newTab().setText("活动");
        mTabLayout.addTab(tab);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mRecyclerView.stopScroll();
                int position = tab.getPosition();
                com.flysand.mylibrary.util.Utils.print(position == 0 ? "精选" : "活动");
                if (position == 0) {
                    preferentialTv.setVisibility(View.GONE);
                    mRecyclerView.setAdapter(choicenessAdapter);
                } else {
                    preferentialTv.setVisibility(View.VISIBLE);
                    mRecyclerView.setAdapter(activityAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        choicenessBeanList.addAll(com.flysand.mylibrary.util.Utils.getTestList(20, new TestBean()));
        choicenessAdapter = new ChoicenessAdapter(getActivity(), choicenessBeanList);
        activityAdapter = new ActivityAdapter(getActivity(), choicenessBeanList);
        mRecyclerView.setAdapter(choicenessAdapter);
        ProjectUtil.initRecyclerView(mRecyclerView, this);
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
