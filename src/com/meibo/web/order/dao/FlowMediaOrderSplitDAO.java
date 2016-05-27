package com.meibo.web.order.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.order.dto.FlowMediaOrderDetailDTO;
import com.meibo.web.order.entity.FlowMediaOrderSplitEntity;

public interface FlowMediaOrderSplitDAO {
	
	void insertOrderFlowSplit( FlowMediaOrderSplitEntity entity );

	void updateOrderFlowSplitStatus( Map<String, Object> params );
	
	List<FlowMediaOrderSplitEntity> selectOrderFlowSplitByOrderId( Integer orderId );
	
	List<Integer> selectPackageIdByOrderId( Integer orderId );

	List<FlowMediaOrderDetailDTO> selectOrderFlowDetailByConditions( Map<String, Object> params );

	Integer selectOrderFlowDetailCountByConditions( Map<String, Object> params );

}
