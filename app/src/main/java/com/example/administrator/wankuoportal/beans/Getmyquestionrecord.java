package com.example.administrator.wankuoportal.beans;

/**
 * Created by Administrator on 2017/9/28 0028.
 */

public class Getmyquestionrecord {

    /**
     * code : 0
     * data : {"aprizeRecordRanking":{"accountId":222,"allNumber":10,"allNumber1":0,"allNumber2":10,"gold":10,"gold2":10,"id":628,"rightNumber":2,"rightNumber1":0,"rightNumber2":2,"wrongNumber":8,"wrongNumber1":0,"wrongNumber2":8},"ranking":1}
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
         * aprizeRecordRanking : {"accountId":222,"allNumber":10,"allNumber1":0,"allNumber2":10,"gold":10,"gold2":10,"id":628,"rightNumber":2,"rightNumber1":0,"rightNumber2":2,"wrongNumber":8,"wrongNumber1":0,"wrongNumber2":8}
         * ranking : 1
         */

        private AprizeRecordRankingBean aprizeRecordRanking;
        private int ranking;
        private int rankingcity;

        public int getRankingcity() {
            return rankingcity;
        }

        public void setRankingcity(int rankingcity) {
            this.rankingcity = rankingcity;
        }

        public AprizeRecordRankingBean getAprizeRecordRanking() {
            return aprizeRecordRanking;
        }

        public void setAprizeRecordRanking(AprizeRecordRankingBean aprizeRecordRanking) {
            this.aprizeRecordRanking = aprizeRecordRanking;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public static class AprizeRecordRankingBean {
            /**
             * accountId : 222
             * allNumber : 10
             * allNumber1 : 0
             * allNumber2 : 10
             * gold : 10
             * gold2 : 10
             * id : 628
             * rightNumber : 2
             * rightNumber1 : 0
             * rightNumber2 : 2
             * wrongNumber : 8
             * wrongNumber1 : 0
             * wrongNumber2 : 8
             */

            private int accountId;
            private int allNumber;
            private int allNumber1;
            private int allNumber2;
            private int gold;
            private int gold2;
            private int id;
            private int rightNumber;
            private int rightNumber1;
            private int rightNumber2;
            private int wrongNumber;
            private int wrongNumber1;
            private int wrongNumber2;

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getAllNumber() {
                return allNumber;
            }

            public void setAllNumber(int allNumber) {
                this.allNumber = allNumber;
            }

            public int getAllNumber1() {
                return allNumber1;
            }

            public void setAllNumber1(int allNumber1) {
                this.allNumber1 = allNumber1;
            }

            public int getAllNumber2() {
                return allNumber2;
            }

            public void setAllNumber2(int allNumber2) {
                this.allNumber2 = allNumber2;
            }

            public int getGold() {
                return gold;
            }

            public void setGold(int gold) {
                this.gold = gold;
            }

            public int getGold2() {
                return gold2;
            }

            public void setGold2(int gold2) {
                this.gold2 = gold2;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRightNumber() {
                return rightNumber;
            }

            public void setRightNumber(int rightNumber) {
                this.rightNumber = rightNumber;
            }

            public int getRightNumber1() {
                return rightNumber1;
            }

            public void setRightNumber1(int rightNumber1) {
                this.rightNumber1 = rightNumber1;
            }

            public int getRightNumber2() {
                return rightNumber2;
            }

            public void setRightNumber2(int rightNumber2) {
                this.rightNumber2 = rightNumber2;
            }

            public int getWrongNumber() {
                return wrongNumber;
            }

            public void setWrongNumber(int wrongNumber) {
                this.wrongNumber = wrongNumber;
            }

            public int getWrongNumber1() {
                return wrongNumber1;
            }

            public void setWrongNumber1(int wrongNumber1) {
                this.wrongNumber1 = wrongNumber1;
            }

            public int getWrongNumber2() {
                return wrongNumber2;
            }

            public void setWrongNumber2(int wrongNumber2) {
                this.wrongNumber2 = wrongNumber2;
            }
        }
    }
}
