package com.example.administrator.wankuoportal.ui.Pay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.flysand.mylibrary.util.Utils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.example.administrator.wankuoportal.beans.ApiPublishDemandOrder;
import com.example.administrator.wankuoportal.beans.Pay;
import com.example.administrator.wankuoportal.beans.PayResult;
import com.example.administrator.wankuoportal.beans.Wxpayinfo;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.PayCore;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 *
 *  发布需求->
 * 支付选择
 */

public class PayRenWuActivity extends BaseActivity {

    private ImageView back;
    private TextView informationtitle;
    private LinearLayout payyinhang;
    private LinearLayout paywx;
    private LinearLayout payzfb;
    private LinearLayout payqianbao;
    private TextView paymoney;
    int price;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_choose);
        this.payqianbao = (LinearLayout) findViewById(R.id.pay_qianbao);
        this.payzfb = (LinearLayout) findViewById(R.id.pay_zfb);
        this.paywx = (LinearLayout) findViewById(R.id.pay_wx);
        this.payyinhang = (LinearLayout) findViewById(R.id.pay_yinhang);
        this.paymoney = (TextView) findViewById(R.id.pay_money);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        this.back = (ImageView) findViewById(R.id.back);
        price = Integer.valueOf(getIntent().getStringExtra("price"));
        id = getIntent().getStringExtra("id");
        paymoney.setText(price + "元");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //微信支付
        paywx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = new Date().getTime() + "";
                String token = new UserService(MyApplication.context).gettoken();
                String accountId = new UserService(MyApplication.context).getaccountid();
                token = MD5Util.md5(time + token);
                String url = Apis.Base + Apis.demandWx + "?token=" + accountId + "," + time + "," + token + "&wxpaytype=1";

                ApiPublishDemandOrder apiRechargeOrder = new ApiPublishDemandOrder();
                apiRechargeOrder.setAccountId(Integer.valueOf(accountId));
                apiRechargeOrder.setPayType(2);
                apiRechargeOrder.setState(1);
                BigDecimal total = new BigDecimal(price);
                apiRechargeOrder.setTotalAmount(total);
                apiRechargeOrder.setType(3);
                apiRechargeOrder.setDemandId(id);

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
                                L.d(response);
                                if (wxpayinfo.getCode() == 0) {
                                    final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
                                    msgApi.registerApp(wxpayinfo.getData().getAppId());
                                    PayReq request = new PayReq();
                                    request.appId = wxpayinfo.getData().getAppId();
                                    request.partnerId = wxpayinfo.getData().getPartnerId();
                                    request.prepayId = wxpayinfo.getData().getPrepayId();
                                    request.packageValue = "Sign=WXPay";
                                    request.nonceStr = wxpayinfo.getData().getNonceStr();
                                    request.timeStamp = wxpayinfo.getData().getTimestamp();
                                    request.sign = wxpayinfo.getData().getSign();
                                    boolean req = msgApi.sendReq(request);
                                    Utils.print("req = " + req);
                                } else {
                                    showShort(wxpayinfo.getMsg());
                                }

                            }
                        });
            }
        });
        //支付宝支付
        payzfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = new Date().getTime() + "";
                String token = new UserService(getBaseContext()).gettoken();
                String accountId = new UserService(getBaseContext()).getaccountid();
                token = MD5Util.md5(time + token);
                String url = Apis.Base + Apis.demandZfb + "?token=" + accountId + "," + time + "," + token;

                ApiPublishDemandOrder apiRechargeOrder = new ApiPublishDemandOrder();
                apiRechargeOrder.setAccountId(Integer.valueOf(accountId));
                apiRechargeOrder.setPayType(1);
                apiRechargeOrder.setState(1);
                BigDecimal total = new BigDecimal(price);
                apiRechargeOrder.setTotalAmount(total);
                apiRechargeOrder.setType(3);
                apiRechargeOrder.setDemandId(id);

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
                                L.d(response);
                                final Pay baseResult = gson.fromJson(response, Pay.class);
                                if (baseResult.getCode() == 0) {
                                    Runnable payRunnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            PayTask alipay = new PayTask(PayRenWuActivity.this);
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
                                    new UserService(PayRenWuActivity.this).setislogin("1");
                                }

                            }
                        });
            }
        });
    }


    @Override
    protected void onResume() {

        super.onResume();
        Utils.print("mWeichatState = " + PayCore.getInstance().mWeichatState);
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            L.d("wchat-1", PayCore.getInstance().mWeichatState + "");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            showShort("支付成功");
            startActivity(PayResultActivity.class);
            finish();
        }

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            L.d("zfb=====" + resultStatus);
            L.d("zfb=====" + resultInfo);
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                Toast.makeText(PayRenWuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(PayResultActivity.class);
                        finish();
                    }
                });
            } else if (TextUtils.equals(resultStatus, "6001")) {
                showShort("支付取消");
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(PayRenWuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
