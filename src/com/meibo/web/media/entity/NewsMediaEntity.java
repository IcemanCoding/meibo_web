package com.meibo.web.media.entity;

import java.math.BigDecimal;
import java.util.Date;

public class NewsMediaEntity {
	
	// 渠道ID
	private Integer channelId;
	// 类目
	private Integer columnId;
	// 地区
	private Integer areaId;
	// 收录类型
	private Integer includeType;
	// 报价
	private BigDecimal quotePrice;
	// 备注
	private String remark;
	// 标题
	private String title;
	// 是否允许带链接
	private Integer allowLink;
	// 是否允许带二维码
	private Integer allowQRCode;
	// 是否允许带联系方式
	private Integer allowContactWay;
	// 本地图片路径
	private String imageUrl;
	// 审核状态： 0-待审核
	private Integer auditStatus;
	// 创建人
	private Integer createdUser;
	
	private Integer auditUser;
	private Date auditDate;
	
	private String channelName;
	private String channelType;
	private String channelColumn;
	private String linkUrl;
	private Integer newsMediaId;
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannelColumn() {
		return channelColumn;
	}
	public void setChannelColumn(String channelColumn) {
		this.channelColumn = channelColumn;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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
	public Integer getIncludeType() {
		return includeType;
	}
	public void setIncludeType(Integer includeType) {
		this.includeType = includeType;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}
	public Integer getColumnId() {
		return columnId;
	}
	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}
	public Integer getNewsMediaId() {
		return newsMediaId;
	}
	public void setNewsMediaId(Integer newsMediaId) {
		this.newsMediaId = newsMediaId;
	}
	public Integer getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}
