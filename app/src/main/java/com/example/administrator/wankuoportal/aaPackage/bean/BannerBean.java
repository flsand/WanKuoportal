package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/22.
 *     desc    :
 * </pre>
 */
public class BannerBean extends DBVO {


    private List<BannerDetailBean> aAdvertiseRecordUpMid1;
    private List<BannerDetailBean> aAdvertiseRecordUpMid2;
    private List<BannerDetailBean> aAdvertiseRecordWx1;
    private List<BannerDetailBean> aAdvertiseRecordWxMid1;

    public List<BannerDetailBean> getaAdvertiseRecordUpMid1() {
        return aAdvertiseRecordUpMid1;
    }

    public void setaAdvertiseRecordUpMid1(List<BannerDetailBean> aAdvertiseRecordUpMid1) {
        this.aAdvertiseRecordUpMid1 = aAdvertiseRecordUpMid1;
    }

    public List<BannerDetailBean> getaAdvertiseRecordUpMid2() {
        return aAdvertiseRecordUpMid2;
    }

    public void setaAdvertiseRecordUpMid2(List<BannerDetailBean> aAdvertiseRecordUpMid2) {
        this.aAdvertiseRecordUpMid2 = aAdvertiseRecordUpMid2;
    }

    public List<BannerDetailBean> getaAdvertiseRecordWx1() {
        return aAdvertiseRecordWx1;
    }

    public void setaAdvertiseRecordWx1(List<BannerDetailBean> aAdvertiseRecordWx1) {
        this.aAdvertiseRecordWx1 = aAdvertiseRecordWx1;
    }

    public List<BannerDetailBean> getaAdvertiseRecordWxMid1() {
        return aAdvertiseRecordWxMid1;
    }

    public void setaAdvertiseRecordWxMid1(List<BannerDetailBean> aAdvertiseRecordWxMid1) {
        this.aAdvertiseRecordWxMid1 = aAdvertiseRecordWxMid1;
    }

    public static class BannerDetailBean extends DBVO {
        /**
         * accountId : 555
         * address : 20
         * advertiseId : 250
         * endTime : 2018-05-08 15:21:47
         * examineAccountId : 0
         * expense : 1200
         * id : 40
         * img : c43aade347e2413ba1df46fed7d943a4
         * startTime : 2018-04-08 15:21:47
         * state : 1
         * throwWay : 1
         * type : 1
         */

        private int accountId;
        private String address;
        private int advertiseId;
        private String endTime;
        private int examineAccountId;
        private int expense;
        private int id;
        private String img;
        private String startTime;
        private int state;
        private int throwWay;
        private int type;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAdvertiseId() {
            return advertiseId;
        }

        public void setAdvertiseId(int advertiseId) {
            this.advertiseId = advertiseId;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getExamineAccountId() {
            return examineAccountId;
        }

        public void setExamineAccountId(int examineAccountId) {
            this.examineAccountId = examineAccountId;
        }

        public int getExpense() {
            return expense;
        }

        public void setExpense(int expense) {
            this.expense = expense;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getThrowWay() {
            return throwWay;
        }

        public void setThrowWay(int throwWay) {
            this.throwWay = throwWay;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


}
