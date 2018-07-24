package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class Getmyinvitation {


    /**
     * code : 0
     * data : {"consumptionAmountTotal":0,"invitationGoldTotal":20,"invitationNum":2,"list":[{"consumptionAmount":0,"invitationDay":"2017-09-22","invitationGold":10,"phone":"15505350000","prizeGold":53},{"consumptionAmount":0,"invitationDay":"2017-09-22","invitationGold":10,"phone":"15505350893","prizeGold":8}],"prizeGoldTotal":305}
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
         * consumptionAmountTotal : 0
         * invitationGoldTotal : 20
         * invitationNum : 2
         * list : [{"consumptionAmount":0,"invitationDay":"2017-09-22","invitationGold":10,"phone":"15505350000","prizeGold":53},{"consumptionAmount":0,"invitationDay":"2017-09-22","invitationGold":10,"phone":"15505350893","prizeGold":8}]
         * prizeGoldTotal : 305
         */

        private int consumptionAmountTotal;
        private int invitationGoldTotal;
        private int invitationNum;
        private int prizeGoldTotal;
        private List<ListBean> list;

        public int getConsumptionAmountTotal() {
            return consumptionAmountTotal;
        }

        public void setConsumptionAmountTotal(int consumptionAmountTotal) {
            this.consumptionAmountTotal = consumptionAmountTotal;
        }

        public int getInvitationGoldTotal() {
            return invitationGoldTotal;
        }

        public void setInvitationGoldTotal(int invitationGoldTotal) {
            this.invitationGoldTotal = invitationGoldTotal;
        }

        public int getInvitationNum() {
            return invitationNum;
        }

        public void setInvitationNum(int invitationNum) {
            this.invitationNum = invitationNum;
        }

        public int getPrizeGoldTotal() {
            return prizeGoldTotal;
        }

        public void setPrizeGoldTotal(int prizeGoldTotal) {
            this.prizeGoldTotal = prizeGoldTotal;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * consumptionAmount : 0
             * invitationDay : 2017-09-22
             * invitationGold : 10
             * phone : 15505350000
             * prizeGold : 53
             */

            private int consumptionAmount;
            private String invitationDay;
            private int invitationGold;
            private String phone;
            private int prizeGold;

            public int getConsumptionAmount() {
                return consumptionAmount;
            }

            public void setConsumptionAmount(int consumptionAmount) {
                this.consumptionAmount = consumptionAmount;
            }

            public String getInvitationDay() {
                return invitationDay;
            }

            public void setInvitationDay(String invitationDay) {
                this.invitationDay = invitationDay;
            }

            public int getInvitationGold() {
                return invitationGold;
            }

            public void setInvitationGold(int invitationGold) {
                this.invitationGold = invitationGold;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getPrizeGold() {
                return prizeGold;
            }

            public void setPrizeGold(int prizeGold) {
                this.prizeGold = prizeGold;
            }
        }
    }
}
