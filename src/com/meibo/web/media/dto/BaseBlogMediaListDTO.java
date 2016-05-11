package com.meibo.web.media.dto;

import java.math.BigDecimal;

public class BaseBlogMediaListDTO {
	
	private Integer blogMediaId;
	private BigDecimal publishPrice;
	private BigDecimal forwardPrice;
	private String remark;
	private String areaName;
	private String nickname;
	private Integer fansCount;
	private String headImage;
	private String qrCode;
	private Integer authType;
	private String authInfo;
	private String desc;
	private String typeName;
	
	public Integer getBlogMediaId() {
		return blogMediaId;
	}
	public void setBlogMediaId(Integer blogMediaId) {
		this.blogMediaId = blogMediaId;
	}
	public BigDecimal getPublishPrice() {
		return publishPrice;
	}
	public void setPublishPrice(BigDecimal publishPrice) {
		this.publishPrice = publishPrice;
	}
	public BigDecimal getForwardPrice() {
		return forwardPrice;
	}
	public void setForwardPrice(BigDecimal forwardPrice) {
		this.forwardPrice = forwardPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getFansCount() {
		return fansCount;
	}
	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public Integer getAuthType() {
		return authType;
	}
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	public String getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(String authInfo) {
		this.authInfo = authInfo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
