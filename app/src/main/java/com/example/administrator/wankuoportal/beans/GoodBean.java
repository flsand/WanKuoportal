package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/5.
 *     desc    :
 *
 * </pre>
 */
public class GoodBean extends DBVO {

    private String id;
    private String province;
    private String city;
    private String area;
    private String address;
    private String name;
    private String phone;
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
