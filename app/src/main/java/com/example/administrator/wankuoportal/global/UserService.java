package com.example.administrator.wankuoportal.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.flysand.mylibrary.util.Utils;

/**
 * Created by ytzht on 2017/07/21 下午11:57
 */

public class UserService {

    private Context context;
    private static final String USER_PREFS = "_member_";
    private static final String shenfen = "shenfen";
    private static final String password = "password";
    private static final String phone = "phone";
    private static final String tabposition = "tabposition";
    private static final String fenleiposition = "fenleiposition";
    private static final String address = "address";
    private static final String accountid = "accountid";
    private static final String token = "token";
    private static final String text = "text";
    private static final String islogin = "islogin";
    private static final String storeposition = "storeposition";
    private static final String storeid = "storeid";
    private static final String storename = "storename";
    private static final String shouyecity = "shouyecity";
    private static final String isOrdinaryMember = "isOrdinaryMember";
    private static final String subLabelPosition = "subLabelPosition";

    public UserService(Context context) {
        this.context = context;
    }

    //保存底部页码
    public void setSubLabelPosition(int value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                tabposition, Context.MODE_PRIVATE);
        memberPrefs.edit().putInt(subLabelPosition, value).apply();
    }

    public int getSubLabelPosition() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                tabposition, Context.MODE_PRIVATE);
        return memberPrefs.getInt(subLabelPosition, 0);

    }

    //保存底部页码
    public void settabposition(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                tabposition, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(tabposition, value).apply();
    }

    public String gettabposition() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                tabposition, Context.MODE_PRIVATE);
        return memberPrefs.getString(tabposition, "0");

    }

    //保存首页城市
    public void setshouyecity(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                shouyecity, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(shouyecity, value).apply();
    }

    public String getshouyecity() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                shouyecity, Context.MODE_PRIVATE);
        return memberPrefs.getString(shouyecity, "0");

    }

    //保存分类当前页码
    public void setfenleiposition(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                fenleiposition, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(fenleiposition, value).apply();
    }

    public String getfenleiposition() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                fenleiposition, Context.MODE_PRIVATE);
        return memberPrefs.getString(fenleiposition, "0");

    }


    //保存账号
    public void setphone(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                phone, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(phone, value).apply();
    }

    public String getphone() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                phone, Context.MODE_PRIVATE);
        return memberPrefs.getString(phone, "");

    }

    //保存密码
    public void setPassword(String value) {
        Utils.saveData(context, password, value);
    }

    public String getPassword() {
        return Utils.getSaveStringData(context, password, "");
    }


    /**
     * 保存当前登录的身份
     *
     * @param value
     */
    public void setShenfen(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(shenfen, value).apply();
    }

    /**
     * 获取当前登录的身份
     *
     * @return
     */
    public String getshenfen() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                USER_PREFS, Context.MODE_PRIVATE);
        return memberPrefs.getString(shenfen, "0");

    }

    /**
     * 保存当前地址
     *
     * @param value
     */
    public void setAddress(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                address, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(address, value).apply();
    }

    /**
     * 获取当前地址
     *
     * @return
     */
    public String getAddress() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                address, Context.MODE_PRIVATE);
        return memberPrefs.getString(address, "0");

    }

    /**
     * 保存当前登录的accountid   0 为未登录  其他为已经登录
     *
     * @param value
     */
    public void setaccountid(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                accountid, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(accountid, value).apply();
    }

    /**
     * 获取当前accountid
     *
     * @return
     */
    public String getaccountid() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                accountid, Context.MODE_PRIVATE);
        return memberPrefs.getString(accountid, "0");

    }

    /**
     * 保存当前登录的token   0 为未登录  其他为已经登录
     *
     * @param value
     */
    public void settoken(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                token, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(token, value).apply();
    }

    /**
     * 获取当前登录的Token
     *
     * @return
     */
    public String gettoken() {
        SharedPreferences memberPrefs = context.getSharedPreferences(token, Context.MODE_PRIVATE);
        return memberPrefs.getString(token, "0");

    }

    /**
     * 保存需要用到的信息
     *
     * @param value
     */
    public void setText(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                text, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(text, value).apply();
    }

    public String getText() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                text, Context.MODE_PRIVATE);
        return memberPrefs.getString(text, "0");

    }

    /**
     * 保存是否需要登录  0为不需要登录  1为需要登录
     *
     * @param value
     */
    public void setislogin(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                islogin, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(islogin, value).apply();
    }

    public String getislogin() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                islogin, Context.MODE_PRIVATE);
        return memberPrefs.getString(islogin, "0");

    }

    //储存店铺页码
    public void setstoreposition(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                storeposition, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(storeposition, value).apply();
    }

    public String getstoreposition() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                storeposition, Context.MODE_PRIVATE);
        return memberPrefs.getString(storeposition, "0");

    }

    //储存店铺id
    public void setstoreid(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                storeid, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(storeid, value).apply();
    }

    public String getstoreid() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                storeid, Context.MODE_PRIVATE);
        return memberPrefs.getString(storeid, "0");

    }

    //储存店铺id
    public void setstorename(String value) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                storename, Context.MODE_PRIVATE);
        memberPrefs.edit().putString(storename, value).apply();
    }

    public String getstorename() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                storename, Context.MODE_PRIVATE);
        return memberPrefs.getString(storename, "0");

    }

    public void setIsOrdinaryMember(int ordinaryMember) {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                isOrdinaryMember, Context.MODE_PRIVATE);
        memberPrefs.edit().putInt(isOrdinaryMember, ordinaryMember).apply();
    }

    public int getIsOrdinaryMember() {
        SharedPreferences memberPrefs = context.getSharedPreferences(
                isOrdinaryMember, Context.MODE_PRIVATE);
        return memberPrefs.getInt(isOrdinaryMember, 1);
    }
}
