package com.meibo.web.member.viewmodel;

import java.util.Date;

import com.meibo.web.common.viewmodel.BasePageViewModel;

public class AccountDetailQueryViewmodel extends BasePageViewModel {

	private Integer orderType;
	private Date beginDate;
	private Date endDate;
	
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
