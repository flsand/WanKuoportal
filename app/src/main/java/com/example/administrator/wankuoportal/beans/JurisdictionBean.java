package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.beans
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/9.
 *     desc    :答题权限
 * </pre>
 */
public class JurisdictionBean extends DBVO {
    /**
     * zlcode : 0
     * ccode : yaJxGVdiCH
     * success : 1
     */
    private int zlcode;
    private String ccode;
    private int success;

    public int getZlcode() {
        return zlcode;
    }

    public void setZlcode(int zlcode) {
        this.zlcode = zlcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
