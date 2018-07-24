package com.example.administrator.wankuoportal.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.administrator.wankuoportal.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.PayCore;
import com.example.administrator.wankuoportal.global.ToastManager;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "com.wankuoportal.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, "wxbf38ad6eed197a47");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

        Log.v("wechat", "onReq() returned: " + req);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        String message ;
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            L.d(baseResp.getType()+"wechat");
            L.d(baseResp.errCode+"wechat");

            if (0 == baseResp.errCode) {
                ToastManager.send(this, "支付成功");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Success;
            } else if (-1 == baseResp.errCode) {
                ToastManager.send(this, "支付失败");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
            } else if (-2 == baseResp.errCode) {
                ToastManager.send(this, "取消支付");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
            } else {
                ToastManager.send(this, "支付失败");
                PayCore.getInstance().mWeichatState = PayCore.WeiChat_Pay_Failed;
            }
            finish();
        }
    }
}