package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

import java.util.Date;

/**
 * @author tsf
 * @ClassName: DealBuy
 * @Description: TODO(我要买订单)
 * @date 2018-6-23 09:05:15
 */
public class TransationBuyBean extends DBVO {
    /*
     * 买方编号
     */
    int userId;
    /*
     * 买方手机号
     */
    String userPhone;
    /*
     * 订单号
     */
    String orderNo;
    /*
     * 创建时间
     */
    Date createTime;
    /*
     * 完成时间
     */
    Date scuessTime;
    /*
     * 强制结束时间
     */
    Date forceTime;
    /*
     * 购买单价
     */
    double unitPrice;
    /*
     * 购买金币数量
     */
    int moneyCount;
    /*
     * 交易完成数量
     */
    int successCount;
    /*
     * 剩余数量
     */
    int surplusCount;
    /*
     * 状态 1 正常 2 交易完成 3 强制结束
     */
    int status;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getScuessTime() {
        return scuessTime;
    }

    public void setScuessTime(Date scuessTime) {
        this.scuessTime = scuessTime;
    }

    public Date getForceTime() {
        return forceTime;
    }

    public void setForceTime(Date forceTime) {
        this.forceTime = forceTime;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(int moneyCount) {
        this.moneyCount = moneyCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(int surplusCount) {
        this.surplusCount = surplusCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
