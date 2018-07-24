package com.example.administrator.wankuoportal.app;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

import android.content.Context;
import android.content.Intent;

import com.baidu.location.BDLocation;
import com.example.administrator.wankuoportal.global.L;
import com.example.administrator.wankuoportal.global.UserService;
import com.example.administrator.wankuoportal.http.MyHttpInterceptor;
import com.example.administrator.wankuoportal.ui.HuiYuanWoDe.MyInfoActivity;
import com.example.administrator.wankuoportal.util.DataRepository;
import com.example.administrator.wankuoportal.util.LocationUtil;
import com.example.administrator.wankuoportal.util.Utils;
import com.flysand.mylibrary.crash.CrashHandler;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends com.example.administrator.wankuoportal.base.MyApplication {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initLocation();
        //初始化网络请求
        context = getApplicationContext();
        initokhttp();
        initpush();
        initShare();
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        initAndroidUtilCode();
        if (com.flysand.mylibrary.util.Utils.isApkDebugable(this, getPackageName())) {
            CrashHandler.getInstance(this).close();
        } else {
            CrashHandler.getInstance(this);
        }
        DataRepository.getInstance().init(this);

    }

    /**
     * 初始化AndroidUtilCode的方法
     */
    private void initAndroidUtilCode() {
        Utils.init(this);
    }

    /**
     * 初始化分享邀请的方法
     */
    private void initShare() {
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wxbf38ad6eed197a47", "a779b72585aabce1303e71354134ff17");
        PlatformConfig.setQQZone("1106304599", "4r5ovZ4n2ySbRBr5");
        PlatformConfig.setSinaWeibo("1587175514", "1c1585d39c0ae8c5da03219843e9550a", "http://www.wankuo5888.com");
    }

    /**
     * 初始化推送的方法
     */
    private void initpush() {
        com.flysand.mylibrary.util.Utils.print("initpush==");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册返回device token
                L.d("deviceTokenyes==", deviceToken);
                com.flysand.mylibrary.util.Utils.print("deviceTokenyes==" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                L.d("deviceTokenno==", s);
            }
        });
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {

                Intent intent = new Intent(MyApplication.this, MyInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    /**
     * 初始化网络请求
     */
    private void initokhttp() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new MyHttpInterceptor("TAG", false))
                .followRedirects(true)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    private void initLocation() {
        LocationUtil locationUtil = new LocationUtil(getApplicationContext());
        locationUtil.setLocationListener(new LocationUtil.LocationListener() {
            @Override
            public void onLocationChanged(BDLocation aMapLocation) {
                Utils.print("addr = " + aMapLocation.getAddress());
                new UserService(MyApplication.this).setAddress(aMapLocation.getCountry() + "/"
                        + aMapLocation.getProvince() + "/" + aMapLocation.getCity() + "/" + aMapLocation.getDistrict());
                setLocation(aMapLocation);
            }

            @Override
            public void onLocationFailure(String err) {
                new UserService(MyApplication.this).setAddress("中国/山东省/烟台市/福山区");
                Utils.print("onLocationFailure");
            }
        });
        locationUtil.startLocation();
    }

}
