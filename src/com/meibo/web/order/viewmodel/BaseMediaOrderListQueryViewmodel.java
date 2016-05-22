package com.meibo.web.order.viewmodel;

public class BaseMediaOrderListQueryViewmodel {
	
	private Integer orderStatus;
	private String orderCode;
	private Integer pageNum;
	private Integer pageRecorders;
	private Integer memberId;
	private Integer isLimit;
	private Integer beginLimit;
	private Integer endLimit;
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	public Integer getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
	public Integer getBeginLimit() {
		return beginLimit;
	}
	public void setBeginLimit(Integer beginLimit) {
		this.beginLimit = beginLimit;
	}
	public Integer getEndLimit() {
		return endLimit;
	}
	public void setEndLimit(Integer endLimit) {
		this.endLimit = endLimit;
	}

}
