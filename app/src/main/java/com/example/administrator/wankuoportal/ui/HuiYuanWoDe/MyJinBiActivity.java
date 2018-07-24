package com.example.administrator.wankuoportal.ui.HuiYuanWoDe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.gold.TransactionSalaActivity;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.GetMyGold;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

import okhttp3.Call;

public class MyJinBiActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.TextView jbduihaun;
    private android.widget.TextView jb_detail;
    private android.widget.LinearLayout mytuijian;
    private TextView jbdetail;
    private TextView mannum;
    private TextView putongjb;
    private TextView datijb;
    private TextView zongjb;
    private TextView tixianjb;
    private TextView keduihuanjb;
    /**
     * 充值金币
     */
    private TextView rechargeGoldTv;
    //    private TextView shopgo;
    private TextView zuanshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jin_bi);
        this.zuanshi = (TextView) findViewById(R.id.zuanshi);
        this.rechargeGoldTv = (TextView) findViewById(R.id.rechargeGoldTv);
//        this.shopgo = (TextView) findViewById(R.id.shop_go);
        this.keduihuanjb = (TextView) findViewById(R.id.keduihuanjb);
        this.tixianjb = (TextView) findViewById(R.id.tixianjb);
        this.zongjb = (TextView) findViewById(R.id.zongjb);
        this.datijb = (TextView) findViewById(R.id.datijb);
        this.putongjb = (TextView) findViewById(R.id.putongjb);
        this.mannum = (TextView) findViewById(R.id.mannum);

        this.jbdetail = (TextView) findViewById(R.id.jb_detail);
        this.mytuijian = (LinearLayout) findViewById(R.id.mytuijian);
        this.jbduihaun = (TextView) findViewById(R.id.jb_duihaun);
        this.jb_detail = (TextView) findViewById(R.id.jb_detail);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        jbduihaun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyJinBiActivity.this, TransactionSalaActivity.class);
                startActivityForResult(intent, Constant.RequestCode.TRANSACTION);
//                startActivity(JiBiDuiHuanActivity.class, "gold", keduihuanjb.getText().toString(), "type", "jb", "phone", phonecard);
            }
        });
        jb_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(DetailJBActivity.class);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mytuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyTuiJianActivity.class);
            }
        });
        /*
        shopgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShopActivity.class);
            }
        });

        TextView mTeam = (TextView) findViewById(R.id.tv_my_jinbi_team);
        mTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MineTeamActivity.class);
            }
        });*/
    }

    String phonecard = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.RequestCode.TRANSACTION) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    private void initview() {
        String time = new Date().getTime() + "";
        String token = new UserService(MyJinBiActivity.this).gettoken();
        String accountId = new UserService(MyJinBiActivity.this).getaccountid();
        token = MD5Util.md5(time + token);

        String url = Apis.Base + Apis.getmygold + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        try {

                            GetMyGold g = gson.fromJson(response, GetMyGold.class);
                            if (g.getCode() == 0) {
                                mannum.setText(g.getData().getRecommenderNum() + "人");
                                datijb.setText(Utils.replaceDoubleZero(g.getData().getPrizeGold()));
                                zongjb.setText(Utils.replaceDoubleZero(g.getData().getAddGold()));
                                putongjb.setText(Utils.replaceDoubleZero(g.getData().getSignInGold()));
                                zuanshi.setText(Utils.replaceDoubleZero(g.getData().getDiamonds()));
                                //充值
                                rechargeGoldTv.setText(g.getData().getNolimitGold());
//                                tixianjb.setText(Utils.replaceDoubleZero(Double.parseDouble(g.getData().getPrizeGold()) + Double.parseDouble(g.getData().getNolimitGold()) + ""));
                                keduihuanjb.setText(ProjectUtil.formatDouble(MyApplication.getInstance().getUser().getAAccountAuthorize().getAnswerGold()));
                                tixianjb.setText(ProjectUtil.formatDouble(MyApplication.getInstance().getUser().getAAccountAuthorize().getAnswerGold()));
                            } else if (g.getCode() == 2) {
                                startActivity(LoginActivity.class);
                                new UserService(MyApplication.context).setislogin("1");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


    }


    @Override
    protected void onResume() {
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
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Regist regist = gson.fromJson(response, Regist.class);
                        if (regist.getCode() == 0) {
                            phonecard = regist.getData().getAaccountInfo().getPhone();
                            initview();
                        }

                    }
                });
        super.onResume();
    }
}
