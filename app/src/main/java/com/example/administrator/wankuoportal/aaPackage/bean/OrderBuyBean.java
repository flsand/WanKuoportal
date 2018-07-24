package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/15.
 *     desc    :
 * </pre>
 */
public class OrderBuyBean extends DBVO {
    /*
     * 1 出售订单 2 购买订单
	 */
    private int type;
    /*
     * 出售/购买 订单
     */
    private int dealId;
    /*
     * 创建时间
     */
    private long createTime;
    /*
     * 购买人用户编号
     */
    private String buyUserId;
    /*
     * 出售人用户编号
     */
    private String sellUserId;
    /*
     * 订单剩余数量
     */
    private double surplusCount;
    /*
     * 单价
     */
    private double unitPrice;
    /*
     * 购买数量
     */
    private double buyCount;
    /*
     * 出售人收益
     */
    private double lucre;
    /*
     * 平台收取手续费
     */
    private double poundage;
    /*
     * 买家昵称
     */
    private String buyUserName;
    /*
     * 卖家昵称
     */
    private String sellUserName;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(String buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId;
    }

    public double getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(double surplusCount) {
        this.surplusCount = surplusCount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(double buyCount) {
        this.buyCount = buyCount;
    }

    public double getLucre() {
        return lucre;
    }

    public void setLucre(double lucre) {
        this.lucre = lucre;
    }

    public double getPoundage() {
        return poundage;
    }

    public void setPoundage(double poundage) {
        this.poundage = poundage;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }

    public String getSellUserName() {
        return sellUserName;
    }

    public void setSellUserName(String sellUserName) {
        this.sellUserName = sellUserName;
    }
}
