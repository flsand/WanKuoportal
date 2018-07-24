package com.example.administrator.wankuoportal.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.other.GlideImageLoader;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.PagingHelper;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.base.BaseAppCompatAcitivity;
import com.flysand.mylibrary.http.HttpAnalysisHelper;
import com.flysand.mylibrary.http.RequestParams;
import com.flysand.mylibrary.util.MyHandler;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyBaseActivity extends BaseAppCompatAcitivity implements MyAsyncHttpAnalysisListener {

    ImageView backIv;
    TextView titleTv;
    RelativeLayout contentLayout;
    ProgressDialog dialogproa;
    protected PagingHelper pagingHelper = new PagingHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (showTitle()) {
            super.setContentView(R.layout.activity_my_base);
            backIv = (ImageView) findViewById(R.id.base_back_iv);
            titleTv = (TextView) findViewById(R.id.base_title_tv);
            contentLayout = (RelativeLayout) findViewById(R.id.base_content_layout);
            titleTv.setText(getTitle());
            backIv.setOnClickListener(v -> {
                finish();
                onKeyBackClick();
            });
        }
        initReceiver(this.getClass().getName());
    }

    /**
     * 初始化View的方法
     */
    protected void afterViews() {

    }

    protected void onKeyBackClick() {

    }

    public View findViewById(int id) {
        return super.findViewById(id);
    }

    /**
     * 添加自定义分割线
     */
    protected void addItemDecoration(RecyclerView recyclerView) {
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);
    }

    /**
     * 初始化Banner
     *
     * @param banner
     * @param <T>
     */
    protected <T> void initBanner(Banner banner) {
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(list);
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    protected HttpAnalysisHelper getCustumAnalysisHelper() {
        return new DefultHttpAnalysisHelper(this);
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
        new MyHandler(1500) {
            @Override
            public void run() {
                if (dialogproa != null)
                    dialogproa.dismiss();
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialogproa != null) {
            dialogproa.dismiss();
        }
        this.unBoundReceiver();
    }

    @Override
    public void setContentView(int layoutResID) {
        if (showTitle()) {
            View view = LayoutInflater.from(this).inflate(layoutResID, contentLayout, false);
            contentLayout.addView(view);
        } else {
            super.setContentView(layoutResID);
        }
    }

    protected boolean showTitle() {
        return true;
    }

    /**
     * 设置标题文字，默认读取activity Lable
     *
     * @param title
     */
    protected void setTitleText(String title) {
        titleTv.setText(title);
    }

    @Override
    protected void afterRestoreInstanceState(Bundle bundle) {

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
    protected void httpGetNoDialog(String type, RequestParams params, String url) {
        BaseHttp.httpGet(this, type, params, url);
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

    public void onHttpSuccess(String type, JSONObject jsonObject) {
        dismissHttpDialog();
    }

    public void onHttpSuccess(String type, JSONArray jsonArray, int page, int size, int count) {
        dismissHttpDialog();
    }

    public void onHttpFailure(String type, String msg) {
        dismissErrDialog(msg);
    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject body) throws Exception {
        return false;
    }

    @Override
    public void onLoginOutTime(String msg) {
        toast.setText(msg);
        startActivity(new Intent(this, LoginActivity.class));
        new UserService(MyApplication.context).setislogin("1");
    }

    @Override
    public void onHttpResult(String type, String code, String msg) {
        dismissHttpDialog();
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) throws Exception {
        dismissHttpDialog();
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

    MyBroadcastReceiver mReceiver;

    private void initReceiver(String action) {
        if (this.mReceiver != null) {
            this.unBoundReceiver();
        }
        Utils.print("Action = " + action);
        this.mReceiver = new MyBroadcastReceiver();
        IntentFilter var2 = new IntentFilter();
        var2.addAction(action);
//        this.registerReceiver(mReceiver, var2, this.getPackageName(), null);
        this.registerReceiver(mReceiver, var2);
    }

    private void unBoundReceiver() {
        if (this.mReceiver != null) {
            this.unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }

    }

    public void forReceiverResult(Intent var1) {
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        public void onReceive(Context var1, Intent var2) {
            com.flysand.mylibrary.util.Utils.printError("onReceive");
            MyBaseActivity.this.forReceiverResult(var2);
        }
    }


}
