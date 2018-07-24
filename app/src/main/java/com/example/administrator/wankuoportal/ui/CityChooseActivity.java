package com.example.administrator.wankuoportal.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.global.UserService;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.example.administrator.wankuoportal.adapter.CityAdapter;
import com.example.administrator.wankuoportal.beans.CityBean;
import com.example.administrator.wankuoportal.beans.DividerItemDecoration;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.util.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.administrator.wankuoportal.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CityChooseActivity extends BaseActivity implements CityAdapter.OnItemClickListener {

    private static final String TAG = "zxt";
    private RecyclerView mRv;
    private CityAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    private android.widget.ImageView back;
    private TextView informationtitle;
    private RecyclerView rv;
    private IndexBar indexBar;
    private TextView tvSideBarHint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        this.tvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);
        this.indexBar = (IndexBar) findViewById(R.id.indexBar);
        this.rv = (RecyclerView) findViewById(R.id.rv);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new CityAdapter(CityChooseActivity.this, mDatas);

        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
        mHeaderAdapter.setHeaderView(R.layout.item_dangqiancity, "当前："+getIntent().getStringExtra("city"));
        mAdapter.setOnItemClickListener(this);

        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(CityChooseActivity.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint =  findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar =  findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        initDatas(getResources().getStringArray(R.array.provinces));
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟200ms 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    CityBean cityBean = new CityBean();
                    cityBean.setCity(data[i]);//设置城市名称
                    mDatas.add(cityBean);
                }

                mIndexBar.setmSourceDatas(mDatas)//设置数据
                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();

                mAdapter.setDatas(mDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mDecoration.setmDatas(mDatas);
            }
        }, 200);

    }

    @Override
    public void onItemClick(View view, int position) {
        L.d("city",mDatas.get(position).getCity());
        new UserService(MyApplication.context).setshouyecity(mDatas.get(position).getCity());
        startActivity(MainActivity.class,"city",mDatas.get(position).getCity());
    }
}
