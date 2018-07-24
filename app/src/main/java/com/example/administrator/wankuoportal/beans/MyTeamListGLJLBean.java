package com.example.administrator.wankuoportal.beans;

import java.util.List;

public class MyTeamListGLJLBean {

    /**
     * code : 0
     * data : [{"headIcon":"","flag":"2","accountId":"241","nickName":"乔","manger":"普通会员","name":"乔","money":"0.00","isManger":"0"},{"headIcon":"45307d59e948488d8683a51d107f0077","flag":"2","accountId":"479","nickName":"赚钱就是好","manger":"普通会员","name":"","money":"0.00","isManger":"0"},{"headIcon":"","flag":"2","accountId":"531","nickName":"赤橙","manger":"白银店长","name":"","money":"0.00","isManger":"1"}]
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
         * headIcon :
         * flag : 2
         * accountId : 241
         * nickName : 乔
         * manger : 普通会员
         * name : 乔
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
