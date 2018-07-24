package com.example.administrator.wankuoportal.ui.DaTi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.PaiHang;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.SetUp.InvitationActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.HttpDataManager;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

import okhttp3.Call;

public class JY_ResultActivity extends BaseActivity {

    private android.widget.ImageView wenback;
    private android.widget.ImageView wenman;
    private android.widget.TextView jyrpaiming;
    private android.widget.TextView jyrresult;
    private android.widget.TextView jyrjb;
    private android.widget.TextView jyrgetjb;
    private android.widget.TextView jyranswer;
    private android.widget.LinearLayout jyrright;
    private android.widget.LinearLayout jyrerror;
    private android.widget.LinearLayout jyrdtjl;
    private android.widget.LinearLayout jyrdtsy;
    private android.widget.LinearLayout jyrsc;
    private TextView zongdui;
    private TextView zongcuo;
    String rightAnswer = "";
    String wrongAnswer = "";
    boolean isshare = false;
    private TextView title;
    private ImageView dtima;
    private TextView dttx;
    int typenum;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jy__result);
        this.dttx = (TextView) findViewById(R.id.dt_tx);
        this.dtima = (ImageView) findViewById(R.id.dt_ima);
        this.title = (TextView) findViewById(R.id.title);
        this.jyrsc = (LinearLayout) findViewById(R.id.jyr_sc);
        this.jyrdtsy = (LinearLayout) findViewById(R.id.jyr_dtsy);
        this.jyrdtjl = (LinearLayout) findViewById(R.id.jyr_dtjl);
        this.jyrerror = (LinearLayout) findViewById(R.id.jyr_error);
        this.zongcuo = (TextView) findViewById(R.id.zong_cuo);
        this.jyrright = (LinearLayout) findViewById(R.id.jyr_right);
        this.zongdui = (TextView) findViewById(R.id.zong_dui);
        this.jyranswer = (TextView) findViewById(R.id.jyr_answer);
        this.jyrgetjb = (TextView) findViewById(R.id.jyr_getjb);
        this.jyrjb = (TextView) findViewById(R.id.jyr_jb);
        this.jyrresult = (TextView) findViewById(R.id.jyr_result);
        this.jyrpaiming = (TextView) findViewById(R.id.jyr_paiming);
        this.wenman = (ImageView) findViewById(R.id.wen_man);
        this.wenback = (ImageView) findViewById(R.id.wen_back);
        type = getIntent().getStringExtra("type");

        if (type.equals("SC")) {
            title.setText("猜题赢大奖结果");
//            dttx.setText("记忆赢大奖");
            typenum = 2;
        } else {
            title.setText("记忆赢大奖结果");
//            dttx.setText("猜题赢大奖");
            typenum = 1;
        }

///继续答题
        jyrsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                returnAnswerno();
                finish();
//                if (ClickHelper.isHandle())
//                    return;
//                checkAnswerPermission(type.equals("SC") ? Constant.HttpType.ANSWER : Constant.HttpType.MEMORY);
            }
        });

        initview();

    }

    private void checkAnswerPermission(String answerType) {

        HttpDataManager.checkAnswerPermission(this, answerType, new HttpDataManager.onResultListenr() {
            @Override
            public void onStart() {
                showHttpDialog();
            }

            @Override
            public void onSuccess(Object s) {
                dismissHttpDialog();
                jumpActivity();
//                startActivity(DaTi_WenActivity.class, "type", type);
                finish();
            }

            @Override
            public void onFail(Object s) {
                dismissHttpDialog();
            }
        });


    }

    private void jumpActivity() {
        if (type.equals("SC")) {
            startActivity(Wzy_AnswerSCActivity.class);
        } else {
            startActivity(MemoryAnswerActivity.class);
        }
        finish();
    }

    private void initview() {

        wenback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                returnAnswerno();
                finish();
            }
        });
        //分享赚金币点击
        jyranswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isshare = true;
                returnAnswer();

            }
        });


        jyrjb.setText(getIntent().getStringExtra("jb"));
        zongcuo.setText(getIntent().getStringExtra("no"));
        zongdui.setText(getIntent().getStringExtra("yes"));
        jyrresult.setText("亲，您答对了" + getIntent().getStringExtra("yes") + "道题，答错了" + getIntent().getStringExtra("no") + "道题，获得了" + getIntent().getStringExtra("jb") + "枚金币，非常棒呦!继续加油!更多金币等您呦!");
        rightAnswer = getIntent().getStringExtra("rightAnswer");
        wrongAnswer = getIntent().getStringExtra("wrongAnswer");
        //领取金币
        jyrgetjb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isshare = false;
                returnAnswer();
            }
        });
        //答题记录点击
        jyrdtjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                returnAnswerno();
                startActivity(WoDeDTActivity.class, "type", getIntent().getStringExtra("type"));
//                finish();
            }
        });
        //答题首页点击
        jyrdtsy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                returnAnswerno();
                finish();
            }
        });
    }

    //返回答题结果
    private void returnAnswer() {
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.returnquestion + "?token=" + accountId + "," + time + "," + token;

        OkHttpUtils
                .get()
                .url(url)
                .addParams("rightAnswer", rightAnswer)
                .addParams("wrongAnswer", wrongAnswer)
                .addParams("accountId", accountId)
                .addParams("questiontpye", typenum + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d("returnquestion", response);
                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                        if (baseResult.getCode() == 0) {

                            if (isshare) {
                                startActivity(InvitationActivity.class);
//                                finish();
                            } else {
                                showShort("您领取了" + getIntent().getStringExtra("jb") + "金币");
                            }

                        } else if (baseResult.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            new UserService(JY_ResultActivity.this).setislogin("1");
                        } else {
                            if (isshare) {
                                startActivity(InvitationActivity.class);
//                                finish();
                            } else {
                                showShort(baseResult.getMsg());
                            }

                        }


                    }
                });


    }

    private void returnAnswerno() {
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.returnquestion + "?token=" + accountId + "," + time + "," + token;

        OkHttpUtils
                .get()
                .url(url)
                .addParams("rightAnswer", rightAnswer)
                .addParams("wrongAnswer", wrongAnswer)
                .addParams("accountId", accountId)
                .addParams("questiontpye", typenum + "")
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d("returnquestion", response);
                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                        if (baseResult.getCode() == 0) {

                        } else if (baseResult.getCode() == 2) {
                            startActivity(LoginActivity.class);
                            new UserService(JY_ResultActivity.this).setislogin("1");
                        } else {
                            showShort(baseResult.getMsg());
                        }


                    }
                });


    }

    @Override
    protected void onResume() {
        super.onResume();
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        String accountId = new UserService(MyApplication.context).getaccountid();
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
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d("paihang", response);
                        try {//TODO NullPointerException
                            PaiHang p = gson.fromJson(response, PaiHang.class);
                            if (p.getCode() == 0) {
                                jyrpaiming.setText("当前排名" + p.getData().getApiPrizeRecordRanking().getRanking());
                            } else if (p.getCode() == 2) {
                                startActivity(LoginActivity.class);
                                showShort(p.getMsg());
                                new UserService(JY_ResultActivity.this).setislogin("1");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });


    }
}
