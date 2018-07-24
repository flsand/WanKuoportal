package com.example.administrator.wankuoportal.aaPackage.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.wankuoportal.aaPackage.listener.PayListener;
import com.example.administrator.wankuoportal.beans.PayResult;
import com.example.administrator.wankuoportal.global.L;

import java.util.Map;

/**
 * Created by Administrator on 2016/6/4.
 */

/**
 * 文件名：AliPay
 * 描    述： 支付宝
 * 作    者：flysand - yjf
 * 时    间：2017/7/25.
 */
public class AliPay {

    private Activity activity;
    private String orderId;

    public AliPay(Activity activity) {
        this.activity = activity;
    }

    public void pay(final String orderInfo, String orderId) {

        this.orderId = orderId;

        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(activity);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Message msg = new Message();
            msg.what = 200;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
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
                Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                if (activity instanceof PayListener) {
                    ((PayListener) activity).paySuccess(orderId);
                }
            } else if (TextUtils.equals(resultStatus, "6001")) {
                Toast.makeText(activity, "支付取消", Toast.LENGTH_SHORT).show();
                if (activity instanceof PayListener) {
                    ((PayListener) activity).payCancel();
                }
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                if (activity instanceof PayListener) {
                    ((PayListener) activity).payFail(orderId);
                }
            }
        }
    };
}
