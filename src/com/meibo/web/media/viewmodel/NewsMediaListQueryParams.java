package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;

public class NewsMediaListQueryParams {
	
	private Integer typeId;
	private String channelName;
	private Integer areaId;
	private Integer includeType;
	private Integer createdUser;
	private BigDecimal maxPrice;
	private BigDecimal minPrice;
	private Integer pageNum;
	private Integer pageRecorders;
	private Integer memberId;
	private Integer isLimit;
	private Integer beginLimit;
	private Integer endLimit;
	private Integer auditStatus;
	private String rate;
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getIncludeType() {
		return includeType;
	}
	public void setIncludeType(Integer includeType) {
		this.includeType = includeType;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageRecorders() {
		return pageRecorders;
	}
	public void setPageRecorders(Integer pageRecorders) {
		this.pageRecorders = pageRecorders;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getEndLimit() {
		return endLimit;
	}
	public void setEndLimit(Integer endLimit) {
		this.endLimit = endLimit;
	}
	public Integer getBeginLimit() {
		return beginLimit;
	}
	public void setBeginLimit(Integer beginLimit) {
		this.beginLimit = beginLimit;
	}
	public Integer getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
	public Integer getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
	
}
