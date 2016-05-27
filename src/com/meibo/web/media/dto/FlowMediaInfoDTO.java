package com.meibo.web.media.dto;

import java.math.BigDecimal;

public class FlowMediaInfoDTO {
	
	private Integer packageId;
	private String packageName;
	private BigDecimal price;
	// 运营商类型：1-电信 2-联通 3-移动
	private Integer carrierType;
	private String carrierName;
	
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(Integer carrierType) {
		this.carrierType = carrierType;
	}
	/**
	 * @return the carrierName
	 */
	public String getCarrierName() {
		return carrierName;
	}
	/**
	 * @param carrierName the carrierName to set
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

}
