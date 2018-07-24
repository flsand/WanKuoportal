package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class ShopDefined {


    /**
     * code : 0
     * datas : [{"groupType":0,"id":75,"img":"7728f2ee3d9944e9a44149ccba00e86f","linkId":10,"linkType":3,"shopDataId":3,"sort":0,"type":1},{"groupType":1,"id":76,"img":"af417c47498b4817b1d3189cd7862709","linkId":0,"linkType":1,"shopDataId":3,"sort":0,"type":1},{"groupType":2,"id":77,"img":"27cb89412bab46f8a859f3361aa137d7","linkId":0,"linkType":2,"shopDataId":3,"sort":0,"type":1},{"groupType":3,"id":78,"img":"f523e0c8b4d743339edd7632e16385db","linkId":0,"linkType":1,"shopDataId":3,"sort":0,"type":1}]
     * msg : 查询成功
     */

    private int code;
    private String msg;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * groupType : 0
         * id : 75
         * img : 7728f2ee3d9944e9a44149ccba00e86f
         * linkId : 10
         * linkType : 3
         * shopDataId : 3
         * sort : 0
         * type : 1
         */

        private int groupType;
        private int id;
        private String img;
        private int linkId;
        private int linkType;
        private int shopDataId;
        private int sort;
        private int type;

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getLinkId() {
            return linkId;
        }

        public void setLinkId(int linkId) {
            this.linkId = linkId;
        }

        public int getLinkType() {
            return linkType;
        }

        public void setLinkType(int linkType) {
            this.linkType = linkType;
        }

        public int getShopDataId() {
            return shopDataId;
        }

        public void setShopDataId(int shopDataId) {
            this.shopDataId = shopDataId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
