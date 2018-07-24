package com.example.administrator.wankuoportal.beans;

public class MyTeamStatusGLJLBean {

    /**
     * code : 0
     * data : {"glje":"0.00","tjje":"0.00","jlze":"0.00","glzr":"3"}
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
         * jlze : 0.00
         * glzr : 3
         */

        private String glje;
        private String tjje;
        private String jlze;
        private String glzr;

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

        public String getJlze() {
            return jlze;
        }

        public void setJlze(String jlze) {
            this.jlze = jlze;
        }

        public String getGlzr() {
            return glzr;
        }

        public void setGlzr(String glzr) {
            this.glzr = glzr;
        }
    }
}
