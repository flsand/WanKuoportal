package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.Pay.ChongZhiActivity;
import com.example.administrator.wankuoportal.ui.SetUp.ChangePassWordActivity;
import com.example.administrator.wankuoportal.ui.SetUp.SetUpPhoneCodeActivity;
import com.example.administrator.wankuoportal.ui.SetUp.SetupYHKActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.customView.AlwaysMarqueeTextView;
import com.flysand.mylibrary.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

public class MyZhanghuActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.LinearLayout yinhangka;
    private android.widget.LinearLayout phone;
    private android.widget.LinearLayout youxiang;
    private TextView textView7;
    private android.widget.LinearLayout paymima;
    private TextView tixian;
    private TextView chongzhi;
    private TextView huanchengjinbi;
    private TextView mingxi;
    private ImageView touxiang;
    private TextView name;
    private TextView qianbao;
    private TextView phonenum;

    private View managerMarginLayout;
    private View managerMarginButtomLayout;
    private Button packUpBtn;
    private AlwaysMarqueeTextView managerMarginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        this.phonenum = (TextView) findViewById(R.id.phonenum);
        this.qianbao = (TextView) findViewById(R.id.qianbao);
        this.name = (TextView) findViewById(R.id.name);
        this.touxiang = (ImageView) findViewById(R.id.touxiang);
        this.mingxi = (TextView) findViewById(R.id.mingxi);
        this.huanchengjinbi = (TextView) findViewById(R.id.huanchengjinbi);
        this.chongzhi = (TextView) findViewById(R.id.chongzhi);
        this.tixian = (TextView) findViewById(R.id.tixian);
        this.paymima = (LinearLayout) findViewById(R.id.pay_mima);
        this.textView7 = (TextView) findViewById(R.id.textView7);
        this.youxiang = (LinearLayout) findViewById(R.id.youxiang);
        this.phone = (LinearLayout) findViewById(R.id.phone);
        this.managerMarginLayout = findViewById(R.id.manager_margin_layout);
        this.managerMarginButtomLayout = findViewById(R.id.manager_margin_buttom_layout);
        this.packUpBtn = (Button) findViewById(R.id.pack_up_btn);
        this.managerMarginTv = (AlwaysMarqueeTextView) findViewById(R.id.manager_margin_tv);

        this.yinhangka = (LinearLayout) findViewById(R.id.yinhangka);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //明细点击
        mingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TransactionDetailsActivity.class);
            }
        });
        //手机设置点击
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SetUpPhoneCodeActivity.class);
            }
        });
        //提现 点击
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double money = Double.parseDouble(qianbao.getText().toString());
                    if (money <= 0) {
                        showShort("暂无可提现金额！");
                    } else {
                        Utils.print("phonecard = " + phonecard);
                        startActivity(TiXianActivity.class, "money", qianbao.getText().toString(), "phone", phonecard);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showShort("暂无可提现金额！");
                }

            }
        });
        //充值点击
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChongZhiActivity.class);
            }
        });
        //换成金币
        huanchengjinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(TransactionSalaActivity.class);
//                if (qianbao.getText().toString().equals("0.00")) {
//                    showShort("暂无可兑换金额！");
//                } else {
//                    Utils.print("phonecard = " + phonecard);
//                    startActivity(JiBiDuiHuanActivity.class, "gold", qianbao.getText().toString(), "money", qianbao.getText().toString(), "type", "jq", "phone", phonecard);
////                    startActivity(JiBiDuiHuanActivity.class, "money", qianbao.getText().toString(), "type", "jq", "phone", phonecard);
//                }
            }
        });
        //银行卡管理
        yinhangka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SetupYHKActivity.class);
            }
        });
        //邮箱点击
        youxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SetUpPhoneCodeActivity.class);
            }
        });
        //修改支付密码
        paymima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChangePassWordActivity.class, "type", "ZF");
            }
        });
        managerMarginLayout.setOnClickListener((v) -> changeManagerMarginLayout(true));
        packUpBtn.setOnClickListener((v) -> changeManagerMarginLayout(false));

    }


    private void changeManagerMarginLayout(boolean isShow) {
        managerMarginButtomLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void updateMarginPrice(String price) {
        String text = "<customfont size='14sp' color='#%1$x'>店长保证金"
                + "</customfont><customfont size='16sp' color='#%2$x'>&nbsp;&nbsp;?</customfont>"
                + "</customfont><customfont size='14sp' color='#%3$x'>&nbsp;&nbsp;&nbsp;&nbsp;%4$s</customfont>";
        managerMarginTv.setHtmlText(String.format(text, getResources().getColor(R.color.main_text_color), getResources().getColor(R.color.text_red), getResources().getColor(R.color.main_text_color), price), this);
    }

    String phonecard = "";

    private void initview() {

        updateMarginPrice("0");
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
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
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Regist regist = gson.fromJson(response, Regist.class);
                        if (regist.getCode() == 0) {
                            String a = regist.getData().getAaccountInfo().getPhone().substring(0, 3);
                            String b = regist.getData().getAaccountInfo().getPhone().substring(regist.getData().getAaccountInfo().getPhone().length() - 4);
                            updateMarginPrice(Utils.replaceDoubleZero(regist.getData().getAaccountAuthorize().getNomoney() + ""));
                            phonenum.setText(a + "****" + b);
                            name.setText("欢迎您：" + regist.getData().getAaccountInfo().getName());

                            if (!TextUtils.isEmpty(regist.getData().getAaccountInfo().getHeadIcon())) {
                                String imaurl = Apis.Baseima + regist.getData().getAaccountInfo().getHeadIcon();
                                Utils.print("imaurl  = " + imaurl);
                                Glide.with(MyZhanghuActivity.this).load(imaurl).into(touxiang);
                            }
                            phonecard = regist.getData().getAaccountInfo().getPhone();
                            if (regist.getData().getAaccountAuthorize().getMoney() == null) {
                                qianbao.setText("0.00");
                            } else {
                                qianbao.setText(regist.getData().getAaccountAuthorize().getMoney() + "");
                            }

                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }
}
