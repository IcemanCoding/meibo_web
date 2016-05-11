package com.meibo.web.media.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BlogMediaInfoEntity {
	
	private Integer blogMediaId;
	private Integer channelId;
	private Integer areaId;
	private BigDecimal publishPrice;
	private BigDecimal forwardPrice;
	private String remark;
	private Integer status;
	private Integer createdUser;
	private Integer auditUser;
	private Date auditDate;
	private Integer auditStatus;
	
	public Integer getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getBlogMediaId() {
		return blogMediaId;
	}
	public void setBlogMediaId(Integer blogMediaId) {
		this.blogMediaId = blogMediaId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}
	
}
