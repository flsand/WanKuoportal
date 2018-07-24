package com.example.administrator.wankuoportal.beans;

public class MyTeamStatusGLSYBean {

    /**
     * code : 0
     * data : {"ztsy":"0.00","dpzs":"2","glsy":"0.00","glzr":"3"}
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
         * ztsy : 0.00
         * dpzs : 2
         * glsy : 0.00
         * glzr : 3
         */

        private String ztsy;
        private String dpzs;
        private String glsy;
        private String glzr;

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

        public String getGlsy() {
            return glsy;
        }

        public void setGlsy(String glsy) {
            this.glsy = glsy;
        }

        public String getGlzr() {
            return glzr;
        }

        public void setGlzr(String glzr) {
            this.glzr = glzr;
        }
    }
}
