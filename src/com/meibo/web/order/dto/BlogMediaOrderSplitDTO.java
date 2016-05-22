package com.meibo.web.order.dto;

import java.math.BigDecimal;

public class BlogMediaOrderSplitDTO {
	
	private Integer blogMediaId;
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
	public Integer getBlogMediaId() {
		return blogMediaId;
	}
	public void setBlogMediaId(Integer blogMediaId) {
		this.blogMediaId = blogMediaId;
	}
	public Integer getPriceType() {
		return priceType;
	}
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

}
