package com.example.administrator.wankuoportal.base;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.baidu.location.BDLocation;
import com.example.administrator.wankuoportal.aaPackage.bean.UserBean;
import com.example.administrator.wankuoportal.beans.AppLocationBean;
import com.example.administrator.wankuoportal.ui.denglu.LoginActivity;
import com.flysand.mylibrary.base.BaseApplication;
import com.flysand.mylibrary.util.MyToast;
import com.flysand.mylibrary.util.SharedPreferenceUtil;
import com.flysand.mylibrary.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/22.
 *     desc    :
 * </pre>
 */
public class MyApplication extends BaseApplication {

    private static MyApplication instance;
    AppLocationBean locationBean;

    Handler handler = new Handler();

    private UserBean userBean;


    public static MyApplication getInstance() {
        return instance;
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public AppLocationBean getLocation() {
        if (locationBean == null) {
            try {
                List<AppLocationBean> beanLis = SharedPreferenceUtil.getInstance(this).readList(AppLocationBean.class, "locationBean");
                if (beanLis != null && beanLis.size() > 0) {
                    locationBean = beanLis.get(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (locationBean == null) {
            locationBean = new AppLocationBean();
            locationBean.setFomatAddr("");
            locationBean.setLatitude(0);
            locationBean.setLongitude(0);
            locationBean.setCity("定位失败");
            locationBean.setDistrict("");
            locationBean.setStreet("");
            locationBean.setAddr("定位失败");
        }

        Utils.print("locationBean = " + locationBean);
        return locationBean;
    }

    public void setLocation(BDLocation aMapLocation) {
        locationBean = new AppLocationBean();
        locationBean.setFomatAddr(aMapLocation.getAddress().address);
        locationBean.setLatitude(aMapLocation.getLatitude());
        locationBean.setLongitude(aMapLocation.getLongitude());
        locationBean.setCity(aMapLocation.getCity());
        locationBean.setDistrict(aMapLocation.getDistrict());
        locationBean.setStreet(aMapLocation.getStreet());
        locationBean.setAddr(aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet() + aMapLocation.getStreetNumber() + "附近");

        Utils.print("addr = " + locationBean.getAddr());
        List<AppLocationBean> locationBeans = new ArrayList<>();
        locationBeans.add(locationBean);
        SharedPreferenceUtil.getInstance(this).saveParam("locationBean", locationBeans);
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
        Utils.saveSerializableBean(this, userBean);
        Utils.print("userBean =" + userBean);
    }

    public UserBean getUser() {
        if (userBean == null) {
            try {
                userBean = Utils.getSaveSerializableBean(this, UserBean.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (userBean == null) {
            userBean = new UserBean();
            userBean.setAAccountAuthorize(new UserBean.AAccountAuthorizeBean());
            userBean.setAAccountInfo(new UserBean.AAccountInfoBean());
            Utils.print("new SignInBean();");
        }
        Utils.print("userBean = " + userBean);
        return userBean;
    }

    public void jumpLogin() {
        Utils.print("jumpLogin()");
        handler.post(new Runnable() {
            @Override
            public void run() {
                Utils.print("jumpLogin()   - -MyToast");
                new MyToast(com.example.administrator.wankuoportal.app.MyApplication.getInstance()).setText("您的账号在另一台设备上登录，您被挤下线，若不是您本人操作，请立即修改密码！");
                Intent intent = new Intent(com.example.administrator.wankuoportal.app.MyApplication.context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                com.example.administrator.wankuoportal.app.MyApplication.getInstance().startActivity(intent);

            }
        });
    }
}
