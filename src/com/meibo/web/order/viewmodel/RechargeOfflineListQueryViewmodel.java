package com.meibo.web.order.viewmodel;

import com.meibo.web.common.viewmodel.BasePageViewModel;

public class RechargeOfflineListQueryViewmodel extends BasePageViewModel {
	
	private Integer orderStatus;

	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

}
