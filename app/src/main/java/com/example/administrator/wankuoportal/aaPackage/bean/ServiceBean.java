package com.example.administrator.wankuoportal.aaPackage.bean;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * <pre>
 *     author  : FlySand
 *     e-mail  : 1156183505@qq.com
 *     time    : 2018/5/25.
 *     desc    :
 * </pre>
 */
public class ServiceBean extends DBVO {

    /**
     * city : 烟台市
     * goodName : 六合一建站
     * id : 115
     * img : 099e88cd0bc74d4bb12b24e7eee94748
     * price : 12800.0
     * province : 山东省
     * salesVolume : 2
     * shopName : 科信软件
     */

    private String city;
    private String goodName;
    private int id;
    private String img;
    private double price;
    private String province;
    private int salesVolume;
    private String shopName;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
