package com.meibo.web.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeOrderEntity {
	
	private Integer orderId;
	private String orderCode;
	private Integer memberId;
	private BigDecimal transAmount;
	private Integer orderStatus;
	private Integer platformId;
	private Integer transType;
	private Date orderDate;
	private Date payDate;
	private Date createDate;
	private String editMsg;
	private String auditMsg;
	private Integer auditUser;
	private Date finishDate;
	private String voucherNum;
	
	public String getEditMsg() {
		return editMsg;
	}
	public void setEditMsg(String editMsg) {
		this.editMsg = editMsg;
	}
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	public Integer getAuditUser() {
		return auditUser;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
	public Integer getTransType() {
		return transType;
	}
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the finishDate
	 */
	public Date getFinishDate() {
		return finishDate;
	}
	/**
	 * @param finishDate the finishDate to set
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	/**
	 * @return the auditMsg
	 */
	public String getAuditMsg() {
		return auditMsg;
	}
	/**
	 * @param auditMsg the auditMsg to set
	 */
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}
	/**
	 * @return the voucherNum
	 */
	public String getVoucherNum() {
		return voucherNum;
	}
	/**
	 * @param voucherNum the voucherNum to set
	 */
	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}

}
