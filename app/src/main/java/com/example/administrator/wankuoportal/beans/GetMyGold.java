package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class GetMyGold {


    /**
     * code : 0
     * data : {"addGold":0,"nolimitGold":0,"prizeGold":0,"recommenderNum":0,"signInGold":10}
     * msg : 查询成功
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
         * addGold : 0
         * nolimitGold : 0
         * prizeGold : 0
         * recommenderNum : 0
         * signInGold : 10
         *
         */

        private String addGold;
        private String nolimitGold;
        private String prizeGold;
        private String recommenderNum;
        private String signInGold;
        //钻石
        private String diamonds;

        public String getDiamonds() {
            return diamonds;
        }

        public void setDiamonds(String diamonds) {
            this.diamonds = diamonds;
        }


        public String getAddGold() {
            return addGold;
        }

        public void setAddGold(String addGold) {
            this.addGold = addGold;
        }

        public String getNolimitGold() {
            return nolimitGold;
        }

        public void setNolimitGold(String nolimitGold) {
            this.nolimitGold = nolimitGold;
        }

        public String getPrizeGold() {
            return prizeGold;
        }

        public void setPrizeGold(String prizeGold) {
            this.prizeGold = prizeGold;
        }

        public String getRecommenderNum() {
            return recommenderNum;
        }

        public void setRecommenderNum(String recommenderNum) {
            this.recommenderNum = recommenderNum;
        }

        public String getSignInGold() {
            return signInGold;
        }

        public void setSignInGold(String signInGold) {
            this.signInGold = signInGold;
        }
    }
}
