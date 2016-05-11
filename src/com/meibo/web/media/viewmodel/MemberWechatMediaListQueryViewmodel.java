package com.meibo.web.media.viewmodel;

import java.math.BigDecimal;

public class MemberWechatMediaListQueryViewmodel extends BaseMediaListQueryViewmodel {

	private String typeIds;
	private String mediaName;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;
	private Integer minFansCount;
	private Integer maxFansCount;
	private String areaIds;
	
	public String getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
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
	public String getAreaIds() {
		return areaIds;
	}
	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}
	
}
