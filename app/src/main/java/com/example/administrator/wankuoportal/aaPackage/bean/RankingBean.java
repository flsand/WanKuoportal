package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/6/2.
 *     desc    : 答题排行
 * </pre>
 */
public class RankingBean extends DBVO {
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