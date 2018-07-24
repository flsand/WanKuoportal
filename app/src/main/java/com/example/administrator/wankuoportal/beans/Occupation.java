package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class Occupation {

    /**
     * code : 0
     * datas : [{"cancel":0,"code":"1","content":"码农","createTime":"","dictionaryId":1,"id":1,"name":"occupation","nameInterpret":"职业"},{"cancel":0,"code":"2","content":"教师","createTime":"","dictionaryId":1,"id":2,"name":"occupation","nameInterpret":"职业"},{"cancel":0,"code":"3","content":"律师","createTime":"","dictionaryId":1,"id":3,"name":"occupation","nameInterpret":"职业"}]
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
         * cancel : 0
         * code : 1
         * content : 码农
         * createTime :
         * dictionaryId : 1
         * id : 1
         * name : occupation
         * nameInterpret : 职业
         */

        private int cancel;
        private String code;
        private String content;
        private String createTime;
        private int dictionaryId;
        private int id;
        private String name;
        private String nameInterpret;

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDictionaryId() {
            return dictionaryId;
        }

        public void setDictionaryId(int dictionaryId) {
            this.dictionaryId = dictionaryId;
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

        public String getNameInterpret() {
            return nameInterpret;
        }

        public void setNameInterpret(String nameInterpret) {
            this.nameInterpret = nameInterpret;
        }
    }
}
