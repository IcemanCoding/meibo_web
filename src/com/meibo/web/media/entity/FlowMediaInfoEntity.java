package com.meibo.web.media.entity;

import java.math.BigDecimal;

public class FlowMediaInfoEntity {
	
	private Integer id;
	private Integer platformId;
	private Integer packageId;
	private String packageName;
	private BigDecimal price;
	// 运营商类型：1-电信 2-联通 3-移动
	private Integer carrierType;
	private String carrierName;
	private String explain;
	private Integer status;
	
	public Integer getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}
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
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
