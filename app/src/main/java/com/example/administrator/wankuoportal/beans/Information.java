package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class Information {

    /**
     * code : 0
     * data : {"accountId":0,"browseCount":"0","content":"测试内容12313","createTime":"2017-05-13","flag":"1","id":2,"linkId":0,"pictureId":"","summary":"打狗棍第二式","title":"打狗棍第二式","type":"2"}
     * msg : 查询成功
     */

    private int code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * accountId : 0
         * browseCount : 0
         * content : 测试内容12313
         * createTime : 2017-05-13
         * flag : 1
         * id : 2
         * linkId : 0
         * pictureId :
         * summary : 打狗棍第二式
         * title : 打狗棍第二式
         * type : 2
         */

        private int accountId;
        private String browseCount;
        private String content;
        private String createTime;
        private String flag;
        private int id;
        private int linkId;
        private String pictureId;
        private String summary;
        private String title;
        private String type;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getBrowseCount() {
            return browseCount;
        }

        public void setBrowseCount(String browseCount) {
            this.browseCount = browseCount;
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

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLinkId() {
            return linkId;
        }

        public void setLinkId(int linkId) {
            this.linkId = linkId;
        }

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
