package com.meibo.web.media.dto;

import java.math.BigDecimal;

public class BaseNewsMediaListDTO {
	
	private String mediaName;
	private String mediaType;
	private String mediaColumn;
	private String imageUrl;
	private Integer includeType;
	private String title;
	private Integer allowLink;
	private Integer allowQRCode;
	private Integer allowContactWay;
	private String linkUrl;
	private Integer areaId;
	private BigDecimal quotePrice;
	private String remark;
	private Integer newsMediaId;
	
	public String getMediaName() {
		return mediaName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAllowLink() {
		return allowLink;
	}
	public void setAllowLink(Integer allowLink) {
		this.allowLink = allowLink;
	}
	public Integer getAllowQRCode() {
		return allowQRCode;
	}
	public void setAllowQRCode(Integer allowQRCode) {
		this.allowQRCode = allowQRCode;
	}
	public Integer getAllowContactWay() {
		return allowContactWay;
	}
	public void setAllowContactWay(Integer allowContactWay) {
		this.allowContactWay = allowContactWay;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaColumn() {
		return mediaColumn;
	}
	public void setMediaColumn(String mediaColumn) {
		this.mediaColumn = mediaColumn;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getIncludeType() {
		return includeType;
	}
	public void setIncludeType(Integer includeType) {
		this.includeType = includeType;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public BigDecimal getQuotePrice() {
		return quotePrice;
	}
	public void setQuotePrice(BigDecimal quotePrice) {
		this.quotePrice = quotePrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getNewsMediaId() {
		return newsMediaId;
	}
	public void setNewsMediaId(Integer newsMediaId) {
		this.newsMediaId = newsMediaId;
	}
	
}
