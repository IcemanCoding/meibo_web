package com.meibo.web.order.dto;

import java.util.Date;

public class BaseMediaOrderStatusDetailDTO {
	
	private Date orderDate;
	private Date acceptDate;
	private Date launchDate;
	private Date finishDate;
	private Date rejectDate;
	private Integer orderStatus;
	private String rejectMsg;
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
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
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the rejectDate
	 */
	public Date getRejectDate() {
		return rejectDate;
	}
	/**
	 * @param rejectDate the rejectDate to set
	 */
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
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
