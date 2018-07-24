package com.example.administrator.wankuoportal.beans;

/**
 * Created by zht on 2017/04/15 14:33
 */

public class BaseResultaddData {

    /**
     * code : 0
     * msg : 正常
     */

    private int code;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
