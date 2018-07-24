package com.example.administrator.wankuoportal.ui.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.LeadingTaskAdapter;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.LeadingTaskBean;
import com.flysand.mylibrary.customView.MyRadioGroup;
import com.flysand.mylibrary.util.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 领取任务
 */
@EActivity(R.layout.activity_leading_task)
public class LeadingTaskActivity extends MyBaseActivity implements MyRadioGroup.OnCheckedChangeListener {

    @ViewById
    RecyclerView mRecyclerView;
    @ViewById
    MyRadioGroup leadingTaskMyRadioGroup;


    LeadingTaskAdapter mAdapter;

    List<LeadingTaskBean> beanList = new ArrayList<>();

    @AfterViews
    protected void afterViews() {
        super.afterViews();
        leadingTaskMyRadioGroup.setOnCheckedChangeListener(this);
        mAdapter = new LeadingTaskAdapter(this, beanList);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
        addItemDecoration(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        beanList.addAll(Utils.getTestList(20, new LeadingTaskBean()));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckedChanged(MyRadioGroup myRadioGroup, int i) {

    }
}
