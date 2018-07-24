package com.example.administrator.wankuoportal.beans;

public class MyInfoAssetDetailBean {

    /**
     * accountId : 521
     * amount : 0.5
     * consumptionType : 99
     * content : 亲，根据您的店长等级(亲)，您2018-04-13日的分红为0.50元，已自动存入我的钱包！
     * createTime : 2018-04-13 08:00:00
     * id : 3463
     * infoTitle : 您的每日分红到账啦！！
     * type : 1
     */

    private int accountId;
    private double amount;
    private int consumptionType;
    private String content;
    private String createTime;
    private int id;
    private String infoTitle;
    private int type;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(int consumptionType) {
        this.consumptionType = consumptionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
