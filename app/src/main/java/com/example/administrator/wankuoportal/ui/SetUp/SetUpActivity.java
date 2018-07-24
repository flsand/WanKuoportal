package com.example.administrator.wankuoportal.ui.SetUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.PushSetupActivity;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.ShenFenRenZhengActivity;
import com.example.administrator.wankuoportal.ui.address.ManageAdressActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.denglu.Personal_SetupActivity;
import com.example.administrator.wankuoportal.ui.denglu.PhoneRenZhengActivity;
import com.example.administrator.wankuoportal.util.GlideCacheUtil;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Date;

import okhttp3.Call;

public class SetUpActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.LinearLayout aboutus;
    private android.widget.LinearLayout setup_push;
    private LinearLayout setuppush;
    private TextView laji;
    private LinearLayout saolaji;
    private LinearLayout manlin;
    private TextView signout;
    private LinearLayout shenfenlin;
    private LinearLayout changepassword;
    private LinearLayout address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        this.address = (LinearLayout) findViewById(R.id.address);
        this.changepassword = (LinearLayout) findViewById(R.id.changepassword);
        this.shenfenlin = (LinearLayout) findViewById(R.id.shenfen_lin);
        this.signout = (TextView) findViewById(R.id.signout);
        this.manlin = (LinearLayout) findViewById(R.id.man_lin);
        this.saolaji = (LinearLayout) findViewById(R.id.saolaji);
        this.laji = (TextView) findViewById(R.id.laji);
        this.setuppush = (LinearLayout) findViewById(R.id.setup_push);
        this.aboutus = (LinearLayout) findViewById(R.id.aboutus);
        this.setup_push = (LinearLayout) findViewById(R.id.setup_push);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //收货地址
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ManageAdressActivity.class);
            }
        });

        //关于我们
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AboutUsActivity.class);
            }
        });
        //设置推送
        setup_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(PushSetupActivity.class);
            }
        });

        //个人信息页
        manlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(PersonalDataActivity.class);
                startActivity(Personal_SetupActivity.class);
            }
        });
        //身份认证
        shenfenlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShenFenRenZhengActivity.class);
            }
        });
        //修改密码
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PhoneRenZhengActivity.class, "type", "forget");
            }
        });


        //显示可以清除的缓存
        laji.setText(GlideCacheUtil.getInstance().getCacheSize(SetUpActivity.this));

        //执行清除glide 缓存清除
        saolaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlideCacheUtil.getInstance().clearImageAllCache(SetUpActivity.this);
                showShort("成功清除"
//                        + GlideCacheUtil.getInstance().getCacheSize(SetUpActivity.this)
                        + "缓存");
                laji.setText("0MB");
            }
        });
        //退出登录按钮
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new UserService(MyApplication.context).setaccountid("0");
                new UserService(MyApplication.context).settoken("0");
                new UserService(MyApplication.context).setislogin("1");
                new UserService(MyApplication.context).setPassword("");
                new UserService(MyApplication.context).setphone("");
                Intent intent = new Intent(SetUpActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
                            if (regist.getData().getAaccountInfo().getIdNum().isEmpty()) {

                            } else {
                                shenfenlin.setVisibility(View.GONE);
                            }


                        } else if (regist.getCode() == 2) {
                            new UserService(MyApplication.context).setislogin("1");
                            startActivity(LoginActivity.class);
                        } else {
                            showShort(regist.getMsg());
                        }
                    }
                });
    }
}
