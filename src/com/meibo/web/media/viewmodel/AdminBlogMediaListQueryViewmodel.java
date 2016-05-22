package com.meibo.web.media.viewmodel;

public class AdminBlogMediaListQueryViewmodel extends BaseMediaListQueryViewmodel {

	private Integer createdUser;
	private Integer auditStatus;

	public Integer getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
