package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class Address implements Serializable {

    /**
     * code : 0
     * datas : [{"accountId":222,"address":"上谷郡一号楼二单元702","area":"福山区","cancel":0,"city":"烟台市","createTime":"2017-10-10 14:11:59","defaultFlay":0,"id":554,"name":"吕道欣","phone":"18153527447","province":"山东省"}]
     */

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private List<DatasBean> datas;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Serializable{
        /**
         * accountId : 222
         * address : 上谷郡一号楼二单元702
         * area : 福山区
         * cancel : 0
         * city : 烟台市
         * createTime : 2017-10-10 14:11:59
         * defaultFlay : 0
         * id : 554
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
