package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30 0030.
 */

public class Gettopline {


    /**
     * code : 0
     * datas : [{"id":10,"nacigationQuestion":"测试公告1","nacigationTypeId":8}]
     */

    private int code;
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

    public static class DatasBean {
        /**
         * id : 10
         * nacigationQuestion : 测试公告1
         * nacigationTypeId : 8
         */

        private int id;
        private String nacigationQuestion;
        private int nacigationTypeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNacigationQuestion() {
            return nacigationQuestion;
        }

        public void setNacigationQuestion(String nacigationQuestion) {
            this.nacigationQuestion = nacigationQuestion;
        }

        public int getNacigationTypeId() {
            return nacigationTypeId;
        }

        public void setNacigationTypeId(int nacigationTypeId) {
            this.nacigationTypeId = nacigationTypeId;
        }
    }
}
