package com.meibo.web.order.viewmodel;

public class FlowMediaOrderBatchAddViewmodel {
	
	private Integer dxPackageId;
	private Integer ltPackageId;
	private Integer ydPackageId;
	private String[] mobile;
	private String orderName;
	private Integer memberId;
	
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
	public String[] getMobile() {
		return mobile;
	}
	public void setMobile(String[] mobile) {
		this.mobile = mobile;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	/**
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}
