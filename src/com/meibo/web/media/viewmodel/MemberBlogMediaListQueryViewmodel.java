package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;

public class MemberBlogMediaListQueryViewmodel extends BaseMediaListQueryViewmodel {
	
	private Integer minFansCount;
	private Integer maxFansCount;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private Integer areaId;
	private Integer typeId;
	private String mediaName;
	private String rate;
	
	public Integer getMinFansCount() {
		return minFansCount;
	}
	public void setMinFansCount(Integer minFansCount) {
		this.minFansCount = minFansCount;
	}
	public Integer getMaxFansCount() {
		return maxFansCount;
	}
	public void setMaxFansCount(Integer maxFansCount) {
		this.maxFansCount = maxFansCount;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

}
