package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/4.
 *     desc    :
 * </pre>
 */
public class SignInBean extends DBVO {

    /**
     * aAccountAuthorize : {"accountId":538,"advertGold":0,"answerGold":20,"diamonds":0,"id":361,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":1,"isServiceProvider":0,"limitGold":200,"memberLevel":0,"money":0.03,"moneycreatetime":"2018-05-14","nolimitGold":0,"nomoney":0,"partnerLevel":0,"selTel":0,"sellerLevel":0,"shopEarnestMoney":0,"shoppingMoney":0}
     * aAccountInfo : {"accountId":538,"age":"27","area":"福山区","autograph":"去外婆","birthDate":"1991-07-07","city":"烟台市","country":"中国","headIcon":"2ffc94614fab4d11b31bbd27de5246f9","id":7851,"idNumber":"","invitationCode":"yaJxGVdiCH","name":"","nickname":"张三","occupation":0,"occupationName":"采矿能源","phone":"15553539021","province":"山东省","sex":1}
     */

    private AAccountAuthorizeBean aAccountAuthorize;
    private AAccountInfoBean aAccountInfo;

    public AAccountAuthorizeBean getAAccountAuthorize() {
        return aAccountAuthorize;
    }

    public void setAAccountAuthorize(AAccountAuthorizeBean aAccountAuthorize) {
        this.aAccountAuthorize = aAccountAuthorize;
    }

    public AAccountInfoBean getAAccountInfo() {
        return aAccountInfo;
    }

    public void setAAccountInfo(AAccountInfoBean aAccountInfo) {
        this.aAccountInfo = aAccountInfo;
    }

    public static class AAccountAuthorizeBean extends DBVO {
        /**
         * accountId : 538
         * advertGold : 0
         * answerGold : 20.0
         * diamonds : 0
         * id : 361
         * isAdvertiser : 0
         * isEmployer : 1
         * isMediaOwner : 0
         * isOrdinaryMember : 1
         * isServiceProvider : 0
         * limitGold : 200
         * memberLevel : 0
         * money : 0.03
         * moneycreatetime : 2018-05-14
         * nolimitGold : 0
         * nomoney : 0.0
         * partnerLevel : 0
         * selTel : 0
         * sellerLevel : 0
         * shopEarnestMoney : 0
         * shoppingMoney : 0
         */

        private int accountId;
        private double advertGold;
        private double answerGold;
        private double diamonds;
        private int id;
        private int isAdvertiser;
        private int isEmployer;
        private int isMediaOwner;
        private int isOrdinaryMember;
        private int isServiceProvider;
        private double limitGold;
        private int memberLevel;
        private double money;
        private String moneycreatetime;
        private double nolimitGold;
        private double nomoney;
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

        public double getDiamonds() {
            return diamonds;
        }

        public void setDiamonds(double diamonds) {
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

        public String getMoneycreatetime() {
            return moneycreatetime;
        }

        public void setMoneycreatetime(String moneycreatetime) {
            this.moneycreatetime = moneycreatetime;
        }

        public double getNolimitGold() {
            return nolimitGold;
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

        @Override
        public String toString() {
            return "AAccountAuthorizeBean{" +
                    "accountId=" + accountId +
                    ", advertGold=" + advertGold +
                    ", answerGold=" + answerGold +
                    ", diamonds=" + diamonds +
                    ", id=" + id +
                    ", isAdvertiser=" + isAdvertiser +
                    ", isEmployer=" + isEmployer +
                    ", isMediaOwner=" + isMediaOwner +
                    ", isOrdinaryMember=" + isOrdinaryMember +
                    ", isServiceProvider=" + isServiceProvider +
                    ", limitGold=" + limitGold +
                    ", memberLevel=" + memberLevel +
                    ", money=" + money +
                    ", moneycreatetime='" + moneycreatetime + '\'' +
                    ", nolimitGold=" + nolimitGold +
                    ", nomoney=" + nomoney +
                    ", partnerLevel=" + partnerLevel +
                    ", selTel=" + selTel +
                    ", sellerLevel=" + sellerLevel +
                    ", shopEarnestMoney=" + shopEarnestMoney +
                    ", shoppingMoney=" + shoppingMoney +
                    '}';
        }
    }

    public static class AAccountInfoBean extends DBVO {
        /**
         * accountId : 538
         * age : 27
         * area : 福山区
         * autograph : 去外婆
         * birthDate : 1991-07-07
         * city : 烟台市
         * country : 中国
         * headIcon : 2ffc94614fab4d11b31bbd27de5246f9
         * id : 7851
         * idNumber :
         * invitationCode : yaJxGVdiCH
         * name :
         * nickname : 张三
         * occupation : 0
         * occupationName : 采矿能源
         * phone : 15553539021
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

        @Override
        public String toString() {
            return "AAccountInfoBean{" +
                    "accountId=" + accountId +
                    ", age='" + age + '\'' +
                    ", area='" + area + '\'' +
                    ", autograph='" + autograph + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", headIcon='" + headIcon + '\'' +
                    ", id=" + id +
                    ", idNumber='" + idNumber + '\'' +
                    ", invitationCode='" + invitationCode + '\'' +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", occupation=" + occupation +
                    ", occupationName='" + occupationName + '\'' +
                    ", phone='" + phone + '\'' +
                    ", province='" + province + '\'' +
                    ", sex=" + sex +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SignInBean{" +
                "aAccountAuthorize=" + aAccountAuthorize +
                ", aAccountInfo=" + aAccountInfo +
                '}';
    }
}
