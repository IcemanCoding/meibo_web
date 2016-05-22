package com.meibo.web.order.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.order.dto.BaseMediaOrderListDTO;
import com.meibo.web.order.entity.BlogMediaOrderDetailEntity;
import com.meibo.web.order.entity.BlogMediaOrderSplitEntity;
import com.meibo.web.order.entity.MediaOrderLaunchDetailEntity;
import com.meibo.web.order.entity.NewsMediaOrderDetailEntity;
import com.meibo.web.order.entity.NewsMediaOrderSplitEntity;
import com.meibo.web.order.entity.OrderInfoEntity;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;

public interface BlogMediaOrderDAO {
	
	void insertOrderBlogInfo( OrderInfoEntity orderInfoEntity );

	void insertOrderBlogSplit( BlogMediaOrderSplitEntity orderSplit );

	void insertOrderBlogDetail( BlogMediaOrderDetailEntity orderDetail );

	void updateOrderBlogInfoStatus( OrderInfoEntity orderInfo );

	void updateOrderBlogSplitStatus( Map<String, Object> params );

	List<BaseMediaOrderListDTO> selectOrderListByAdmin( BaseMediaOrderListQueryViewmodel viewmodel );

	Integer selectOrderListByAdminCount( BaseMediaOrderListQueryViewmodel viewmodel );

	List<String> selectOrderSplitMediaId( Map<String, Object> params );

	List<BaseMediaOrderListDTO> selectOrderListBySupplier( BaseMediaOrderListQueryViewmodel viewmodel );

	Integer selectOrderListBySupplierCount( BaseMediaOrderListQueryViewmodel viewmodel );

	List<BaseMediaOrderListDTO> selectOrderListByMember( BaseMediaOrderListQueryViewmodel viewmodel );

	Integer selectOrderListByMemberCount( BaseMediaOrderListQueryViewmodel viewmodel );
	
	BlogMediaOrderDetailEntity selectBlogMediaOrderDetailByOrderId( Integer orderId );

	OrderInfoEntity selectBlogMediaOrderInfoByOrderId( Integer orderId );

	List<MediaOrderLaunchDetailEntity> selectOrderLaunchDetailByOrderId( Integer orderId );
	
	
}
