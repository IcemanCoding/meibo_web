package com.meibo.web.order.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.meibo.web.order.entity.BlogMediaOrderSplitEntity;
import com.meibo.web.order.entity.NewsMediaOrderSplitEntity;
import com.meibo.web.order.entity.OrderInfoEntity;
import com.meibo.web.order.entity.WechatMediaOrderSplitEntity;

public interface WechatMediaOrderSplitDAO {
	
	BigDecimal countOrderSplitTransAmountByOrderId( Map<String, Object> params );

	WechatMediaOrderSplitEntity selectWechatMediaOrderSplitById( Integer orderSplitId );

	void updateWechatMediaOrderSplitById( Map<String, Object> params );

	List<WechatMediaOrderSplitEntity> selectWechatMediaOrderSplitByOrderId( Integer orderId );



}
