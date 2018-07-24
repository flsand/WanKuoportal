package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class ShopExampleDetails {

    /**
     * code : 0
     * data : {"accountId":200,"appHtml":"","content":"简介","endEditTime":"","id":10,"img":"aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":2818,"name":"华硕电脑","pcHtml":"","publishTime":"2017-10-07 16:54:00","state":0,"type":1}
     * datas : [{"accountId":0,"appHtml":"","content":"","id":16,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":15,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":14,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":13,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":12,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":11,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":10,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":9,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":8,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0},{"accountId":0,"appHtml":"","content":"","id":7,"img":"http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51","img1":"","img2":"","img3":"","img4":"","labelId":0,"name":"华硕电脑","pcHtml":"","state":0,"type":0}]
     * msg : 查询成功
     */

    private int code;
    private DataBean data;
    private String msg;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DataBean {
        /**
         * accountId : 200
         * appHtml :
         * content : 简介
         * endEditTime :
         * id : 10
         * img : aae02c0caf4e4a58b9b853fd3d3c0b51
         * img1 :
         * img2 :
         * img3 :
         * img4 :
         * labelId : 2818
         * name : 华硕电脑
         * pcHtml :
         * publishTime : 2017-10-07 16:54:00
         * state : 0
         * type : 1
         */

        private int accountId;
        private String appHtml;
        private String content;
        private String endEditTime;
        private int id;
        private String img;
        private String img1;
        private String img2;
        private String img3;
        private String img4;
        private int labelId;
        private String name;
        private String pcHtml;
        private String publishTime;
        private int state;
        private int type;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAppHtml() {
            return appHtml;
        }

        public void setAppHtml(String appHtml) {
            this.appHtml = appHtml;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getEndEditTime() {
            return endEditTime;
        }

        public void setEndEditTime(String endEditTime) {
            this.endEditTime = endEditTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }

        public int getLabelId() {
            return labelId;
        }

        public void setLabelId(int labelId) {
            this.labelId = labelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPcHtml() {
            return pcHtml;
        }

        public void setPcHtml(String pcHtml) {
            this.pcHtml = pcHtml;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class DatasBean {
        /**
         * accountId : 0
         * appHtml :
         * content :
         * id : 16
         * img : http://192.168.1.233:8080/wankuoportal/img/downloadimage/aae02c0caf4e4a58b9b853fd3d3c0b51
         * img1 :
         * img2 :
         * img3 :
         * img4 :
         * labelId : 0
         * name : 华硕电脑
         * pcHtml :
         * state : 0
         * type : 0
         */

        private int accountId;
        private String appHtml;
        private String content;
        private int id;
        private String img;
        private String img1;
        private String img2;
        private String img3;
        private String img4;
        private int labelId;
        private String name;
        private String pcHtml;
        private int state;
        private int type;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAppHtml() {
            return appHtml;
        }

        public void setAppHtml(String appHtml) {
            this.appHtml = appHtml;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }

        public int getLabelId() {
            return labelId;
        }

        public void setLabelId(int labelId) {
            this.labelId = labelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPcHtml() {
            return pcHtml;
        }

        public void setPcHtml(String pcHtml) {
            this.pcHtml = pcHtml;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
