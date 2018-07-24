package com.example.administrator.wankuoportal.util;

import android.content.Context;
import android.os.CountDownTimer;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;


/**
 * Created by SunFangtao on 2016/8/9.
 */
public class LocationUtil implements BDLocationListener {
    private CountDownTimer cdt = null;
    private int OUT_TIME = 20 * 1000;
    private LocationListener locationListener;
    //声明mLocationOption对象
    public LocationClient mLocationClient = null;
    private Context mContext;

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation != null) {
            //定位成功回调信息，设置相关消息
            com.flysand.mylibrary.util.Utils.print("getCity =" + bdLocation.getCity());//获取精度信息
            if (locationListener != null) {
                locationListener.onLocationChanged(bdLocation);
            }
        } else {
            if (locationListener != null) {
                locationListener.onLocationFailure("location Error");
            }
        }
        stopLocation();
    }

    public interface LocationListener {
        void onLocationChanged(BDLocation aMapLocation);

        void onLocationFailure(String err);
    }

    private LocationUtil() {
    }

    public LocationUtil(Context context) {
        com.flysand.mylibrary.util.Utils.print("LocationUtil");
        mContext = context;
        //初始化定位参数
        mLocationClient = new LocationClient(context);
        //设置定位监听
        mLocationClient.registerLocationListener(this);
        // 配置定位参数
        initLocation();
    }

    public void setOutTime(int time) {
        this.OUT_TIME = time;
    }

    public void setLocationListener(LocationListener locationListener) {
        this.locationListener = locationListener;
    }

    public void startLocation() {
        //设置超时时间
        cdt = new CountDownTimer(OUT_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                com.flysand.mylibrary.util.Utils.print(" millisUntilFinished = " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                if (locationListener != null && mContext != null) {
                    locationListener.onLocationFailure("请求定位超时");
                    stopLocation();
                    com.flysand.mylibrary.util.Utils.print("onLocationFailure(\"定位超时\")");
                    locationListener = null;
                }
            }
        };
        cdt.start();
        //启动定位
        mLocationClient.start();
    }

    public void stopLocation() {
        locationListener = null;
        //停止定位
        mLocationClient.stop();
        cdt.cancel();
        com.flysand.mylibrary.util.Utils.print("停止定位");
//        mlocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }


    private void initLocation() {
        com.flysand.mylibrary.util.Utils.print("---------");

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("gcj02");
        //可选，默认gcj02，设置返回的定位结果坐标系
//        int span = 1000;
        option.setScanSpan(0);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//    option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
    }
}
