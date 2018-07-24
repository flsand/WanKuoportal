package com.example.administrator.wankuoportal.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class PaiHang {

    /**
     * code : 0
     * data : {"apiPrizeRecordRanking":{"city":"烟台市","gold":45,"headimg":"1edaf7e44c7043a9900252f1f7d1f1c7","nickname":"路路通","province":"山东省","ranking":2},"list":[{"city":"烟台市","gold":265,"headimg":"","nickname":"喜洋洋","province":"山东省","ranking":1},{"city":"烟台市","gold":45,"headimg":"1edaf7e44c7043a9900252f1f7d1f1c7","nickname":"路路通","province":"山东省","ranking":2},{"city":"烟台市","gold":40,"headimg":"","nickname":"美洋洋","province":"山东省","ranking":3}]}
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
         * apiPrizeRecordRanking : {"city":"烟台市","gold":45,"headimg":"1edaf7e44c7043a9900252f1f7d1f1c7","nickname":"路路通","province":"山东省","ranking":2}
         * list : [{"city":"烟台市","gold":265,"headimg":"","nickname":"喜洋洋","province":"山东省","ranking":1},{"city":"烟台市","gold":45,"headimg":"1edaf7e44c7043a9900252f1f7d1f1c7","nickname":"路路通","province":"山东省","ranking":2},{"city":"烟台市","gold":40,"headimg":"","nickname":"美洋洋","province":"山东省","ranking":3}]
         */

        private ApiPrizeRecordRankingBean apiPrizeRecordRanking;
        private List<ListBean> list;

        public ApiPrizeRecordRankingBean getApiPrizeRecordRanking() {
            return apiPrizeRecordRanking;
        }

        public void setApiPrizeRecordRanking(ApiPrizeRecordRankingBean apiPrizeRecordRanking) {
            this.apiPrizeRecordRanking = apiPrizeRecordRanking;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ApiPrizeRecordRankingBean {
            /**
             * city : 烟台市
             * gold : 45
             * headimg : 1edaf7e44c7043a9900252f1f7d1f1c7
             * nickname : 路路通
             * province : 山东省
             * ranking : 2
             */

            private String city;
            private int gold;
            private String headimg;
            private String nickname;
            private String province;
            private int ranking;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getGold() {
                return gold;
            }

            public void setGold(int gold) {
                this.gold = gold;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }
        }

        public static class ListBean {
            /**
             * city : 烟台市
             * gold : 265
             * headimg :
             * nickname : 喜洋洋
             * province : 山东省
             * ranking : 1
             */

            private String city;
            private int gold;
            private String headimg;
            private String nickname;
            private String province;
            private int ranking;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getGold() {
                return gold;
            }

            public void setGold(int gold) {
                this.gold = gold;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }
        }
    }
}
