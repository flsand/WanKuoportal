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
public class SellGoldBean extends DBVO {
    /**
     * "createTime": 1530171295000,
     * "createTimeStr": "2018-06-28 15:34",
     * "dealAllCount": 1,
     * "dealAllMoney": 1000,
     * "forceTime": 1530344095000,
     * "head_icon": "72a80535f19540108e57819e9bd8d3d7",
     * "id": 22,
     * "moneyCount": 100,
     * "orderNo": "20180628153455030",
     * "status": 1,
     * "successCount": 0,
     * "totalMoney": 15,
     * "unitPrice": 0.15,
     * "userId": 538,
     * "userPhone": "15553539021"
     */
    private String id;

    /*
        * 买方编号
        */
    private String userId;
    /*
     * 买方手机号
     */
    private String userPhone;
    /*
     * 订单号
     */
    private String orderNo;
    /*
     * 创建时间
     */
    private long createTime;
    /*
     * 创建时间
     */
    private String createTimeStr;
    /*
     * 完成时间
     */
    private long scuessTime;
    /*
     * 强制结束时间
     */
    private long forceTime;
    /*
     * 购买单价
     */
    private String unitPrice;
    /*
     * 购买金币数量
     * 出售数量
     */
    private String moneyCount;
    /*
     * 交易完成数量
     */
    private String successCount;
    /*
     * 剩余数量
     */
    private String surplusCount;
    /*
     * 交易成功总笔数
	 */
    private int dealAllCount;
    /*
     * 交易成功总金币数量
     */
    private int dealAllMoney;
    /*
     * 用户头像
     */
    private String head_icon;
    /*
     * 状态 1 正常 2 交易完成 3 强制结束
     */
    private String status;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDealAllCount() {
        return dealAllCount;
    }

    public void setDealAllCount(int dealAllCount) {
        this.dealAllCount = dealAllCount;
    }

    public int getDealAllMoney() {
        return dealAllMoney;
    }

    public void setDealAllMoney(int dealAllMoney) {
        this.dealAllMoney = dealAllMoney;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getScuessTime() {
        return scuessTime;
    }

    public void setScuessTime(long scuessTime) {
        this.scuessTime = scuessTime;
    }

    public long getForceTime() {
        return forceTime;
    }

    public void setForceTime(long forceTime) {
        this.forceTime = forceTime;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(String moneyCount) {
        this.moneyCount = moneyCount;
    }

    public String getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(String successCount) {
        this.successCount = successCount;
    }

    public String getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(String surplusCount) {
        this.surplusCount = surplusCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
