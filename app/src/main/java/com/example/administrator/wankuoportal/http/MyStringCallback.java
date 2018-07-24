package com.example.administrator.wankuoportal.http;


import android.content.Intent;
import android.text.TextUtils;

import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.base.DefultHttpAnalysisHelper;
import com.example.administrator.wankuoportal.base.MyAsyncHttpAnalysisListener;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.flysand.mylibrary.util.MyToast;
import com.flysand.mylibrary.util.Utils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/4.
 *     desc    :
 *
 * </pre>
 */
public class MyStringCallback extends StringCallback implements MyAsyncHttpAnalysisListener {

    DefultHttpAnalysisHelper helper = new DefultHttpAnalysisHelper(this);

    public void onHttpResponse(String response, int id) throws Exception {

        Utils.print("response = " + response);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        try {
            helper.onResponse("MyStringCallback", null, null, e.getMessage());
            e.printStackTrace();
            Utils.print("Exception =" + e.getMessage());
            if (!TextUtils.isEmpty(e.getMessage()) && e.getMessage().contains("Failed to connect to")) {
                new MyToast(MyApplication.context).setText(DefultHttpAnalysisHelper.OUT_TIME_MSG);
            } else if (e.getMessage().contains("No address associated with hostname")) {
                new MyToast(MyApplication.context).setText(DefultHttpAnalysisHelper.NO_ADDRESS_MSG);
            } else {
                new MyToast(MyApplication.context).setText(DefultHttpAnalysisHelper.ERR_MSG);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onResponse(String response, int id) {
        try {
            Utils.print("response = " + response);
            onHttpResponse(response, id);
            helper.onResponse("MyStringCallback", null, null, response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.getString("code").equals("2")) {
                    try {
                        onLoginOutTime(jsonObject.getString("msg"));
                    } catch (Exception e1) {
                        onLoginOutTime("登录信息过期，请重新登录");
                        e1.printStackTrace();
                    }
                    return;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            onHttpException();

            new MyToast(MyApplication.context).setText(DefultHttpAnalysisHelper.ERR_MSG);
//            Toast.makeText(MyApplication.context, DefultHttpAnalysisHelper.ERR_MSG, Toast.LENGTH_SHORT).show();
        }

    }

    public void jumpLogin() {
        Intent intent = new Intent(MyApplication.context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.context.startActivity(intent);
    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception {
        return false;
    }

    @Override
    public void onLoginOutTime(String msg) {
        new UserService(MyApplication.context).setislogin("1");
        new MyToast(MyApplication.context).setText(msg);

    }

    @Override
    public void onHttpSuccess(String s, JSONObject jsonObject) throws Exception {
    }

    @Override
    public void onHttpSuccess(String s, JSONArray jsonArray, int i, int i1, int i2) throws Exception {

    }

    @Override
    public void onHttpFailure(String s, String s1) throws Exception {
//        new MyToast(MyApplication.context).setText(s1);
    }

    public void onHttpException() {

    }

    @Override
    public void onHttpResult(String type, String code, String msg) {

    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {

    }
}
