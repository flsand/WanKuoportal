package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class Getmywalletpage {

    /**
     * code : 0
     * datas : [{"accountId":222,"afterMoney":21000,"amount":-1000,"consumptionType":22,"createTime":"2017-10-21 15:14:27","frontMoney":22000,"id":2080}]
     * msg : 查询成功
     * pageInfo : {"first":true,"last":true,"number":0,"numberOfElements":1,"size":15,"totalElements":1,"totalPages":1}
     */

    private int code;
    private String msg;
    private PageInfoBean pageInfo;
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

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class PageInfoBean {
        /**
         * first : true
         * last : true
         * number : 0
         * numberOfElements : 1
         * size : 15
         * totalElements : 1
         * totalPages : 1
         */

        private boolean first;
        private boolean last;
        private int number;
        private int numberOfElements;
        private int size;
        private int totalElements;
        private int totalPages;

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

    public static class DatasBean {
        /**
         * accountId : 222
         * afterMoney : 21000
         * amount : -1000
         * consumptionType : 22
         * createTime : 2017-10-21 15:14:27
         * frontMoney : 22000
         * id : 2080
         */

        private int accountId;
        private String afterMoney;
        private String amount;
        private int consumptionType;
        private String createTime;
        private String frontMoney;
        private int id;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAfterMoney() {
            return afterMoney;
        }

        public void setAfterMoney(String afterMoney) {
            this.afterMoney = afterMoney;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getConsumptionType() {
            return consumptionType;
        }

        public void setConsumptionType(int consumptionType) {
            this.consumptionType = consumptionType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFrontMoney() {
            return frontMoney;
        }

        public void setFrontMoney(String frontMoney) {
            this.frontMoney = frontMoney;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
