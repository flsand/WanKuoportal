package com.example.administrator.wankuoportal.base;


import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.flysand.mylibrary.listener.AsyncHttpAnalysisListener;

import org.json.JSONArray;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.base
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/3.
 *     desc    :
 *
 * </pre>
 */
public interface MyAsyncHttpAnalysisListener extends AsyncHttpAnalysisListener {
    //    boolean onHttpAnalysisIntercept(String type,String body)throws Exception;;
    void onHttpResult(String type, String code, String msg) throws Exception;

    //
    void onHttpSuccess(String type, JSONArray datas, PageInfoBean pageInfoBean) throws Exception;
}
