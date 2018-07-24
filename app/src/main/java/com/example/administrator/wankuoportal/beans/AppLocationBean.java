package com.example.administrator.wankuoportal.beans;


import android.text.TextUtils;

import com.flysand.mylibrary.sqlhelper.DBVO;


/**
 * Created by FlySand on 2017/9/29.
 */

public class AppLocationBean extends DBVO {

    private double longitude;// 经度
    private double latitude;// 纬度
    private String fomatAddr;
    //不显示省
    private String addr;
    private String street;
    private String city;
    private String district;


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        if (TextUtils.isEmpty(city))
            return "";
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public AppLocationBean() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFomatAddr() {
        return fomatAddr;
    }

    public void setFomatAddr(String fomatAddr) {
        this.fomatAddr = fomatAddr;
    }

    @Override
    public String toString() {
        return "AppLocationBean{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", fomatAddr='" + fomatAddr + '\'' +
                ", addr='" + addr + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
