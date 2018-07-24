package com.example.administrator.wankuoportal.aaPackage.bean;

public class WechatPayBean {
    /**
     * appId : wxbf38ad6eed197a47
     * nonceStr : 882730
     * packageValue : Sign=WXPay
     * partnerId : 1488655172
     * payNumber : W20171016142233007
     * prepayId : wx20171016142233e440c9f35e0946971200
     * sign : 8957E6AF5C1BB30F2BC3FFA204C345A5
     * timestamp : 1508134953
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