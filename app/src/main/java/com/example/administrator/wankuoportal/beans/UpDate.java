package com.example.administrator.wankuoportal.beans;

/**
 * Created by ytzht on 2017/07/28 下午9:22
 */

public class UpDate {


    /**
     * code : 0
     * data : {"appMongoid":"6bb20dcfde934434ae1bc1adf0380062","appName":"万阔安卓","appSize":15,"downLink":"http://o2o.zhongyishijia.com/api/file/downloadapp/yijiankangapk","infoId":1,"upContent":"1.增加自动登录功能2.优化性能与流畅性","versionCode":10,"versionName":"1.0.1"}
     */

    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appMongoid : 6bb20dcfde934434ae1bc1adf0380062
         * appName : 万阔安卓
         * appSize : 15
         * downLink : http://o2o.zhongyishijia.com/api/file/downloadapp/yijiankangapk
         * infoId : 1
         * upContent : 1.增加自动登录功能2.优化性能与流畅性
         * versionCode : 10
         * versionName : 1.0.1
         */

        private String appMongoid;
        private String appName;
        private int appSize;
        private String downLink;
        private int infoId;
        private String upContent;
        private int versionCode;
        //1是强制更新
        private int isUpdate;
        private String versionName;

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }


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
}
