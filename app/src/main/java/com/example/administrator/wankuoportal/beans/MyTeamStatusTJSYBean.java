package com.example.administrator.wankuoportal.beans;

public class MyTeamStatusTJSYBean {

    /**
     * code : 0
     * data : {"tjsy":"0.00","ztsy":"0.00","dpzs":"2","tjzr":"22"}
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
         * tjsy : 0.00
         * ztsy : 0.00
         * dpzs : 2
         * tjzr : 22
         */

        private String tjsy;
        private String ztsy;
        private String dpzs;
        private String tjzr;

        public String getTjsy() {
            return tjsy;
        }

        public void setTjsy(String tjsy) {
            this.tjsy = tjsy;
        }

        public String getZtsy() {
            return ztsy;
        }

        public void setZtsy(String ztsy) {
            this.ztsy = ztsy;
        }

        public String getDpzs() {
            return dpzs;
        }

        public void setDpzs(String dpzs) {
            this.dpzs = dpzs;
        }

        public String getTjzr() {
            return tjzr;
        }

        public void setTjzr(String tjzr) {
            this.tjzr = tjzr;
        }
    }
}
