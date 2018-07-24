package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class GetGuiZe {


    /**
     * code : 0
     * datas : [{"id":36,"nacigationQuestion":"全球首发，万阔，万物互联能给你带来什么？","nacigationTypeId":10}]
     * pageInfo : {"first":true,"last":true,"number":0,"numberOfElements":1,"size":10,"totalElements":1,"totalPages":1}
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
         * numberOfElements : 1
         * size : 10
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
         * id : 36
         * nacigationQuestion : 全球首发，万阔，万物互联能给你带来什么？
         * nacigationTypeId : 10
         */

        private int id;
        private String nacigationQuestion;
        private int nacigationTypeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNacigationQuestion() {
            return nacigationQuestion;
        }

        public void setNacigationQuestion(String nacigationQuestion) {
            this.nacigationQuestion = nacigationQuestion;
        }

        public int getNacigationTypeId() {
            return nacigationTypeId;
        }

        public void setNacigationTypeId(int nacigationTypeId) {
            this.nacigationTypeId = nacigationTypeId;
        }
    }
}
