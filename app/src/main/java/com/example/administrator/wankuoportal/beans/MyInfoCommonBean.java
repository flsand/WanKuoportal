package com.example.administrator.wankuoportal.beans;

import java.util.List;

public class MyInfoCommonBean {

    /**
     * code : 0
     * datas : [{"aPushinfoId":44,"accountType":0,"cancel":0,"content":"stsadsadsadssadda","createTime":"2018-04-24 16:06:45","endtime":1524499200000,"goodsMatchId":0,"infoRevoke":0,"infoTitle":"tes313","infoType":1,"linkUrl":"0","openType":0,"status":1},{"aPushinfoId":19,"accountType":0,"cancel":0,"content":"测试","createTime":"2017-11-14 10:28:55","endtime":1510588800000,"goodsMatchId":0,"infoRevoke":0,"infoTitle":"测试","infoType":1,"linkUrl":"https://www.baidu.com","openType":0,"status":1},{"aPushinfoId":15,"accountType":0,"cancel":0,"content":"st","createTime":"2017-11-14 10:11:06","endtime":1510588800000,"goodsMatchId":0,"infoRevoke":0,"infoTitle":"tes","infoType":1,"linkUrl":"0","openType":1,"status":0},{"aPushinfoId":9,"accountType":0,"cancel":0,"content":"st","createTime":"2017-11-14 09:26:24","endtime":1510588800000,"goodsMatchId":0,"infoRevoke":0,"infoTitle":"tes","infoType":1,"linkUrl":"0","openType":0,"status":0},{"aPushinfoId":7,"accountType":0,"cancel":0,"content":"st","createTime":"2017-11-13 08:58:58","endtime":1510502400000,"goodsMatchId":0,"infoRevoke":0,"infoTitle":"tes","infoType":1,"linkUrl":"0","openType":0,"status":0},{"aPushinfoId":4,"accountType":0,"cancel":0,"content":"st","createTime":"2017-11-11 11:18:43","endtime":1510329600000,"goodsMatchId":0,"infoRevoke":0,"infoTitle":"tes","infoType":1,"linkUrl":"0","openType":0,"status":0}]
     * pageInfo : {"first":true,"last":true,"number":0,"numberOfElements":6,"size":1000,"totalElements":6,"totalPages":1}
     */

    private int code;
    private PageInfoBean pageInfo;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
         * numberOfElements : 6
         * size : 1000
         * totalElements : 6
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
         * aPushinfoId : 44
         * accountType : 0
         * cancel : 0
         * content : stsadsadsadssadda
         * createTime : 2018-04-24 16:06:45
         * endtime : 1524499200000
         * goodsMatchId : 0
         * infoRevoke : 0
         * infoTitle : tes313
         * infoType : 1
         * linkUrl : 0
         * openType : 0
         * status : 1
         */

        private int aPushinfoId;
        private int accountType;
        private int cancel;
        private String content;
        private String createTime;
        private long endtime;
        private int goodsMatchId;
        private int infoRevoke;
        private String infoTitle;
        private int infoType;
        private String linkUrl;
        private int openType;
        private int status;

        public int getAPushinfoId() {
            return aPushinfoId;
        }

        public void setAPushinfoId(int aPushinfoId) {
            this.aPushinfoId = aPushinfoId;
        }

        public int getAccountType() {
            return accountType;
        }

        public void setAccountType(int accountType) {
            this.accountType = accountType;
        }

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public int getGoodsMatchId() {
            return goodsMatchId;
        }

        public void setGoodsMatchId(int goodsMatchId) {
            this.goodsMatchId = goodsMatchId;
        }

        public int getInfoRevoke() {
            return infoRevoke;
        }

        public void setInfoRevoke(int infoRevoke) {
            this.infoRevoke = infoRevoke;
        }

        public String getInfoTitle() {
            return infoTitle;
        }

        public void setInfoTitle(String infoTitle) {
            this.infoTitle = infoTitle;
        }

        public int getInfoType() {
            return infoType;
        }

        public void setInfoType(int infoType) {
            this.infoType = infoType;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public int getOpenType() {
            return openType;
        }

        public void setOpenType(int openType) {
            this.openType = openType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
