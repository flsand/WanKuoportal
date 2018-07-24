package com.example.administrator.wankuoportal.ui.denglu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.flysand.mylibrary.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.ButterKnife;
import okhttp3.Call;

public class PassWordSetupActivity extends BaseActivity {


    String password = "";
    String passwordtwo = "";
    private EditText passwordsetup;
    private EditText passwordsetuptwo;
    private Button btsetuppassword;
    private android.widget.ImageView back;
    private android.widget.TextView title;
    Regist regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word_setup);
        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);
        this.btsetuppassword = (Button) findViewById(R.id.bt_setup_password);
        this.passwordsetuptwo = (EditText) findViewById(R.id.password_setup_two);
        this.passwordsetup = (EditText) findViewById(R.id.password_setup);
        ButterKnife.bind(this);
        final String type = getIntent().getStringExtra("type");
        if (type.equals("zhuce")) {
            title.setText("设置密码");
        } else {
            title.setText("修改密码");
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btsetuppassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = passwordsetup.getEditableText().toString();
                passwordtwo = passwordsetuptwo.getEditableText().toString();
                if (!password.isEmpty()) {
                    if (!passwordtwo.isEmpty()) {
                        if (password.equals(passwordtwo)) {
                            if (type.equals("zhuce")) {
                                String url = Apis.Base + Apis.regist;
                                OkHttpUtils
                                        .post()
                                        .url(url)
                                        .addParams("telephone", getIntent().getStringExtra("phone"))
                                        .addParams("password", password)
                                        .build()
                                        .execute(new MyStringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {

                                            }

                                            @Override
                                            public void onHttpResponse(String response, int id) throws Exception {
                                                L.d("regist", response);
                                                regist = gson.fromJson(response, Regist.class);
                                                if (regist.getCode() == 0) {
                                                    String url = Apis.Base + Apis.login;
                                                    OkHttpUtils
                                                            .post()
                                                            .url(url)
                                                            .addParams("telephone", getIntent().getStringExtra("phone"))
                                                            .addParams("password", password)
                                                            .addParams("logintype", "1")
                                                            .build()
                                                            .execute(new MyStringCallback() {
                                                                @Override
                                                                public void onError(Call call, Exception e, int id) {

                                                                }

                                                                @Override
                                                                public void onHttpResponse(String response, int id) throws Exception {
                                                                    L.d("login", response);
                                                                    regist = gson.fromJson(response, Regist.class);
                                                                    if (regist.getCode() == 0) {
                                                                        new UserService(PassWordSetupActivity.this).setphone(getIntent().getStringExtra("phone"));
                                                                        new UserService(PassWordSetupActivity.this).settoken(regist.getMsg());
                                                                        Utils.print("token = "+regist.getMsg());
//                                                                        Intent intent = new Intent(PassWordSetupActivity.this, Personal_SetupActivity.class);
                                                                        startActivity(LoginActivity.class);
                                                                        showShort("有朋自远方来 不亦乐乎，欢迎您成为万阔会员 ");
                                                                        finish();
                                                                    }

                                                                }
                                                            });
                                                } else {
                                                    showShort(regist.getMsg());
                                                    setResult(Constant.ResultCode.FAIL,new Intent());
                                                    finish();
                                                }
                                            }
                                        });
                            } else {
                                String url = Apis.Base + Apis.updatepwd;
                                String phone = getIntent().getStringExtra("phone");
                                OkHttpUtils
                                        .post()
                                        .url(url)
                                        .addParams("telephone", phone)
                                        .addParams("newpassword", password)
                                        .build()
                                        .execute(new MyStringCallback() {

                                            @Override
                                            public void onError(Call call, Exception e, int id) {

                                            }

                                            @Override
                                            public void onHttpResponse(String response, int id) throws Exception {
                                                L.d(response);
                                                BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                                if (baseResult.getCode() == 0) {
                                                    showShort(baseResult.getMsg());
                                                    new UserService(PassWordSetupActivity.this).settoken("0");
                                                    new UserService(PassWordSetupActivity.this).setphone(phone);
                                                    Intent intent = new Intent(PassWordSetupActivity.this, LoginActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });
                            }
                        }else {
                            showShort("输入密码不一致");
                        }
                    } else {
                        showShort("请再次输入密码");
                    }
                } else {
                    showShort("请输入密码");
                }
            }
        });
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
