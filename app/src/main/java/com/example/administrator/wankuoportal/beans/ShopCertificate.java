package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class ShopCertificate {


    /**
     * code : 0
     * data : {"accountId":200,"certificateImg1":"9ddaa306a95d471bb41152fa8daeaa35","certificateImg2":"9ddaa306a95d471bb41152fa8daeaa35","certificateImg3":"9ddaa306a95d471bb41152fa8daeaa35","certificateid":"134564611320321","id":5,"legalId":"370686199307171895","legalIdImg":"2f7afee8a8f843eb94f3c1a27742bf97","legalIdImg1":"7d65ad79848d45a3a06c20866ad1c0a6","legalIdImg2":"f0c5a557afe14057bf6f607d5e1f6c87","legalName":"刘德华","passTime":"2017-09-26 10:29:37","state":0,"type":2}
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
         * accountId : 200
         * certificateImg1 : 9ddaa306a95d471bb41152fa8daeaa35
         * certificateImg2 : 9ddaa306a95d471bb41152fa8daeaa35
         * certificateImg3 : 9ddaa306a95d471bb41152fa8daeaa35
         * certificateid : 134564611320321
         * id : 5
         * legalId : 370686199307171895
         * legalIdImg : 2f7afee8a8f843eb94f3c1a27742bf97
         * legalIdImg1 : 7d65ad79848d45a3a06c20866ad1c0a6
         * legalIdImg2 : f0c5a557afe14057bf6f607d5e1f6c87
         * legalName : 刘德华
         * passTime : 2017-09-26 10:29:37
         * state : 0
         * type : 2
         */

        private int accountId;
        private String certificateImg1;
        private String certificateImg2;
        private String certificateImg3;
        private String certificateid;
        private int id;
        private String legalId;
        private String legalIdImg;
        private String legalIdImg1;
        private String legalIdImg2;
        private String legalName;
        private String passTime;
        private int state;
        private int type;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getCertificateImg1() {
            return certificateImg1;
        }

        public void setCertificateImg1(String certificateImg1) {
            this.certificateImg1 = certificateImg1;
        }

        public String getCertificateImg2() {
            return certificateImg2;
        }

        public void setCertificateImg2(String certificateImg2) {
            this.certificateImg2 = certificateImg2;
        }

        public String getCertificateImg3() {
            return certificateImg3;
        }

        public void setCertificateImg3(String certificateImg3) {
            this.certificateImg3 = certificateImg3;
        }

        public String getCertificateid() {
            return certificateid;
        }

        public void setCertificateid(String certificateid) {
            this.certificateid = certificateid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLegalId() {
            return legalId;
        }

        public void setLegalId(String legalId) {
            this.legalId = legalId;
        }

        public String getLegalIdImg() {
            return legalIdImg;
        }

        public void setLegalIdImg(String legalIdImg) {
            this.legalIdImg = legalIdImg;
        }

        public String getLegalIdImg1() {
            return legalIdImg1;
        }

        public void setLegalIdImg1(String legalIdImg1) {
            this.legalIdImg1 = legalIdImg1;
        }

        public String getLegalIdImg2() {
            return legalIdImg2;
        }

        public void setLegalIdImg2(String legalIdImg2) {
            this.legalIdImg2 = legalIdImg2;
        }

        public String getLegalName() {
            return legalName;
        }

        public void setLegalName(String legalName) {
            this.legalName = legalName;
        }

        public String getPassTime() {
            return passTime;
        }

        public void setPassTime(String passTime) {
            this.passTime = passTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
