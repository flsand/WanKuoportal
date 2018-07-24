package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by lv on 2017/9/26 搜索服务结果bean.
 */

public class SearchFuWu {


    /**
     * code : 0
     * datas : [{"agood":{"accountId":200,"content":"","endEditTime":"2017-09-26 11:37:44","id":4,"img":"http://192.168.1.198:8080/wankuoportal/img/downloadimage/4fff483b926940aaa5b8e04a3359733d","img1":"bbf4a298f398411b9c812e246c5c54ad","isExample":0,"labelId":0,"name":"笔记本电脑","pcHtml":"<p>asfdsgdasgfds<\/p>","phoneDiscount":2.3,"pirce":3.22,"salesVolume":0,"shopId":3,"state":0,"type":0,"unit":"元"},"ashopData":{"ability":"电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的","accountId":200,"advantageImg":"38d15c4d2fe747da9b57f2ab8c78a922","advantageIntro":"阿斯蒂芬阿斯蒂芬阿斯蒂芬","city":"扬州市","evaluate":0,"id":3,"img1":"902df0ed86b64a99b51383d2aa3d0b0c","img2":"08e42c01c3b34ae78f0f4d02a81343d4","img5":"332f6d605a2c45009c18cb44de06d2d6","intro":"电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的","level":1,"linkman":" 宋英才","mobile":"韩国jgf","province":"江苏省","qq":"940303356","salesVolume":0,"shopName":"美团外卖","teamIntro":"阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬","type":2}}]
     * pageInfo : {"first":true,"last":true,"number":0,"numberOfElements":1,"size":15,"totalElements":1,"totalPages":1}
     */

    private int code;
    private PageInfoBean pageInfo;
    private List<DatasBean> datas;

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class PageInfoBean {
        /**
         * first : true
         * last : true
         * number : 0
         * numberOfElements : 1
         * size : 15
         * totalElements : 1
         * totalPages : 1
         */

        private boolean first;
        private boolean last;
        private int number;
        private int numberOfElements;
        private int size;
        private int totalElements;
        private int totalPages;

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

    public static class DatasBean {
        /**
         * agood : {"accountId":200,"content":"","endEditTime":"2017-09-26 11:37:44","id":4,"img":"http://192.168.1.198:8080/wankuoportal/img/downloadimage/4fff483b926940aaa5b8e04a3359733d","img1":"bbf4a298f398411b9c812e246c5c54ad","isExample":0,"labelId":0,"name":"笔记本电脑","pcHtml":"<p>asfdsgdasgfds<\/p>","phoneDiscount":2.3,"pirce":3.22,"salesVolume":0,"shopId":3,"state":0,"type":0,"unit":"元"}
         * ashopData : {"ability":"电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的","accountId":200,"advantageImg":"38d15c4d2fe747da9b57f2ab8c78a922","advantageIntro":"阿斯蒂芬阿斯蒂芬阿斯蒂芬","city":"扬州市","evaluate":0,"id":3,"img1":"902df0ed86b64a99b51383d2aa3d0b0c","img2":"08e42c01c3b34ae78f0f4d02a81343d4","img5":"332f6d605a2c45009c18cb44de06d2d6","intro":"电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的","level":1,"linkman":" 宋英才","mobile":"韩国jgf","province":"江苏省","qq":"940303356","salesVolume":0,"shopName":"美团外卖","teamIntro":"阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬","type":2}
         */

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

        public static class AgoodBean {
            /**
             * accountId : 200
             * content :
             * endEditTime : 2017-09-26 11:37:44
             * id : 4
             * img : http://192.168.1.198:8080/wankuoportal/img/downloadimage/4fff483b926940aaa5b8e04a3359733d
             * img1 : bbf4a298f398411b9c812e246c5c54ad
             * isExample : 0
             * labelId : 0
             * name : 笔记本电脑
             * pcHtml : <p>asfdsgdasgfds</p>
             * phoneDiscount : 2.3
             * pirce : 3.22
             * salesVolume : 0
             * shopId : 3
             * state : 0
             * type : 0
             * unit : 元
             */

            private int accountId;
            private String content;
            private String endEditTime;
            private int id;
            private String img;
            private String img1;
            private int isExample;
            private int labelId;
            private String name;
            private String pcHtml;
            private double phoneDiscount;
            private double pirce;
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

        public static class AshopDataBean {
            /**
             * ability : 电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的
             * accountId : 200
             * advantageImg : 38d15c4d2fe747da9b57f2ab8c78a922
             * advantageIntro : 阿斯蒂芬阿斯蒂芬阿斯蒂芬
             * city : 扬州市
             * evaluate : 0
             * id : 3
             * img1 : 902df0ed86b64a99b51383d2aa3d0b0c
             * img2 : 08e42c01c3b34ae78f0f4d02a81343d4
             * img5 : 332f6d605a2c45009c18cb44de06d2d6
             * intro : 电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的电饭锅的
             * level : 1
             * linkman :  宋英才
             * mobile : 韩国jgf
             * province : 江苏省
             * qq : 940303356
             * salesVolume : 0
             * shopName : 美团外卖
             * teamIntro : 阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬
             * type : 2
             */

            private String ability;
            private int accountId;
            private String advantageImg;
            private String advantageIntro;
            private String city;
            private int evaluate;
            private int id;
            private String img1;
            private String img2;
            private String img5;
            private String intro;
            private int level;
            private String linkman;
            private String mobile;
            private String province;
            private String qq;
            private int salesVolume;
            private String shopName;
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
}
