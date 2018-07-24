package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;


/**
 * The persistent class for the a_bank_card database table.
 * 
 */
public class ABankCard implements Serializable {

	private int id;

	private String accountId;

	private String bankCardNo;  //卡号

	private int bankType;  // 银行   1工商银行  2 农业银行  3 中国银行  4 建设银行

	private int cardType; //开户类型  1 对公账户   2 私人账户

	private String createTime;

	private String province;

	private String city;

	private String bankName;

	private String name;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ABankCard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBankCardNo() {
		return this.bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public int getBankType() {
		return this.bankType;
	}

	public void setBankType(int bankType) {
		this.bankType = bankType;
	}

	public int getCardType() {
		return this.cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}