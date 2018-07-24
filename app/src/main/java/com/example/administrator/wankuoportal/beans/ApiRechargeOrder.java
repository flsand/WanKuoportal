package com.example.administrator.wankuoportal.beans;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class ApiRechargeOrder {
    private int accountId;

    private int payType;

    private int state;

    private BigDecimal totalAmount;

    private int type;//钻石会员充值填1普通充值填2

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
