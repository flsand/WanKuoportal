package com.example.administrator.wankuoportal.fragment.login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.activity.NewMainActivity;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.util.Forms;
import com.flysand.mylibrary.util.MyHandler;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Random;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class LoginFragment_phone extends BaseFragment {
    private android.widget.TextView textView;
    private android.widget.EditText phonelogin;
    private android.widget.EditText passwordlogin;
    private android.widget.Button btloginaccout;
    private android.widget.LinearLayout loginwbphone;
    private android.widget.LinearLayout loginqqphone;
    private android.widget.LinearLayout loginwxphone;
    private TextView huiyuanphone;
    private TextView guzhuphone;
    private TextView getcode;
    private String shenfen = "";
    private EditText codelogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_phone, container, false);//关联布局文件
        this.codelogin = (EditText) rootView.findViewById(R.id.code_login);
        this.getcode = (TextView) rootView.findViewById(R.id.getcode);
        this.guzhuphone = (TextView) rootView.findViewById(R.id.guzhu_phone);
        this.huiyuanphone = (TextView) rootView.findViewById(R.id.huiyuan_phone);
        this.loginwxphone = (LinearLayout) rootView.findViewById(R.id.login_wx_phone);
        this.loginqqphone = (LinearLayout) rootView.findViewById(R.id.login_qq_phone);
        this.loginwbphone = (LinearLayout) rootView.findViewById(R.id.login_wb_phone);
        this.btloginaccout = (Button) rootView.findViewById(R.id.bt_login_accout);
        this.phonelogin = (EditText) rootView.findViewById(R.id.phone_login);
        this.textView = (TextView) rootView.findViewById(R.id.textView);

        phonelogin.setText(new UserService(MyApplication.context).getphone());
        initOnclick();
        initPhoneEdit();
        return rootView;
    }

    private void initPhoneEdit() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                getcode.setText(sec + "");
                sec--;
                if (sec > 0) {
                    getcode.setEnabled(false);
                    if (handler != null && runnable != null) {
                        handler.postDelayed(runnable, 1000);
                    }
                } else {
                    getcode.setText("获取验证码");
                    getcode.setEnabled(true);
                }
            }
        };

        phonelogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Forms.disValid(s.toString(), Forms.PHONENUM)) {
                    getcode.setBackgroundResource(R.color.gray);
                    getcode.setEnabled(false);
                } else {
                    getcode.setEnabled(true);
                    getcode.setBackgroundResource(R.color.baseColor);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void updateAccout(String account) {
        if (!TextUtils.isEmpty(account))
            phonelogin.setText(account);
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

    private void initOnclick() {
        //验证码登录按钮点击
        btloginaccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String account = phonelogin.getText().toString();
                final String password = codelogin.getText().toString();
                if (yzm.equals(password)) {
                    if (!account.isEmpty()) {
                        if (!password.isEmpty()) {
                            if (!shenfen.isEmpty()) {

                                ProgressDialog dialogproa = new ProgressDialog(getActivity());
                                dialogproa.setMessage("登录中");
                                dialogproa.setCancelable(false);
                                dialogproa.show();

                                String url = Apis.Base + Apis.login;
                                OkHttpUtils
                                        .post()
                                        .url(url)
                                        .addParams("telephone", account)
                                        .addParams("password", password)
                                        .addParams("logintype", "2")
                                        .build()
                                        .execute(new MyStringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                                dialogproa.setMessage("登录失败");
                                                new MyHandler(1000) {
                                                    @Override
                                                    public void run() {
                                                        dialogproa.dismiss();
                                                    }
                                                };
                                            }

                                            @Override
                                            public void onHttpResponse(String response, int id) throws Exception {
                                                L.d(response);
                                                Regist regist = gson.fromJson(response, Regist.class);
                                                if (regist.getCode() == 0) {
                                                    dialogproa.dismiss();
                                                    PushAgent mPushAgent = PushAgent.getInstance(getActivity());
                                                    mPushAgent.removeAlias(new UserService(getActivity()).getaccountid(), "USER_ID", new UTrack.ICallBack() {
                                                        @Override
                                                        public void onMessage(boolean isSuccess, String message) {
                                                            L.d(message);
                                                        }
                                                    });
                                                    new UserService(MyApplication.context).setShenfen(shenfen);
                                                    new UserService(MyApplication.context).setphone(account);
                                                    new UserService(MyApplication.context).setPassword(password);
                                                    new UserService(MyApplication.context).setaccountid(regist.getData().getAaccountInfo().getAccountId() + "");
                                                    new UserService(MyApplication.context).settoken(regist.getMsg());
                                                    new UserService(MyApplication.context).setislogin("0");
                                                  jumpMain();
                                                    mPushAgent.addExclusiveAlias(new UserService(getActivity()).getaccountid(), "USER_ID", new UTrack.ICallBack() {
                                                        @Override
                                                        public void onMessage(boolean isSuccess, String message) {
                                                            L.d(message);
                                                        }
                                                    });
                                                    getActivity().finish();
                                                } else {

                                                    dialogproa.setMessage(regist.getMsg());
                                                    new MyHandler(1000) {
                                                        @Override
                                                        public void run() {
                                                            dialogproa.dismiss();
                                                        }
                                                    };
                                                }

                                            }
                                        });
                            } else {
                                showShort("请选择要登录的身份");
                            }
                        } else {
                            showShort("请输入密码");
                        }
                    } else {
                        showShort("请输入账号");
                    }
                } else {
                    showShort("验证码验证失败");
                }
            }
        });
        huiyuanphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initview();
                huiyuanphone.setBackgroundResource(R.drawable.bg_shenfen);
                huiyuanphone.setTextColor(getResources().getColor(R.color.white));
                shenfen = "huiyuan";

            }
        });
        guzhuphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initview();
                guzhuphone.setBackgroundResource(R.drawable.bg_shenfen);
                guzhuphone.setTextColor(getResources().getColor(R.color.white));
                shenfen = "guzhu";
            }
        });
        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String accout = phonelogin.getEditableText().toString();
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

    }

    private void jumpMain() {
        startActivity(NewMainActivity.class);
    }

    private void initview() {
        huiyuanphone.setBackgroundResource(R.drawable.bg_shenfen_none);
        huiyuanphone.setTextColor(getResources().getColor(R.color.all_textc));
        guzhuphone.setTextColor(getResources().getColor(R.color.all_textc));
        guzhuphone.setBackgroundResource(R.drawable.bg_shenfen_none);
    }
}
