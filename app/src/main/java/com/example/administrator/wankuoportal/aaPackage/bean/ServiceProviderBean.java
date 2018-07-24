package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

import java.util.List;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/31.
 *     desc    :
 * </pre>
 */
public class ServiceProviderBean extends DBVO {


    private AshopDataBean ashopData;
    private InfoBean info;
    private List<LabellistBean> labellist;

    public AshopDataBean getAshopData() {
        return ashopData;
    }

    public void setAshopData(AshopDataBean ashopData) {
        this.ashopData = ashopData;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<LabellistBean> getLabellist() {
        return labellist;
    }

    public void setLabellist(List<LabellistBean> labellist) {
        this.labellist = labellist;
    }

    public static class AshopDataBean extends DBVO {
        /**
         * ability :
         * accountId : 208
         * advantageIntro :
         * city : 营口市
         * evaluate : 0
         * id : 7
         * intro :
         * level : 0
         * linkman : 王先生
         * mobile : 13181639297
         * province : 辽宁省
         * qq : 415918612
         * salesVolume : 0
         * shopName : 营口快传同城
         * state : 0
         * teamIntro :
         * type : 3
         */

        private String ability;
        private int accountId;
        private String advantageIntro;
        private String city;
        private int evaluate;
        private int id;
        private String intro;
        private int level;
        private String linkman;
        private String mobile;
        private String province;
        private String qq;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

    public static class InfoBean extends DBVO {
        /**
         * accountId : 0
         * id : 0
         * img1 :
         */

        private int accountId;
        private int id;
        private String img1;

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

        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }
    }

    public static class LabellistBean extends DBVO {
        /**
         * cancel : 0
         * id : 1201
         * img :
         * labelName : 综合网站
         * parentId : 1156
         * root : 0
         */

        private int cancel;
        private int id;
        private String img;
        private String labelName;
        private int parentId;
        private int root;

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
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

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getRoot() {
            return root;
        }

        public void setRoot(int root) {
            this.root = root;
        }
    }
}
