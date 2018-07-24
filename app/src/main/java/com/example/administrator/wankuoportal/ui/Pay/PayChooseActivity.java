package com.example.administrator.wankuoportal.ui.Pay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.ApiRechargeOrder;
import com.example.administrator.wankuoportal.beans.Pay;
import com.example.administrator.wankuoportal.beans.PayResult;
import com.example.administrator.wankuoportal.beans.Wxpayinfo;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.PayCore;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.DialogUtil;
import com.example.administrator.wankuoportal.util.LogUtils;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 升级店长
 * 支付选择Activity
 * <p>
 * 修改时间:2018年2月28日14:23:12
 */
public class PayChooseActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.LinearLayout payyinhang;
    private android.widget.LinearLayout paywx;
    private android.widget.LinearLayout payzfb;
    private android.widget.LinearLayout payqianbao;
    private TextView paymoney;
    double price;
    int mTarget;
    public static final String PAY_CHOOSE_SUCCESS_TIP = "青，取之于蓝，而青于蓝；冰，水为之，而寒于水,恭喜您升级为万阔店长";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_choose);
        initVariables();
        this.payqianbao = (LinearLayout) findViewById(R.id.pay_qianbao);
        this.payzfb = (LinearLayout) findViewById(R.id.pay_zfb);
        this.paywx = (LinearLayout) findViewById(R.id.pay_wx);
        this.payyinhang = (LinearLayout) findViewById(R.id.pay_yinhang);
        this.paymoney = (TextView) findViewById(R.id.pay_money);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        paymoney.setText(price + "元");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 发起微信支付
        paywx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wxPaySubmit();
            }
        });
        // 支付宝支付
        payzfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliPaySubmit();
            }
        });
        // 钱包支付
        payqianbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.flysand.mylibrary.customView.DialogUtil dialogUtil = new com.flysand.mylibrary.customView.DialogUtil(PayChooseActivity.this) {
                    @Override
                    public void confirmClick(View view) {
                        qianbaoPaySubmit();
                    }
                }.setContent("立即升级店长").setConfirmBtnText("确认");
                dialogUtil.showDialog(2, 0);
            }
        });
    }

    /**
     * 初始化变量的方法
     */
    private void initVariables() {
        price = Double.valueOf(getIntent().getStringExtra("price"));
        mTarget = Integer.valueOf(getIntent().getStringExtra("target"));
    }

    /**
     * 发起支付宝支付的请求
     */
    private void aliPaySubmit() {
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url;

        url = Apis.Base + Apis.zfb + "?token=" + accountId + "," + time + "," + token + "&accountId=" +
                Integer.valueOf(accountId) + "&target=" + mTarget + "&money=" + price;

        ApiRechargeOrder apiRechargeOrder = new ApiRechargeOrder();
        apiRechargeOrder.setAccountId(Integer.valueOf(accountId));
        BigDecimal total = new BigDecimal(price);
        apiRechargeOrder.setTotalAmount(total);
        apiRechargeOrder.setType(1);

        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(apiRechargeOrder))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        LogUtils.e("支付宝支付调用小仙女服务器返回值");
                        final Pay baseResult = gson.fromJson(response, Pay.class);
                        if (baseResult.getCode() == 0) {
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(PayChooseActivity.this);
                                    Map<String, String> result = alipay.payV2(baseResult.getData(), true);
                                    L.d(baseResult.getData());
                                    Message msg = new Message();
                                    msg.what = 200;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else if (baseResult.getCode() == 2) {
                            showShort(baseResult.getMsg());
                            startActivity(LoginActivity.class);
                            new UserService(MyApplication.context).setislogin("1");
                        }

                    }
                });
    }

    /**
     * 发起微信支付的请求
     */
    private void wxPaySubmit() {
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);

        //String url = Apis.Base + Apis.wx + "?token=" + accountId + "," + time + "," + token + "&wxpaytype=1";

        String url = "";

        url = Apis.Base + Apis.wx + "?token=" + accountId + "," + time + "," + token + "&target=" + mTarget;

        ApiRechargeOrder apiRechargeOrder = new ApiRechargeOrder();
        apiRechargeOrder.setAccountId(Integer.valueOf(accountId));
        apiRechargeOrder.setPayType(2);
        apiRechargeOrder.setState(2);
        BigDecimal total = new BigDecimal(price);
        //BigDecimal total = new BigDecimal(price);
        apiRechargeOrder.setTotalAmount(total);
        apiRechargeOrder.setType(1);


        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(apiRechargeOrder))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        Wxpayinfo wxpayinfo = gson.fromJson(response, Wxpayinfo.class);
                        LogUtils.e("微信支付调用小仙女服务器返回值 : " + response);
                        if (wxpayinfo.getCode() == 0) {
                            final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
                            //注册的AppId
                            msgApi.registerApp(wxpayinfo.getData().getAppId());
                            PayReq request = new PayReq();
                            request.appId = wxpayinfo.getData().getAppId();
                            request.partnerId = wxpayinfo.getData().getPartnerId();
                            request.prepayId = wxpayinfo.getData().getPrepayId();
                            request.packageValue = "Sign=WXPay";
                            request.nonceStr = wxpayinfo.getData().getNonceStr();
                            request.timeStamp = wxpayinfo.getData().getTimestamp();
                            request.sign = wxpayinfo.getData().getSign();
                            msgApi.sendReq(request);
                        } else {
                            showShort(wxpayinfo.getMsg());
                        }
                    }
                });
    }

    private void qianbaoPaySubmit() {
        Log.d("qzw", "qianbaoPaySubmit");
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);

        String url = "";

        url = Apis.Base + Apis.qianbao + "?token=" + accountId + "," + time + "," + token + "&accountId=" +
                Integer.valueOf(accountId) + "&target=" + mTarget + "&money=" + price;

        ApiRechargeOrder apiRechargeOrder = new ApiRechargeOrder();
        apiRechargeOrder.setAccountId(Integer.valueOf(accountId));
        BigDecimal total = new BigDecimal(price);
        //BigDecimal total = new BigDecimal(price);
        apiRechargeOrder.setTotalAmount(total);
        apiRechargeOrder.setType(1);

        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(apiRechargeOrder))
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new MyStringCallback() {

                    @Override
                    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception {

                        if (body.getInt("code") == 0) {
                            new com.flysand.mylibrary.customView.DialogUtil(PayChooseActivity.this) {
                                @Override
                                public void confirmClick(View view) {
                                    setResult(RESULT_OK);
                                    finish();
                                }
                            }.setContent(PAY_CHOOSE_SUCCESS_TIP).showDialog(1, 0);
                        } else {
                            new com.flysand.mylibrary.customView.DialogUtil(PayChooseActivity.this) {
                                @Override
                                public void confirmClick(View view) {
                                    startActivity(ChongZhiActivity.class);
                                    finish();
                                }
                            }.setConfirmBtnText("充值").setContent(body.getString("msg")).showDialog(2, 0);
                        }
                        return true;
                    }
                });
    }


    @Override
    protected void onResume() {

        super.onResume();
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            LogUtils.e("微信支付返回值 : " + PayCore.getInstance().mWeichatState + "");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
//            showShort("支付成功");
            DialogUtil.showMessage(this, PAY_CHOOSE_SUCCESS_TIP);
//            finish();
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //支付宝支付返回值回调
                case 200:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    L.d("zfb=====" + resultStatus);
                    L.d("zfb=====" + resultInfo);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayChooseActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        });
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        showShort("支付取消");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayChooseActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
}
