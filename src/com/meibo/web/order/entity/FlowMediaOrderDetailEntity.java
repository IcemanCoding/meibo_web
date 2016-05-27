package com.meibo.web.order.entity;

import java.util.Date;

public class FlowMediaOrderDetailEntity {
	
	private Integer orderSplitId;
	private Integer orderId;
	private Integer dxPackageId;
	private Integer ltPackageId;
	private Integer ydPackageId;
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
	public Integer getDxPackageId() {
		return dxPackageId;
	}
	public void setDxPackageId(Integer dxPackageId) {
		this.dxPackageId = dxPackageId;
	}
	public Integer getLtPackageId() {
		return ltPackageId;
	}
	public void setLtPackageId(Integer ltPackageId) {
		this.ltPackageId = ltPackageId;
	}
	public Integer getYdPackageId() {
		return ydPackageId;
	}
	public void setYdPackageId(Integer ydPackageId) {
		this.ydPackageId = ydPackageId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
