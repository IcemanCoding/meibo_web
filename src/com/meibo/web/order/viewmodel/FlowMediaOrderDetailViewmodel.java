package com.meibo.web.order.viewmodel;

import com.meibo.web.common.viewmodel.BasePageViewModel;

public class FlowMediaOrderDetailViewmodel extends BasePageViewModel {
	
	private Integer orderId;

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
