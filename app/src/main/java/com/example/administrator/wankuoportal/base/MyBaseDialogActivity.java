package com.example.administrator.wankuoportal.base;

import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.flysand.mylibrary.base.BaseActivity;
import com.flysand.mylibrary.http.HttpAnalysisHelper;
import com.flysand.mylibrary.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/29.
 *     desc    :
 * </pre>
 */
public class MyBaseDialogActivity extends BaseActivity implements MyAsyncHttpAnalysisListener {
    /**
     * http请 post请求
     *
     * @param type
     * @param params
     * @param url
     */
    protected void httpPost(String type, RequestParams params, String url) {
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
        BaseHttp.httpGet(this, type, params, url);
    }

    @Override
    protected HttpAnalysisHelper getCustumAnalysisHelper() {
        return new DefultHttpAnalysisHelper(this);
    }

    @Override
    public void onHttpResult(String type, String code, String msg) {
    }

    @Override
    public void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) {

    }

    @Override
    public void onHttpSuccess(String type, JSONObject jsonObject) throws Exception {

    }

    @Override
    public void onHttpSuccess(String type, JSONArray jsonArray, int i, int i1, int i2) throws Exception {

    }

    @Override
    public void onHttpFailure(String type, String s1) throws Exception {

    }

    @Override
    public boolean onHttpAnalysisIntercept(String type, JSONObject jsonObject) throws Exception {
        return false;
    }

    @Override
    public void onLoginOutTime(String type) {

    }
}
