package com.example.administrator.wankuoportal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.MyTeamAdapter;
import com.example.administrator.wankuoportal.beans.MyTeamListAllBean;
import com.example.administrator.wankuoportal.beans.MyTeamListGLJLBean;
import com.example.administrator.wankuoportal.beans.MyTeamListGLSYBean;
import com.example.administrator.wankuoportal.beans.MyTeamListTJJLBean;
import com.example.administrator.wankuoportal.beans.MyTeamListTJSYBean;
import com.example.administrator.wankuoportal.beans.MyTeamStatusAllBean;
import com.example.administrator.wankuoportal.beans.MyTeamStatusGLJLBean;
import com.example.administrator.wankuoportal.beans.MyTeamStatusGLSYBean;
import com.example.administrator.wankuoportal.beans.MyTeamStatusTJJLBean;
import com.example.administrator.wankuoportal.beans.MyTeamStatusTJSYBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BuildConfig;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.OkHttpUtil;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MyTeamActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.my_team_back)
    ImageView my_team_back;

    @BindView(R.id.my_team_title_1)
    TextView my_team_title_1;

    @BindView(R.id.my_team_title_2)
    TextView my_team_title_2;

    @BindView(R.id.my_team_title_3)
    TextView my_team_title_3;

    @BindView(R.id.my_team_title_4)
    TextView my_team_title_4;

    @BindView(R.id.my_team_num_1)
    TextView my_team_num_1;

    @BindView(R.id.my_team_num_2)
    TextView my_team_num_2;

    @BindView(R.id.my_team_num_3)
    TextView my_team_num_3;

    @BindView(R.id.my_team_num_4)
    TextView my_team_num_4;

    @BindView(R.id.my_team_all_root)
    View my_team_all_root;

    @BindView(R.id.my_team_tjjl_root)
    View my_team_tjjl_root;

    @BindView(R.id.my_team_gljl_root)
    View my_team_gljl_root;

    @BindView(R.id.my_team_tjsy_root)
    View my_team_tjsy_root;

    @BindView(R.id.my_team_glsy_root)
    View my_team_glsy_root;

    @BindView(R.id.my_team_all_iv)
    ImageView my_team_all_iv;

    @BindView(R.id.my_team_tjjl_iv)
    ImageView my_team_tjjl_iv;

    @BindView(R.id.my_team_gljl_iv)
    ImageView my_team_gljl_iv;

    @BindView(R.id.my_team_tjsy_iv)
    ImageView my_team_tjsy_iv;

    @BindView(R.id.my_team_glsy_iv)
    ImageView my_team_glsy_iv;

    @BindView(R.id.my_team_recycler_view)
    RecyclerView my_team_recycler_view;

    private int type = 2;

    private int pageType = 0;

    public static final int PAGE_ALL = 0;
    public static final int PAGE_TJJL = 1;
    public static final int PAGE_GLJL = 2;
    public static final int PAGE_TJSY = 3;
    public static final int PAGE_GLSY = 4;

    private int currentPage = PAGE_ALL;

    private MyTeamAdapter mMyTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        ButterKnife.bind(this);

        initView();
        refreshStatus();
        refreshList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshStatus();
        refreshList();
    }

    private void initView() {
        my_team_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        my_team_back.setOnClickListener(this);
        my_team_all_root.setOnClickListener(this);
        my_team_tjjl_root.setOnClickListener(this);
        my_team_gljl_root.setOnClickListener(this);
        my_team_tjsy_root.setOnClickListener(this);
        my_team_glsy_root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_team_all_root:
                switchPage(PAGE_ALL);
                break;
            case R.id.my_team_tjjl_root:
                switchPage(PAGE_TJJL);
                break;
            case R.id.my_team_gljl_root:
                switchPage(PAGE_GLJL);
                break;
            case R.id.my_team_tjsy_root:
                switchPage(PAGE_TJSY);
                break;
            case R.id.my_team_glsy_root:
                switchPage(PAGE_GLSY);
                break;
            case R.id.my_team_back:
                finish();
                break;
        }
        refreshStatus();
        refreshList();
    }

    private void switchPage(int page) {
        my_team_all_iv.setVisibility(View.GONE);
        my_team_tjjl_iv.setVisibility(View.GONE);
        my_team_gljl_iv.setVisibility(View.GONE);
        my_team_tjsy_iv.setVisibility(View.GONE);
        my_team_glsy_iv.setVisibility(View.GONE);

        switch (page) {
            case PAGE_ALL:
                my_team_all_iv.setVisibility(View.VISIBLE);
                type = 2;
                pageType = 0;
                currentPage = PAGE_ALL;
                break;
            case PAGE_TJJL:
                my_team_tjjl_iv.setVisibility(View.VISIBLE);
                type = 0;
                pageType = 1;
                currentPage = PAGE_TJJL;
                break;
            case PAGE_GLJL:
                my_team_gljl_iv.setVisibility(View.VISIBLE);
                type = 1;
                pageType = 1;
                currentPage = PAGE_GLJL;
                break;
            case PAGE_TJSY:
                my_team_tjsy_iv.setVisibility(View.VISIBLE);
                type = 0;
                pageType = 2;
                currentPage = PAGE_TJSY;
                break;
            case PAGE_GLSY:
                my_team_glsy_iv.setVisibility(View.VISIBLE);
                type = 1;
                pageType = 2;
                currentPage = PAGE_GLSY;
                break;
        }
    }

    /**
     * 刷新总数据
     */
    private void refreshStatus() {
        my_team_num_1.setText(getString(R.string.my_team_refresh));
        my_team_num_2.setText(getString(R.string.my_team_refresh));
        my_team_num_3.setText(getString(R.string.my_team_refresh));
        my_team_num_4.setText(getString(R.string.my_team_refresh));

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

        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("qzw", "onFailure " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();

                Gson gson = new Gson();

                switch (currentPage) {
                    case PAGE_ALL:
                        MyTeamStatusAllBean all = gson.fromJson(result, MyTeamStatusAllBean.class);

                        MyTeamActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_title_1.setText("奖励总金额");
                                my_team_title_2.setText("收益总额");
                                my_team_title_3.setText("推荐人数");
                                my_team_title_4.setText("管理人数");
                                my_team_num_1.setText(all.getData().getJlze());
                                my_team_num_2.setText(all.getData().getSyze());
                                my_team_num_3.setText(all.getData().getTjzr());
                                my_team_num_4.setText(all.getData().getGlzr());
                            }
                        });
                        break;
                    case PAGE_TJJL:
                        MyTeamStatusTJJLBean tjjl = gson.fromJson(result, MyTeamStatusTJJLBean.class);

                        MyTeamActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_title_1.setText("奖励总金额");
                                my_team_title_2.setText("推荐奖励");
                                my_team_title_3.setText("管理奖励");
                                my_team_title_4.setText("推荐人数");
                                my_team_num_1.setText(tjjl.getData().getJlze());
                                my_team_num_2.setText(tjjl.getData().getTjje());
                                my_team_num_3.setText(tjjl.getData().getGlje());
                                my_team_num_4.setText(tjjl.getData().getTjzr());
                            }
                        });
                        break;
                    case PAGE_GLJL:
                        MyTeamStatusGLJLBean gljl = gson.fromJson(result, MyTeamStatusGLJLBean.class);

                        MyTeamActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_title_1.setText("奖励总金额");
                                my_team_title_2.setText("推荐奖励");
                                my_team_title_3.setText("管理奖励");
                                my_team_title_4.setText("管理人数");
                                my_team_num_1.setText(gljl.getData().getJlze());
                                my_team_num_2.setText(gljl.getData().getTjje());
                                my_team_num_3.setText(gljl.getData().getGlje());
                                my_team_num_4.setText(gljl.getData().getGlzr());
                            }
                        });
                        break;
                    case PAGE_TJSY:
                        MyTeamStatusTJSYBean tjsy = gson.fromJson(result, MyTeamStatusTJSYBean.class);

                        MyTeamActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_title_1.setText("店铺总数");
                                my_team_title_2.setText("总体收益");
                                my_team_title_3.setText("推荐收益");
                                my_team_title_4.setText("推荐人数");
                                my_team_num_1.setText(tjsy.getData().getDpzs());
                                my_team_num_2.setText(tjsy.getData().getZtsy());
                                my_team_num_3.setText(tjsy.getData().getTjsy());
                                my_team_num_4.setText(tjsy.getData().getTjzr());
                            }
                        });
                        break;
                    case PAGE_GLSY:
                        MyTeamStatusGLSYBean glsy = gson.fromJson(result, MyTeamStatusGLSYBean.class);

                        MyTeamActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_title_1.setText("店铺总数");
                                my_team_title_2.setText("总体收益");
                                my_team_title_3.setText("管理收益");
                                my_team_title_4.setText("管理人数");
                                my_team_num_1.setText(glsy.getData().getDpzs());
                                my_team_num_2.setText(glsy.getData().getZtsy());
                                my_team_num_3.setText(glsy.getData().getGlsy());
                                my_team_num_4.setText(glsy.getData().getGlzr());
                            }
                        });
                        break;
                }


            }
        });
    }

    /**
     * 刷新列表数据
     */
    private void refreshList() {
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
                Log.d("qzw", "onFailure " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                mMyTeamAdapter = new MyTeamAdapter(MyTeamActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMyTeamAdapter == null) {
                            my_team_recycler_view.setAdapter(mMyTeamAdapter);
                        }
                    }
                });
                switch (currentPage) {
                    case PAGE_ALL:
                        MyTeamListAllBean all = gson.fromJson(result, MyTeamListAllBean.class);
                        mMyTeamAdapter.setAllBeanList(all.getData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_recycler_view.setAdapter(mMyTeamAdapter);
                                mMyTeamAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case PAGE_TJJL:
                        MyTeamListTJJLBean tjjl = gson.fromJson(result, MyTeamListTJJLBean.class);
                        mMyTeamAdapter.setTJJLBeanList(tjjl.getData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_recycler_view.setAdapter(mMyTeamAdapter);
                                mMyTeamAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case PAGE_GLJL:
                        MyTeamListGLJLBean gljl = gson.fromJson(result, MyTeamListGLJLBean.class);
                        mMyTeamAdapter.setGLJLBeanList(gljl.getData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_recycler_view.setAdapter(mMyTeamAdapter);
                                mMyTeamAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case PAGE_TJSY:
                        MyTeamListTJSYBean tjsy = gson.fromJson(result, MyTeamListTJSYBean.class);
                        mMyTeamAdapter.setTJSYBeanList(tjsy.getData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_recycler_view.setAdapter(mMyTeamAdapter);
                                mMyTeamAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case PAGE_GLSY:
                        MyTeamListGLSYBean glsy = gson.fromJson(result, MyTeamListGLSYBean.class);
                        mMyTeamAdapter.setGLSYBeanList(glsy.getData());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                my_team_recycler_view.setAdapter(mMyTeamAdapter);
                                mMyTeamAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }
        });
    }
}
