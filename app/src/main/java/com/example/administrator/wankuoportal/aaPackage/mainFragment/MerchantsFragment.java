package com.example.administrator.wankuoportal.aaPackage.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.merchants.ClassificationDetailActivity;
import com.example.administrator.wankuoportal.aaPackage.adapter.MerchantsLeftAdapter;
import com.example.administrator.wankuoportal.aaPackage.adapter.MerchantsRightAdapter;
import com.example.administrator.wankuoportal.aaPackage.adapter.MerchantsSubDetailAdapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.base.MyBaseFragment;
import com.example.administrator.wankuoportal.beans.NodeBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.base.BaseAdapter;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.RecyclerViewItemClickListener;
import com.flysand.mylibrary.util.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/17.
 *     desc    : 商家分类
 * </pre>
 */
public class MerchantsFragment extends MyBaseFragment implements RecyclerViewItemClickListener, MerchantsRightAdapter.onPackUpClickListener {

    @BindView(R.id.merchants_left_recyclerView)
    RecyclerView mLeftRecyclerView;

    @BindView(R.id.merchants_right_recyclerView)
    RecyclerView mRightRecyclerView;

    @BindView(R.id.fragment_title_tv)
    TextView titleTv;

    Unbinder unbinder;

    MerchantsLeftAdapter mLeftAdapter;
    MerchantsRightAdapter mRightAdapter;

    private int subLabelPosition = 0;
    //左边一级
    ArrayList<NodeBean> leftBeanList = new ArrayList<>();
    //右边二级
    ArrayList<NodeBean> rightBeanList = new ArrayList<>();
    //右边三级
    ArrayList<NodeBean> nodesDetailBeans = new ArrayList<>();

    private int spanCount = 3;
    //当前点击的位置 分类
    private int clickPosition = -1;

    GridLayoutManager layoutManager;
    //二级列表数据缓存
    private Map<String, List<NodeBean>> cacheMap = new ConcurrentHashMap<>();

    protected int leftClickPosition = 0;
    protected int rightClickPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgment_merchants_layout, container, false);//关联布局文件
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        initData();
        return rootView;
    }

    private void initView() {
        titleTv.setText(getTitleText());
        clickPosition = -1;
        for (int i = 0; i < 10; i++) {
            leftBeanList.add(new NodeBean());
        }
        mLeftAdapter = new MerchantsLeftAdapter(this, leftBeanList, 0);
        mLeftRecyclerView.setAdapter(mLeftAdapter);
        mLeftRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, 1));
        initRightListBean();
        mRightAdapter = new MerchantsRightAdapter(this, rightBeanList, nodesDetailBeans);
        layoutManager = new GridLayoutManager(getActivity(), spanCount);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == clickPosition ? spanCount : 1;
            }
        });
        mRightAdapter.setOnPackUpClickListener(this);
        mRightRecyclerView.setLayoutManager(layoutManager);
        mRightRecyclerView.setAdapter(mRightAdapter);
    }

    protected String getTitleText() {
        return "商家分类";
    }

    private void initRightListBean() {
        for (int i = 0; i < 21; i++) {
            rightBeanList.add(new NodeBean());
        }
    }

    public void setSubLabelPosition(int subLabelPosition) {
        try {
            leftClick(subLabelPosition);
            mLeftAdapter.setClickPosition(subLabelPosition);
            mLeftAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initData() {
        httpPost("initMerchantsData", new RequestParams(), Apis.getLabel);
    }

    private void getSubData(String id, String type) {
        httpPost(type + id, new RequestParams("id", id), Apis.getLabel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 子菜单收起点击
     *
     * @param v
     */
    @Override
    public void onPackUpClick(View v) {
        mRightAdapter.setClickPosition(clickPosition = -1);
        mRightAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecyclerViewItemClick(BaseAdapter baseAdapter, View view, int position) {
        Utils.print("position = " + position);
        if (mLeftAdapter == baseAdapter) {//左侧列表点击
            leftClick(position);
        } else if (mRightAdapter == baseAdapter) {//右侧列表点击
            rightClick(position);
        } else if (baseAdapter instanceof MerchantsSubDetailAdapter) {//详情点击
            Utils.print("MerchantsSubDetailAdapter p =" + position);
            onSubDeatailItemClick(nodesDetailBeans.get(position));
        }
    }

    private void rightClick(int position) {
        clickPosition = position + (spanCount - position % spanCount);
        if (clickPosition >= rightBeanList.size()) {
            clickPosition = rightBeanList.size();
            mRightRecyclerView.scrollToPosition(clickPosition);
        }
        Utils.print("clickPosition = " + clickPosition);
        mRightAdapter.setClickPosition(clickPosition);
        nodesDetailBeans.clear();
        String id = rightBeanList.get(position).getId();
        if (TextUtils.isEmpty(id)) {
            changeSub(new ArrayList<>());
        } else {
            getSubData(id, "getNodes");
            dismissHttpDialog();
        }
        rightClickPosition = position;
    }

    private void leftClick(int position) {
        String nodeId = leftBeanList.get(position).getId();
        if (TextUtils.isEmpty(nodeId)) {
            changeSub(new ArrayList<>());
            mRightAdapter.notifyDataSetChanged();
            return;
        }
        if (cacheMap.containsKey(nodeId)) {
            changeSub(cacheMap.get(nodeId));
        } else {
            getSubData(nodeId, "getSubData");
        }
        leftClickPosition = position;
    }

    public void onSubDeatailItemClick(NodeBean nodesBean) {
        if (new UserService(MyApplication.context).getislogin().equals("0")) {
            new UserService(MyApplication.context).setText(nodesBean.getLabelName());
//            Intent intent = new Intent(getActivity(), SearchResult_Activity.class);
            Intent intent = new Intent(getActivity(), ClassificationDetailActivity.class);
            intent.putExtra(Constant.IntentKey.TITLE, nodesBean.getLabelName());
            intent.putExtra(Constant.IntentKey.LABEL_ID, nodesBean.getId());
            Utils.print("node=" + nodesBean.toString());
            startActivity(intent);
        } else {
            ProjectUtil.jumpLogin(getActivity());
        }
    }

    private void changeSub(List<NodeBean> beanXs) {
        Utils.print("changeSub");
        clickPosition = -1;
        mRightAdapter.setClickPosition(clickPosition);
        rightBeanList.clear();
        if (beanXs.size() > 0) {
            rightBeanList.addAll(beanXs);
        } else {
            initRightListBean();
        }
        mRightAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        super.onHttpSuccess(type, jsonObject);
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        super.onHttpFailure(type, s1);
        if (type.contains("getSubData")) {
            changeSub(new ArrayList<>());
        } else if (type.contains("getNodes")) {
            clickPosition = -1;
            mRightAdapter.setClickPosition(clickPosition);
            nodesDetailBeans.clear();
            mRightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int i, int i1, int i2) throws Exception {
        if ("initMerchantsData".equals(type)) {
            Utils.print("size = " + jsonArray.length());
            leftBeanList.clear();
            leftBeanList.addAll(JSONUtil.toJavaBeanList(NodeBean.class, jsonArray));
            mLeftAdapter.notifyDataSetChanged();
            mLeftAdapter.setClickPosition(subLabelPosition);
            if (leftBeanList.size() > 0)
                getSubData(leftBeanList.get(subLabelPosition).getId(), "getSubData");
        } else if (type.contains("getSubData")) {
            rightBeanList.clear();
            List<NodeBean> beanXList = JSONUtil.toJavaBeanList(NodeBean.class, jsonArray);
            Utils.print("beanXList = " + beanXList.size());
            changeSub(beanXList);
            Utils.print("put id = " + type.replace("getSubData", ""));
            cacheMap.put(type.replace("getSubData", ""), beanXList);
        } else if (type.contains("getNodes")) {
            nodesDetailBeans.clear();
            nodesDetailBeans.addAll(JSONUtil.toJavaBeanList(NodeBean.class, jsonArray));
            mRightAdapter.notifyDataSetChanged();
        }
        super.onHttpSuccess(type, jsonArray, i, i1, i2);
    }


}
