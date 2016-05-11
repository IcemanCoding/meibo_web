package com.meibo.web.media.dto;

public class AdminBlogMediaListDTO extends BaseBlogMediaListDTO {
	
	private Integer auditStatus;
	private String createdUser;
	
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	
}
