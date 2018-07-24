package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.MyInfoRecyclerAdapter;
import com.example.administrator.wankuoportal.beans.MyInfoAssetBean;
import com.example.administrator.wankuoportal.beans.MyInfoCommonBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.OkHttpUtil;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.crash.CrashHandler;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 我的消息
 */
public class MyInfoActivity extends BaseActivity implements MyInfoRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.my_info_title)
    TextView my_info_title;

    @BindView(R.id.my_info_back)
    ImageView my_info_back;

    @BindView(R.id.my_info_recycler_view)
    RecyclerView my_info_recycler_view;

    private CompositeDisposable disposable;

    private MyInfoRecyclerAdapter mAdapter;

    private List<MyInfoAssetBean.DatasBean> mAssetList = new ArrayList<>();
    private List<MyInfoCommonBean.DatasBean> mCommonList = new ArrayList<>();

    public static final String PREFERENCE_MY_INFO = "PREFERENCE_MY_INFO";
    public static final String INFO_READ = "INFO_READ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);

        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        my_info_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        my_info_recycler_view.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        disposable = new CompositeDisposable();

        getAssetData();
    }

    private void getAssetData() {
        String token = new UserService(this).gettoken();
        String time = new Date().getTime() + "";
        final String accountId = new UserService(this).getaccountid();
        CrashHandler.getInstance(getApplicationContext()).putParams("phone", new UserService(this).getphone());
        token = MD5Util.md5(time + token);

        String url = Apis.Base + "/api/Message/assetSelect?token=" + accountId + "," + time + "," + token + "&accountId=" + accountId + "&page=1";

        Log.d("qzw", "url = " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Log.d("qzw", "result1 = " + result);
                        Gson gson = new Gson();
                        MyInfoAssetBean bean = gson.fromJson(result, MyInfoAssetBean.class);
                        if (bean != null) {
                            mAssetList = bean.getDatas();
                            getCommonData();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getCommonData() {
        String token = new UserService(this).gettoken();
        String time = new Date().getTime() + "";
        final String accountId = new UserService(this).getaccountid();
        CrashHandler.getInstance(getApplicationContext()).putParams("phone", new UserService(this).getphone());
        token = MD5Util.md5(time + token);

        String url = Apis.Base + "/api/Message/commonSelect?token=" + accountId + "," + time + "," + token + "&accountId=" + accountId + "&page=1";
        Log.d("qzw", "url = " + url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Log.d("qzw", "result2 = " + result);
                        Gson gson = new Gson();
                        MyInfoCommonBean bean = gson.fromJson(result, MyInfoCommonBean.class);
                        if (bean != null) {
                            mCommonList = bean.getDatas();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (mAdapter == null) {
                                        mAdapter = new MyInfoRecyclerAdapter(MyInfoActivity.this, mAssetList, mCommonList);
                                        mAdapter.setOnItemClickListener(MyInfoActivity.this);
                                        my_info_recycler_view.setAdapter(mAdapter);
                                    } else {
                                        mAdapter.setDataList(mAssetList, mCommonList);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                    int notRead = getNumInfoNotRead();
                                    if (notRead == 0) {
                                        my_info_title.setText(getString(R.string.my_info_title));
                                    } else {
                                        my_info_title.setText(getString(R.string.my_info_title_num, notRead));
                                    }
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        disposable.dispose();
    }

    @Override
    public void onItemClick(int position) {
        if (position < mAssetList.size()) {
            setInfoReadId(mAssetList.get(position).getId());
        } else {
            setInfoReadId(mCommonList.get(position - mAssetList.size()).getAPushinfoId());
        }

        DataRepository.getInstance().setMyInfoDataList(mAssetList, mCommonList);
        Intent intent = new Intent(this, MyInfoDetailActivity.class);
        intent.putExtra(MyInfoDetailActivity.MESSAGE_POSITION, position);
        startActivity(intent);
    }

    public void setInfoReadId(long id) {
        SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCE_MY_INFO, Context.MODE_PRIVATE);
        Set<String> readSet = mSharedPreferences.getStringSet(INFO_READ, null);
        if (readSet == null) {
            readSet = new HashSet<>();
        }
        readSet.add(String.valueOf(id));
        mSharedPreferences.edit().clear().putStringSet(INFO_READ, readSet).apply();
    }

    public boolean isInfoRead(long id) {
        SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCE_MY_INFO, Context.MODE_PRIVATE);
        Set<String> readSet = mSharedPreferences.getStringSet(INFO_READ, null);
        if (readSet == null) {
            return false;
        }
        return readSet.contains(String.valueOf(id));
    }

    public int getNumInfoNotRead() {
        SharedPreferences mSharedPreferences = getSharedPreferences(PREFERENCE_MY_INFO, Context.MODE_PRIVATE);
        Set<String> readSet = mSharedPreferences.getStringSet(INFO_READ, null);

        int notRead = mAssetList.size() + mCommonList.size();
        if (readSet == null) {
            return notRead;
        }
        for (int i = 0; i < mAssetList.size(); i++) {
            if (readSet.contains(String.valueOf(mAssetList.get(i).getId()))) {
                notRead--;
            }
        }
        for (int i = 0; i < mCommonList.size(); i++) {
            if (readSet.contains(String.valueOf(mCommonList.get(i).getAPushinfoId()))) {
                notRead--;
            }
        }
        return notRead;
    }
}
