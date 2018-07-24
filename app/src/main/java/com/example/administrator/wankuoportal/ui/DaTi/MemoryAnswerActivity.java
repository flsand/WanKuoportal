package com.example.administrator.wankuoportal.ui.DaTi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.QuersitionBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.denglu.Personal_SetupActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.util.Utils;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

public class MemoryAnswerActivity extends BaseActivity {

    private android.widget.ImageView wenback;
    private android.widget.TextView jymanposition;
    private android.widget.TextView djsjy;
    private android.widget.TextView jyquessitionposition;
    private android.widget.TextView jyqstbody;
    private android.widget.TextView jyqstqst;
    private android.widget.ImageView jyqstima;
    private android.widget.TextView jyqstanswer;
    private android.widget.TextView jynext;
    QuersitionBean q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_answer);
        this.jynext = (TextView) findViewById(R.id.jy_next);
        this.jyqstanswer = (TextView) findViewById(R.id.jy_qst_answer);
        this.jyqstima = (ImageView) findViewById(R.id.jy_qst_ima);
        this.jyqstqst = (TextView) findViewById(R.id.jy_qst_qst);
        this.jyqstbody = (TextView) findViewById(R.id.jy_qst_body);
        this.jyquessitionposition = (TextView) findViewById(R.id.jy_quessitionposition);
        this.djsjy = (TextView) findViewById(R.id.djs_jy);
        this.jymanposition = (TextView) findViewById(R.id.jy_manposition);
        this.wenback = (ImageView) findViewById(R.id.wen_back);
//        jynext.setEnabled(false);
        StatusBarUtil.setTransparent(MemoryAnswerActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }

    private void initview() {

        wenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String str = new UserService(getBaseContext()).getAddress();

        String address[] = str.split("/");
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getquestion + "?token=" + accountId + "," + time + "," + token;

        OkHttpUtils
                .get()
                .url(url)
                .addParams("questiontype", "1")
                .addParams("country", address[0])
                .addParams("province", address[1])
                .addParams("city", address[2])
                .addParams("area", address[3])
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        q = gson.fromJson(response, QuersitionBean.class);

                        if (q.getCode() == 0) {

                            if (Utils.isApkDebugable(MemoryAnswerActivity.this,getPackageName())){
                                Intent it = new Intent(MemoryAnswerActivity.this, Wzy_TZ_Activity.class);
                                it.putExtra("QuersitionBean", q);
                                startActivity(it);
                                finish();
                            }
                            //更新题目0
                            jynext.setEnabled(true);
                            jyqstbody.setText(q.getDatas().get(0).getAprizeQuestion().getContent());
                            jyqstqst.setText("问题：" + q.getDatas().get(0).getAprizeQuestion().getQuestion());
                            String imaurl = Apis.Baseima + q.getDatas().get(0).getAprizeQuestion().getImg();
                            try {
                                Glide.with(MemoryAnswerActivity.this).load(imaurl).into(jyqstima);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            jymanposition.setText("您是第" + q.getDatas().get(0).getAprizeQuestion().getNumber() + "个记忆此题的");
                            for (int i = 0; i < 4; i++) {
                                if (q.getDatas().get(0).getAprizeAnswer().get(i).getIsRight() == 0) {
                                    jyqstanswer.setText("答案：" + q.getDatas().get(0).getAprizeAnswer().get(i).getAnswer());
                                }
                            }
                            //获取到题目后开始倒计时
                            initHandler();
                            djs();

                        } else if (q.getCode() == 2) {
                            showShort(q.getMsg());
                            startActivity(LoginActivity.class);
                            new UserService(MemoryAnswerActivity.this).setislogin("1");
                            finish();
                        } else if (q.getCode() == 1) {
                            showShort(q.getMsg());
                            startActivity(Personal_SetupActivity.class);
                            finish();
                        } else if (q.getCode() == 3) {
                            showShort(q.getMsg());
                        }
                    }
                });


        wenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        jynext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (qst < 10) {
                    if (handler != null) {
                        if (runnable != null) {
                            handler.removeCallbacks(runnable);
                            runnable = null;
                        }
                        handler = null;
                    }
                    qst++;
                    jyquessitionposition.setText(qst + "");
//                    showShort("更新记忆的题目");
                    changejiyi();
                    initHandler();
                    djs();
                } else {
                    if (handler != null) {
                        if (runnable != null) {
                            handler.removeCallbacks(runnable);
                            runnable = null;
                        }
                        handler = null;
                    }
                    Intent it = new Intent(MemoryAnswerActivity.this, Wzy_TZ_Activity.class);
                    it.putExtra("QuersitionBean", q);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    private void changejiyi() {

        if (q != null && q.getCode() == 0) {
            //更新题目0
            jyqstbody.setText(q.getDatas().get(qst - 1).getAprizeQuestion().getContent());
            jyqstqst.setText("问题：" + q.getDatas().get(qst - 1).getAprizeQuestion().getQuestion());
            String imaurl = Apis.Baseima + q.getDatas().get(qst - 1).getAprizeQuestion().getImg();
            Glide.with(MyApplication.context).load(imaurl).into(jyqstima);
            jymanposition.setText("您是第" + q.getDatas().get(qst - 1).getAprizeQuestion().getNumber() + "个记忆此题的");
            for (int i = 0; i < 4; i++) {
                if (q.getDatas().get(qst - 1).getAprizeAnswer().get(i).getIsRight() == 0) {
                    jyqstanswer.setText("答案：" + q.getDatas().get(qst - 1).getAprizeAnswer().get(i).getAnswer());
                }
            }
        }

    }


    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                sec--;
                if (sec > 0) {
                    if (sec < 5) {
                        jynext.setEnabled(true);
                        jynext.setText("下一题");
                        jynext.setBackgroundResource(R.drawable.bg_shenfen);
                    } else {
                        jynext.setText("下一题 (" + (sec - 5) + ")");
                        jynext.setBackgroundResource(R.drawable.bg_hui_kuang);
                        jynext.setEnabled(false);
                    }

                    djsjy.setText(sec + "");
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    if (qst < 10) {
                        if (handler != null) {
                            if (runnable != null) {
                                handler.removeCallbacks(runnable);
                                runnable = null;
                            }
                            handler = null;
                        }
                        initHandler();
                        qst++;
                        jyquessitionposition.setText(qst + "");
//                        showShort("更新记忆的题目");
                        changejiyi();
                        djs();
                    } else {
                        if (handler != null) {
                            if (runnable != null) {
                                handler.removeCallbacks(runnable);
                                runnable = null;
                            }
                            handler = null;
                        }
                        Intent it = new Intent(MemoryAnswerActivity.this, Wzy_TZ_Activity.class);
                        it.putExtra("QuersitionBean", q);
                        startActivity(it);
                        finish();
                    }

                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 15;
    private int qst = 1;

    public void djs() {
        sec = 15;
        handler.post(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
            handler = null;
        }
    }
}
