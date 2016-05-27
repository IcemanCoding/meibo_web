package com.meibo.web.order.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.order.dto.FlowMediaOrderDTO;
import com.meibo.web.order.entity.FlowMediaOrderEntity;

public interface FlowMediaOrderDAO {

	void insertOrderFlowInfo( FlowMediaOrderEntity entity );
	
	void updateOrderFlowInfoStatus( FlowMediaOrderEntity entity );
	
	FlowMediaOrderEntity selectOrderFlowInfoByOrderId( Integer orderId );

	List<FlowMediaOrderDTO> selectOrderFlowListByConditions( Map<String, Object> params );
	
	Integer selectOrderFlowListCountByConditions( Map<String, Object> params );
	
}
