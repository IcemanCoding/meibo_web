package com.meibo.web.order.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.order.dto.RechargeOfflineDTO;
import com.meibo.web.order.entity.RechargeOrderEntity;

public interface RechargeOrderDAO {
	
	void insertRechargeOrder( RechargeOrderEntity entity );
	
	RechargeOrderEntity selectRechargeOrderByOrderId( Integer orderId );
	
	RechargeOrderEntity selectRechargeOrderByVoucherNum( String voucherNum );
	
	void updateRechargeOrder( RechargeOrderEntity entity );

	List<RechargeOfflineDTO> selectRechargeOrderListByConditions( Map<String, Object> params );

	Integer selectRechargeOrderListCountByConditions( Map<String, Object> params );

}
