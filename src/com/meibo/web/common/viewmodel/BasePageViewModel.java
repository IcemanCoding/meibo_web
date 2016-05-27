package com.meibo.web.common.viewmodel;

public class BasePageViewModel {
	
	private Integer pageNum;
	private Integer pageRecorders;
	private Integer memberId;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageRecorders() {
		return pageRecorders;
	}
	public void setPageRecorders(Integer pageRecorders) {
		this.pageRecorders = pageRecorders;
	}
	/**
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}
