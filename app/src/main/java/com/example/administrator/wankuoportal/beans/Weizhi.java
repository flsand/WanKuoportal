package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10 0010.
 */

public class Weizhi implements Serializable{


    /**
     * result : {"addressComponent":{"adcode":"110108","city":"北京市","country":"中国","country_code":0,"direction":"附近","distance":"7","district":"海淀区","province":"北京市","street":"中关村大街","street_number":"27号1101-08室"},"business":"中关村,人民大学,苏州街","cityCode":131,"formatted_address":"北京市海淀区中关村大街27号1101-08室","location":{"lat":39.98342407140365,"lng":116.32298699999993},"poiRegions":[],"pois":[{"addr":"北京北京海淀海淀区中关村大街27号（地铁海淀黄庄站A1","cp":"","direction":"内","distance":"0","name":"北京远景国际公寓(中关村店)","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"房地产","point":{"x":116.32294589160055,"y":39.983610361549296},"tag":"房地产","tel":"","uid":"35a08504cb51b1138733049d","zip":""},{"addr":"海淀区中关村北大街27号","cp":" ","direction":"附近","distance":"26","name":"中关村大厦","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"房地产","point":{"x":116.32288301021485,"y":39.98358962971618},"tag":"房地产;写字楼","tel":"","uid":"06d2dffdaef1b7ef88f15d04","zip":""},{"addr":"北京市海淀区中关村大街27号中关村大厦二层","cp":" ","direction":"附近","distance":"5","name":"眉州东坡酒楼(中关村店)","parent_poi":{"addr":"海淀区中关村北大街27号","direction":"附近","distance":"26","name":"中关村大厦","point":{"x":116.32288301021485,"y":39.98358962971618},"tag":"房地产;写字楼","uid":"06d2dffdaef1b7ef88f15d04"},"poiType":"美食","point":{"x":116.32293690854546,"y":39.98343759607948},"tag":"美食;中餐厅","tel":"","uid":"2c0bd6c57dbdd3b342ab9a8c","zip":""},{"addr":"海淀区中关村大街27号中关村大厦(近海淀医院)","cp":" ","direction":"附近","distance":"41","name":"中国建设银行(中关村支行)","parent_poi":{"addr":"海淀区中关村北大街27号","direction":"附近","distance":"26","name":"中关村大厦","point":{"x":116.32288301021485,"y":39.98358962971618},"tag":"房地产;写字楼","uid":"06d2dffdaef1b7ef88f15d04"},"poiType":"金融","point":{"x":116.32291894243525,"y":39.98370711002003},"tag":"金融;银行","tel":"","uid":"14076deea757edf402294f41","zip":""},{"addr":"中关村大街19号新中关大厦(近丹棱街)","cp":" ","direction":"东南","distance":"179","name":"新中关购物中心","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"购物","point":{"x":116.32175114527229,"y":39.9842184924999},"tag":"购物;购物中心","tel":"","uid":"8d96925c0372e65cc1f1cf7f","zip":""},{"addr":"海淀区新中关购物中心B101a","cp":" ","direction":"东南","distance":"151","name":"Donhot当哈驴火(新中关店)","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"自然地物","point":{"x":116.32246080662517,"y":39.98439125597929},"tag":"自然地物;山峰","tel":"","uid":"806a4ec9baab572101897285","zip":""},{"addr":"北京市海淀区中关村大街29号","cp":" ","direction":"东北","distance":"147","name":"北京市海淀医院","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"医疗","point":{"x":116.32195775553959,"y":39.98278108327827},"tag":"医疗;综合医院","tel":"","uid":"fa01e9371a040053774ff1ca","zip":""},{"addr":"中科院南路甲4号","cp":" ","direction":"西","distance":"136","name":"黄庄社区-菜站","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"购物","point":{"x":116.32419453625943,"y":39.98360345093896},"tag":"购物;集市","tel":"","uid":"bd6bf3b7b86225c4eb12e471","zip":""},{"addr":"北京市海淀区中关村大街19号","cp":" ","direction":"东南","distance":"132","name":"新中关大厦-C座","parent_poi":{"addr":"中关村大街19号","direction":"东南","distance":"186","name":"新中关大厦","point":{"x":116.32193978942938,"y":39.98442580862234},"tag":"房地产;写字楼","uid":"d687662c0a24ffaffa42b2cc"},"poiType":"房地产","point":{"x":116.32237097607417,"y":39.98421158195154},"tag":"房地产;写字楼","tel":"","uid":"bb566acf61f5a07b1b11d59e","zip":""},{"addr":"北京市海淀区中关村大街28号","cp":" ","direction":"西北","distance":"229","name":"海淀剧院","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"休闲娱乐","point":{"x":116.32476046873072,"y":39.98262213711767},"tag":"休闲娱乐;剧院","tel":"","uid":"edd64ce1a6d799913ee231b3","zip":""}],"roads":[],"sematic_description":"北京远景国际公寓(中关村店)内0米"}
     * status : 0
     */

    private ResultBean result;
    private int status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBean implements Serializable{
        /**
         * addressComponent : {"adcode":"110108","city":"北京市","country":"中国","country_code":0,"direction":"附近","distance":"7","district":"海淀区","province":"北京市","street":"中关村大街","street_number":"27号1101-08室"}
         * business : 中关村,人民大学,苏州街
         * cityCode : 131
         * formatted_address : 北京市海淀区中关村大街27号1101-08室
         * location : {"lat":39.98342407140365,"lng":116.32298699999993}
         * poiRegions : []
         * pois : [{"addr":"北京北京海淀海淀区中关村大街27号（地铁海淀黄庄站A1","cp":"","direction":"内","distance":"0","name":"北京远景国际公寓(中关村店)","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"房地产","point":{"x":116.32294589160055,"y":39.983610361549296},"tag":"房地产","tel":"","uid":"35a08504cb51b1138733049d","zip":""},{"addr":"海淀区中关村北大街27号","cp":" ","direction":"附近","distance":"26","name":"中关村大厦","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"房地产","point":{"x":116.32288301021485,"y":39.98358962971618},"tag":"房地产;写字楼","tel":"","uid":"06d2dffdaef1b7ef88f15d04","zip":""},{"addr":"北京市海淀区中关村大街27号中关村大厦二层","cp":" ","direction":"附近","distance":"5","name":"眉州东坡酒楼(中关村店)","parent_poi":{"addr":"海淀区中关村北大街27号","direction":"附近","distance":"26","name":"中关村大厦","point":{"x":116.32288301021485,"y":39.98358962971618},"tag":"房地产;写字楼","uid":"06d2dffdaef1b7ef88f15d04"},"poiType":"美食","point":{"x":116.32293690854546,"y":39.98343759607948},"tag":"美食;中餐厅","tel":"","uid":"2c0bd6c57dbdd3b342ab9a8c","zip":""},{"addr":"海淀区中关村大街27号中关村大厦(近海淀医院)","cp":" ","direction":"附近","distance":"41","name":"中国建设银行(中关村支行)","parent_poi":{"addr":"海淀区中关村北大街27号","direction":"附近","distance":"26","name":"中关村大厦","point":{"x":116.32288301021485,"y":39.98358962971618},"tag":"房地产;写字楼","uid":"06d2dffdaef1b7ef88f15d04"},"poiType":"金融","point":{"x":116.32291894243525,"y":39.98370711002003},"tag":"金融;银行","tel":"","uid":"14076deea757edf402294f41","zip":""},{"addr":"中关村大街19号新中关大厦(近丹棱街)","cp":" ","direction":"东南","distance":"179","name":"新中关购物中心","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"购物","point":{"x":116.32175114527229,"y":39.9842184924999},"tag":"购物;购物中心","tel":"","uid":"8d96925c0372e65cc1f1cf7f","zip":""},{"addr":"海淀区新中关购物中心B101a","cp":" ","direction":"东南","distance":"151","name":"Donhot当哈驴火(新中关店)","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"自然地物","point":{"x":116.32246080662517,"y":39.98439125597929},"tag":"自然地物;山峰","tel":"","uid":"806a4ec9baab572101897285","zip":""},{"addr":"北京市海淀区中关村大街29号","cp":" ","direction":"东北","distance":"147","name":"北京市海淀医院","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"医疗","point":{"x":116.32195775553959,"y":39.98278108327827},"tag":"医疗;综合医院","tel":"","uid":"fa01e9371a040053774ff1ca","zip":""},{"addr":"中科院南路甲4号","cp":" ","direction":"西","distance":"136","name":"黄庄社区-菜站","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"购物","point":{"x":116.32419453625943,"y":39.98360345093896},"tag":"购物;集市","tel":"","uid":"bd6bf3b7b86225c4eb12e471","zip":""},{"addr":"北京市海淀区中关村大街19号","cp":" ","direction":"东南","distance":"132","name":"新中关大厦-C座","parent_poi":{"addr":"中关村大街19号","direction":"东南","distance":"186","name":"新中关大厦","point":{"x":116.32193978942938,"y":39.98442580862234},"tag":"房地产;写字楼","uid":"d687662c0a24ffaffa42b2cc"},"poiType":"房地产","point":{"x":116.32237097607417,"y":39.98421158195154},"tag":"房地产;写字楼","tel":"","uid":"bb566acf61f5a07b1b11d59e","zip":""},{"addr":"北京市海淀区中关村大街28号","cp":" ","direction":"西北","distance":"229","name":"海淀剧院","parent_poi":{"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""},"poiType":"休闲娱乐","point":{"x":116.32476046873072,"y":39.98262213711767},"tag":"休闲娱乐;剧院","tel":"","uid":"edd64ce1a6d799913ee231b3","zip":""}]
         * roads : []
         * sematic_description : 北京远景国际公寓(中关村店)内0米
         */

        private AddressComponentBean addressComponent;
        private String business;
        private int cityCode;
        private String formatted_address;
        private LocationBean location;
        private String sematic_description;
        private List<?> poiRegions;
        private List<PoisBean> pois;
        private List<?> roads;

        public AddressComponentBean getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponentBean addressComponent) {
            this.addressComponent = addressComponent;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public int getCityCode() {
            return cityCode;
        }

        public void setCityCode(int cityCode) {
            this.cityCode = cityCode;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getSematic_description() {
            return sematic_description;
        }

        public void setSematic_description(String sematic_description) {
            this.sematic_description = sematic_description;
        }

        public List<?> getPoiRegions() {
            return poiRegions;
        }

        public void setPoiRegions(List<?> poiRegions) {
            this.poiRegions = poiRegions;
        }

        public List<PoisBean> getPois() {
            return pois;
        }

        public void setPois(List<PoisBean> pois) {
            this.pois = pois;
        }

        public List<?> getRoads() {
            return roads;
        }

        public void setRoads(List<?> roads) {
            this.roads = roads;
        }

        public static class AddressComponentBean implements Serializable{
            /**
             * adcode : 110108
             * city : 北京市
             * country : 中国
             * country_code : 0
             * direction : 附近
             * distance : 7
             * district : 海淀区
             * province : 北京市
             * street : 中关村大街
             * street_number : 27号1101-08室
             */

            private String adcode;
            private String city;
            private String country;
            private int country_code;
            private String direction;
            private String distance;
            private String district;
            private String province;
            private String street;
            private String street_number;

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
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

            public int getCountry_code() {
                return country_code;
            }

            public void setCountry_code(int country_code) {
                this.country_code = country_code;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }
        }

        public static class LocationBean implements Serializable{
            /**
             * lat : 39.98342407140365
             * lng : 116.32298699999993
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }

        public static class PoisBean implements Serializable{
            /**
             * addr : 北京北京海淀海淀区中关村大街27号（地铁海淀黄庄站A1
             * cp :
             * direction : 内
             * distance : 0
             * name : 北京远景国际公寓(中关村店)
             * parent_poi : {"addr":"","direction":"","distance":"","name":"","point":{"x":0,"y":0},"tag":"","uid":""}
             * poiType : 房地产
             * point : {"x":116.32294589160055,"y":39.983610361549296}
             * tag : 房地产
             * tel :
             * uid : 35a08504cb51b1138733049d
             * zip :
             */

            private String addr;
            private String cp;
            private String direction;
            private String distance;
            private String name;
            private ParentPoiBean parent_poi;
            private String poiType;
            private PointBeanX point;
            private String tag;
            private String tel;
            private String uid;
            private String zip;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCp() {
                return cp;
            }

            public void setCp(String cp) {
                this.cp = cp;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ParentPoiBean getParent_poi() {
                return parent_poi;
            }

            public void setParent_poi(ParentPoiBean parent_poi) {
                this.parent_poi = parent_poi;
            }

            public String getPoiType() {
                return poiType;
            }

            public void setPoiType(String poiType) {
                this.poiType = poiType;
            }

            public PointBeanX getPoint() {
                return point;
            }

            public void setPoint(PointBeanX point) {
                this.point = point;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public static class ParentPoiBean implements Serializable{
                /**
                 * addr :
                 * direction :
                 * distance :
                 * name :
                 * point : {"x":0,"y":0}
                 * tag :
                 * uid :
                 */

                private String addr;
                private String direction;
                private String distance;
                private String name;
                private PointBean point;
                private String tag;
                private String uid;

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                public String getDirection() {
                    return direction;
                }

                public void setDirection(String direction) {
                    this.direction = direction;
                }

                public String getDistance() {
                    return distance;
                }

                public void setDistance(String distance) {
                    this.distance = distance;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public PointBean getPoint() {
                    return point;
                }

                public void setPoint(PointBean point) {
                    this.point = point;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public static class PointBean implements Serializable{
                    /**
                     * x : 0.0
                     * y : 0.0
                     */

                    private double x;
                    private double y;

                    public double getX() {
                        return x;
                    }

                    public void setX(double x) {
                        this.x = x;
                    }

                    public double getY() {
                        return y;
                    }

                    public void setY(double y) {
                        this.y = y;
                    }
                }
            }

            public static class PointBeanX implements Serializable{
                /**
                 * x : 116.32294589160055
                 * y : 39.983610361549296
                 */

                private double x;
                private double y;

                public double getX() {
                    return x;
                }

                public void setX(double x) {
                    this.x = x;
                }

                public double getY() {
                    return y;
                }

                public void setY(double y) {
                    this.y = y;
                }
            }
        }
    }
}
