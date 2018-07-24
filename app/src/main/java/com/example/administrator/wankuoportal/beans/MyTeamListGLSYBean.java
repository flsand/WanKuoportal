package com.example.administrator.wankuoportal.beans;

import java.util.List;

public class MyTeamListGLSYBean {

    /**
     * code : 0
     * data : [{"isMang":"2","headIcon":"","accountId":"241","nickName":"乔","manger":"普通会员","name":"乔","money":"0.00","money_dz":"0"},{"isMang":"2","headIcon":"45307d59e948488d8683a51d107f0077","accountId":"479","nickName":"赚钱就是好","manger":"普通会员","name":"","money":"0.00","money_dz":"0"},{"isMang":"2","headIcon":"","accountId":"531","nickName":"赤橙","manger":"白银店长","name":"","money":"0.00","money_dz":"1"}]
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
         * isMang : 2
         * headIcon :
         * accountId : 241
         * nickName : 乔
         * manger : 普通会员
         * name : 乔
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
