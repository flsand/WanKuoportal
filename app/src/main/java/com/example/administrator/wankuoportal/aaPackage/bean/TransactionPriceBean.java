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
public class TransactionPriceBean extends DBVO {

    /**
     * id : 1
     * newPrice : 0.01
     * upTime : 1529732457000
     *
     * 	"beginPrice": 1.15,
     "endPrice": 1.99,
     "id": 1,
     "upTime": 1530163569000
     */

    private int id;
    private long upTime;
    /*
     * 最新单价
	 */
    private double beginPrice;
    /*
     * 最新单价
     */
    private double endPrice;
    /*
     * 更新时间
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(double beginPrice) {
        this.beginPrice = beginPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }
}
