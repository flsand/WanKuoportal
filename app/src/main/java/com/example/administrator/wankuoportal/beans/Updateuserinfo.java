package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class Updateuserinfo {


    /**
     * code : 0
     * data : {"aaccountAuthorize":{"accountId":188,"id":25,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":0,"isServiceProvider":0,"limitGold":0,"nolimitGold":0},"aaccountInfo":{"accountId":188,"age":"","area":"福山区","autograph":"","birthDate":"1994-07-07","city":"烟台市","country":"中国","headIcon":"","id":7540,"invitationCode":"","name":"吕道欣","nickname":"胡说","occupation":0,"phone":"18153527447","province":"山东省","sex":1}}
     * msg : 修改成功
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
         * aaccountAuthorize : {"accountId":188,"id":25,"isAdvertiser":0,"isEmployer":1,"isMediaOwner":0,"isOrdinaryMember":0,"isServiceProvider":0,"limitGold":0,"nolimitGold":0}
         * aaccountInfo : {"accountId":188,"age":"","area":"福山区","autograph":"","birthDate":"1994-07-07","city":"烟台市","country":"中国","headIcon":"","id":7540,"invitationCode":"","name":"吕道欣","nickname":"胡说","occupation":0,"phone":"18153527447","province":"山东省","sex":1}
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

        public static class AaccountAuthorizeBean {
            /**
             * accountId : 188
             * id : 25
             * isAdvertiser : 0
             * isEmployer : 1
             * isMediaOwner : 0
             * isOrdinaryMember : 0
             * isServiceProvider : 0
             * limitGold : 0
             * nolimitGold : 0
             */

            private int accountId;
            private int id;
            private int isAdvertiser;
            private int isEmployer;
            private int isMediaOwner;
            private int isOrdinaryMember;
            private int isServiceProvider;
            private int limitGold;
            private int nolimitGold;

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

            public int getLimitGold() {
                return limitGold;
            }

            public void setLimitGold(int limitGold) {
                this.limitGold = limitGold;
            }

            public int getNolimitGold() {
                return nolimitGold;
            }

            public void setNolimitGold(int nolimitGold) {
                this.nolimitGold = nolimitGold;
            }
        }

        public static class AaccountInfoBean {
            /**
             * accountId : 188
             * age :
             * area : 福山区
             * autograph :
             * birthDate : 1994-07-07
             * city : 烟台市
             * country : 中国
             * headIcon :
             * id : 7540
             * invitationCode :
             * name : 吕道欣
             * nickname : 胡说
             * occupation : 0
             * phone : 18153527447
             * province : 山东省
             * sex : 1  1是女  2是男
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
