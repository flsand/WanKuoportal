package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by lv on 2017/9/26 我的答题列表.
 */

public class MyDaTi {

    /**
     * code : 0
     * datas : [{"accountId":222,"city":"烟台市","gold":0,"id":637,"province":"山东省","questionTime":"2017-09-25 14:47:25","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":638,"province":"山东省","questionTime":"2017-09-25 14:50:43","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":639,"province":"山东省","questionTime":"2017-09-25 14:51:31","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":640,"province":"山东省","questionTime":"2017-09-25 14:53:44","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":641,"province":"山东省","questionTime":"2017-09-25 14:56:09","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":642,"province":"山东省","questionTime":"2017-09-25 15:02:23","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":643,"province":"山东省","questionTime":"2017-09-25 15:09:29","rightSum":0,"wrongSum":0},{"accountId":222,"city":"烟台市","gold":0,"id":644,"province":"山东省","questionTime":"2017-09-25 15:10:41","rightSum":0,"wrongSum":0},{"accountId":222,"answerTime":"2017-09-25 15:13:58","city":"烟台市","gold":40,"id":645,"province":"山东省","questionTime":"2017-09-25 15:13:33","rightSum":8,"wrongSum":2},{"accountId":222,"answerTime":"2017-09-25 15:14:26","city":"烟台市","gold":5,"id":646,"province":"山东省","questionTime":"2017-09-25 15:14:18","rightSum":1,"wrongSum":9}]
     * msg : 获取成功
     * pageInfo : {"first":true,"last":false,"number":0,"numberOfElements":10,"size":10,"totalElements":11,"totalPages":2}
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
         * numberOfElements : 10
         * size : 10
         * totalElements : 11
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
         * city : 烟台市
         * gold : 0
         * id : 637
         * province : 山东省
         * questionTime : 2017-09-25 14:47:25
         * rightSum : 0
         * wrongSum : 0
         * answerTime : 2017-09-25 15:13:58
         */

        private int accountId;
        private String city;
        private int gold;
        private int id;
        private String province;
        private String questionTime;
        private int rightSum;
        private int wrongSum;
        private String answerTime;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getQuestionTime() {
            return questionTime;
        }

        public void setQuestionTime(String questionTime) {
            this.questionTime = questionTime;
        }

        public int getRightSum() {
            return rightSum;
        }

        public void setRightSum(int rightSum) {
            this.rightSum = rightSum;
        }

        public int getWrongSum() {
            return wrongSum;
        }

        public void setWrongSum(int wrongSum) {
            this.wrongSum = wrongSum;
        }

        public String getAnswerTime() {
            return answerTime;
        }

        public void setAnswerTime(String answerTime) {
            this.answerTime = answerTime;
        }
    }
}
