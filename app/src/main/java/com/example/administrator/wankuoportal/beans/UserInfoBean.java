package com.example.administrator.wankuoportal.beans;

public class UserInfoBean {

    /**
     * code : 0
     * data : {"aaccountAuthorize":{"accountId":836,"advertGold":0,"answerGold":0,"diamonds":3600000,"id":659,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":102,"isServiceProvider":0,"limitGold":210,"memberLevel":0,"money":99.98,"nolimitGold":0,"partnerLevel":0,"selTel":0,"sellerLevel":0,"shopEarnestMoney":0,"shoppingMoney":360000},"aaccountInfo":{"accountId":836,"age":"26","area":"福山区","autograph":"qqq","birthDate":"1992-07-07","city":"烟台市","country":"中国","headIcon":"b6213b21e41b4505a081e12970ebae71","id":8149,"idNumber":"","invitationCode":"WnM7AIvCUK","name":"","nickname":"过来Dave","occupation":0,"occupationName":"采矿能源","phone":"18762671112","province":"山东省","sex":1},"usercount":605}
     */

    private int code;
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

    public static class DataBean {
        /**
         * aaccountAuthorize : {"accountId":836,"advertGold":0,"answerGold":0,"diamonds":3600000,"id":659,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":102,"isServiceProvider":0,"limitGold":210,"memberLevel":0,"money":99.98,"nolimitGold":0,"partnerLevel":0,"selTel":0,"sellerLevel":0,"shopEarnestMoney":0,"shoppingMoney":360000}
         * aaccountInfo : {"accountId":836,"age":"26","area":"福山区","autograph":"qqq","birthDate":"1992-07-07","city":"烟台市","country":"中国","headIcon":"b6213b21e41b4505a081e12970ebae71","id":8149,"idNumber":"","invitationCode":"WnM7AIvCUK","name":"","nickname":"过来Dave","occupation":0,"occupationName":"采矿能源","phone":"18762671112","province":"山东省","sex":1}
         * usercount : 605
         */

        private AaccountAuthorizeBean aaccountAuthorize;
        private AaccountInfoBean aaccountInfo;
        private int usercount;

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

        public int getUsercount() {
            return usercount;
        }

        public void setUsercount(int usercount) {
            this.usercount = usercount;
        }

        public static class AaccountAuthorizeBean {
            /**
             * accountId : 836
             * advertGold : 0
             * answerGold : 0
             * diamonds : 3600000
             * id : 659
             * isAdvertiser : 0
             * isEmployer : 1
             * isMediaOwner : 0
             * isOrdinaryMember : 102
             * isServiceProvider : 0
             * limitGold : 210
             * memberLevel : 0
             * money : 99.98
             * nolimitGold : 0
             * partnerLevel : 0
             * selTel : 0
             * sellerLevel : 0
             * shopEarnestMoney : 0
             * shoppingMoney : 360000
             */

            private int accountId;
            private double advertGold;
            private double answerGold;
            private int diamonds;
            private int id;
            private int isAdvertiser;
            private int isEmployer;
            private int isMediaOwner;
            private int isOrdinaryMember;
            private int isServiceProvider;
            private double limitGold;
            private int memberLevel;
            private double money;
            private double nolimitGold;
            private int partnerLevel;
            private int selTel;
            private int sellerLevel;
            private double shopEarnestMoney;
            private double shoppingMoney;

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public double getAdvertGold() {
                return advertGold;
            }

            public void setAdvertGold(double advertGold) {
                this.advertGold = advertGold;
            }

            public double getAnswerGold() {
                return answerGold;
            }

            public void setAnswerGold(double answerGold) {
                this.answerGold = answerGold;
            }

            public int getDiamonds() {
                return diamonds;
            }

            public void setDiamonds(int diamonds) {
                this.diamonds = diamonds;
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

            public double getLimitGold() {
                return limitGold;
            }

            public void setLimitGold(double limitGold) {
                this.limitGold = limitGold;
            }

            public int getMemberLevel() {
                return memberLevel;
            }

            public void setMemberLevel(int memberLevel) {
                this.memberLevel = memberLevel;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getNolimitGold() {
                return nolimitGold;
            }

            public void setNolimitGold(double nolimitGold) {
                this.nolimitGold = nolimitGold;
            }

            public int getPartnerLevel() {
                return partnerLevel;
            }

            public void setPartnerLevel(int partnerLevel) {
                this.partnerLevel = partnerLevel;
            }

            public int getSelTel() {
                return selTel;
            }

            public void setSelTel(int selTel) {
                this.selTel = selTel;
            }

            public int getSellerLevel() {
                return sellerLevel;
            }

            public void setSellerLevel(int sellerLevel) {
                this.sellerLevel = sellerLevel;
            }

            public double getShopEarnestMoney() {
                return shopEarnestMoney;
            }

            public void setShopEarnestMoney(double shopEarnestMoney) {
                this.shopEarnestMoney = shopEarnestMoney;
            }

            public double getShoppingMoney() {
                return shoppingMoney;
            }

            public void setShoppingMoney(double shoppingMoney) {
                this.shoppingMoney = shoppingMoney;
            }
        }

        public static class AaccountInfoBean {
            /**
             * accountId : 836
             * age : 26
             * area : 福山区
             * autograph : qqq
             * birthDate : 1992-07-07
             * city : 烟台市
             * country : 中国
             * headIcon : b6213b21e41b4505a081e12970ebae71
             * id : 8149
             * idNumber :
             * invitationCode : WnM7AIvCUK
             * name :
             * nickname : 过来Dave
             * occupation : 0
             * occupationName : 采矿能源
             * phone : 18762671112
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
            private String idNumber;
            private String invitationCode;
            private String name;
            private String nickname;
            private int occupation;
            private String occupationName;
            private String phone;
            private String province;
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

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
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

            public int getOccupation() {
                return occupation;
            }

            public void setOccupation(int occupation) {
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
