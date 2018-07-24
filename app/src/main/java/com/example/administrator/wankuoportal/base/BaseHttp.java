package com.example.administrator.wankuoportal.base;

import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.AsyncHttpReturnListener;

import java.util.Date;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/27.
 *     desc    :
 * </pre>
 */
public class BaseHttp {

    public static void httpGet(AsyncHttpReturnListener listener, String type, RequestParams params, String url) {
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.getInstance()).gettoken();
        final String accountId = new UserService(MyApplication.getInstance()).getaccountid();
        token = MD5Util.md5(time + token);
        params.put("accountId", accountId);
        params.put("token", accountId + "," + time + "," + token);
        if (!url.startsWith("http"))
            url = Apis.Base + url;
        HttpUtil.getInstance(listener).httpGet(type, params, url);
    }

    public static void httpPost(AsyncHttpReturnListener listener, String type, RequestParams params, String url) {
        String time = new Date().getTime() + "";
        String token = new UserService(MyApplication.getInstance()).gettoken();
        final String accountId = new UserService(MyApplication.getInstance()).getaccountid();
        token = MD5Util.md5(time + token);
        params.put("accountId", accountId);
        params.put("token", accountId + "," + time + "," + token);
        if (!url.startsWith("http"))
            url = Apis.Base + url;
        HttpUtil.getInstance(listener).httpPost(type, params, url);
    }
}
