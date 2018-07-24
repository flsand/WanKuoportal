package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class ShopAboutUs {


    /**
     * code : 0
     * data : {"aPersonZone":{"id":13,"personImg":"edb919d4843a4db29198aad15511dd99","personImg2":"63fad87c458842f68f245f65a02dc85d","personImg3":" ","personIntro":"阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬","personIntro2":"设法将发送方就是","personIntro3":"","personJob":"研发经理","personJob2":"技术开发","personJob3":"","personName":"李想","personName2":"宋英才","personName3":"","shopId":3},"info":{"ability":"擅长技能","accountId":200,"advantageImg":"38d15c4d2fe747da9b57f2ab8c78a922","advantageIntro":"优势简介","city":"江苏省扬州市","earnestMoney":"0","id":3,"img1":"5a05dc5139b94afaa7835735383353f4","img2":"","intro":"公司简介","mobMenuName":"mod测试","mobile":"韩国jgf","money":"0","photoImg1":"902df0ed86b64a99b51383d2aa3d0b0c","photoImg2":"08e42c01c3b34ae78f0f4d02a81343d4","photoImg3":"08e42c01c3b34ae78f0f4d02a81343d4","photoImg4":"08e42c01c3b34ae78f0f4d02a81343d4","photoImg5":"332f6d605a2c45009c18cb44de06d2d6","saleNumber":"0","shopLevel":"普通会员","shopName":"美团外卖","teamIntro":"团队简介","type":"工作室"}}
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
         * aPersonZone : {"id":13,"personImg":"edb919d4843a4db29198aad15511dd99","personImg2":"63fad87c458842f68f245f65a02dc85d","personImg3":" ","personIntro":"阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬","personIntro2":"设法将发送方就是","personIntro3":"","personJob":"研发经理","personJob2":"技术开发","personJob3":"","personName":"李想","personName2":"宋英才","personName3":"","shopId":3}
         * info : {"ability":"擅长技能","accountId":200,"advantageImg":"38d15c4d2fe747da9b57f2ab8c78a922","advantageIntro":"优势简介","city":"江苏省扬州市","earnestMoney":"0","id":3,"img1":"5a05dc5139b94afaa7835735383353f4","img2":"","intro":"公司简介","mobMenuName":"mod测试","mobile":"韩国jgf","money":"0","photoImg1":"902df0ed86b64a99b51383d2aa3d0b0c","photoImg2":"08e42c01c3b34ae78f0f4d02a81343d4","photoImg3":"08e42c01c3b34ae78f0f4d02a81343d4","photoImg4":"08e42c01c3b34ae78f0f4d02a81343d4","photoImg5":"332f6d605a2c45009c18cb44de06d2d6","saleNumber":"0","shopLevel":"普通会员","shopName":"美团外卖","teamIntro":"团队简介","type":"工作室"}
         */

        private APersonZoneBean aPersonZone;
        private InfoBean info;

        public APersonZoneBean getAPersonZone() {
            return aPersonZone;
        }

        public void setAPersonZone(APersonZoneBean aPersonZone) {
            this.aPersonZone = aPersonZone;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class APersonZoneBean {
            /**
             * id : 13
             * personImg : edb919d4843a4db29198aad15511dd99
             * personImg2 : 63fad87c458842f68f245f65a02dc85d
             * personImg3 :
             * personIntro : 阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬
             * personIntro2 : 设法将发送方就是
             * personIntro3 :
             * personJob : 研发经理
             * personJob2 : 技术开发
             * personJob3 :
             * personName : 李想
             * personName2 : 宋英才
             * personName3 :
             * shopId : 3
             */

            private int id;
            private String personImg;
            private String personImg2;
            private String personImg3;
            private String personIntro;
            private String personIntro2;
            private String personIntro3;
            private String personJob;
            private String personJob2;
            private String personJob3;
            private String personName;
            private String personName2;
            private String personName3;
            private int shopId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPersonImg() {
                return personImg;
            }

            public void setPersonImg(String personImg) {
                this.personImg = personImg;
            }

            public String getPersonImg2() {
                return personImg2;
            }

            public void setPersonImg2(String personImg2) {
                this.personImg2 = personImg2;
            }

            public String getPersonImg3() {
                return personImg3;
            }

            public void setPersonImg3(String personImg3) {
                this.personImg3 = personImg3;
            }

            public String getPersonIntro() {
                return personIntro;
            }

            public void setPersonIntro(String personIntro) {
                this.personIntro = personIntro;
            }

            public String getPersonIntro2() {
                return personIntro2;
            }

            public void setPersonIntro2(String personIntro2) {
                this.personIntro2 = personIntro2;
            }

            public String getPersonIntro3() {
                return personIntro3;
            }

            public void setPersonIntro3(String personIntro3) {
                this.personIntro3 = personIntro3;
            }

            public String getPersonJob() {
                return personJob;
            }

            public void setPersonJob(String personJob) {
                this.personJob = personJob;
            }

            public String getPersonJob2() {
                return personJob2;
            }

            public void setPersonJob2(String personJob2) {
                this.personJob2 = personJob2;
            }

            public String getPersonJob3() {
                return personJob3;
            }

            public void setPersonJob3(String personJob3) {
                this.personJob3 = personJob3;
            }

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
            }

            public String getPersonName2() {
                return personName2;
            }

            public void setPersonName2(String personName2) {
                this.personName2 = personName2;
            }

            public String getPersonName3() {
                return personName3;
            }

            public void setPersonName3(String personName3) {
                this.personName3 = personName3;
            }

            public int getShopId() {
                return shopId;
            }

            public void setShopId(int shopId) {
                this.shopId = shopId;
            }
        }

        public static class InfoBean {
            /**
             * ability : 擅长技能
             * accountId : 200
             * advantageImg : 38d15c4d2fe747da9b57f2ab8c78a922
             * advantageIntro : 优势简介
             * city : 江苏省扬州市
             * earnestMoney : 0
             * id : 3
             * img1 : 5a05dc5139b94afaa7835735383353f4
             * img2 :
             * intro : 公司简介
             * mobMenuName : mod测试
             * mobile : 韩国jgf
             * money : 0
             * photoImg1 : 902df0ed86b64a99b51383d2aa3d0b0c
             * photoImg2 : 08e42c01c3b34ae78f0f4d02a81343d4
             * photoImg3 : 08e42c01c3b34ae78f0f4d02a81343d4
             * photoImg4 : 08e42c01c3b34ae78f0f4d02a81343d4
             * photoImg5 : 332f6d605a2c45009c18cb44de06d2d6
             * saleNumber : 0
             * shopLevel : 普通会员
             * shopName : 美团外卖
             * teamIntro : 团队简介
             * type : 工作室
             */

            private String ability;
            private int accountId;
            private String advantageImg;
            private String advantageIntro;
            private String city;
            private String earnestMoney;
            private int id;
            private String img1;
            private String img2;
            private String intro;
            private String mobMenuName;
            private String mobile;
            private String money;
            private String photoImg1;
            private String photoImg2;
            private String photoImg3;
            private String photoImg4;
            private String photoImg5;
            private String saleNumber;
            private String shopLevel;
            private String shopName;
            private String teamIntro;
            private String type;

            public String getAbility() {
                return ability;
            }

            public void setAbility(String ability) {
                this.ability = ability;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public String getAdvantageImg() {
                return advantageImg;
            }

            public void setAdvantageImg(String advantageImg) {
                this.advantageImg = advantageImg;
            }

            public String getAdvantageIntro() {
                return advantageIntro;
            }

            public void setAdvantageIntro(String advantageIntro) {
                this.advantageIntro = advantageIntro;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getEarnestMoney() {
                return earnestMoney;
            }

            public void setEarnestMoney(String earnestMoney) {
                this.earnestMoney = earnestMoney;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getMobMenuName() {
                return mobMenuName;
            }

            public void setMobMenuName(String mobMenuName) {
                this.mobMenuName = mobMenuName;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getPhotoImg1() {
                return photoImg1;
            }

            public void setPhotoImg1(String photoImg1) {
                this.photoImg1 = photoImg1;
            }

            public String getPhotoImg2() {
                return photoImg2;
            }

            public void setPhotoImg2(String photoImg2) {
                this.photoImg2 = photoImg2;
            }

            public String getPhotoImg3() {
                return photoImg3;
            }

            public void setPhotoImg3(String photoImg3) {
                this.photoImg3 = photoImg3;
            }

            public String getPhotoImg4() {
                return photoImg4;
            }

            public void setPhotoImg4(String photoImg4) {
                this.photoImg4 = photoImg4;
            }

            public String getPhotoImg5() {
                return photoImg5;
            }

            public void setPhotoImg5(String photoImg5) {
                this.photoImg5 = photoImg5;
            }

            public String getSaleNumber() {
                return saleNumber;
            }

            public void setSaleNumber(String saleNumber) {
                this.saleNumber = saleNumber;
            }

            public String getShopLevel() {
                return shopLevel;
            }

            public void setShopLevel(String shopLevel) {
                this.shopLevel = shopLevel;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getTeamIntro() {
                return teamIntro;
            }

            public void setTeamIntro(String teamIntro) {
                this.teamIntro = teamIntro;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
