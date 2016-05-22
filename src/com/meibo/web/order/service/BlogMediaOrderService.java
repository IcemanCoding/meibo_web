package com.meibo.web.order.service;

import java.util.Date;
import java.util.Map;

import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.BlogMediaOrderDetailDTO;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.BlogMediaCommitOrderViewmodel;

public interface BlogMediaOrderService {
	
	Integer commitBlogMediaOrder( BlogMediaCommitOrderViewmodel viewmodel ) throws Exception;

	Map<String, Object> getBlogMediaOrderList( BaseMediaOrderListQueryViewmodel viewmodel );

	BlogMediaOrderDetailDTO getBlogMediaOrderDetail( Integer orderId );

	BaseMediaOrderStatusDetailDTO getOrderSplitStatusDetail( Integer orderSplitId );

	Boolean editBlogMediaOrderSplitStatus( Integer orderSplitId, Integer procType );

	Boolean editBlogMediaOrderLaunch( Integer orderSplitId, String launchUrl, Date launchDate);

}
