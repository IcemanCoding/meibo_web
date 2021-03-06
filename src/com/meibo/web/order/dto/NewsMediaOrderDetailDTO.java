package com.meibo.web.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.meibo.web.order.entity.MediaOrderLaunchDetailEntity;

public class NewsMediaOrderDetailDTO {
	
	private String executeDate;
	private String title;
	private String resourceLink;
	private String orderUploadFile;
	private String remark;
	private Integer orderStatus;
	private BigDecimal transAmount;
	private List<MediaOrderLaunchDetailEntity> launchDetail;
	
	public String getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResourceLink() {
		return resourceLink;
	}
	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}
	public String getOrderUploadFile() {
		return orderUploadFile;
	}
	public void setOrderUploadFile(String orderUploadFile) {
		this.orderUploadFile = orderUploadFile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public BigDecimal getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}
	public List<MediaOrderLaunchDetailEntity> getLaunchDetail() {
		return launchDetail;
	}
	public void setLaunchDetail(List<MediaOrderLaunchDetailEntity> launchDetail) {
		this.launchDetail = launchDetail;
	}

}
