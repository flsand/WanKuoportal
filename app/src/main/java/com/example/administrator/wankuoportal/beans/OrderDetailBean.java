package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

import java.util.List;

/**
 * <pre>
 *     pagckage:com.example.administrator.wankuoportal.beans
 *     author  : Administrator
 *     e-mail  : 1156183505@qq.com
 *     time    :2018/5/7.
 *     desc    :
 *
 * </pre>
 */
public class OrderDetailBean extends DBVO {
    /**
     * gList : [{"accountId":203,"appHtml":"<p style=\"text-align: center;\"><img src=\"http://www.wankuo5888.com/img/downloadimage/60abc475790c4bd18af65aca8992d274\" title=\"960.jpg\" alt=\"960.jpg\"/><\/p>","costPrice":198,"endEditTime":"2018-04-18 13:30:22","examineAccountId":158,"examineTime":"2018-04-18 13:32:13","id":214,"img":"fb9d490cf0b34b4ba04ada79e2ca9adb","img1":"90e461c5c3584a13af502f5a4dd19a93","img2":"c794a4ae23ec477d9708ba23d1972f53","img3":"4e5414ba13d345f6bb1660a6bafd863d","img4":"1f907d0c228e433e8630c9b4a66de205","isExample":0,"labelId":0,"labelShop":"10补水面膜","name":"紫熙天然蚕丝面膜，深层10倍补水，让肌肤足足喝饱水","pcHtml":"<p style=\"text-align: center;\"><img src=\"http://www.wankuo5888.com/img/downloadimage/0809961cadd34818a36f12496c814dfd\" title=\"848.jpg\" alt=\"848.jpg\"/><\/p>","phoneDiscount":99,"pirce":198,"publishTime":"2018-04-14 14:47:10","reason":"","salesVolume":19,"shopId":0,"state":7,"stock":81,"type":1,"unit":"元"}]
     * mList : [{"cancelMall":0,"id":83,"mallEndEditTime":"2018-04-17 14:29:45","mallPublishTime":"2018-04-14 17:20:41","priceShop":198,"productId":214,"salesVolume":19}]
     * order : {"accountId":521,"address":"恭候您莫名","city":"烟台市","consignee":"公公","count":1,"country":"中国","createTime":"2018-05-07 13:39:00","district":"福山区","id":173,"mobile":"46466","orderStatus":"0101","payId":0,"payStatus":2,"province":"山东省","saPrice":198,"shippingId":0,"stPrice":198}
     */

    private OrderBean order;
    private List<GListBean> gList;
    private List<MListBean> mList;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<GListBean> getGList() {
        return gList;
    }

    public void setGList(List<GListBean> gList) {
        this.gList = gList;
    }

    public List<MListBean> getMList() {
        return mList;
    }

    public void setMList(List<MListBean> mList) {
        this.mList = mList;
    }

    public static class OrderBean {
        /**
         * accountId : 521
         * address : 恭候您莫名
         * city : 烟台市
         * consignee : 公公
         * count : 1
         * country : 中国
         * createTime : 2018-05-07 13:39:00
         * district : 福山区
         * id : 173
         * mobile : 46466
         * orderStatus : 0101
         * payId : 0
         * payStatus : 2
         * province : 山东省
         * saPrice : 198.0
         * shippingId : 0
         * stPrice : 198.0
         */

        private int accountId;
        private String address;
        private String city;
        private String consignee;
        private int count;
        private String country;
        private String createTime;
        private String district;
        private int id;
        private String mobile;
        private String orderStatus;
        private int payId;
        private int payStatus;
        private String province;
        private int shippingId;
        //商品名称
        private String name;
        //销售价格
        private double saPrice;
        //进货价格
        private double stPrice;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayId() {
            return payId;
        }

        public void setPayId(int payId) {
            this.payId = payId;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public double getSaPrice() {
            return saPrice;
        }

        public void setSaPrice(double saPrice) {
            this.saPrice = saPrice;
        }

        public int getShippingId() {
            return shippingId;
        }

        public void setShippingId(int shippingId) {
            this.shippingId = shippingId;
        }

        public double getStPrice() {
            return stPrice;
        }

        public void setStPrice(double stPrice) {
            this.stPrice = stPrice;
        }
    }

    public static class GListBean extends DBVO{
        /**
         * accountId : 203
         * appHtml : <p style="text-align: center;"><img src="http://www.wankuo5888.com/img/downloadimage/60abc475790c4bd18af65aca8992d274" title="960.jpg" alt="960.jpg"/></p>
         * costPrice : 198.0
         * endEditTime : 2018-04-18 13:30:22
         * examineAccountId : 158
         * examineTime : 2018-04-18 13:32:13
         * id : 214
         * img : fb9d490cf0b34b4ba04ada79e2ca9adb
         * img1 : 90e461c5c3584a13af502f5a4dd19a93
         * img2 : c794a4ae23ec477d9708ba23d1972f53
         * img3 : 4e5414ba13d345f6bb1660a6bafd863d
         * img4 : 1f907d0c228e433e8630c9b4a66de205
         * isExample : 0
         * labelId : 0
         * labelShop : 10补水面膜
         * name : 紫熙天然蚕丝面膜，深层10倍补水，让肌肤足足喝饱水
         * pcHtml : <p style="text-align: center;"><img src="http://www.wankuo5888.com/img/downloadimage/0809961cadd34818a36f12496c814dfd" title="848.jpg" alt="848.jpg"/></p>
         * phoneDiscount : 99.0
         * pirce : 198.0
         * publishTime : 2018-04-14 14:47:10
         * reason :
         * salesVolume : 19
         * shopId : 0
         * state : 7
         * stock : 81
         * type : 1
         * unit : 元
         */

        private int accountId;
        private String appHtml;
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
        private double phoneDiscount;
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

    public static class MListBean extends DBVO {
        /**
         * cancelMall : 0
         * id : 83
         * mallEndEditTime : 2018-04-17 14:29:45
         * mallPublishTime : 2018-04-14 17:20:41
         * priceShop : 198
         * productId : 214
         * salesVolume : 19
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
    }
}
