package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * Created by Flysand on 2018/3/29.
 */

public class StatisticsBean extends DBVO {


    private String tjzr;//推荐人数
    private String syze;//收益总额
    private String jlze;//奖励总金额
    private String glzr;//管理人数
    private String tjje;//推荐奖励
    private String glje;//管理奖励
    private String dpzs;//店铺总数
    private String ztsy;//总体收益
    private String tjsy;//推荐收益
    private String glsy;//管理收益

    public String getGlsy() {
        return glsy;
    }

    public void setGlsy(String glsy) {
        this.glsy = glsy;
    }

    public String getDpzs() {
        return dpzs;
    }

    public void setDpzs(String dpzs) {
        this.dpzs = dpzs;
    }

    public String getZtsy() {
        return ztsy;
    }

    public void setZtsy(String ztsy) {
        this.ztsy = ztsy;
    }

    public String getTjsy() {
        return tjsy;
    }

    public void setTjsy(String tjsy) {
        this.tjsy = tjsy;
    }

    public String getGlje() {
        return glje;
    }

    public void setGlje(String glje) {
        this.glje = glje;
    }

    public String getTjje() {
        return tjje;
    }

    public void setTjje(String tjje) {
        this.tjje = tjje;
    }

    public String getTjzr() {
        return tjzr;
    }

    public void setTjzr(String tjzr) {
        this.tjzr = tjzr;
    }

    public String getSyze() {
        return syze;
    }

    public void setSyze(String syze) {
        this.syze = syze;
    }

    public String getJlze() {
        return jlze;
    }

    public void setJlze(String jlze) {
        this.jlze = jlze;
    }

    public String getGlzr() {
        return glzr;
    }

    public void setGlzr(String glzr) {
        this.glzr = glzr;
    }
}
