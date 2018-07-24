package com.example.administrator.wankuoportal.ui.Pay;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.aaPackage.listener.PayListener;
import com.example.administrator.wankuoportal.aaPackage.utils.AliPay;
import com.example.administrator.wankuoportal.base.MyBaseActivity;
import com.example.administrator.wankuoportal.beans.WeChatPayBean;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.Constant;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.PayCore;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 充值
 */
public class ChongZhiActivity extends MyBaseActivity implements PayListener {

    private android.widget.ImageView back;
    private android.widget.TextView informationtitle;
    private android.widget.CheckBox payzfb;
    private android.widget.LinearLayout zfb;
    private android.widget.CheckBox paywx;
    private android.widget.LinearLayout wx;
    private android.widget.EditText jine;
    private TextView queding;
    private String paytype = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi);
        this.queding = (TextView) findViewById(R.id.queding);
        this.jine = (EditText) findViewById(R.id.jine);
        this.wx = (LinearLayout) findViewById(R.id.wx);
        this.paywx = (CheckBox) findViewById(R.id.pay_wx);
        this.zfb = (LinearLayout) findViewById(R.id.zfb);
        this.payzfb = (CheckBox) findViewById(R.id.pay_zfb);
        this.informationtitle = (TextView) findViewById(R.id.information_title);
        initview();
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initview();
                paywx.setChecked(true);
                paytype = "wx";
            }
        });

        zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ProjectUtil.showDevelopmentMessage();
                initview();
                payzfb.setChecked(true);
                paytype = "zfb";
            }
        });
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paytype.equals("0")) {
                    toast.setText("请选择充值类型");
                } else if (!TextUtils.isEmpty(jine.getText())) {
                    if (paytype.equals("wx")) {
                        RequestParams params = new RequestParams();
                        params.put("money", jine.getText().toString());
                        httpGet("weChatRechange", params, Apis.weChatRechange);
                    } else {

                        RequestParams params = new RequestParams();
                        params.put("accountId", new UserService(ChongZhiActivity.this).getaccountid());
                        params.put("money", jine.getText().toString());
                        httpGet("aliPayRechange", params, Apis.aliPayRechange);
                    }
                } else {
                    toast.setText("请输入充值金额");
                }
            }
        });

        double money = getIntent().getDoubleExtra(Constant.IntentKey.DOUBLE, 0);
        if (money > 0) {
            jine.setText(ProjectUtil.formatDouble(money));
            jine.setSelection(jine.getText().toString().length());
        }
    }

    private void initview() {
        paywx.setChecked(false);
        payzfb.setChecked(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PayCore.getInstance().mWeichatState == PayCore.WeiChat_Pay_Success) {
            L.d("wchat-1", PayCore.getInstance().mWeichatState + "");
            PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Normal;
            toast.setText("充值成功");
            finish();
        }

    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception {

        if ("aliPayRechange".equals(type)) {

            Utils.print("挑转支付宝支付");
            try {
                new AliPay(this).pay(body.getString("data"), "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        return super.onHttpAnalysisIntercept(type, body);
    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) {
        super.onHttpSuccess(type, jsonObject);
        if ("weChatRechange".equals(type)) {
            WeChatPayBean weChatPayBean = JSONUtil.toJavaBean(WeChatPayBean.class, jsonObject);
            final IWXAPI msgApi = WXAPIFactory.createWXAPI(getBaseContext(), null);
            msgApi.registerApp(weChatPayBean.getAppId());
            PayReq request = new PayReq();
            request.appId = weChatPayBean.getAppId();
            request.partnerId = weChatPayBean.getPartnerId();
            request.prepayId = weChatPayBean.getPrepayId();
            request.packageValue = "Sign=WXPay";
            request.nonceStr = weChatPayBean.getNonceStr();
            request.timeStamp = weChatPayBean.getTimestamp();
            request.sign = weChatPayBean.getSign();
            msgApi.sendReq(request);
        }

    }


    @Override
    public void paySuccess(String orderId) {
        finish();
    }

    @Override
    public void payFail(String orderId) {
        dismissHttpDialog();
    }

    @Override
    public void payCancel() {
        dismissHttpDialog();
    }
}
