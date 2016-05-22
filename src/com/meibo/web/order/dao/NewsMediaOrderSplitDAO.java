package com.meibo.web.order.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.meibo.web.order.entity.NewsMediaOrderSplitEntity;

public interface NewsMediaOrderSplitDAO {
	
	void updateNewsMediaOrderSplitById( Map<String, Object> params );
	
	NewsMediaOrderSplitEntity selectNewsMediaOrderSplitById( Integer orderSplitId );
	
	BigDecimal countOrderSplitTransAmountByOrderId( Map<String, Object> params );

}
