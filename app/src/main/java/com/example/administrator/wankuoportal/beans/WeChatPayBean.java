package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.beans
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/3.
 *     desc    :
 *
 * </pre>
 */
public class WeChatPayBean extends DBVO {


    /**
     * appId : wx5f63fa96acbaa3dc
     * nonceStr : 809220
     * packageValue : Sign=WXPay
     * partnerId : 1488655172
     * payNumber : W20180503155900035
     * prepayId : wx031558589093625398e0a6a21491684312
     * sign : 4D85BEDCD74BA0C904A9115149253E1A
     * timestamp : 1525334341612
     */

    private String appId;
    private String nonceStr;
    private String packageValue;
    private String partnerId;
    private String payNumber;
    private String prepayId;
    private String sign;
    private String timestamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
