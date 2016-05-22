package com.meibo.web.system.entity;

import java.math.BigDecimal;

public class SystemTransDtlEntity {
	
	private Integer transDetailId;
	private String transDate;
	private String transTime;
	private BigDecimal transAmount;
	private Integer transStatus;
	private Integer transType;
	private Integer transCode;
	private Integer memberId;
	private Integer orderId;
	
	public Integer getTransDetailId() {
		return transDetailId;
	}
	public void setTransDetailId(Integer transDetailId) {
		this.transDetailId = transDetailId;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public Integer getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(Integer transStatus) {
		this.transStatus = transStatus;
	}
	public Integer getTransType() {
		return transType;
	}
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	public Integer getTransCode() {
		return transCode;
	}
	public void setTransCode(Integer transCode) {
		this.transCode = transCode;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
