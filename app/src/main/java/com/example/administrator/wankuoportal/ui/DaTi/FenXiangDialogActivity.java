package com.example.administrator.wankuoportal.ui.DaTi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wankuoportal.R;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;


public class FenXiangDialogActivity extends Activity {

    private android.widget.TextView fenxiangjb;
    private android.widget.ImageView fenxiangclose;
    private android.widget.ImageView fenxiangnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_xiang_dialog);
        this.fenxiangnow = (ImageView) findViewById(R.id.fenxiang_now);
        this.fenxiangclose = (ImageView) findViewById(R.id.fenxiang_close);
        this.fenxiangjb = (TextView) findViewById(R.id.fenxiang_jb);
        fenxiangclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fenxiangnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UMImage thumb =  new UMImage(this, R.drawable.thumb);
//                image.setThumb(thumb);
                UMWeb web = new UMWeb(shareurl);
                web.setTitle("万阔");//标题
//                web.setThumb(thumb);  //缩略图
                web.setDescription("全球首发，闲赚赚钱,不耽误工作,不耽误学习,利用碎片时间轻松赚钱");//描述

                new ShareAction(FenXiangDialogActivity.this)
                        .withText("hello")
                        .withMedia(web)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
                        .setCallback(shareListener)
                        .open();
            }
        });
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {

            Toast.makeText(FenXiangDialogActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(FenXiangDialogActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(FenXiangDialogActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    /***
     * 关键是如何做到点击其他地方退出和如何关闭程序。
     * 1、点击其他地方退出，只要监听OnClickListener让所有的点击都退出就行了(除了xml中指定按钮事件之外)。
     * 2、退出主程序。只要在退出程序中将主程序自己设置成静态对象，在外部调用即可 public static MainWeixin
     * instance=null;
     * **/
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    String  shareurl ="";
    @Override

    protected void onResume() {
        super.onResume();
        String time = new Date().getTime() + "";
        String token = new UserService(getBaseContext()).gettoken();
        String accountId = new UserService(getBaseContext()).getaccountid();
        token = MD5Util.md5(time + token);
        String url = Apis.Base + Apis.getshare + "?token=" + accountId + "," + time + "," + token;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("accountId", accountId)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Gson gson = new Gson();
                        BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                        if (baseResult.getCode() == 0) {
                            shareurl = baseResult.getMsg();
                        } else if (baseResult.getCode() == 2) {
                            Intent intent = new Intent(FenXiangDialogActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(FenXiangDialogActivity.this, baseResult.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
