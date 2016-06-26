package com.meibo.web.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WechatMediaOrderSplitEntity {
	
	private Integer orderSplitId;
	private Integer orderId;
	private Integer wechatMediaId;
	private Integer mediaMemberId;
	private Integer priceType;
	private Integer orderStatus;
	private BigDecimal transAmount;
	private Date createDate;
	private Date acceptDate;
	private Date rejectDate;
	private Date launchDate;
	private Date finishDate;
	private String launchUrl;
	private String rejectMsg;
	
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	public Date getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getLaunchUrl() {
		return launchUrl;
	}
	public void setLaunchUrl(String launchUrl) {
		this.launchUrl = launchUrl;
	}
	public Integer getOrderSplitId() {
		return orderSplitId;
	}
	public void setOrderSplitId(Integer orderSplitId) {
		this.orderSplitId = orderSplitId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getMediaMemberId() {
		return mediaMemberId;
	}
	public void setMediaMemberId(Integer mediaMemberId) {
		this.mediaMemberId = mediaMemberId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	/**
	 * @return the rejectMsg
	 */
	public String getRejectMsg() {
		return rejectMsg;
	}
	/**
	 * @param rejectMsg the rejectMsg to set
	 */
	public void setRejectMsg(String rejectMsg) {
		this.rejectMsg = rejectMsg;
	}
	
}
