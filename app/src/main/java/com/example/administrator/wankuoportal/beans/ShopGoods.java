package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9 0009.
 */

public class ShopGoods {

    /**
     * code : 0
     */

    private int code;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * aproductExchangeMall : {"cancelMall":0,"id":76,"mallPublishTime":"2018-03-11 14:40:28","priceShop":65,"productId":108,"salesVolume":4}
         */

        private AgoodBean agood;
        private AproductExchangeMallBean aproductExchangeMall;

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

        public static class AgoodBean {
            /**
             * accountId : 203
             * appHtml : <p style="text-align: center;"><img src="http://www.wankuo5888.com/img/downloadimage/380dacb9aff54e9b9f409dd2b18cf88e" title="里脊详情页.jpg" alt="里脊详情页.jpg"/></p>
             * content :
             * costPrice : 10
             * endEditTime : 2018-03-21 11:47:24
             * examineAccountId : 158
             * examineTime : 2018-03-21 11:48:46
             * id : 108
             * img : 5581270532e54eb79eb501e3d30e3598
             * img1 : 507dea923f5145f29dd3d9a3c9290a8f
             * img2 : 3c7f8014b7c14790b639577413848931
             * img3 : 21cde03379f1417ea3887874f7640982
             * img4 : 296629a7405c4b62a85c4ec0e2f376e9
             * isExample : 0
             * labelId : 食品
             * labelShop
             * name : 富硒散养黑山猪里脊肉，散养时间12-14个月
             * pcHtml : <p style="text-align: center;"><img src="http://www.wankuo5888.com/img/downloadimage1/23dc5a393c814b27903e4c3fa3a900af" title="里脊详情页.jpg" alt="里脊详情页.jpg"/></p>
             * phoneDiscount : 99
             * pirce : 78
             * publishTime : 2018-03-11 14:37:30
             * reason :
             * salesVolume : 4
             * shopId : 0
             * state : 7
             * stock : 92
             * type : 1
             * unit : 元
             */

            private int accountId;
            private String appHtml;
            private String content;
            private double costPrice;
            private String endEditTime;
            private int examineAccountId;
            private String examineTime;
            private int id;
            private String img;
            private String img1;
            private String img2;
            private String img3;
            private String img4;
            private int isExample;
            private int labelId;
            private String labelShop;
            private String name;
            private String pcHtml;
            private int phoneDiscount;
            private double pirce;
            private String publishTime;
            private String reason;
            private int salesVolume;
            private int shopId;
            private int state;
            private int stock;
            private int type;
            private String unit;

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

            public double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(double costPrice) {
                this.costPrice = costPrice;
            }

            public String getEndEditTime() {
                return endEditTime;
            }

            public void setEndEditTime(String endEditTime) {
                this.endEditTime = endEditTime;
            }

            public int getExamineAccountId() {
                return examineAccountId;
            }

            public void setExamineAccountId(int examineAccountId) {
                this.examineAccountId = examineAccountId;
            }

            public String getExamineTime() {
                return examineTime;
            }

            public void setExamineTime(String examineTime) {
                this.examineTime = examineTime;
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

            public String getLabelShop() {
                return labelShop;
            }

            public void setLabelShop(String labelShop) {
                this.labelShop = labelShop;
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

            public int getPhoneDiscount() {
                return phoneDiscount;
            }

            public void setPhoneDiscount(int phoneDiscount) {
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

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
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

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
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
             * id : 76
             * mallPublishTime : 2018-03-11 14:40:28
             * priceShop : 65
             * productId : 108
             * salesVolume : 4
             */

            private int cancelMall;
            private int id;
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
        }
    }
}
