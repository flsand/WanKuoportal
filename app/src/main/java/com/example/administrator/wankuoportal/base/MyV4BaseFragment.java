package com.example.administrator.wankuoportal.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.util.PagingHelper;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.http.HttpAnalysisHelper;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.AsyncHttpReturnListener;
import com.flysand.mylibrary.listener.ObserverListener;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.MyNewToast;
import com.flysand.mylibrary.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by FlySand on 2018/4/4.
 */

public class MyV4BaseFragment extends Fragment implements MyAsyncHttpAnalysisListener, AsyncHttpReturnListener {

    protected MyNewToast toast;
    protected Bundle bundle;
    protected HttpUtil httpUtil;
    protected HttpAnalysisHelper analysisHelper;
    ProgressDialog dialogproa;
    protected PagingHelper pagingHelper = new PagingHelper();

    public MyV4BaseFragment() {
    }

    public void onAttach(Activity var1) {
        super.onAttach(var1);
        this.bundle = this.getArguments();
        if (this.toast == null) {
            this.toast = new MyNewToast(var1);
        }

        if (this.httpUtil == null) {
            this.httpUtil = HttpUtil.getInstance(this);
        }

        this.analysisHelper = this.getCustumAnalysisHelper();
    }

    protected HttpAnalysisHelper getCustumAnalysisHelper() {
        return new DefultHttpAnalysisHelper(this);
    }

    public void sendUpdateMessage(int var1, Intent var2) {
        if (this.getActivity() instanceof ObserverListener) {
            ((ObserverListener) this.getActivity()).sendUpdateMessage(var1, var2);
            Utils.print("sendUpdateMessage   " + var1);
        }
    }

    public void onUpdate(int var1, Intent var2) {
    }

    /**
     * http请 post请求
     *
     * @param type
     * @param params
     * @param url
     */
    protected void httpPost(String type, RequestParams params, String url) {
        showHttpDialog();
        BaseHttp.httpPost(this, type, params, url);
    }
    /**
     * http请 post请求
     *
     * @param type
     * @param params
     * @param url
     */
    protected void httpPostNoDialog(String type, RequestParams params, String url) {
        BaseHttp.httpPost(this, type, params, url);
    }

    /**
     * http请 post请求
     *
     * @param type
     * @param params
     * @param url
     */
    protected void httpGet(String type, RequestParams params, String url) {
        showHttpDialog();
        BaseHttp.httpGet(this, type, params, url);
    }

    /**
     * 显示加载对话框
     */
    protected void showHttpDialog() {
        if (dialogproa != null)
            dialogproa.dismiss();
        dialogproa = new ProgressDialog(getActivity());
        dialogproa.setMessage("请稍候...");
//        dialogproa.setCancelable(false);
        dialogproa.show();
    }

    /**
     * 显示加载对话框
     *
     * @param str
     */
    protected void showHttpDialog(String str) {
        if (dialogproa != null) {
            dialogproa.dismiss();
            dialogproa = null;
        }
        dialogproa = new ProgressDialog(getActivity());
        dialogproa.setMessage(str);
//        dialogproa.setCancelable(false);
        dialogproa.show();
    }

    protected void dismissHttpDialog() {
        if (dialogproa != null) {
            dialogproa.dismiss();
        }
    }

    protected void dismissErrDialog(String msg) {

        showHttpDialog(msg);
        new MyHandler(1500) {
            @Override
            public void run() {
                if (dialogproa != null)
                    dialogproa.dismiss();
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialogproa != null) {
            dialogproa.dismiss();
        }
    }

    public void onFailure(String type, Call var2, Exception var3) {
        if (this.analysisHelper != null) {
            this.analysisHelper.onFailure(type, var2, var3);
        }

    }

    public void onResponse(String type, Call var2, Response var3, String var4) {
        if (this.analysisHelper != null) {
            this.analysisHelper.onResponse(type, var2, var3, var4);
        }

    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        dismissHttpDialog();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int i, int i1, int i2) throws Exception {
        dismissHttpDialog();
    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {
        dismissErrDialog(s1);

    }

    public boolean onHttpAnalysisIntercept(String type, JSONObject var2) throws Exception {
        return false;
    }

    public void onLoginOutTime(String type) {
        ProjectUtil.jumpLogin(getActivity());
    }

    @Override
    public void onHttpResult(String type, String code, String msg) {
        dismissHttpDialog();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {
        dismissHttpDialog();
    }
}

