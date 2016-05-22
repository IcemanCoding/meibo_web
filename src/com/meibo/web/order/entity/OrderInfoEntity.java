package com.meibo.web.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfoEntity {
	
	// order主键
	private Integer orderId;
	// 订单号
	private String orderCode;
	// 下单会员id
	private Integer memberId;
	// 创建时间
	private Date createDate;
	// 支付时间
	private Date payDate;
	// 订单状态：1-待支付 2-已支付
	private Integer orderStatus;
	// 交易发生额
	private BigDecimal transAmount;
	// remark_msg
	private String remarkMsg;
	// 订单类型：1-新闻媒体
	private Integer transType;
	// 下单时间
	private Date orderDate;
	
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRemarkMsg() {
		return remarkMsg;
	}
	public void setRemarkMsg(String remarkMsg) {
		this.remarkMsg = remarkMsg;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
