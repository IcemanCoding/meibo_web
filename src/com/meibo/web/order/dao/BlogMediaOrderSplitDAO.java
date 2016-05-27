package com.meibo.web.order.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.meibo.web.order.entity.BlogMediaOrderSplitEntity;
import com.meibo.web.order.entity.NewsMediaOrderSplitEntity;

public interface BlogMediaOrderSplitDAO {
	
	BigDecimal countOrderSplitTransAmountByOrderId( Map<String, Object> params );

	BlogMediaOrderSplitEntity selectBlogMediaOrderSplitById( Integer orderSplitId );

	void updateBlogMediaOrderSplitById( Map<String, Object> params );

	List<BlogMediaOrderSplitEntity> selectBlogMediaOrderSplitByOrderId( Integer orderId );

}
