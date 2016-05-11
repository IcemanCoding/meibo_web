package com.meibo.web.media.entity;

public class WechatMediaChannelEntity {
	
	private Integer channelId;
	private String headImage;
	private String nickName;
	private String account;
	private String qrCode;
	private String desc;
	private String authentication;
	private Integer typeId;
	private Integer status;
	private Integer fansCount;
	
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Integer getFansCount() {
		return fansCount;
	}
	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

}
