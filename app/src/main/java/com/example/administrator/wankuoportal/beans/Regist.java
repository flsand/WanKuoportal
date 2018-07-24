package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/19 0019.
 */
public class Regist implements Serializable {


    /**
     * code : 0
     * data : {"aaccountAuthorize":{"accountId":222,"id":55,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":1,"isServiceProvider":0,"limitGold":20,"memberLevel":0,"money":0,"nolimitGold":48995,"partnerLevel":0,"sellerLevel":0},"aaccountInfo":{"accountId":222,"age":"23","area":"福山区","autograph":"五路口","birthDate":"1994-07-07","city":"烟台市","country":"中国","headIcon":"3115ff5311234ed5a17e97f437f6dc9b","id":7574,"invitationCode":"XJxUlFxJFT","name":"吕道欣","nickname":"路路通","occupation":0,"occupationName":"服务行业","phone":"18153527447","province":"山东省","sex":1}}
     */

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private DataBean data;

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

    public static class DataBean implements Serializable {
        /**
         * aaccountAuthorize : {"accountId":222,"id":55,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":1,"isServiceProvider":0,"limitGold":20,"memberLevel":0,"money":0,"nolimitGold":48995,"partnerLevel":0,"sellerLevel":0}
         * aaccountInfo : {"accountId":222,"age":"23","area":"福山区","autograph":"五路口","birthDate":"1994-07-07","city":"烟台市","country":"中国","headIcon":"3115ff5311234ed5a17e97f437f6dc9b","id":7574,"invitationCode":"XJxUlFxJFT","name":"吕道欣","nickname":"路路通","occupation":0,"occupationName":"服务行业","phone":"18153527447","province":"山东省","sex":1}
         */

        private AaccountAuthorizeBean aaccountAuthorize;
        private AaccountInfoBean aaccountInfo;

        public AaccountAuthorizeBean getAaccountAuthorize() {
            return aaccountAuthorize;
        }

        public void setAaccountAuthorize(AaccountAuthorizeBean aaccountAuthorize) {
            this.aaccountAuthorize = aaccountAuthorize;
        }

        public AaccountInfoBean getAaccountInfo() {
            return aaccountInfo;
        }

        public void setAaccountInfo(AaccountInfoBean aaccountInfo) {
            this.aaccountInfo = aaccountInfo;
        }

        public static class AaccountAuthorizeBean implements Serializable {
            /**
             * accountId : 222
             * id : 55
             * isAdvertiser : 0
             * isEmployer : 1
             * isMediaOwner : 0
             * isOrdinaryMember : 1
             * isServiceProvider : 0
             * limitGold : 20
             * memberLevel : 0
             * money : 0.0
             * nolimitGold : 48995
             * partnerLevel : 0
             * sellerLevel : 0
             */

            private int accountId;
            private int id;
            private int isAdvertiser;
            private int isEmployer;
            private int isMediaOwner;
            private int isOrdinaryMember;
            private int isServiceProvider;
            private int memberLevel;
            private String money;
            private String diamonds;
            private double nomoney;
            private double nolimitGold;
            private double limitGold;
            private int partnerLevel;
            private int sellerLevel;


            public double getNolimitGold() {
                return nolimitGold;
            }

            public double getLimitGold() {
                return limitGold;
            }

            public void setLimitGold(double limitGold) {
                this.limitGold = limitGold;
            }


            public void setNolimitGold(double nolimitGold) {
                this.nolimitGold = nolimitGold;
            }

            public double getNomoney() {
                return nomoney;
            }

            public void setNomoney(double nomoney) {
                this.nomoney = nomoney;
            }

            public String getDiamonds() {
                return diamonds;
            }

            public void setDiamonds(String diamonds) {
                this.diamonds = diamonds;
            }


            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsAdvertiser() {
                return isAdvertiser;
            }

            public void setIsAdvertiser(int isAdvertiser) {
                this.isAdvertiser = isAdvertiser;
            }

            public int getIsEmployer() {
                return isEmployer;
            }

            public void setIsEmployer(int isEmployer) {
                this.isEmployer = isEmployer;
            }

            public int getIsMediaOwner() {
                return isMediaOwner;
            }

            public void setIsMediaOwner(int isMediaOwner) {
                this.isMediaOwner = isMediaOwner;
            }

            public int getIsOrdinaryMember() {
                return isOrdinaryMember;
            }

            public void setIsOrdinaryMember(int isOrdinaryMember) {
                this.isOrdinaryMember = isOrdinaryMember;
            }

            public int getIsServiceProvider() {
                return isServiceProvider;
            }

            public void setIsServiceProvider(int isServiceProvider) {
                this.isServiceProvider = isServiceProvider;
            }

            public void setLimitGold(int limitGold) {
                this.limitGold = limitGold;
            }

            public int getMemberLevel() {
                return memberLevel;
            }

            public void setMemberLevel(int memberLevel) {
                this.memberLevel = memberLevel;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public void setNolimitGold(int nolimitGold) {
                this.nolimitGold = nolimitGold;
            }

            public int getPartnerLevel() {
                return partnerLevel;
            }

            public void setPartnerLevel(int partnerLevel) {
                this.partnerLevel = partnerLevel;
            }

            public int getSellerLevel() {
                return sellerLevel;
            }

            public void setSellerLevel(int sellerLevel) {
                this.sellerLevel = sellerLevel;
            }
        }

        public static class AaccountInfoBean implements Serializable {
            /**
             * accountId : 222
             * age : 23
             * area : 福山区
             * autograph : 五路口
             * birthDate : 1994-07-07
             * city : 烟台市
             * country : 中国
             * headIcon : 3115ff5311234ed5a17e97f437f6dc9b
             * id : 7574
             * invitationCode : XJxUlFxJFT
             * name : 吕道欣
             * nickname : 路路通
             * occupation : 0
             * occupationName : 服务行业
             * phone : 18153527447
             * province : 山东省
             * sex : 1
             */

            private int accountId;
            private String age;
            private String area;
            private String autograph;
            private String birthDate;
            private String city;
            private String country;
            private String headIcon;
            private int id;
            private String invitationCode;
            private String name;
            private String nickname;
            private String occupation;
            private String occupationName;
            private String phone;
            private String province;

            public String getIdNum() {
                return idNumber;
            }

            public void setIdNum(String idNum) {
                this.idNumber = idNum;
            }

            private String idNumber;
            private int sex;

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAutograph() {
                return autograph;
            }

            public void setAutograph(String autograph) {
                this.autograph = autograph;
            }

            public String getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(String birthDate) {
                this.birthDate = birthDate;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getHeadIcon() {
                return headIcon;
            }

            public void setHeadIcon(String headIcon) {
                this.headIcon = headIcon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getOccupationName() {
                return occupationName;
            }

            public void setOccupationName(String occupationName) {
                this.occupationName = occupationName;
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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }
        }
    }
}
