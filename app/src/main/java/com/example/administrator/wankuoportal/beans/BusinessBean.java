package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * Created by FlySand on 2018/3/31.
 */

public class BusinessBean extends DBVO {
    String title;
    String body;
    int img;
    String num;
    String unit;
    String nothing;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNothing() {
        return nothing;
    }

    public void setNothing(String nothing) {
        this.nothing = nothing;
    }
}
