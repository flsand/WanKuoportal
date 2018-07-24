package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by lv on 2017/9/13 帮助中心第一列表.
 */

public class Gethelpcenter {

    /**
     * code : 0
     * datas : [{"createTime":"2017-09-10 14:57:30","helpTypeName":"提交商品","id":1},{"createTime":"2017-09-10 14:57:30","helpTypeName":"如何加盟","id":2}]
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
         * createTime : 2017-09-10 14:57:30
         * helpTypeName : 提交商品
         * id : 1
         */

        private String createTime;
        private String helpTypeName;
        private int id;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHelpTypeName() {
            return helpTypeName;
        }

        public void setHelpTypeName(String helpTypeName) {
            this.helpTypeName = helpTypeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
