package com.meibo.web.order.dao;

import com.meibo.web.order.entity.FlowMediaOrderDetailEntity;

public interface FlowMediaOrderDetailDAO {
	
	void insertOrderFlowDetail( FlowMediaOrderDetailEntity entity );
	
	FlowMediaOrderDetailEntity selectOrderFlowDetailByOrderId( Integer orderId );

}
