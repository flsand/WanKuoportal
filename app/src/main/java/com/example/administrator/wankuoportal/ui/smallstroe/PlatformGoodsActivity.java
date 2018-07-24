package com.example.administrator.wankuoportal.ui.smallstroe;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.PlatformGoodsAdapter;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.PlatformGoodsBean;
import com.flysand.mylibrary.util.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 平台商品
 */
@EActivity(R.layout.activity_platform_goods)
public class PlatformGoodsActivity extends MyBaseActivity {


    @ViewById
    RecyclerView mRecyclerView;


    PlatformGoodsAdapter mAdapter;

    List<PlatformGoodsBean> beanList = new ArrayList<>();

    @AfterViews
    protected void afterViews() {
        super.afterViews();
        beanList.addAll(Utils.getTestList(20, new PlatformGoodsBean()));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        mAdapter = new PlatformGoodsAdapter(this, beanList);
        mRecyclerView.setAdapter(mAdapter);

    }
}
