package com.meibo.web.member.entity;

import java.util.Date;

import com.meibo.web.member.viewmodel.AuthCompanyViewmodel;

public class MemberCompanyEntity extends AuthCompanyViewmodel {
	
	private Integer memberCompanyId;
	private Integer status;
	private Integer auditUser;
	private Date createDate;
	private Date auditDate;
	private String auditMsg;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * @return the memberCompanyId
	 */
	public Integer getMemberCompanyId() {
		return memberCompanyId;
	}
	/**
	 * @param memberCompanyId the memberCompanyId to set
	 */
	public void setMemberCompanyId(Integer memberCompanyId) {
		this.memberCompanyId = memberCompanyId;
	}
	/**
	 * @return the auditMsg
	 */
	public String getAuditMsg() {
		return auditMsg;
	}
	/**
	 * @param auditMsg the auditMsg to set
	 */
	public void setAuditMsg(String auditMsg) {
		this.auditMsg = auditMsg;
	}

}
