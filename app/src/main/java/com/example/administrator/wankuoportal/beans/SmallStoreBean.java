package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * Created by FlySand on 2018/3/18.
 */

public class SmallStoreBean extends DBVO {

    private String img;//图片
    private String name;//名称介绍
    private String phoneDiscount;//当前价格
    private String pirce;//往期价格


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneDiscount() {
        return phoneDiscount;
    }

    public void setPhoneDiscount(String phoneDiscount) {
        this.phoneDiscount = phoneDiscount;
    }

    public String getPirce() {
        return pirce;
    }

    public void setPirce(String pirce) {
        this.pirce = pirce;
    }
}
