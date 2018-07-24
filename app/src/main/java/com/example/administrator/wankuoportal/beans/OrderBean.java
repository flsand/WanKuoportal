package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/7.
 *     desc    :
 *
 * </pre>
 */
public class OrderBean extends DBVO {


    /**
     * accountId : 521
     * address : 恭候您莫名
     * city : 烟台市
     * consignee : 公公
     * count : 1
     * country : 中国
     * district : 福山区
     * id : 149
     * mobile : 46466
     * orderStatus : 0101
     * payId : 0
     * payStatus : 0
     * province : 山东省
     * saPrice : 198
     * shippingId : 0
     * stPrice : 198.0
     */

    private int accountId;
    private String address;
    private String city;
    private String consignee;
    private int count;
    private String country;
    private String district;
    private int id;
    private String mobile;
    private String orderStatus;
    private int payId;
    private int payStatus;
    private String province;
    private String payType;
    private String rechargeSeq;
    //商品名称
    private String name;
    private double shippingId;
    //销售价格
    private double saPrice;
    //进货价格
    private double stPrice;

    public String getRechargeSeq() {
        return rechargeSeq;
    }

    public void setRechargeSeq(String rechargeSeq) {
        this.rechargeSeq = rechargeSeq;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getSaPrice() {
        return saPrice;
    }

    public void setSaPrice(double saPrice) {
        this.saPrice = saPrice;
    }

    public double getShippingId() {
        return shippingId;
    }

    public void setShippingId(double shippingId) {
        this.shippingId = shippingId;
    }

    public double getStPrice() {
        return stPrice;
    }

    public void setStPrice(double stPrice) {
        this.stPrice = stPrice;
    }
}
