package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/22.
 *     desc    :
 * </pre>
 */
public class VersionBean extends DBVO {


    /**
     * appMongoid : 6bb20dcfde934434ae1bc1adf0380062
     * appName : 万阔安卓
     * appSize : 28
     * downLink : http://p8jml0ajn.bkt.clouddn.com/wankuoportal-V1.3.5.apk
     * infoId : 1
     * isUpdate : 3
     * upContent : 功能更加强大,期待您的加入!
     * versionCode : 35
     * versionName : 1.3.5
     */

    private String appMongoid;
    private String appName;
    private int appSize;
    private String downLink;
    private int infoId;
    private int isUpdate;
    private String upContent;
    private int versionCode;
    private String versionName;

    public String getAppMongoid() {
        return appMongoid;
    }

    public void setAppMongoid(String appMongoid) {
        this.appMongoid = appMongoid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppSize() {
        return appSize;
    }

    public void setAppSize(int appSize) {
        this.appSize = appSize;
    }

    public String getDownLink() {
        return downLink;
    }

    public void setDownLink(String downLink) {
        this.downLink = downLink;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUpContent() {
        return upContent;
    }

    public void setUpContent(String upContent) {
        this.upContent = upContent;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
