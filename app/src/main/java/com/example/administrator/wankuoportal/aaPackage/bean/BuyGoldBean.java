package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/23.
 *     desc    :
 * </pre>
 */
public class BuyGoldBean extends DBVO {


    /**
     * createTime : 1529995271000
     * createTimeStr : 2018-06-26 14:41
     * dealAllCount : 0
     * dealAllMoney : 0
     * head_icon : ""
     * id : 21
     * moneyCount : 10000
     * orderNo : 20180626144111993
     * status : 1
     * successCount : 0
     * totalMoney : 10000
     * unitPrice : 1
     * userId : 538
     * * userPhone : 15553539021
     */

    private long createTime;
    private String createTimeStr;
    private int dealAllCount;
    private String dealAllMoney;
    private String head_icon;
    private String id;
    private long moneyCount;
    private String orderNo;
    private String status;
    private long successCount;
    private String totalMoney;
    private String unitPrice;
    private String userId;
    private String userPhone;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public int getDealAllCount() {
        return dealAllCount;
    }

    public void setDealAllCount(int dealAllCount) {
        this.dealAllCount = dealAllCount;
    }

    public String getDealAllMoney() {
        return dealAllMoney;
    }

    public void setDealAllMoney(String dealAllMoney) {
        this.dealAllMoney = dealAllMoney;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(long moneyCount) {
        this.moneyCount = moneyCount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
