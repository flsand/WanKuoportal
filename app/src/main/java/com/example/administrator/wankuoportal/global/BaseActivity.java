package com.example.administrator.wankuoportal.global;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.base.DefultHttpAnalysisHelper;
import com.example.administrator.wankuoportal.base.MyAsyncHttpAnalysisListener;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.http.APIFactory;
import com.example.administrator.wankuoportal.http.SubscriberOnNextListener;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flysand.mylibrary.http.HttpUtil;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.listener.AsyncHttpReturnListener;
import com.flysand.mylibrary.util.MyHandler;
import com.flysand.mylibrary.util.MyToast;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.lang.Strings;

import java.util.Date;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by zht on 2017/04/07 9:04
 */

public class BaseActivity extends AppCompatActivity implements MyAsyncHttpAnalysisListener, AsyncHttpReturnListener {

    public static String _ID = "_id_";
    public static String _TITLE = "_title_";
    public static Gson gson = new Gson();
    ProgressDialog dialogproa;
    public SubscriberOnNextListener getResultOnNext;
    public static final APIFactory retrofitUtil = (APIFactory) APIFactory.getInstance();

    DefultHttpAnalysisHelper analysisHelper = new DefultHttpAnalysisHelper(this);


    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscription getCompositeSubscription() {

        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
//        StatusBarUtil.setTransparent(BaseActivity.this);
//        PushAgent.getInstance(context).onAppStart();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    private Toast mToast = null;

    public void showLong(String msg) {
        if (!Strings.isBlank(msg)) {
            if (mToast == null) {
                mToast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
        }
    }

    public void showShort(final String msg) {
        if (!TextUtils.isEmpty(msg)) {

            new MyToast(getApplicationContext()).setText(msg);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (mToast == null) {
//                        mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
//                    } else {
//                        mToast.setText(msg);
//                    }
//                    mToast.show();
//                }
//            });

        }
    }


    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }


    public void startActivity(Class<? extends Activity> clazz, String... data) {
        if (data.length % 2 == 1) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        for (int i = 0; i < data.length / 2; i++) {
            intent.putExtra(data[i * 2], data[i * 2 + 1]);
        }
        startActivity(intent);
    }


    public void alert(String msg) {
        NormalDialog dialog = new NormalDialog(this);
        dialog.content(msg).isTitleShow(false).btnNum(1).btnText("确定").show();
    }

    public NormalDialog confirm(String msg, OnBtnClickL on) {
        NormalDialog dialog = new NormalDialog(this);
        dialog.setOnBtnClickL(null, on);
        dialog.content(msg).isTitleShow(false).btnNum(2).btnText("取消", "确定")
                .show();
        return dialog;
    }


    public String _ID() {
        return getIntent().getStringExtra(_ID);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BaseEvent event) {

    }

    /**
     * 显示加载对话框
     */
    protected void showHttpDialog() {
        if (dialogproa != null)
            dialogproa.dismiss();
        dialogproa = new ProgressDialog(this);
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
        dialogproa = new ProgressDialog(this);
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
        new MyHandler(1000) {
            @Override
            public void run() {
                if (dialogproa != null)
                    dialogproa.dismiss();
            }
        };
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
        String time = new Date().getTime() + "";
        String token = new UserService(getApplicationContext()).gettoken();
        final String accountId = new UserService(this).getaccountid();
        token = MD5Util.md5(time + token);
        params.put("accountId", accountId);
        params.put("token", accountId + "," + time + "," + token);
        HttpUtil.getInstance(this).httpPost(type, params, Apis.Base + url);
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
        String time = new Date().getTime() + "";
        String token = new UserService(getApplicationContext()).gettoken();
        final String accountId = new UserService(this).getaccountid();
        token = MD5Util.md5(time + token);
        params.put("accountId", accountId);
        params.put("token", accountId + "," + time + "," + token);
        HttpUtil.getInstance(this).httpGet(type, params, Apis.Base + url);
    }

    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {
        dismissHttpDialog();
    }

    public void onHttpSuccess(String type, JSONArray jsonArray, int page, int size, int count) throws Exception {
        dismissHttpDialog();
    }

    public void onHttpFailure(String type, String msg) throws Exception {
        dismissErrDialog(msg);

    }

    public void onFailure(String var1, Call var2, Exception var3) {
        if (this.analysisHelper != null) {
            this.analysisHelper.onFailure(var1, var2, var3);
        }

    }

    public void onResponse(String var1, Call var2, Response var3, String var4) {
        if (this.analysisHelper != null) {
            this.analysisHelper.onResponse(var1, var2, var3, var4);
        }

    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject jsonObject) throws Exception {
        return false;
    }

    @Override
    public void onLoginOutTime(String msg) {
        showShort(msg);
        startActivity(new Intent(this, LoginActivity.class));
        new UserService(MyApplication.context).setislogin("1");
    }

    @Override
    public void onHttpResult(String type, String code,String msg) {

    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {

    }
}
