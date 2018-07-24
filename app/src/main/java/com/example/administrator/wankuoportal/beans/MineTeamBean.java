package com.example.administrator.wankuoportal.beans;

import com.flysand.mylibrary.sqlhelper.DBVO;

/**
 * Created by FlySand on 2018/3/18.
 */

public class MineTeamBean extends DBVO {


    /**
     * profit : 0
     * account_id : 488
     * nickname : 万阔小王
     * name :
     * create_time : 2018-01-26 10:20:17
     * head_icon : 9c4382b6d51f4c35b8d755b95824d7bc
     * cost : 0.000000
     */

    private String profit;
    private String account_id;
    private String nickname;
    private String name;
    private String create_time;
    private String head_icon;
    private String cost;
    /**
     * headIcon :
     * flag : 1
     * accountId : 307
     * nickName :
     * manger : 普通会员
     * money : 0.00
     * isManger : 0
     */

    private String headIcon;
    private String flag;
    private String accountId;
    private String nickName;
    private String manger;
    private String money;
    private String isManger;


    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getHead_icon() {
        return head_icon;
    }

    public void setHead_icon(String head_icon) {
        this.head_icon = head_icon;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getManger() {
        return manger;
    }

    public void setManger(String manger) {
        this.manger = manger;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getIsManger() {
        return isManger;
    }

    public void setIsManger(String isManger) {
        this.isManger = isManger;
    }
}
