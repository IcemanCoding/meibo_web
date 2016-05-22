package com.meibo.web.order.dto;

import java.math.BigDecimal;

public class NewsMediaOrderSplitDTO {
	
	private Integer newsMediaId;
	private BigDecimal transAmount;
	private Integer mediaMemberId;
	
	public Integer getNewsMediaId() {
		return newsMediaId;
	}
	public void setNewsMediaId(Integer newsMediaId) {
		this.newsMediaId = newsMediaId;
	}
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

}
