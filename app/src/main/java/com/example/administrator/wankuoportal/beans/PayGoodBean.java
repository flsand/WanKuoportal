package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/14.
 *     desc    :
 *
 * </pre>
 */
public class PayGoodBean extends DBVO {
    String goods_id;
    int count;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
