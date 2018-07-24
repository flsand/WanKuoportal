package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class ShopGoods_xiangqing {

    /**
     * code : 0
     * data : {"accountId":200,"content":"白色","id":1,"img":"88fd8dbe543343b08ebe3416e41a2806","img1":"88fd8dbe543343b08ebe3416e41a2806","img2":"88fd8dbe543343b08ebe3416e41a2806","img3":"88fd8dbe543343b08ebe3416e41a2806","img4":"88fd8dbe543343b08ebe3416e41a2806","isExample":1,"labelId":2818,"name":"笔记本电脑1","pcHtml":"","phoneDiscount":3.5,"pirce":30.2,"publishTime":"2017-10-07 16:51:30","salesVolume":0,"shopId":3,"state":4,"type":2,"unit":"原"}
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
         * accountId : 200
         * content : 白色
         * id : 1
         * img : 88fd8dbe543343b08ebe3416e41a2806
         * img1 : 88fd8dbe543343b08ebe3416e41a2806
         * img2 : 88fd8dbe543343b08ebe3416e41a2806
         * img3 : 88fd8dbe543343b08ebe3416e41a2806
         * img4 : 88fd8dbe543343b08ebe3416e41a2806
         * isExample : 1
         * labelId : 2818
         * name : 笔记本电脑1
         * pcHtml :
         * phoneDiscount : 3.5
         * pirce : 30.2
         * publishTime : 2017-10-07 16:51:30
         * salesVolume : 0
         * shopId : 3
         * state : 4
         * type : 2
         * unit : 原
         */

        private int accountId;
        private String content;
        private int id;
        private String img;
        private String img1;
        private String img2;
        private String img3;
        private String img4;
        private int isExample;
        private int labelId;
        private String name;
        private String pcHtml;
        private String appHtml;

        public String getAppHtml() {
            return appHtml;
        }

        public void setAppHtml(String appHtml) {
            this.appHtml = appHtml;
        }

        private double phoneDiscount;
        private double pirce;
        private String publishTime;
        private int salesVolume;
        private String shopId;
        private int state;
        private int type;
        private String unit;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
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

        public int getIsExample() {
            return isExample;
        }

        public void setIsExample(int isExample) {
            this.isExample = isExample;
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

        public double getPhoneDiscount() {
            return phoneDiscount;
        }

        public void setPhoneDiscount(double phoneDiscount) {
            this.phoneDiscount = phoneDiscount;
        }

        public double getPirce() {
            return pirce;
        }

        public void setPirce(double pirce) {
            this.pirce = pirce;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public int getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(int salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
