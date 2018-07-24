package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class ERWEIMA {


    /**
     * code : 0
     * data : {"gold":453401,"headicon":"e58ce5aae640479babc828857ba6ba36","imglink":"http://192.168.1.198:8080/wankuoportal/api/share?invitationcode=XJxUlFxJFT","nickname":"胡说"}
     * msg : 获取成功
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
         * gold : 453401
         * headicon : e58ce5aae640479babc828857ba6ba36
         * imglink : http://192.168.1.198:8080/wankuoportal/api/share?invitationcode=XJxUlFxJFT
         * nickname : 胡说
         */

        private int gold;
        private String headicon;
        private String imglink;
        private String nickname;

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public String getHeadicon() {
            return headicon;
        }

        public void setHeadicon(String headicon) {
            this.headicon = headicon;
        }

        public String getImglink() {
            return imglink;
        }

        public void setImglink(String imglink) {
            this.imglink = imglink;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
