package com.example.administrator.wankuoportal.base;


import com.example.administrator.wankuoportal.beans.PageInfoBean;
import com.flysand.mylibrary.http.HttpAnalysisHelper;
import com.flysand.mylibrary.listener.AsyncHttpAnalysisListener;
import com.flysand.mylibrary.util.JSONUtil;
import com.flysand.mylibrary.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by FlySand on 2018/4/11.
 */

public class DefultHttpAnalysisHelper extends HttpAnalysisHelper {

    public static String ERR_MSG = "系统维护中，请稍后";
    public static String OUT_TIME_MSG = "不好意思,网络出问题了";
    public static String NO_ADDRESS_MSG = "不好意思,断网了呢";
    public static String FAILED_CONNECT = "连接失败";

    protected MyAsyncHttpAnalysisListener listener;

    public <T extends AsyncHttpAnalysisListener> DefultHttpAnalysisHelper(T var1) {
        super(null);
        this.listener = (MyAsyncHttpAnalysisListener) var1;
    }

    @Override
    public void onFailure(String type, Call call, Exception e) {
        Utils.print("   onFailure type  = " + type);
        if (listener != null) {
            try {
                Utils.print("onFailure type = " + type);
                Utils.print("   e = " + e.getMessage());
                if (e.getMessage().contains("No address associated with hostname")) {
                    listener.onHttpFailure(type, NO_ADDRESS_MSG);
                } else if (e.getMessage().contains("failed to connect to ")) {
                    listener.onHttpFailure(type, FAILED_CONNECT);
                } else {
                    listener.onHttpFailure(type, ERR_MSG);
                }
            } catch (Exception err) {
                err.printStackTrace();
                try {
                    listener.onHttpFailure(type, "请求失败");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onResponse(String type, Call call, Response response, String body) {
        try {
            Utils.print("code =" + response.code());
        } catch (Exception e) {
        }
        if (listener != null) {
            Utils.print("type = " + type + "   body = " + body);
            JSONObject jsonObject = null;
            String msg = ERR_MSG;
            try {
                jsonObject = new JSONObject(body);
                if (jsonObject.has("code")) {
                    String code = jsonObject.getString("code");
                    if ("2".equals(code)) {
                        if (jsonObject.has("msg")) {
                            msg = jsonObject.getString("msg");
                            listener.onLoginOutTime(msg);
                        } else {
                            listener.onLoginOutTime("登录信息过期，请重新登录");
                        }
                    } else if ("0".equals(code)) {
                        try {
                            if (listener.onHttpAnalysisIntercept(type, jsonObject)) {
                                return;
                            }
                            try {
                                listener.onHttpSuccess(type, jsonObject.getJSONObject("data"));
                                Utils.print("onHttpSuccess(" + type + ", jsonObject)");
                                return;
                            } catch (JSONException e) {
                                try {
                                    PageInfoBean pageInfoBean = JSONUtil.toJavaBean(PageInfoBean.class, jsonObject.getJSONObject("pageInfo"));
//                                    pageInfoBean.setFirst(jsonObject.get);
                                    listener.onHttpSuccess(type, jsonObject.getJSONArray("datas"), pageInfoBean);
                                    Utils.print("onHttpSuccess(" + type + ", JSONArray,pageInfoBean)");
//                                    listener.onHttpSuccess(type, jsonObject.getJSONArray("datas"), jsonObject.getInt("page"), jsonObject.getInt("size"), jsonObject.getInt("count"));
//                                    Utils.print("onHttpSuccess(" + type + ", JSONArray,page,size,count)");
                                } catch (Exception e1) {
//                                    e1.printStackTrace();
                                    Utils.print("Exception =" + e1.getMessage());
                                    try {
                                        listener.onHttpSuccess(type, jsonObject.getJSONArray("datas"), -1, -1, -1);
                                        Utils.print("onHttpSuccess(" + type + ", JSONArray)");
                                    } catch (Exception e2) {
//                                        e2.printStackTrace();
                                        listener.onHttpResult(type, jsonObject.getString("code"), jsonObject.getString("msg"));
                                        Utils.print("onHttpSuccess(" + type + ", code : " + jsonObject.getString("code") + "  , msg : " + jsonObject.getString("msg") + ")");
                                    }
                                }
                                return;
                            }
                        } catch (Exception E) {
                            E.printStackTrace();
                            if (jsonObject.has("msg")) {
                                msg = jsonObject.getString("msg");
                            } else {
                                msg = "no data";
                            }
                        }

                    } else {
                        if (listener.onHttpAnalysisIntercept(type, jsonObject)) {
                            return;
                        }
                        if (jsonObject.has("msg")) {
                            msg = jsonObject.getString("msg");
                        } else {
                            msg = "code :" + code;
                        }
                    }
                } else {
                    msg = "no code";
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (jsonObject == null) {
                    msg = ERR_MSG;
                } else {
                    if (jsonObject.has("msg")) {
                        try {
                            msg = jsonObject.getString("msg");
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
            try {
                listener.onHttpResult(type, jsonObject.getString("code"), jsonObject.getString("msg"));
                Utils.print("onHttpResult(" + type + ", code : " + jsonObject.getString("code") + "  , msg : " + jsonObject.getString("msg") + ")");

                listener.onHttpFailure(type, msg);
                Utils.print("onHttpFailure(" + type + "   " + msg + ")");
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    listener.onHttpFailure(type, msg);
                } catch (Exception e1) {
                    Utils.print("onHttpFailure(" + type + "   " + msg + ")");
                    e1.printStackTrace();
                }
            }
        }
    }

}
