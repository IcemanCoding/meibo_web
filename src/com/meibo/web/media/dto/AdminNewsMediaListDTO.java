package com.meibo.web.media.dto;

import java.util.Date;

public class AdminNewsMediaListDTO extends BaseNewsMediaListDTO {
	
	private String creator;
	private Date createdate;
	private Integer auditStatus;
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
}
