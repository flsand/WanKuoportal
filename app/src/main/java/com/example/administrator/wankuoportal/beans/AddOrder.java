package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class AddOrder implements Serializable {


    /**
     * code : 0
     * data : {"aexchangeOrder":{"accountId":222,"actualAmount":2400,"addressId":564,"amount":2400,"content":"","createTime":"2017-10-31 13:35:23","exchangeId":58,"goodsId":2,"id":13,"number":1,"orderId":"D20171031133523002","remark":"蓝色2","state":2},"agood":{"accountId":200,"appHtml":"","content":"白色","endEditTime":"","id":2,"img":"88fd8dbe543343b08ebe3416e41a2806","img1":"88fd8dbe543343b08ebe3416e41a2806","img2":"88fd8dbe543343b08ebe3416e41a2806","img3":"88fd8dbe543343b08ebe3416e41a2806","img4":"88fd8dbe543343b08ebe3416e41a2806","isExample":1,"labelId":2818,"name":"笔记本电脑2","pcHtml":"","phoneDiscount":3.5,"pirce":30.2,"publishTime":"2017-10-07 16:51:30","salesVolume":0,"shopId":3,"state":4,"type":2,"unit":"原"},"aorderAddress":{"accountId":222,"address":"low心魔自","area":"福山区","cancel":0,"city":"烟台市","createTime":"2017-10-31 13:35:23","defaultFlay":1,"id":564,"name":"吕道欣","phone":"18153527447","province":"山东省"}}
     * msg : 兑换成功
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

    public static class DataBean implements Serializable{
        /**
         * aexchangeOrder : {"accountId":222,"actualAmount":2400,"addressId":564,"amount":2400,"content":"","createTime":"2017-10-31 13:35:23","exchangeId":58,"goodsId":2,"id":13,"number":1,"orderId":"D20171031133523002","remark":"蓝色2","state":2}
         * agood : {"accountId":200,"appHtml":"","content":"白色","endEditTime":"","id":2,"img":"88fd8dbe543343b08ebe3416e41a2806","img1":"88fd8dbe543343b08ebe3416e41a2806","img2":"88fd8dbe543343b08ebe3416e41a2806","img3":"88fd8dbe543343b08ebe3416e41a2806","img4":"88fd8dbe543343b08ebe3416e41a2806","isExample":1,"labelId":2818,"name":"笔记本电脑2","pcHtml":"","phoneDiscount":3.5,"pirce":30.2,"publishTime":"2017-10-07 16:51:30","salesVolume":0,"shopId":3,"state":4,"type":2,"unit":"原"}
         * aorderAddress : {"accountId":222,"address":"low心魔自","area":"福山区","cancel":0,"city":"烟台市","createTime":"2017-10-31 13:35:23","defaultFlay":1,"id":564,"name":"吕道欣","phone":"18153527447","province":"山东省"}
         */

        private AexchangeOrderBean aexchangeOrder;
        private AgoodBean agood;
        private AorderAddressBean aorderAddress;

        public AexchangeOrderBean getAexchangeOrder() {
            return aexchangeOrder;
        }

        public void setAexchangeOrder(AexchangeOrderBean aexchangeOrder) {
            this.aexchangeOrder = aexchangeOrder;
        }

        public AgoodBean getAgood() {
            return agood;
        }

        public void setAgood(AgoodBean agood) {
            this.agood = agood;
        }

        public AorderAddressBean getAorderAddress() {
            return aorderAddress;
        }

        public void setAorderAddress(AorderAddressBean aorderAddress) {
            this.aorderAddress = aorderAddress;
        }

        public static class AexchangeOrderBean implements Serializable {
            /**
             * accountId : 222
             * actualAmount : 2400
             * addressId : 564
             * amount : 2400
             * content :
             * createTime : 2017-10-31 13:35:23
             * exchangeId : 58
             * goodsId : 2
             * id : 13
             * number : 1
             * orderId : D20171031133523002
             * remark : 蓝色2
             * state : 2
             */

            private int accountId;
            private int actualAmount;
            private int addressId;
            private int amount;
            private String content;
            private String createTime;
            private String amountDiamonds;

            public String getAmountDiamonds() {
                return amountDiamonds;
            }

            public void setAmountDiamonds(String amountDiamonds) {
                this.amountDiamonds = amountDiamonds;
            }

            public String getAmountGold() {
                return amountGold;
            }

            public void setAmountGold(String amountGold) {
                this.amountGold = amountGold;
            }

            private String amountGold;
            private int exchangeId;
            private int goodsId;
            private int id;
            private int number;
            private String orderId;
            private String remark;
            private int state;

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(int actualAmount) {
                this.actualAmount = actualAmount;
            }

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
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

            public int getExchangeId() {
                return exchangeId;
            }

            public void setExchangeId(int exchangeId) {
                this.exchangeId = exchangeId;
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

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }

        public static class AgoodBean implements Serializable{
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
             * pirce : 30.2
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
            private String publishTime;
            private int salesVolume;
            private int shopId;
            private int state;
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

        public static class AorderAddressBean implements Serializable{
            /**
             * accountId : 222
             * address : low心魔自
             * area : 福山区
             * cancel : 0
             * city : 烟台市
             * createTime : 2017-10-31 13:35:23
             * defaultFlay : 1
             * id : 564
             * name : 吕道欣
             * phone : 18153527447
             * province : 山东省
             */

            private int accountId;
            private String address;
            private String area;
            private int cancel;
            private String city;
            private String createTime;
            private int defaultFlay;
            private int id;
            private String name;
            private String phone;
            private String province;

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

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getCancel() {
                return cancel;
            }

            public void setCancel(int cancel) {
                this.cancel = cancel;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getDefaultFlay() {
                return defaultFlay;
            }

            public void setDefaultFlay(int defaultFlay) {
                this.defaultFlay = defaultFlay;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }
    }
}
