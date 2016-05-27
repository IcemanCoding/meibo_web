package com.meibo.web.order.entity;

import java.math.BigDecimal;
import java.util.Date;

public class FlowMediaOrderSplitEntity {
	
	private Integer orderSplitId;
	private Integer orderId;
	private Integer packageId;
	private String destMobile;
	private BigDecimal transAmount;
	private Integer orderStatus;
	private Date finishDate;
	private Date createDate;
	
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
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public String getDestMobile() {
		return destMobile;
	}
	public void setDestMobile(String destMobile) {
		this.destMobile = destMobile;
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
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
