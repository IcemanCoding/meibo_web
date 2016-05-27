package com.meibo.web.external.model;

public class SendMessageModel {
	
	private String destMobileNum;
	private String messageContent;
	// 1、注册验证码
	private Integer messageType;
	private Integer messagePlatform;
	
	public String getDestMobileNum() {
		return destMobileNum;
	}
	public void setDestMobileNum(String destMobileNum) {
		this.destMobileNum = destMobileNum;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Integer getMessagePlatform() {
		return messagePlatform;
	}
	public void setMessagePlatform(Integer messagePlatform) {
		this.messagePlatform = messagePlatform;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

}
