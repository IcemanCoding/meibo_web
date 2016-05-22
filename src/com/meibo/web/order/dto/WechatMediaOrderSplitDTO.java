package com.meibo.web.order.dto;

import java.math.BigDecimal;

public class WechatMediaOrderSplitDTO {
	
	private Integer wechatMediaId;
	private Integer priceType;
	private BigDecimal transAmount;
	private Integer mediaMemberId;
	
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public Integer getMediaMemberId() {
		return mediaMemberId;
	}
	public void setMediaMemberId(Integer mediaMemberId) {
		this.mediaMemberId = mediaMemberId;
	}
	public Integer getPriceType() {
		return priceType;
	}
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}
	/**
	 * @return the wechatMediaId
	 */
	public Integer getWechatMediaId() {
		return wechatMediaId;
	}
	/**
	 * @param wechatMediaId the wechatMediaId to set
	 */
	public void setWechatMediaId(Integer wechatMediaId) {
		this.wechatMediaId = wechatMediaId;
	}

}
