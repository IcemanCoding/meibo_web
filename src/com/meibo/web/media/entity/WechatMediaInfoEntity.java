package com.meibo.web.media.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WechatMediaInfoEntity {
	
	private Integer wechatMediaId;
	private Integer channelId;
	private Integer areaId;
	private BigDecimal firstPrice;
	private BigDecimal secondPrice;
	private BigDecimal otherPrice;
	private String remark;
	private Integer auditUser;
	private Date auditDate;
	private Integer auditStatus;
	private Integer status;
	private Integer createdUser;
	
	public Integer getWechatMediaId() {
		return wechatMediaId;
	}
	public void setWechatMediaId(Integer wechatMediaId) {
		this.wechatMediaId = wechatMediaId;
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
