package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class Xinxi {


    /**
     * count : 0
     * ro : {"code":0,"datas":[{"content":"qeqweqweqweqeqweqwewqewqeqwewq","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":137,"status":1,"time":"2017-10-19 17:22:49","title":"tes","type":1,"validitytime":1},{"content":"st555","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":136,"status":0,"time":"2017-10-19 17:22:14","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/https://www.baidu.com/","pushinfoid":135,"status":1,"time":"2017-10-19 17:21:53","title":"tes","type":1,"validitytime":1},{"content":"steqw","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":134,"status":0,"time":"2017-10-19 17:21:44","title":"tes","type":1,"validitytime":1},{"content":"stqweqwe","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/https://www.baidu.com/","pushinfoid":133,"status":1,"time":"2017-10-19 17:21:23","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":132,"status":1,"time":"2017-10-19 16:50:27","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":127,"status":0,"time":"2017-10-18 14:24:41","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":116,"status":0,"time":"2017-10-11 13:50:44","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":115,"status":0,"time":"2017-10-11 13:50:21","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":113,"status":0,"time":"2017-10-11 13:46:54","title":"tes","type":1,"validitytime":1}],"pageInfo":{"first":true,"last":false,"number":0,"numberOfElements":10,"size":10,"totalElements":20,"totalPages":2}}
     */

    private int count;
    private RoBean ro;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public RoBean getRo() {
        return ro;
    }

    public void setRo(RoBean ro) {
        this.ro = ro;
    }

    public static class RoBean {
        /**
         * code : 0
         * datas : [{"content":"qeqweqweqweqeqweqwewqewqeqwewq","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":137,"status":1,"time":"2017-10-19 17:22:49","title":"tes","type":1,"validitytime":1},{"content":"st555","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":136,"status":0,"time":"2017-10-19 17:22:14","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/https://www.baidu.com/","pushinfoid":135,"status":1,"time":"2017-10-19 17:21:53","title":"tes","type":1,"validitytime":1},{"content":"steqw","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":134,"status":0,"time":"2017-10-19 17:21:44","title":"tes","type":1,"validitytime":1},{"content":"stqweqwe","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/https://www.baidu.com/","pushinfoid":133,"status":1,"time":"2017-10-19 17:21:23","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":132,"status":1,"time":"2017-10-19 16:50:27","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":127,"status":0,"time":"2017-10-18 14:24:41","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":116,"status":0,"time":"2017-10-11 13:50:44","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":115,"status":0,"time":"2017-10-11 13:50:21","title":"tes","type":1,"validitytime":1},{"content":"st","goodsId":0,"goodsMatchId":0,"linkurl":"http://192.168.1.198:8080/wankuoportal/0","pushinfoid":113,"status":0,"time":"2017-10-11 13:46:54","title":"tes","type":1,"validitytime":1}]
         * pageInfo : {"first":true,"last":false,"number":0,"numberOfElements":10,"size":10,"totalElements":20,"totalPages":2}
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
             * last : false
             * number : 0
             * numberOfElements : 10
             * size : 10
             * totalElements : 20
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
             * content : qeqweqweqweqeqweqwewqewqeqwewq
             * goodsId : 0
             * goodsMatchId : 0
             * linkurl : http://192.168.1.198:8080/wankuoportal/0
             * pushinfoid : 137
             * status : 1
             * time : 2017-10-19 17:22:49
             * title : tes
             * type : 1
             * validitytime : 1
             */

            private String content;
            private int goodsId;
            private int goodsMatchId;
            private String linkurl;
            private int pushinfoid;
            private int status;
            private String time;
            private String title;

            public String getOpenType() {
                return openType;
            }

            public void setOpenType(String openType) {
                this.openType = openType;
            }

            private String openType;
            private int type;
            private int validitytime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getGoodsMatchId() {
                return goodsMatchId;
            }

            public void setGoodsMatchId(int goodsMatchId) {
                this.goodsMatchId = goodsMatchId;
            }

            public String getLinkurl() {
                return linkurl;
            }

            public void setLinkurl(String linkurl) {
                this.linkurl = linkurl;
            }

            public int getPushinfoid() {
                return pushinfoid;
            }

            public void setPushinfoid(int pushinfoid) {
                this.pushinfoid = pushinfoid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getValiditytime() {
                return validitytime;
            }

            public void setValiditytime(int validitytime) {
                this.validitytime = validitytime;
            }
        }
    }
}
