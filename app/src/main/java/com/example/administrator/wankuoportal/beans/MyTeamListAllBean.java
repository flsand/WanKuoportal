package com.example.administrator.wankuoportal.beans;

import java.util.List;

public class MyTeamListAllBean {

    /**
     * code : 0
     * data : [{"headIcon":"01f3783713bc4920b15d0cf8a39be2e6","flag":"1","accountId":"232","nickName":"赚钱的","manger":"普通会员","name":"照片","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"240","nickName":"乔一","manger":"普通会员","name":"乔","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"253","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"254","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"255","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"271","nickName":"哎呦喂","manger":"普通会员","name":"刘志远","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"273","nickName":"万阔网络","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"278","nickName":"至尊寶","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"303","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"9567b622ac6348aa8397e5c22ffbd98a","flag":"1","accountId":"304","nickName":"Asphel","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"306","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"307","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"443","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"457","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"6b9ddd65853e48749a79a23f0317e704","flag":"1","accountId":"478","nickName":"人人赚钱的","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"522","nickName":"雁南飞","manger":"钻石店长","name":"","money":"0.00","isManger":"1"},{"headIcon":"","flag":"1","accountId":"544","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"545","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"550","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"554","nickName":"哦哦哦哦","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"1","accountId":"558","nickName":"","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"67e6fedfa57245debc0c15cf45265bfd","flag":"1","accountId":"596","nickName":"半醉人间","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"2","accountId":"241","nickName":"乔","manger":"普通会员","name":"乔","money":"0.00","isManger":"0"},{"headIcon":"45307d59e948488d8683a51d107f0077","flag":"2","accountId":"479","nickName":"赚钱就是好","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"2","accountId":"531","nickName":"赤橙","manger":"白银店长","name":"","money":"0.00","isManger":"1"}]
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
         * headIcon : 01f3783713bc4920b15d0cf8a39be2e6
         * flag : 1
         * accountId : 232
         * nickName : 赚钱的
         * manger : 普通会员
         * name : 照片
         * money : 0.00
         * isManger : 0
         */

        private String headIcon;
        private String flag;
        private String accountId;
        private String nickName;
        private String manger;
        private String name;
        private String money;
        private String isManger;

        public String getHeadIcon() {
            return headIcon;
        }

        public void setHeadIcon(String headIcon) {
            this.headIcon = headIcon;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
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

        public String getIsManger() {
            return isManger;
        }

        public void setIsManger(String isManger) {
            this.isManger = isManger;
        }
    }
}
