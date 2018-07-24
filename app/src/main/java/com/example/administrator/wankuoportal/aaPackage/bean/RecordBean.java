package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/28.
 *     desc    :
 * </pre>
 */
public class RecordBean extends DBVO {
    /*
         * 卖出金币
         */
    double sellGold;
    /*
     * 收购金币
     */
    double buyGold;
    /*
     * 赚取金额
     */
    double inMoney;
    /*
     * 花费金额
     */
    double outMoney;

    public double getSellGold() {
        return sellGold;
    }

    public void setSellGold(double sellGold) {
        this.sellGold = sellGold;
    }

    public double getBuyGold() {
        return buyGold;
    }

    public void setBuyGold(double buyGold) {
        this.buyGold = buyGold;
    }

    public double getInMoney() {
        return inMoney;
    }

    public void setInMoney(double inMoney) {
        this.inMoney = inMoney;
    }

    public double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(double outMoney) {
        this.outMoney = outMoney;
    }
}
