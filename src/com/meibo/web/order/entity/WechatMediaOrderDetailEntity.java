package com.meibo.web.order.entity;

import java.util.Date;

public class WechatMediaOrderDetailEntity {
	
	private Integer orderId;
	private String executeDate;
	private String resourceLink;
	private String textContent;
	private String title;
	private String originalLink;
	private String fileUrl;
	private String remark;
	private Date createDate;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	public String getResourceLink() {
		return resourceLink;
	}
	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	/**
	 * @return the originalLink
	 */
	public String getOriginalLink() {
		return originalLink;
	}
	/**
	 * @param originalLink the originalLink to set
	 */
	public void setOriginalLink(String originalLink) {
		this.originalLink = originalLink;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
