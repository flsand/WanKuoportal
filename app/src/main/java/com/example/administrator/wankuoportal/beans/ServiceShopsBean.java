package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/25.
 *     desc    :
 * </pre>
 */
public class ServiceShopsBean extends DBVO{


    /**
     * ability : 程序UI设计
     * city : 烟台市
     * evaluate : 0
     * headIcon : be39bb88312f4d3e91a78881634f38c3
     * id : 20
     * level : 钻石服务商
     * nomoney : 3.0
     * province : 山东省
     * shopName : 鼎信科技
     * shopType : 公司
     */

    private String ability;
    private String city;
    private int evaluate;
    private String headIcon;
    private int id;
    private String level;
    private double nomoney;
    private String province;
    private String shopName;
    private String shopType;

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getNomoney() {
        return nomoney;
    }

    public void setNomoney(double nomoney) {
        this.nomoney = nomoney;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }
}
