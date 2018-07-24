package com.example.administrator.wankuoportal.http;

import com.flysand.mylibrary.http.HttpAnalysisHelper;
import com.flysand.mylibrary.listener.AsyncHttpReturnListener;

import okhttp3.Call;
import okhttp3.Response;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.http
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/14.
 *     desc    :
 *
 * </pre>
 */
public class MyHttpReturnListener implements AsyncHttpReturnListener {

    HttpAnalysisHelper helper;

    public MyHttpReturnListener(HttpAnalysisHelper helper) {
        this.helper = helper;
    }

    @Override
    public void onFailure(String s, Call call, Exception e) {
        helper.onFailure(s,call,e);
    }

    @Override
    public void onResponse(String s, Call call, Response response, String s1) throws Exception {
        helper.onResponse(s,call,response,s1);
    }
}
