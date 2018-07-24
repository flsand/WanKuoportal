package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30 0030.
 */

public class JBdetial {

    /**
     * code : 0
     * datas : [{"accountId":222,"afterGold":40,"amount":40,"consumptionType":11,"createTime":"2017-09-25 15:13:58","frontGold":0,"goldType":2,"id":937},{"accountId":222,"afterGold":45,"amount":5,"consumptionType":11,"createTime":"2017-09-25 15:14:26","frontGold":40,"goldType":2,"id":948},{"accountId":222,"afterGold":45,"amount":0,"consumptionType":11,"createTime":"2017-09-26 16:51:50","frontGold":45,"goldType":2,"id":999},{"accountId":222,"afterGold":45,"amount":0,"consumptionType":11,"createTime":"2017-09-26 16:55:09","frontGold":45,"goldType":2,"id":1010},{"accountId":222,"afterGold":75,"amount":30,"consumptionType":11,"createTime":"2017-09-28 10:29:06","frontGold":45,"goldType":2,"id":1072},{"accountId":222,"afterGold":80,"amount":5,"consumptionType":11,"createTime":"2017-09-28 11:17:43","frontGold":75,"goldType":2,"id":1103},{"accountId":222,"afterGold":125,"amount":45,"consumptionType":11,"createTime":"2017-09-28 11:18:55","frontGold":80,"goldType":2,"id":1114},{"accountId":222,"afterGold":140,"amount":15,"consumptionType":11,"createTime":"2017-09-28 11:21:06","frontGold":125,"goldType":2,"id":1125},{"accountId":222,"afterGold":140,"amount":0,"consumptionType":11,"createTime":"2017-09-28 11:21:31","frontGold":140,"goldType":2,"id":1136},{"accountId":222,"afterGold":150,"amount":10,"consumptionType":11,"createTime":"2017-09-28 11:27:18","frontGold":140,"goldType":2,"id":1147},{"accountId":222,"afterGold":10,"amount":10,"consumptionType":13,"createTime":"2017-09-28 14:32:28","frontGold":0,"goldType":1,"id":1198},{"accountId":222,"afterGold":180,"amount":30,"consumptionType":11,"createTime":"2017-09-29 10:26:57","frontGold":150,"goldType":2,"id":1319},{"accountId":222,"afterGold":185,"amount":5,"consumptionType":11,"createTime":"2017-09-29 14:05:29","frontGold":180,"goldType":2,"id":1340},{"accountId":222,"afterGold":195,"amount":10,"consumptionType":11,"createTime":"2017-09-29 14:07:16","frontGold":185,"goldType":2,"id":1351},{"accountId":222,"afterGold":245,"amount":50,"consumptionType":11,"createTime":"2017-09-29 14:14:17","frontGold":195,"goldType":2,"id":1362}]
     * msg : 查询成功
     * pageInfo : {"first":true,"last":false,"number":0,"numberOfElements":15,"size":15,"totalElements":18,"totalPages":2}
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
         * last : false
         * number : 0
         * numberOfElements : 15
         * size : 15
         * totalElements : 18
         * totalPages : 2
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
         * afterGold : 40
         * amount : 40
         * consumptionType : 11
         * createTime : 2017-09-25 15:13:58
         * frontGold : 0
         * goldType : 2
         * id : 937
         */

        private int accountId;
        private int afterGold;
        private int amount;
        private int consumptionType;
        private String createTime;
        private int frontGold;
        private int goldType;
        private int id;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public int getAfterGold() {
            return afterGold;
        }

        public void setAfterGold(int afterGold) {
            this.afterGold = afterGold;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
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

        public int getFrontGold() {
            return frontGold;
        }

        public void setFrontGold(int frontGold) {
            this.frontGold = frontGold;
        }

        public int getGoldType() {
            return goldType;
        }

        public void setGoldType(int goldType) {
            this.goldType = goldType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
