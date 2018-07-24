package com.example.administrator.wankuoportal.fragment.login;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseFragment;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.denglu.RegisterActivity;
import com.example.administrator.wankuoportal.util.LogUtils;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.Utils;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/19 0019.
 * <p>
 * 账号密码登录
 * 修改时间:2018年3月1日08:43:55
 */
public class loginFragment_Account extends BaseFragment {
    private android.widget.EditText accountlogin;
    private android.widget.EditText passwordlogin;
    private android.widget.Button btloginaccout;
    private android.widget.LinearLayout loginwbaccount;
    private android.widget.LinearLayout loginqqaccount;
    private android.widget.LinearLayout loginwxaccount;
    private android.widget.TextView huiyuan;
    private android.widget.TextView guzhu;
    private android.widget.TextView forget_password;
    private String shenfen = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.login_account, container, false);//关联布局文件
        initDefaultView(rootView);
        initOnclick();
        return rootView;
    }

    private void initDefaultView(View rootView) {
        this.guzhu = (TextView) rootView.findViewById(R.id.guzhu);
        this.huiyuan = (TextView) rootView.findViewById(R.id.huiyuan);
        this.forget_password = (TextView) rootView.findViewById(R.id.forget_password);
        this.loginwxaccount = (LinearLayout) rootView.findViewById(R.id.login_wx_account);
        this.loginqqaccount = (LinearLayout) rootView.findViewById(R.id.login_qq_account);
        this.loginwbaccount = (LinearLayout) rootView.findViewById(R.id.login_wb_account);
        this.btloginaccout = (Button) rootView.findViewById(R.id.bt_login_accout);
        this.passwordlogin = (EditText) rootView.findViewById(R.id.password_login);
        this.accountlogin = (EditText) rootView.findViewById(R.id.account_login);

        accountlogin.setText(new UserService(MyApplication.context).getphone());
        passwordlogin.setText(new UserService(MyApplication.context).getPassword());
    }

    private void initOnclick() {
        //账号密码登录按钮点击
        btloginaccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSubmit();
            }
        });
        huiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initView();
                huiyuan.setBackgroundResource(R.drawable.bg_shenfen);
                huiyuan.setTextColor(getResources().getColor(R.color.white));
                shenfen = "huiyuan";

            }
        });
        guzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initView();
                guzhu.setBackgroundResource(R.drawable.bg_shenfen);
                guzhu.setTextColor(getResources().getColor(R.color.white));
                shenfen = "guzhu";
            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RegisterActivity.class, "type", "forget");
            }
        });
    }

    public void updateAccout(String account) {
        if (!TextUtils.isEmpty(account))
            accountlogin.setText(account);
    }

    /**
     * 执行登录的方法
     */
    private void loginSubmit() {
        final String account = accountlogin.getText().toString();
        final String password = passwordlogin.getText().toString();
        if (account.isEmpty()) {
            showShort("请输入账号");
            return;
        }
        if (password.isEmpty()) {
            showShort("请输入密码");
            return;
        }
        if (shenfen.isEmpty()) {
            showShort("请选择要登录的身份");
            return;
        }

        ProgressDialog dialogproa = new ProgressDialog(getActivity());
        dialogproa.setMessage("登录中");
        dialogproa.setCancelable(false);
        dialogproa.show();
        //信息验证无误,执行登录操作
        String url = Apis.Base + Apis.login;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("telephone", account)
                .addParams("password", password)
                .addParams("logintype", "1")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("onError:" + e.toString());
                        Utils.print("onError");
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
                        LogUtils.e("执行登录的方法:" + response);
                        Regist regist = gson.fromJson(response, Regist.class);
                        if (regist.getCode() == 0) {
                            dialogproa.dismiss();
                            //注销别名推送信息
                            PushAgent mPushAgent = PushAgent.getInstance(getActivity());
                            mPushAgent.removeAlias(new UserService(MyApplication.context).getaccountid(), "USER_ID", new UTrack.ICallBack() {
                                @Override
                                public void onMessage(boolean isSuccess, String message) {
                                    LogUtils.e("removeAlias isSuccess : " + isSuccess);
                                    LogUtils.e("removeAlias message : " + message);
                                }
                            });
                            //保存身份
                            new UserService(MyApplication.context).setShenfen(shenfen);
                            //保存手机号
                            new UserService(MyApplication.context).setphone(account);
                            new UserService(MyApplication.context).setPassword(password);
                            //保存accountId
                            new UserService(MyApplication.context).setaccountid(regist.getData().getAaccountInfo().getAccountId() + "");
                            //保存Token
                            new UserService(MyApplication.context).settoken(regist.getMsg());
                            //保存登录状态
                            new UserService(MyApplication.context).setislogin("0");
                            //跳转到导航页面
                            jumpMain();
                            //注销推送信息
                            mPushAgent.addExclusiveAlias(new UserService(MyApplication.context).getaccountid(), "USER_ID", new UTrack.ICallBack() {
                                @Override
                                public void onMessage(boolean isSuccess, String message) {
                                    LogUtils.e("addExclusiveAlias isSuccess : " + isSuccess);
                                    LogUtils.e("addExclusiveAlias message : " + message);
                                }
                            });
                            //获取当前Fragment对象所以来的Activity,进行finish操作
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
    }

    private void jumpMain() {
        startActivity(NewMainActivity.class);
    }

    private void initView() {
        huiyuan.setBackgroundResource(R.drawable.bg_shenfen_none);
        guzhu.setBackgroundResource(R.drawable.bg_shenfen_none);
        guzhu.setTextColor(getResources().getColor(R.color.all_textc));
        huiyuan.setTextColor(getResources().getColor(R.color.all_textc));
    }
}
