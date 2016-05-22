package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;

import org.apache.commons.fileupload.FileItem;

public class BlogMediaUpdateViewmodel {
	
	// info
	private Integer blogMediaId;
	private Integer areaId;
	private BigDecimal publishPrice;
	private BigDecimal forwardPrice;
	private String remark;
	private Integer channelId;
	
	// channel
	private String nickname;
	private Integer fansCount;
	private FileItem file;
	private String headImage;
	private FileItem qrCode;
	private String qrCodeUrl;
	private String desc;
	private String typeName;
	private Integer authType;
	private String authInfo;
	private String registerDate;
	
	public Integer getBlogMediaId() {
		return blogMediaId;
	}
	public void setBlogMediaId(Integer blogMediaId) {
		this.blogMediaId = blogMediaId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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
	public FileItem getFile() {
		return file;
	}
	public void setFile(FileItem file) {
		this.file = file;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public FileItem getQrCode() {
		return qrCode;
	}
	public void setQrCode(FileItem qrCode) {
		this.qrCode = qrCode;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
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
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

}
