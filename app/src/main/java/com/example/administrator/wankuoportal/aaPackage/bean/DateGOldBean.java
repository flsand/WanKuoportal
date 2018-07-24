package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/7/2.
 *     desc    :
 * </pre>
 */
public class DateGOldBean extends DBVO {


    /**
     * goldCount : 2400//
     * id : 0
     * scuessCount : 18000
     * seller_level : 0
     */

    private long goldCount;
    private long id;
    private long scuessCount;
    private long seller_level;

    public long getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(long goldCount) {
        this.goldCount = goldCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getScuessCount() {
        return scuessCount;
    }

    public void setScuessCount(long scuessCount) {
        this.scuessCount = scuessCount;
    }

    public long getSeller_level() {
        return seller_level;
    }

    public void setSeller_level(long seller_level) {
        this.seller_level = seller_level;
    }
}
