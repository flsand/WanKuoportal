package com.example.administrator.wankuoportal.ui.DaTi;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.adapter.DTPaiHang_Adapter;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.PaiHang;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

public class DTPaiHangActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private android.widget.TextSwitcher tvmessage;
    private android.widget.TextView quanguo;
    private android.widget.TextView benshi;
    private android.widget.ListView list;
    private TextView mingci;
    private TextView nickname;
    private TextView chengshi;
    private TextView jbshu;
    private de.hdodenhof.circleimageview.CircleImageView ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtpai_hang);
        this.ima = (CircleImageView) findViewById(R.id.ima);
        this.jbshu = (TextView) findViewById(R.id.jbshu);
        this.chengshi = (TextView) findViewById(R.id.chengshi);
        this.nickname = (TextView) findViewById(R.id.nickname);
        this.mingci = (TextView) findViewById(R.id.mingci);
        this.list = (ListView) findViewById(R.id.list);
        this.benshi = (TextView) findViewById(R.id.benshi);
        this.quanguo = (TextView) findViewById(R.id.quanguo);
        this.tvmessage = (TextSwitcher) findViewById(R.id.tv_message);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        String type = getIntent().getStringExtra("type");
//        if (type.equals("sc")) {
        title.setText("答题排行");
//        } else {
//            title.setText("记忆赢大奖排行");
//        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        benshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initbackgroud();
                benshi.setBackgroundResource(R.drawable.bg_hong);
                String time = new Date().getTime() + "";
                String token = new UserService(MyApplication.context).gettoken();
                String accountId = new UserService(MyApplication.context).getaccountid();
                token = MD5Util.md5(time + token);
                String url = Apis.Base + Apis.getranking + "?token=" + accountId + "," + time + "," + token;
                String str = new UserService(getBaseContext()).getAddress();
                String address[] = str.split("/");
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("accountId", accountId)
                        .addParams("type", "2")
                        .addParams("city", address[2])
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onHttpResponse(String response, int id) throws Exception {
                                L.d("paihang", response);
                                PaiHang p = gson.fromJson(response, PaiHang.class);
                                try {//TODO NullPointerException
                                    if (p.getCode() == 0) {//DataBean.getList()
                                        DTPaiHang_Adapter d = new DTPaiHang_Adapter(getBaseContext(), p.getData().getList());
                                        list.setAdapter(d);
                                    } else if (p.getCode() == 2) {
                                        startActivity(LoginActivity.class);
                                        showShort(p.getMsg());
                                        new UserService(DTPaiHangActivity.this).setislogin("1");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }
        });
        quanguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initbackgroud();
                quanguo.setBackgroundResource(R.drawable.bg_hong);
                String time = new Date().getTime() + "";
                String token = new UserService(getBaseContext()).gettoken();
                String accountId = new UserService(getBaseContext()).getaccountid();
                token = MD5Util.md5(time + token);

                String url = Apis.Base + Apis.getranking + "?token=" + accountId + "," + time + "," + token;
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("accountId", accountId)
                        .addParams("type", "1")
                        .addParams("city", "")
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onHttpResponse(String response, int id) throws Exception {
                                L.d("paihang", response);
                                PaiHang p = gson.fromJson(response, PaiHang.class);
                                try {//TODO NullPointerException


                                    if (p.getCode() == 0) {
                                        DTPaiHang_Adapter d = new DTPaiHang_Adapter(getBaseContext(), p.getData().getList());
                                        list.setAdapter(d);
                                    } else if (p.getCode() == 2) {
                                        startActivity(LoginActivity.class);
                                        showShort(p.getMsg());
                                        new UserService(DTPaiHangActivity.this).setislogin("1");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
            }
        });

        newsMessage();
    }

    private void initbackgroud() {
        quanguo.setBackgroundResource(R.color.white);
        benshi.setBackgroundResource(R.color.white);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getranking + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .addParams("type", "1")
                .addParams("city", "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d("paihang", response);
                        PaiHang p = gson.fromJson(response, PaiHang.class);
                        try {//TODO NullPointerException
                            // java.lang.NullPointerException: Attempt to invoke virtual method 'java.util.List com.example.administrator.wankuoportal.beans.PaiHang$DataBean.getList()'


                            if (p.getCode() == 0) {
                                DTPaiHang_Adapter d = new DTPaiHang_Adapter(getBaseContext(), p.getData().getList());
                                list.setAdapter(d);
                                jbshu.setText(p.getData().getApiPrizeRecordRanking().getGold() + "");
                                mingci.setText(p.getData().getApiPrizeRecordRanking().getRanking() + "");
                                nickname.setText(p.getData().getApiPrizeRecordRanking().getNickname() + "");
                                chengshi.setText(p.getData().getApiPrizeRecordRanking().getProvince() + " " + p.getData().getApiPrizeRecordRanking().getCity());
                                if (p.getData().getApiPrizeRecordRanking().getHeadimg().isEmpty()) {
                                    Glide.with(getBaseContext()).load(R.drawable.tou).into(ima);
                                } else {
                                    String imaurl = Apis.Baseima + p.getData().getApiPrizeRecordRanking().getHeadimg();
                                    Glide.with(getBaseContext()).load(imaurl).into(ima);
                                }
                            } else if (p.getCode() == 2) {
                                startActivity(LoginActivity.class);
                                showShort(p.getMsg());
                                new UserService(DTPaiHangActivity.this).setislogin("1");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });


        quanguo.setBackgroundResource(R.drawable.bg_hong);
    }

    //滚动新闻
    private void newsMessage() {

        tvmessage.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getBaseContext());
                textView.setSingleLine();
                textView.setTextSize(13);//字号
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (index < news.length) {
                    synchronized (this) {
                        SystemClock.sleep(4000);//每隔4秒滚动一次
                        handler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
                    }
                }
            }
        }.start();
    }

    private String[] news = {"双11回馈活动产品利率增长0.05%", "国家大数据发展纲要", "郑重公告", "某某网站会员须知", "网站维护公告"};
    private int index = 0;//textview上下滚动下标
    public static final int NEWS_MESSAGE_TEXTVIEW = 100;//通知公告信息

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    index++;
                    tvmessage.setText(news[index % news.length]);
                    if (index == news.length) {
                        index = 0;
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
