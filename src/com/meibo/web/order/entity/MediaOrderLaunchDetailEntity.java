package com.meibo.web.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MediaOrderLaunchDetailEntity {
	
	private Integer orderSplitId;
	private Integer newsMediaId;
	private String mediaName;
	private String launchUrl;
	private Date launchDate;
	private Integer orderStatus;
	private BigDecimal splitAmount;
	
	public Integer getOrderSplitId() {
		return orderSplitId;
	}
	public void setOrderSplitId(Integer orderSplitId) {
		this.orderSplitId = orderSplitId;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public String getLaunchUrl() {
		return launchUrl;
	}
	public void setLaunchUrl(String launchUrl) {
		this.launchUrl = launchUrl;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public Integer getNewsMediaId() {
		return newsMediaId;
	}
	public void setNewsMediaId(Integer newsMediaId) {
		this.newsMediaId = newsMediaId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public BigDecimal getSplitAmount() {
		return splitAmount;
	}
	public void setSplitAmount(BigDecimal splitAmount) {
		this.splitAmount = splitAmount;
	}

}
