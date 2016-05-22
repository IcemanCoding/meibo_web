package com.meibo.web.system.model;

import java.math.BigDecimal;

public class ConsumeTransModel {
	
	private Integer memberId;
	private	Integer orderId;
	// 交易编码：1-消费 2-充值
	private Integer transCode;
	// 交易类型：1-新闻媒体
	private Integer transType;
	private BigDecimal transAmount;
	
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
	public Integer getTransCode() {
		return transCode;
	}
	public void setTransCode(Integer transCode) {
		this.transCode = transCode;
	}
	public Integer getTransType() {
		return transType;
	}
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	
}
