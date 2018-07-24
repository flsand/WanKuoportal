package com.example.administrator.wankuoportal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.wankuoportal.R;
import com.example.administrator.wankuoportal.app.MyApplication;
import com.example.administrator.wankuoportal.beans.BaseResult;
import com.example.administrator.wankuoportal.beans.Information;
import com.example.administrator.wankuoportal.global.Apis;
import com.example.administrator.wankuoportal.global.BaseActivity;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.example.administrator.wankuoportal.util.MD5Util;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.zhy.http.okhttp.OkHttpUtils;
import com.example.administrator.wankuoportal.http.MyStringCallback;

import java.util.Date;

import okhttp3.Call;

public class ZiXunDetialActivity extends BaseActivity {

    private android.widget.ImageView back;
    private android.widget.TextView title;
    private TextView share;
    private TextView biaoti;
    private TextView riqi;
    private ImageView tupian;
    private TextView neirong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_xun_detial);

        this.neirong = (TextView) findViewById(R.id.neirong);
        this.tupian = (ImageView) findViewById(R.id.tupian);
        this.riqi = (TextView) findViewById(R.id.riqi);
        this.biaoti = (TextView) findViewById(R.id.biaoti);
        this.share = (TextView) findViewById(R.id.share);

        this.title = (TextView) findViewById(R.id.title);
        this.back = (ImageView) findViewById(R.id.back);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String time = new Date().getTime() + "";
                String token = new UserService(MyApplication.context).gettoken();
                String accountId = new UserService(MyApplication.context).getaccountid();
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
                                BaseResult baseResult = gson.fromJson(response, BaseResult.class);
                                if (baseResult.getCode() == 0) {
                                    String shareurl = baseResult.getMsg();
                                    UMWeb web = new UMWeb(shareurl);
                                    web.setTitle("万阔");//标题
//                web.setThumb(thumb);  //缩略图
                                    web.setDescription("让生活更简单！");//描述
                                    new ShareAction(ZiXunDetialActivity.this)
                                            .withText("hello")
                                            .withMedia(web)
                                            .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
                                            .setCallback(shareListener)
                                            .open();
                                } else if (baseResult.getCode() == 2) {
                                    Intent intent = new Intent(ZiXunDetialActivity.this, LoginActivity.class);
                                    startActivity(intent);

                                } else {
                                    showShort(baseResult.getMsg());
                                }
                            }
                        });

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String url = Apis.Base + Apis.information + "/" + getIntent().getStringExtra("id");
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onHttpResponse(String response, int id) throws Exception {
                        L.d(response);
                        Information s = gson.fromJson(response, Information.class);
                        if (s.getCode() == 0) {
                            biaoti.setText(s.getData().getTitle());
                            neirong.setText(s.getData().getContent());
                            riqi.setText("来源:万阔 " + s.getData().getCreateTime());
                            Glide.with(ZiXunDetialActivity.this).load(Apis.Baseima + s.getData().getPictureId()).into(tupian);
                        } else {
                            showShort(s.getMsg());
                            finish();
                        }
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

            showShort("成功了");


            String url = Apis.Base + Apis.collectionshare;
            OkHttpUtils
                    .get()
                    .url(url)
                    .addParams("id", getIntent().getStringExtra("id"))
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onHttpResponse(String response, int id) throws Exception {
                            L.d(response);
                        }
                    });


        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };
}
