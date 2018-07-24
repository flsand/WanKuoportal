package com.example.administrator.wankuoportal.beans;

public class MyTeamStatusAllBean {

    /**
     * code : 0
     * data : {"tjzr":"0","syze":"0.00","jlze":"0.00","glzr":"1"}
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
         * tjzr : 0
         * syze : 0.00
         * jlze : 0.00
         * glzr : 1
         */

        private String tjzr;
        private String syze;
        private String jlze;
        private String glzr;

        public String getTjzr() {
            return tjzr;
        }

        public void setTjzr(String tjzr) {
            this.tjzr = tjzr;
        }

        public String getSyze() {
            return syze;
        }

        public void setSyze(String syze) {
            this.syze = syze;
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
