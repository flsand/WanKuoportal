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
public class PayResultBean extends DBVO {


    /**
     * order : {"accountId":521,"address":"详细地址呵呵","city":"烟台市","consignee":"十楼","count":1,"country":"中国","district":"福山区","id":168,"mobile":"15556464997","orderStatus":"0104","payId":0,"payStatus":1,"province":"山东省","saPrice":65,"shippingId":0,"stPrice":10}
     * goodOrderList : [{"count":1,"gPrice":10,"goodsId":107,"mId":1,"mPrice":68,"name":"富硒散养黑山猪后腿肉，散养时间12-14个月","orderId":168,"wId":0}]
     * log : [{"amount":6500,"id":74,"logTime":1525691540377,"orderID":168,"payTime":1525691540377,"status":1,"type":"0203"}]
     */

    private OrderBean order;
    private List<GoodOrderListBean> goodOrderList;
    private List<LogBean> log;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<GoodOrderListBean> getGoodOrderList() {
        return goodOrderList;
    }

    public void setGoodOrderList(List<GoodOrderListBean> goodOrderList) {
        this.goodOrderList = goodOrderList;
    }

    public List<LogBean> getLog() {
        return log;
    }

    public void setLog(List<LogBean> log) {
        this.log = log;
    }

    public static class OrderBean extends DBVO{
        /**
         * accountId : 521
         * address : 详细地址呵呵
         * city : 烟台市
         * consignee : 十楼
         * count : 1
         * country : 中国
         * district : 福山区
         * id : 168
         * mobile : 15556464997
         * orderStatus : 0104
         * payId : 0
         * payStatus : 1
         * province : 山东省
         * saPrice : 65.0
         * shippingId : 0
         * stPrice : 10.0
         */

        private int accountId;
        private String address;
        private String city;
        private String consignee;
        private int count;
        private String country;
        private String district;
        private int id;
        private String mobile;
        private String orderStatus;
        private int payId;
        private int payStatus;
        private String province;
        private String payType;
        private int shippingId;
        //销售价格
        private double saPrice;
        //进货价格
        private double stPrice;

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

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

    public static class GoodOrderListBean extends DBVO{
        /**
         * count : 1
         * gPrice : 10.0
         * goodsId : 107
         * mId : 1
         * mPrice : 68.0
         * name : 富硒散养黑山猪后腿肉，散养时间12-14个月
         * orderId : 168
         * wId : 0
         */

        private int count;
        private double gPrice;
        private int goodsId;
        private int mId;
        private double mPrice;
        private String name;
        private int orderId;
        private int wId;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getGPrice() {
            return gPrice;
        }

        public void setGPrice(double gPrice) {
            this.gPrice = gPrice;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getMId() {
            return mId;
        }

        public void setMId(int mId) {
            this.mId = mId;
        }

        public double getMPrice() {
            return mPrice;
        }

        public void setMPrice(double mPrice) {
            this.mPrice = mPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getWId() {
            return wId;
        }

        public void setWId(int wId) {
            this.wId = wId;
        }
    }

    public static class LogBean extends DBVO {
        /**
         * amount : 6500.0
         * id : 74
         * logTime : 1525691540377
         * orderID : 168
         * payTime : 1525691540377
         * status : 1
         * type : 0203
         */

        private double amount;
        private String id;
        private String logTime;
        private String orderID;
        private String payTime;
        private String status;
        private String type;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogTime() {
            return logTime;
        }

        public void setLogTime(String logTime) {
            this.logTime = logTime;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
