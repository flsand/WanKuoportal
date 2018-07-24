package com.example.administrator.wankuoportal.beans;

import java.io.Serializable;


/**
 * The persistent class for the a_publish_demand database table.
 * 
 */
public class APublishDemand implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int accountId;

	private int isCountry;

	private int isEmploy;

	private int labelId;

	private String lowerBound;

	private String mobile;

	private String myArea;

	private String myCity;

	private String myProvince;

	private String orderId;

	private String qq;

	private String requirementTitle;

	private String serviceCity;

	private String serviceProvince;

	private int serviceType;

	private String specificRequirements;

	private String uploadingAttachments;

	private String upperBound;

	private String weChat;

	public APublishDemand() {
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

	public int getIsCountry() {
		return this.isCountry;
	}

	public void setIsCountry(int isCountry) {
		this.isCountry = isCountry;
	}

	public int getIsEmploy() {
		return this.isEmploy;
	}

	public void setIsEmploy(int isEmploy) {
		this.isEmploy = isEmploy;
	}

	public int getLabelId() {
		return this.labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLowerBound() {
		return this.lowerBound;
	}

	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMyArea() {
		return this.myArea;
	}

	public void setMyArea(String myArea) {
		this.myArea = myArea;
	}

	public String getMyCity() {
		return this.myCity;
	}

	public void setMyCity(String myCity) {
		this.myCity = myCity;
	}

	public String getMyProvince() {
		return this.myProvince;
	}

	public void setMyProvince(String myProvince) {
		this.myProvince = myProvince;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRequirementTitle() {
		return this.requirementTitle;
	}

	public void setRequirementTitle(String requirementTitle) {
		this.requirementTitle = requirementTitle;
	}

	public String getServiceCity() {
		return this.serviceCity;
	}

	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}

	public String getServiceProvince() {
		return this.serviceProvince;
	}

	public void setServiceProvince(String serviceProvince) {
		this.serviceProvince = serviceProvince;
	}

	public int getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public String getSpecificRequirements() {
		return this.specificRequirements;
	}

	public void setSpecificRequirements(String specificRequirements) {
		this.specificRequirements = specificRequirements;
	}

	public String getUploadingAttachments() {
		return this.uploadingAttachments;
	}

	public void setUploadingAttachments(String uploadingAttachments) {
		this.uploadingAttachments = uploadingAttachments;
	}

	public String getUpperBound() {
		return this.upperBound;
	}

	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

	public String getWeChat() {
		return this.weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

}