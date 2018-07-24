package com.example.administrator.wankuoportal.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.util.PagingHelper;
import com.example.administrator.wankuoportal.util.ProjectUtil;
import com.flysand.mylibrary.base.BaseFragment;
import com.flysand.mylibrary.http.HttpAnalysisHelper;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.ObserverListener;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.MyToast;
import com.flysand.mylibrary.util.Utils;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by FlySand on 2018/4/4.
 */

public class MyBaseFragment extends BaseFragment implements MyAsyncHttpAnalysisListener {

    protected MyToast toast;
    protected Bundle bundle;
    protected HttpUtil httpUtil;
    protected HttpAnalysisHelper analysisHelper;
    ProgressDialog dialogproa;

    protected PagingHelper pagingHelper = new PagingHelper();
    protected MyApplication app = MyApplication.getInstance();

    public void onAttach(Activity var1) {
        super.onAttach(var1);
        this.bundle = this.getArguments();
        if (this.toast == null) {
            this.toast = new MyToast(var1);
        }

        if (this.httpUtil == null) {
            this.httpUtil = HttpUtil.getInstance(this);
        }

        this.analysisHelper = this.getCustumAnalysisHelper();
    }

    protected void initBanner(Banner banner, List<Integer> list) {
        int delayTime = 3000;
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        banner.setDelayTime(delayTime);
        banner.start();
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

    protected String getAccountId() {
        return new UserService(MyApplication.getInstance()).getaccountid();
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
        httpPostNoDialog(type, params, url);
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
        httpGetNoDialog(type, params, url);
    }

    /**
     * http请 post请求
     *
     * @param type
     * @param params
     * @param url
     */
    protected void httpGetNoDialog(String type, RequestParams params, String url) {
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

    public void onUpdate(int var1, Intent var2) {
    }

    protected void dismissErrDialog(String msg) {

        dismissHttpDialog();
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
    public void onHttpResult(String type, String code, String msg) throws Exception {
        dismissHttpDialog();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) throws Exception {
        dismissHttpDialog();
    }

    public void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> clazz, String... data) {
        if (data.length % 2 == 1) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), clazz);
        for (int i = 0; i < data.length / 2; i++) {
            intent.putExtra(data[i * 2], data[i * 2 + 1]);
        }
        startActivity(intent);
    }
}


