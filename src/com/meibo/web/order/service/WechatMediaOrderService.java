package com.meibo.web.order.service;

import java.util.Date;
import java.util.Map;

import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.WechatMediaOrderDetailDTO;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.WechatMediaCommitOrderViewmodel;

public interface WechatMediaOrderService {

	Integer commitWechatMediaOrder( WechatMediaCommitOrderViewmodel viewmodel ) throws Exception;

	Map<String, Object> getWechatMediaOrderList( BaseMediaOrderListQueryViewmodel viewmodel );

	WechatMediaOrderDetailDTO getWechatMediaOrderDetail( Integer orderId );

	BaseMediaOrderStatusDetailDTO getOrderSplitStatusDetail( Integer orderSplitId );

	Boolean editWechatMediaOrderSplitStatus( Integer orderSplitId, Integer procType );

	Boolean editWechatMediaOrderLaunch( Integer orderSplitId, String launchUrl, Date launchDate );

	Integer payWechatMediaOrder( Integer orderId ) throws Exception;

}
