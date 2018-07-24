package com.example.administrator.wankuoportal.util;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.administrator.wankuoportal.beans.JurisdictionBean;
import com.example.administrator.wankuoportal.beans.Regist;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyStringCallback;
import com.example.administrator.wankuoportal.ui.SetUp.InvitationActivity;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.ui.denglu.Personal_SetupActivity;
import com.flysand.mylibrary.util.JSONUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.util.Date;

import okhttp3.Call;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/15.
 *     desc    :
 *
 * </pre>
 */
public class HttpDataManager {

    public interface onResultListenr<T> {
        void onStart();

        void onSuccess(T s);

        void onFail(T s);
    }

    public static void checkAnswerPermission(Context context, String answerType, onResultListenr listenr) {
        try {
            if (new UserService(context).getislogin().equals("0")) {
//                    if (new UserService(getActivity()).getAddress().isEmpty()) {
                String time = new Date().getTime() + "";
                String token = new UserService(context).gettoken();
                final String accountId = new UserService(context).getaccountid();
                token = MD5Util.md5(time + token);
                String url = Apis.Base + Apis.getuserinfo + "?token=" + accountId + "," + time + "," + token;
                listenr.onStart();
                OkHttpUtils
                        .post()
                        .url(url)
                        .addParams("accountId", accountId)
                        .build()
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                super.onError(call, e, id);
                                listenr.onFail(null);
                            }

                            @Override
                            public void onHttpResponse(String response, int id) throws Exception {
                                L.d(response);
                                Gson gson = new Gson();
                                Regist regist = gson.fromJson(response, Regist.class);
                                if (regist.getCode() == 2) {
                                    new UserService(context).setislogin("1");
//                                    Toast.makeText(context, "登录信息过期，请重新登录", Toast.LENGTH_SHORT).show();
                                    listenr.onFail(null);
                                } else {
                                    new UserService(context).setislogin("0");
//                                            if (regist.getData().getAaccountInfo().getProvince().isEmpty()) {
                                    String time = new Date().getTime() + "";
                                    String token = new UserService(context).gettoken();
                                    final String accountId = new UserService(context).getaccountid();
                                    token = MD5Util.md5(time + token);
                                    String url = Apis.Base + Apis.apiAnswer + "?token=" + accountId + "," + time + "," + token;
                                    Utils.print("answerType ="+answerType);
                                    OkHttpUtils
                                            .post()
                                            .url(url)
                                            .addParams("accountId", accountId)
                                            .addParams("type", answerType)
                                            .build()
                                            .execute(new MyStringCallback() {

                                                         @Override
                                                         public void onError(Call call, Exception e, int id) {
                                                             super.onError(call, e, id);
                                                             listenr.onFail(null);
                                                         }

                                                         @Override
                                                         public boolean onHttpAnalysisIntercept(String type, JSONObject jsonObject) throws Exception {

                                                             JurisdictionBean bean = JSONUtil.toJavaBean(JurisdictionBean.class, jsonObject.getString("data"));
                                                             if (bean.getZlcode() != 1) {
                                                                 if (bean.getSuccess() == 1) {
                                                                     listenr.onSuccess(bean);
                                                                     return true;
                                                                 } else {
                                                                     context.startActivity(new Intent(context, InvitationActivity.class));
                                                                     try {
                                                                         Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                                                                     } catch (Exception e) {
                                                                         Toast.makeText(context, "邀请好友注册，增加答题机会！", Toast.LENGTH_SHORT).show();
                                                                         e.printStackTrace();
                                                                     }
//                                                                                 showShort("本轮答题次数已用完，1小时以后再来吧！\n邀请好友注册，增加答题机会！");
                                                                 }
                                                             } else {
                                                                 context.startActivity(new Intent(context, Personal_SetupActivity.class));
                                                                 Toast.makeText(context, "请先完善个人信息", Toast.LENGTH_SHORT).show();
//                                                                     showShort("请先完善个人信息");
                                                             }
                                                             listenr.onFail(null);
                                                             return true;
                                                         }

                                                     }
                                            );
                                }
                            }
                        });
            } else {
                context.startActivity(new Intent(context, LoginActivity.class));
                listenr.onFail(null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
