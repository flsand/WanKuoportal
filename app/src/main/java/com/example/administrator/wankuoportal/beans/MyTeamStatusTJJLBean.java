package com.example.administrator.wankuoportal.beans;

public class MyTeamStatusTJJLBean {

    /**
     * code : 0
     * data : {"glje":"0.00","tjje":"0.00","tjzr":"22","jlze":"0.00"}
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
         * glje : 0.00
         * tjje : 0.00
         * tjzr : 22
         * jlze : 0.00
         */

        private String glje;
        private String tjje;
        private String tjzr;
        private String jlze;

        public String getGlje() {
            return glje;
        }

        public void setGlje(String glje) {
            this.glje = glje;
        }

        public String getTjje() {
            return tjje;
        }

        public void setTjje(String tjje) {
            this.tjje = tjje;
        }

        public String getTjzr() {
            return tjzr;
        }

        public void setTjzr(String tjzr) {
            this.tjzr = tjzr;
        }

        public String getJlze() {
            return jlze;
        }

        public void setJlze(String jlze) {
            this.jlze = jlze;
        }
    }
}
