package com.example.administrator.wankuoportal.service;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;
import com.umeng.message.UmengMessageService;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

/**
 * Created by zht on 2017/08/17 16:32
 */

public class MyPushIntentService extends UmengMessageService {
    private static final String TAG = MyPushIntentService.class.getName() + "=====";


    private static int NOTIFICATION_ID = 0x123;

    NotificationManager nm;


    @Override
    public void onMessage(Context context, Intent intent) {

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NOTIFICATION_ID += 1;
        try {
            //可以通过MESSAGE_BODY取得消息体
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage msg = new UMessage(new JSONObject(message));
            UmLog.d(TAG, "message=" + message);      //消息体
            UmLog.d(TAG, "custom=" + msg.custom);    //自定义消息的内容
            UmLog.d(TAG, "title=" + msg.title);      //通知标题
            UmLog.d(TAG, "text=" + msg.text);        //通知内容
            // code  to handle message here
            sendNotific(msg);


        } catch (Exception e) {
            UmLog.e(TAG, e.getMessage());
        }


    }

    private boolean isChat = false;

    private void sendNotific(UMessage msg) {

        Intent intent;
        intent = new Intent(MyPushIntentService.this, MyInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}