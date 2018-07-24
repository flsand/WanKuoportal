package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class ShopInformation {


    /**
     * code : 0
     * datas : [{"accountId":200,"browseCount":"1","content":"","createTime":"2017-10-12","flag":"0","id":83,"linkId":0,"pictureId":"954eef24f1eb42658d433b5e95b42ebd","summary":"","title":"测试","type":"3"},{"accountId":200,"browseCount":"12","content":"","createTime":"2017-10-12","flag":"0","id":84,"linkId":0,"pictureId":"954eef24f1eb42658d433b5e95b42ebd","summary":"测试店铺","title":"测试","type":"3"},{"accountId":200,"browseCount":"27","content":"","createTime":"2017-10-12","flag":"0","id":85,"linkId":0,"pictureId":"954eef24f1eb42658d433b5e95b42ebd","summary":"测试店铺","title":"测试","type":"3"}]
     * msg : 查询成功
     * pageInfo : {"first":true,"last":true,"number":0,"numberOfElements":3,"size":8,"totalElements":3,"totalPages":1}
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
         * numberOfElements : 3
         * size : 8
         * totalElements : 3
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
         * accountId : 200
         * browseCount : 1
         * content :
         * createTime : 2017-10-12
         * flag : 0
         * id : 83
         * linkId : 0
         * pictureId : 954eef24f1eb42658d433b5e95b42ebd
         * summary :
         * title : 测试
         * type : 3
         */

        private int accountId;
        private String browseCount;
        private String content;
        private String createTime;
        private String flag;
        private int id;
        private int linkId;
        private String pictureId;
        private String summary;
        private String title;
        private String type;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getBrowseCount() {
            return browseCount;
        }

        public void setBrowseCount(String browseCount) {
            this.browseCount = browseCount;
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

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLinkId() {
            return linkId;
        }

        public void setLinkId(int linkId) {
            this.linkId = linkId;
        }

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
