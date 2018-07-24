package com.example.administrator.wankuoportal.ui.denglu;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.Forms;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;
import java.util.Random;

import okhttp3.Call;


/**
 * 手机号认证 by lv 2017/8/19
 */
public class PhoneRenZhengActivity extends BaseActivity {

    private TextView huiyuanzhuce;
    private TextView guzhuzhuce;
    private TextView detileszhuce;
    private TextView textView;
    private EditText phonezhuce;
    private TextView getcodezhuce;
    private EditText codezhuce;
    private Button btzhuce;
    private LinearLayout shenfenlin;
    private ImageView back;
    private TextView title;
    private TextView shoujinum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.shoujinum = (TextView) findViewById(R.id.shoujinum);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        this.shenfenlin = (LinearLayout) findViewById(R.id.shenfen_lin);
        this.btzhuce = (Button) findViewById(R.id.bt_zhuce);
        this.codezhuce = (EditText) findViewById(R.id.code_zhuce);
        this.getcodezhuce = (TextView) findViewById(R.id.getcode_zhuce);
        this.phonezhuce = (EditText) findViewById(R.id.phone_zhuce);
        this.textView = (TextView) findViewById(R.id.textView);
        this.detileszhuce = (TextView) findViewById(R.id.detiles_zhuce);
        this.guzhuzhuce = (TextView) findViewById(R.id.guzhu_zhuce);
        this.huiyuanzhuce = (TextView) findViewById(R.id.huiyuan_zhuce);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initOnclick();
        initHandler();
        shoujinum.setVisibility(View.VISIBLE);
        phonezhuce.setVisibility(View.GONE);
        shenfenlin.setVisibility(View.GONE);
        detileszhuce.setVisibility(View.GONE);
        title.setText("验证手机号");
        getcodezhuce.setBackgroundResource(R.color.text_orange);
        getcodezhuce.setTextColor(getResources().getColor(R.color.white));


        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.context).gettoken();
        final String accountId = new UserService(MyApplication.context).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getuserinfo;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("token", accountId + "," + time + "," + token)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Regist regist = gson.fromJson(response, Regist.class);
                        if (regist.getCode() == 0) {
                            accout = regist.getData().getAaccountInfo().getPhone();
                            shoujinum.setText(accout);

                        } else if (regist.getCode() == 2) {
                            new UserService(MyApplication.context).setislogin("1");
                            startActivity(LoginActivity.class);
                        } else {
                            showShort(regist.getMsg());
                        }
                    }
                });


    }

    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                getcodezhuce.setText(sec + "s后可重新获取");
                sec--;
                if (sec > 0) {
                    getcodezhuce.setEnabled(false);
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    getcodezhuce.setText("获取验证码");
                    getcodezhuce.setEnabled(true);
                }
            }
        };
    }


    private Handler handler;
    private Runnable runnable;
    private int sec = 60;

    public void djs() {
        sec = 60;
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

    String yzm = "";
    String shenfen = "";
    String accout = "";

    private void initOnclick() {
        //获取验证码点击
        getcodezhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                accout = phonezhuce.getEditableText().toString();
                if (Forms.disValid(accout, Forms.PHONENUM)) {
                    showShort("请输入正确的手机号");
                } else {

                    djs();
                    Random r = new Random();
                    String str = "0123456789";//列出所有的字母数字
                    yzm = "";
                    for (int i = 0; i < 4; i++)//循环4次，输出四个数
                    {
                        int a = r.nextInt(10);//从0-61中随机一个数，作为字符串的索引
                        yzm = yzm + str.substring(a, a + 1);
                    }
                    L.d("yzm", yzm);

                    String url = Apis.Base + Apis.send;
                    OkHttpUtils
                            .get()
                            .url(url)
                            .addParams("telephone", accout)
                            .addParams("random", yzm)
                            .addParams("invitationcode", "")
                            .build()
                            .execute(new MyStringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onHttpResponse(String response, int id) throws Exception {
                                    BaseResult b = gson.fromJson(response, BaseResult.class);
                                    if (b.getCode() == 1) {
                                        showShort(b.getMsg());
                                    } else {
                                        showShort(b.getMsg());
                                    }
                                }
                            });
                }
            }
        });
        //下一步点击
        btzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = codezhuce.getEditableText().toString();
                if (code.isEmpty()) {
                    showShort("请输入验证码");
                } else {
                    if (code.equals(yzm)) {
                        showShort("手机验证成功");
                        startActivity(PassWordSetupActivity.class, "shenfen", shenfen, "phone", accout, "type", getIntent().getStringExtra("type"));
                    } else {
                        showShort("验证码错误");
                    }
                }

            }
        });
        //会员点击
        huiyuanzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initview();
                huiyuanzhuce.setBackgroundResource(R.drawable.bg_shenfen);
                huiyuanzhuce.setTextColor(getResources().getColor(R.color.white));
                detileszhuce.setText("会员功能：免费发布求职、有奖答题赢金币、诗词大赛赢金币、答题排行榜赢大奖，金币免费兑换产品");
                shenfen = "";
            }
        });
        //雇主点击
        guzhuzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initview();
                guzhuzhuce.setBackgroundResource(R.drawable.bg_shenfen);
                guzhuzhuce.setTextColor(getResources().getColor(R.color.white));
                detileszhuce.setText("雇主功能：免费发布需求任务、找人来帮忙，生活服务、同城服务，帮我买、帮我送、帮我取");
                shenfen = "";
            }
        });
    }

    private void initview() {
        guzhuzhuce.setBackgroundResource(R.drawable.bg_shenfen_none);
        huiyuanzhuce.setBackgroundResource(R.drawable.bg_shenfen_none);
        guzhuzhuce.setTextColor(getResources().getColor(R.color.all_textc));
        huiyuanzhuce.setTextColor(getResources().getColor(R.color.all_textc));
    }
}
