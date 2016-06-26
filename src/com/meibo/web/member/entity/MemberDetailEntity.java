package com.meibo.web.member.entity;

import java.util.Date;

public class MemberDetailEntity {
	
	private Integer memberDetailId;
	private Integer memberId;
	private Integer isAuthCompany;
	private Date createDate;
	
	public Integer getMemberDetailId() {
		return memberDetailId;
	}
	public void setMemberDetailId(Integer memberDetailId) {
		this.memberDetailId = memberDetailId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getIsAuthCompany() {
		return isAuthCompany;
	}
	public void setIsAuthCompany(Integer isAuthCompany) {
		this.isAuthCompany = isAuthCompany;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
