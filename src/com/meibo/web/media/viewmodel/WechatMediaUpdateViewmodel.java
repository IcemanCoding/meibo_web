package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;

import org.apache.commons.fileupload.FileItem;

public class WechatMediaUpdateViewmodel {
	
	// info
	private Integer wechatMediaId;
	private Integer areaId;
	private BigDecimal firstPrice;
	private BigDecimal secondPrice;
	private BigDecimal otherPrice;
	private String remark;
	private Integer channelId;
	
	// channel
	private String nickname;
	private String account;
	private Integer fansCount;
	private FileItem file;
	private String headImage;
	private FileItem qrCode;
	private String qrCodeUrl;
	private String desc;
	private String authentication;
	private String typeName;
	
	public Integer getWechatMediaId() {
		return wechatMediaId;
	}
	public void setWechatMediaId(Integer wechatMediaId) {
		this.wechatMediaId = wechatMediaId;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	
}
