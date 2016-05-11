package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;

public class WechatMediaInsertViewmodel {
	
	private String nickname;
	private String account;
	private String qrCode;
	private String desc;
	private String authentication;
	private String typeName;
	private Integer fansCount;
	private Integer areaId;
	private BigDecimal firstPrice;
	private BigDecimal secondPrice;
	private BigDecimal otherPrice;
	private String remark;
	private Integer memberId;
	
	public String getNickName() {
		return nickname;
	}
	public void setNickName(String nickname) {
		this.nickname = nickname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public BigDecimal getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(BigDecimal firstPrice) {
		this.firstPrice = firstPrice;
	}
	public BigDecimal getSecondPrice() {
		return secondPrice;
	}
	public void setSecondPrice(BigDecimal secondPrice) {
		this.secondPrice = secondPrice;
	}
	public BigDecimal getOtherPrice() {
		return otherPrice;
	}
	public void setOtherPrice(BigDecimal otherPrice) {
		this.otherPrice = otherPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getFansCount() {
		return fansCount;
	}
	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}
	
}
