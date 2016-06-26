package com.meibo.web.member.viewmodel;

import com.meibo.web.common.viewmodel.BasePageViewModel;

public class CompanyListViewmodel extends BasePageViewModel {
	
	private Integer auditStatus;
	private Integer beginLimit;
	private Integer endLimit;

	/**
	 * @return the auditStatus
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the endLimit
	 */
	public Integer getEndLimit() {
		return endLimit;
	}

	/**
	 * @param endLimit the endLimit to set
	 */
	public void setEndLimit(Integer endLimit) {
		this.endLimit = endLimit;
	}

	/**
	 * @return the beginLimit
	 */
	public Integer getBeginLimit() {
		return beginLimit;
	}

	/**
	 * @param beginLimit the beginLimit to set
	 */
	public void setBeginLimit(Integer beginLimit) {
		this.beginLimit = beginLimit;
	}

}
