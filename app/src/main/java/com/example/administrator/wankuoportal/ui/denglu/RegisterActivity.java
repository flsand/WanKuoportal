package com.example.administrator.wankuoportal.ui.denglu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.util.Forms;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Random;

import okhttp3.Call;


/**
 * 注册界面 by lv 2017/8/19
 */
public class RegisterActivity extends BaseActivity {

    private android.widget.TextView huiyuanzhuce;
    private android.widget.TextView guzhuzhuce;
    private android.widget.TextView detileszhuce;
    private android.widget.TextView textView;
    private android.widget.EditText phonezhuce;
    private android.widget.TextView getcodezhuce;
    private android.widget.EditText codezhuce;
    private android.widget.Button btzhuce;
    private android.widget.LinearLayout shenfenlin;
    private android.widget.ImageView back;
    private TextView title;
    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        mType = getIntent().getStringExtra("type");
        if (mType.equals("zhuce")) {
            title.setText("注册");
            shenfenlin.setVisibility(View.VISIBLE);
            detileszhuce.setVisibility(View.VISIBLE);

        } else if (mType.equals("forget")) {
            title.setText("验证手机号");
            shenfenlin.setVisibility(View.GONE);
            detileszhuce.setVisibility(View.GONE);
        }

        phonezhuce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Forms.disValid(s.toString(), Forms.PHONENUM)) {
                    getcodezhuce.setBackgroundResource(R.color.gray);
                    getcodezhuce.setEnabled(false);
                } else {
                    getcodezhuce.setEnabled(true);
                    getcodezhuce.setBackgroundResource(R.color.baseColor);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                getcodezhuce.setText(sec + "");
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
                accout = phonezhuce.getEditableText().toString();
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

//                    String url = Apis.BASE_TEST;
                    String url = Apis.Base;

                    if (mType.equals("zhuce")) {
                        url = url + Apis.REGISTER_SEND;
                    } else {
                        url = url + Apis.send;
                    }

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
                String phone = phonezhuce.getEditableText().toString();

                if (TextUtils.isEmpty(phone)) {
                    showShort("请输入手机号");
                    return;
                } else if (Forms.disValid(phone, Forms.PHONENUM)) {
                    showShort("请输入正确的手机号");
                    return;
                }

                if (code.isEmpty()) {
                    showShort("请输入验证码");
                } else {
                    if (code.equals(yzm)) {
                        showShort("手机验证成功");
                        Intent intent = new Intent(RegisterActivity.this, PassWordSetupActivity.class);
                        intent.putExtra("shenfen", shenfen);
                        intent.putExtra("phone", accout);
                        intent.putExtra("type", getIntent().getStringExtra("type"));
                        startActivityForResult(intent, Constant.RequestCode.REGISER);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == Constant.RequestCode.REGISER) {
                if (resultCode == Constant.ResultCode.FAIL) {
                    data.putExtra(Constant.IntentKey.ACCOUNT, accout);
                    setResult(Constant.ResultCode.FAIL, data);
                    finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initview() {
        guzhuzhuce.setBackgroundResource(R.drawable.bg_shenfen_none);
        huiyuanzhuce.setBackgroundResource(R.drawable.bg_shenfen_none);
        guzhuzhuce.setTextColor(getResources().getColor(R.color.all_textc));
        huiyuanzhuce.setTextColor(getResources().getColor(R.color.all_textc));
    }
}
