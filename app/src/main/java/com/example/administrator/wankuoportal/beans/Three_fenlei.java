package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class Three_fenlei {

    /**
     * code : 0
     * datas : [{"cancel":0,"id":1755,"img":"","labelName":"影视明星","parentId":1305,"root":0},{"cancel":0,"id":1756,"img":"","labelName":"歌手模特","parentId":1305,"root":0},{"cancel":0,"id":1757,"img":"","labelName":"体育明星","parentId":1305,"root":0},{"cancel":0,"id":1758,"img":"","labelName":"段子手","parentId":1305,"root":0},{"cancel":0,"id":1759,"img":"","labelName":"网络红人","parentId":1305,"root":0},{"cancel":0,"id":1760,"img":"","labelName":"健康养生","parentId":1305,"root":0},{"cancel":0,"id":1761,"img":"","labelName":"地方媒体","parentId":1305,"root":0},{"cancel":0,"id":1762,"img":"","labelName":"母婴亲子","parentId":1305,"root":0},{"cancel":0,"id":1763,"img":"","labelName":"时尚搭配","parentId":1305,"root":0},{"cancel":0,"id":1764,"img":"","labelName":"娱乐八卦","parentId":1305,"root":0},{"cancel":0,"id":1765,"img":"","labelName":"幽默搞笑","parentId":1305,"root":0},{"cancel":0,"id":1766,"img":"","labelName":"互联网IT","parentId":1305,"root":0}]
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
         * id : 1755
         * img :
         * labelName : 影视明星
         * parentId : 1305
         * root : 0
         */

        private int cancel;
        private int id;
        private String img;
        private String labelName;
        private int parentId;
        private int root;

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
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

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getRoot() {
            return root;
        }

        public void setRoot(int root) {
            this.root = root;
        }
    }
}
