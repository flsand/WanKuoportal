package com.example.administrator.wankuoportal.beans;

import java.util.List;

public class MyTeamListTJSYBean {

    /**
     * code : 0
     * data : [{"isMang":"1","headIcon":"01f3783713bc4920b15d0cf8a39be2e6","accountId":"232","nickName":"赚钱的","manger":"普通会员","name":"照片","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"240","nickName":"乔一","manger":"普通会员","name":"乔","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"253","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"254","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"255","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"271","nickName":"哎呦喂","manger":"普通会员","name":"刘志远","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"273","nickName":"万阔网络","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"278","nickName":"至尊寶","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"303","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"9567b622ac6348aa8397e5c22ffbd98a","accountId":"304","nickName":"Asphel","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"306","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"307","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"443","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"457","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"6b9ddd65853e48749a79a23f0317e704","accountId":"478","nickName":"人人赚钱的","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"522","nickName":"雁南飞","manger":"钻石店长","name":"","money":"0.00","money_dz":"1"},{"isMang":"1","headIcon":"","accountId":"544","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"545","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"550","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"554","nickName":"哦哦哦哦","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"","accountId":"558","nickName":"","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"1","headIcon":"67e6fedfa57245debc0c15cf45265bfd","accountId":"596","nickName":"半醉人间","manger":"普通会员","name":"","money":"0.00","money_dz":"0"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isMang : 1
         * headIcon : 01f3783713bc4920b15d0cf8a39be2e6
         * accountId : 232
         * nickName : 赚钱的
         * manger : 普通会员
         * name : 照片
         * money : 0.00
         * money_dz : 0
         */

        private String isMang;
        private String headIcon;
        private String accountId;
        private String nickName;
        private String manger;
        private String name;
        private String money;
        private String money_dz;

        public String getIsMang() {
            return isMang;
        }

        public void setIsMang(String isMang) {
            this.isMang = isMang;
        }

        public String getHeadIcon() {
            return headIcon;
        }

        public void setHeadIcon(String headIcon) {
            this.headIcon = headIcon;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getManger() {
            return manger;
        }

        public void setManger(String manger) {
            this.manger = manger;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoney_dz() {
            return money_dz;
        }

        public void setMoney_dz(String money_dz) {
            this.money_dz = money_dz;
        }
    }
}
