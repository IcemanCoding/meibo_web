package com.meibo.web.order.service;

import java.util.Date;
import java.util.Map;

import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.NewsMediaOrderDetailDTO;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.NewsMediaCommitOrderViewmodel;

public interface NewsMediaOrderService {
	
	Integer commitNewsMediaOrder( NewsMediaCommitOrderViewmodel viewmodel ) throws Exception;
	
	Map<String, Object> getNewsMediaOrderList( BaseMediaOrderListQueryViewmodel viewmodel ) throws Exception;

	NewsMediaOrderDetailDTO getNewsMediaOrderDetail( Integer orderId ) throws Exception;
	
	Boolean editNewsMediaOrderSplitStatus( Integer orderSplitId, Integer procType ) throws Exception;
	
	Boolean editNewsMediaOrderLaunch( Integer orderSplitId, String launchUrl, Date launchDate ) throws Exception;
	
	BaseMediaOrderStatusDetailDTO getOrderSplitStatusDetail( Integer orderSplitId ) throws Exception;
	
}
