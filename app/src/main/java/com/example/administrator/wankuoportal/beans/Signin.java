package com.example.administrator.wankuoportal.beans;

/**
 * Created by lv on 2017/9/25 0025   签到.
 */

public class Signin {


    /**
     * code : 0
     * data : {"accountId":222,"gold":10,"id":4,"signDay":"2017-09-25"}
     * msg : 签到成功
     */

    private int code;
    private DataBean data;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * accountId : 222
         * gold : 10
         * id : 4
         * signDay : 2017-09-25
         */

        private int accountId;
        private String gold;
        private int id;
        private String signDay;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSignDay() {
            return signDay;
        }

        public void setSignDay(String signDay) {
            this.signDay = signDay;
        }
    }
}
