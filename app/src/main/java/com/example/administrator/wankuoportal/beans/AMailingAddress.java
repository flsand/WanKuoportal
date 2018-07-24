package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/10 地址beans.
 */

public class AMailingAddress {
    private static final long serialVersionUID = 1L;

    private int id;

    private int accountId;

    private String address;

    private String area;

    private int cancel;

    private String cardNumber;

    private String city;

    private String createTime;

    private int defaultFlay;

    private String name;

    private String phone;

    private String province;

    public AMailingAddress() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCancel() {
        return this.cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDefaultFlay() {
        return this.defaultFlay;
    }

    public void setDefaultFlay(int defaultFlay) {
        this.defaultFlay = defaultFlay;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
