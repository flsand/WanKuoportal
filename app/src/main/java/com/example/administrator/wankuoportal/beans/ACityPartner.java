package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;



/**
 * The persistent class for the a_city_partner database table.
 * 
 */

public class ACityPartner implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;

	private int accountId;
	
	/**
	 * 团队人数
	 */

	private String groupSize;

	/**
	 * 联系人
	 */
	private String linkman;

	/**
	 * 电话
	 */
	private String mobile;

	/**
	 * 类型：1：城市 2：区域
	 */
	private int type;
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 区域
	 */
	private String area;
	
	/**
	 * 状态
	 * 0未通过1通过2待审核
	 */
	private int state;
	

	private String failingReason;

	public ACityPartner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getGroupSize() {
		return this.groupSize;
	}

	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getFailingReason() {
		return failingReason;
	}

	public void setFailingReason(String failingReason) {
		this.failingReason = failingReason;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}