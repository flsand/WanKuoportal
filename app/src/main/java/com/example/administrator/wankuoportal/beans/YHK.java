package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class YHK {


    /**
     * code : 0
     * datas : [{"accountId":222,"bankCardNo":"6228480269023787477","bankName":"东厅分行","bankType":2,"cardType":2,"city":"烟台市","id":2,"name":"吕道欣","province":"山东省"},{"accountId":222,"bankCardNo":"6228480269023787477","bankName":"东厅分行","bankType":2,"cardType":2,"city":"烟台市","id":3,"name":"吕道欣","province":"山东省"}]
     * msg : 获取成功
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * accountId : 222
         * bankCardNo : 6228480269023787477
         * bankName : 东厅分行
         * bankType : 2
         * cardType : 2
         * city : 烟台市
         * id : 2
         * name : 吕道欣
         * province : 山东省
         */

        private int accountId;
        private String bankCardNo;
        private String bankName;
        private int bankType;
        private int cardType;
        private String city;
        private int id;
        private String name;
        private String province;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getBankType() {
            return bankType;
        }

        public void setBankType(int bankType) {
            this.bankType = bankType;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
