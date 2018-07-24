package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.MyInfoAssetBean;
import com.example.administrator.wankuoportal.beans.MyInfoAssetDetailBean;
import com.example.administrator.wankuoportal.beans.MyInfoBean;
import com.example.administrator.wankuoportal.beans.MyInfoCommonBean;
import com.example.administrator.wankuoportal.beans.MyInfoCommonDetailBean;
import com.example.administrator.wankuoportal.global.Apis;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity.INFO_READ;
import static com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity.PREFERENCE_MY_INFO;

public class MyInfoDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MESSAGE_POSITION = "MESSAGE_POSITION";

    private static final int REFRESH_TYPE_INIT = 0;
    private static final int REFRESH_TYPE_NEXT = 1;
    private static final int REFRESH_TYPE_LAST = 2;

    private int position;

    private static final int ANIMATION_DURATION = 200;

    private List<MyInfoAssetBean.DatasBean> mAssetList = new ArrayList<>();
    private List<MyInfoCommonBean.DatasBean> mCommonList = new ArrayList<>();

    @BindView(R.id.my_info_detail_root)
    ViewGroup my_info_detail_root;

    @BindView(R.id.my_info_detail_tv_title)
    TextView my_info_detail_tv_title;

    @BindView(R.id.my_info_detail_tv_content)
    TextView my_info_detail_tv_content;

    @BindView(R.id.my_info_detail_tv_date)
    TextView my_info_detail_tv_date;

    @BindView(R.id.my_info_detail_tv_last)
    TextView my_info_detail_tv_last;

    @BindView(R.id.my_info_detail_tv_next)
    TextView my_info_detail_tv_next;

    @BindView(R.id.my_info_detail_back)
    ImageView my_info_detail_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_detail);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        position = getIntent().getExtras().getInt(MESSAGE_POSITION);
        mAssetList = DataRepository.getInstance().getMyInfoAssetList();
        mCommonList = DataRepository.getInstance().getMyInfoCommonList();
        my_info_detail_back.setOnClickListener(this);
        refreshData(REFRESH_TYPE_INIT);
    }

    private void refreshData(int type) {
        try {
            if (type == REFRESH_TYPE_NEXT) {
                if (position != mAssetList.size() + mCommonList.size() - 1) {
                    position++;
                } else {
                    return;
                }
            } else if (type == REFRESH_TYPE_LAST) {
                if (position != 0) {
                    position--;
                } else {
                    return;
                }
            }

            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(my_info_detail_root, "alpha", 1.0f, 0.0f)
                    .setDuration(ANIMATION_DURATION);

            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(my_info_detail_root, "alpha", 0.0f, 1.0f)
                    .setDuration(ANIMATION_DURATION);

            fadeOut.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (position == 0) {
                        my_info_detail_tv_last.setOnClickListener(null);
                        my_info_detail_tv_next.setOnClickListener(MyInfoDetailActivity.this);
                        my_info_detail_tv_last.setBackground(getResources().getDrawable(R.drawable.my_info_next_bg_gray));
                        my_info_detail_tv_last.setTextColor(getResources().getColor(R.color.gray));
                        my_info_detail_tv_next.setBackground(getResources().getDrawable(R.drawable.my_info_next_bg_theme_color));
                        my_info_detail_tv_next.setTextColor(getResources().getColor(R.color.theme_color));
                    } else if (position == mAssetList.size() + mCommonList.size() - 1) {
                        my_info_detail_tv_last.setOnClickListener(MyInfoDetailActivity.this);
                        my_info_detail_tv_next.setOnClickListener(null);
                        my_info_detail_tv_last.setBackground(getResources().getDrawable(R.drawable.my_info_next_bg_theme_color));
                        my_info_detail_tv_last.setTextColor(getResources().getColor(R.color.theme_color));
                        my_info_detail_tv_next.setBackground(getResources().getDrawable(R.drawable.my_info_next_bg_gray));
                        my_info_detail_tv_next.setTextColor(getResources().getColor(R.color.gray));
                    } else {
                        my_info_detail_tv_last.setOnClickListener(MyInfoDetailActivity.this);
                        my_info_detail_tv_next.setOnClickListener(MyInfoDetailActivity.this);
                        my_info_detail_tv_last.setBackground(getResources().getDrawable(R.drawable.my_info_next_bg_theme_color));
                        my_info_detail_tv_next.setBackground(getResources().getDrawable(R.drawable.my_info_next_bg_theme_color));
                        my_info_detail_tv_last.setTextColor(getResources().getColor(R.color.theme_color));
                        my_info_detail_tv_next.setTextColor(getResources().getColor(R.color.theme_color));
                    }

                    if (position < mAssetList.size()) {
                        MyInfoAssetBean.DatasBean assetBean = mAssetList.get(position);
                        setInfoReadId(assetBean.getId());
                        String token = new UserService(MyInfoDetailActivity.this).gettoken();
                        String time = new Date().getTime() + "";
                        final String accountId = new UserService(MyInfoDetailActivity.this).getaccountid();
                        CrashHandler.getInstance(getApplicationContext()).putParams("phone", new UserService(MyInfoDetailActivity.this).getphone());
                        token = MD5Util.md5(time + token);

                        String url = Apis.Base + "/api/Message/assetSelect/detail?token=" + accountId + "," + time + "," + token + "&id=" + assetBean.getId();

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
                                if (response.isSuccessful()) {
                                    Gson gson = new Gson();
                                    String result = response.body().string();
                                    Log.d("qzw", "result1 = " + result);
                                    MyInfoAssetDetailBean bean = gson.fromJson(result, MyInfoAssetDetailBean.class);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            fadeIn.addListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {
                                                    super.onAnimationStart(animation);
                                                    my_info_detail_tv_title.setText(bean.getInfoTitle());
                                                    my_info_detail_tv_content.setText(bean.getContent());
                                                    my_info_detail_tv_date.setText(bean.getCreateTime());
                                                }
                                            });

                                            fadeIn.addListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                }
                                            });
                                            fadeIn.start();
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        MyInfoCommonBean.DatasBean commonBean = mCommonList.get(position - mAssetList.size());
                        setInfoReadId(commonBean.getAPushinfoId());

                        String token = new UserService(MyInfoDetailActivity.this).gettoken();
                        String time = new Date().getTime() + "";
                        final String accountId = new UserService(MyInfoDetailActivity.this).getaccountid();
                        CrashHandler.getInstance(getApplicationContext()).putParams("phone", new UserService(MyInfoDetailActivity.this).getphone());
                        token = MD5Util.md5(time + token);

                        String url = Apis.Base + "/api/Message/commonSelect/detail?token=" + accountId + "," + time + "," + token + "&accountId=" + accountId + "&id=" + commonBean.getAPushinfoId();

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
                                if (response.isSuccessful()) {
                                    Gson gson = new Gson();
                                    String result = response.body().string();
                                    Log.d("qzw", "result2 = " + result);
                                    MyInfoCommonDetailBean bean = gson.fromJson(result, MyInfoCommonDetailBean.class);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            fadeIn.addListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationStart(Animator animation) {
                                                    super.onAnimationStart(animation);
                                                    my_info_detail_tv_title.setText(bean.getInfoTitle());
                                                    my_info_detail_tv_content.setText(bean.getContent());
                                                    my_info_detail_tv_date.setText(bean.getCreateTime());
                                                }
                                            });

                                            fadeIn.addListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                }
                                            });
                                            fadeIn.start();
                                        }
                                    });
                                }
                                }
                        });
                    }
                }
            });
            fadeOut.start();
        } catch (Exception e) {
            Log.d("qzw", "error", e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info_detail_tv_next:
                refreshData(REFRESH_TYPE_NEXT);
                break;
            case R.id.my_info_detail_tv_last:
                refreshData(REFRESH_TYPE_LAST);
                break;
            case R.id.my_info_detail_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        my_info_detail_root.clearAnimation();
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
}
