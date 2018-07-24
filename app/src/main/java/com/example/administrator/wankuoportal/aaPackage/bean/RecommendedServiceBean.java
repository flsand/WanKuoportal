package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/31.
 *     desc    :
 * </pre>
 */
public class RecommendedServiceBean extends DBVO {


    private AgoodBean agood;
    private AshopDataBean ashopData;

    public AgoodBean getAgood() {
        return agood;
    }

    public void setAgood(AgoodBean agood) {
        this.agood = agood;
    }

    public AshopDataBean getAshopData() {
        return ashopData;
    }

    public void setAshopData(AshopDataBean ashopData) {
        this.ashopData = ashopData;
    }

    public static class AgoodBean extends DBVO {
        /**
         * accountId : 200
         * appHtml :
         * content : 白色
         * endEditTime :
         * examineAccountId : 0
         * examineTime : 2017-10-07 16:51:30
         * id : 11
         * img : http://192.168.1.198:8080/wankuoportal/img/downloadimage/88fd8dbe543343b08ebe3416e41a2806
         * img1 : 88fd8dbe543343b08ebe3416e41a2806
         * img2 : 88fd8dbe543343b08ebe3416e41a2806
         * img3 : 88fd8dbe543343b08ebe3416e41a2806
         * img4 : 88fd8dbe543343b08ebe3416e41a2806
         * isExample : 1
         * labelId : 2818
         * name : 笔记本电脑11
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

    public static class AshopDataBean extends DBVO {
        /**
         * ability : 擅长技能
         * accountId : 200
         * advantageImg : 38d15c4d2fe747da9b57f2ab8c78a922
         * advantageIntro : 优势简介
         * city : 扬州市
         * evaluate : 0
         * examineAccountId : 158
         * examineTime : 2017-11-01 09:52:18
         * id : 3
         * img1 : 902df0ed86b64a99b51383d2aa3d0b0c
         * img2 : 08e42c01c3b34ae78f0f4d02a81343d4
         * img3 : 08e42c01c3b34ae78f0f4d02a81343d4
         * img4 : 08e42c01c3b34ae78f0f4d02a81343d4
         * img5 : 332f6d605a2c45009c18cb44de06d2d6
         * intro : 公司简介
         * level : 1
         * linkman :  宋英才
         * mobMenuName : mod测试
         * mobile : 韩国jgf
         * pcMenuName : PC测试名称
         * province : 江苏省
         * qq : 940303356
         * reason : 的股份然而个人体育教育软件体验
         * salesVolume : 0
         * shopName : 美团外卖
         * state : 3
         * teamIntro : 团队简介
         * type : 2
         */

        private String ability;
        private int accountId;
        private String advantageImg;
        private String advantageIntro;
        private String city;
        private int evaluate;
        private int examineAccountId;
        private String examineTime;
        private int id;
        private String img1;
        private String img2;
        private String img3;
        private String img4;
        private String img5;
        private String intro;
        private int level;
        private String linkman;
        private String mobMenuName;
        private String mobile;
        private String pcMenuName;
        private String province;
        private String qq;
        private String reason;
        private int salesVolume;
        private String shopName;
        private int state;
        private String teamIntro;
        private int type;

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

        public int getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(int evaluate) {
            this.evaluate = evaluate;
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

        public String getImg5() {
            return img5;
        }

        public void setImg5(String img5) {
            this.img5 = img5;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
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

        public String getPcMenuName() {
            return pcMenuName;
        }

        public void setPcMenuName(String pcMenuName) {
            this.pcMenuName = pcMenuName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTeamIntro() {
            return teamIntro;
        }

        public void setTeamIntro(String teamIntro) {
            this.teamIntro = teamIntro;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
