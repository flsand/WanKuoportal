package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class ShopGoodsDetials {


    /**
     * code : 0
     * data : {"agood":{"accountId":200,"appHtml":"","content":"白色","endEditTime":"","id":2,"img":"88fd8dbe543343b08ebe3416e41a2806","img1":"88fd8dbe543343b08ebe3416e41a2806","img2":"88fd8dbe543343b08ebe3416e41a2806","img3":"88fd8dbe543343b08ebe3416e41a2806","img4":"88fd8dbe543343b08ebe3416e41a2806","isExample":1,"labelId":2818,"name":"笔记本电脑2","pcHtml":"","phoneDiscount":3.5,"pirce":3020,"publishTime":"2017-10-07 16:51:30","salesVolume":0,"shopId":3,"state":4,"type":2,"unit":"原"},"agoodsDetail":[{"content":"蓝色1","goodsId":2,"id":25,"price":23},{"content":"蓝色2","goodsId":2,"id":26,"price":24},{"content":"蓝色3","goodsId":2,"id":27,"price":25},{"content":"蓝色4","goodsId":2,"id":28,"price":26}],"aproductExchangeMall":{"cancelMall":0,"id":58,"mallEndEditTime":"","mallPublishTime":"2017-10-08 10:21:32","priceShop":2,"productId":2,"salesVolume":9999}}
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
         * agood : {"accountId":200,"appHtml":"","content":"白色","endEditTime":"","id":2,"img":"88fd8dbe543343b08ebe3416e41a2806","img1":"88fd8dbe543343b08ebe3416e41a2806","img2":"88fd8dbe543343b08ebe3416e41a2806","img3":"88fd8dbe543343b08ebe3416e41a2806","img4":"88fd8dbe543343b08ebe3416e41a2806","isExample":1,"labelId":2818,"name":"笔记本电脑2","pcHtml":"","phoneDiscount":3.5,"pirce":3020,"publishTime":"2017-10-07 16:51:30","salesVolume":0,"shopId":3,"state":4,"type":2,"unit":"原"}
         * agoodsDetail : [{"content":"蓝色1","goodsId":2,"id":25,"price":23},{"content":"蓝色2","goodsId":2,"id":26,"price":24},{"content":"蓝色3","goodsId":2,"id":27,"price":25},{"content":"蓝色4","goodsId":2,"id":28,"price":26}]
         * aproductExchangeMall : {"cancelMall":0,"id":58,"mallEndEditTime":"","mallPublishTime":"2017-10-08 10:21:32","priceShop":2,"productId":2,"salesVolume":9999}
         */

        private AgoodBean agood;
        private AproductExchangeMallBean aproductExchangeMall;
        private List<AgoodsDetailBean> agoodsDetail;

        public AgoodBean getAgood() {
            return agood;
        }

        public void setAgood(AgoodBean agood) {
            this.agood = agood;
        }

        public AproductExchangeMallBean getAproductExchangeMall() {
            return aproductExchangeMall;
        }

        public void setAproductExchangeMall(AproductExchangeMallBean aproductExchangeMall) {
            this.aproductExchangeMall = aproductExchangeMall;
        }

        public List<AgoodsDetailBean> getAgoodsDetail() {
            return agoodsDetail;
        }

        public void setAgoodsDetail(List<AgoodsDetailBean> agoodsDetail) {
            this.agoodsDetail = agoodsDetail;
        }

        public static class AgoodBean {
            /**
             * accountId : 200
             * appHtml :
             * content : 白色
             * endEditTime :
             * id : 2
             * img : 88fd8dbe543343b08ebe3416e41a2806
             * img1 : 88fd8dbe543343b08ebe3416e41a2806
             * img2 : 88fd8dbe543343b08ebe3416e41a2806
             * img3 : 88fd8dbe543343b08ebe3416e41a2806
             * img4 : 88fd8dbe543343b08ebe3416e41a2806
             * isExample : 1
             * labelId : 2818
             * name : 笔记本电脑2
             * pcHtml :
             * phoneDiscount : 3.5
             * pirce : 3020
             * publishTime : 2017-10-07 16:51:30
             * salesVolume : 0
             * shopId : 3
             * state : 4
             * type : 2
             * unit : 原
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
            private int isExample;
            private int labelId;
            private String name;
            private String pcHtml;
            private double phoneDiscount;
            private double pirce;
            private double pirceShop;
            private String publishTime;
            private int salesVolume;
            private int shopId;
            private int state;
            private int type;
            private String unit;
            private String labelShop;

            public String getLabelShop() {
                return labelShop;
            }

            public void setLabelShop(String labelShop) {
                this.labelShop = labelShop;
            }

            public double getPirceShop() {
                return pirceShop;
            }

            public void setPirceShop(double pirceShop) {
                this.pirceShop = pirceShop;
            }

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

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
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

        public static class AproductExchangeMallBean {
            /**
             * cancelMall : 0
             * id : 58
             * mallEndEditTime :
             * mallPublishTime : 2017-10-08 10:21:32
             * priceShop : 2
             * productId : 2
             * salesVolume : 9999
             */

            private int cancelMall;
            private int id;
            private String mallEndEditTime;
            private String mallPublishTime;
            private double priceShop;
            private int productId;
            private int salesVolume;

            public int getCancelMall() {
                return cancelMall;
            }

            public void setCancelMall(int cancelMall) {
                this.cancelMall = cancelMall;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMallEndEditTime() {
                return mallEndEditTime;
            }

            public void setMallEndEditTime(String mallEndEditTime) {
                this.mallEndEditTime = mallEndEditTime;
            }

            public String getMallPublishTime() {
                return mallPublishTime;
            }

            public void setMallPublishTime(String mallPublishTime) {
                this.mallPublishTime = mallPublishTime;
            }

            public double getPriceShop() {
                return priceShop;
            }

            public void setPriceShop(double priceShop) {
                this.priceShop = priceShop;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getSalesVolume() {
                return salesVolume;
            }

            public void setSalesVolume(int salesVolume) {

                this.salesVolume = salesVolume;
            }

            @Override
            public String toString() {
                return "AproductExchangeMallBean{" +
                        "cancelMall=" + cancelMall +
                        ", id=" + id +
                        ", mallEndEditTime='" + mallEndEditTime + '\'' +
                        ", mallPublishTime='" + mallPublishTime + '\'' +
                        ", priceShop=" + priceShop +
                        ", productId=" + productId +
                        ", salesVolume=" + salesVolume +
                        '}';
            }
        }

        public static class AgoodsDetailBean {
            /**
             * content : 蓝色1
             * goodsId : 2
             * id : 25
             * price : 23
             */

            private String content;
            private int goodsId;
            private int id;
            private int price;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
