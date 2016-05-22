package com.meibo.web.order.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.order.dto.BaseMediaOrderListDTO;
import com.meibo.web.order.entity.MediaOrderLaunchDetailEntity;
import com.meibo.web.order.entity.NewsMediaOrderDetailEntity;
import com.meibo.web.order.entity.NewsMediaOrderSplitEntity;
import com.meibo.web.order.entity.OrderInfoEntity;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;

public interface NewsMediaOrderDAO {
	
	void insertOrderNewsInfo( OrderInfoEntity orderInfoEntity );
	
	void insertOrderNewsSplit( NewsMediaOrderSplitEntity newsOrderSplit );
	
	void insertOrderNewsDetail( NewsMediaOrderDetailEntity newsOrderDetail );
	
	void updateOrderNewsInfoStatus( OrderInfoEntity orderInfoEntity );

	void updateOrderNewsSplitStatus( Map<String, Object> params );
	
	List<BaseMediaOrderListDTO> selectOrderListByAdmin( BaseMediaOrderListQueryViewmodel viewmodel );
	
	Integer selectOrderListByAdminCount( BaseMediaOrderListQueryViewmodel viewmodel );
	
	List<BaseMediaOrderListDTO> selectOrderListBySupplier( BaseMediaOrderListQueryViewmodel viewmodel );
	
	Integer selectOrderListBySupplierCount( BaseMediaOrderListQueryViewmodel viewmodel );
	
	List<BaseMediaOrderListDTO> selectOrderListByMember( BaseMediaOrderListQueryViewmodel viewmodel );
	
	Integer selectOrderListByMemberCount( BaseMediaOrderListQueryViewmodel viewmodel );

	List<String> selectOrderSplitMediaId( Map<String, Object> params );
	
	NewsMediaOrderDetailEntity selectNewsMediaOrderDetailByOrderId( Integer orderId );
	
	OrderInfoEntity selectNewsMediaOrderInfoByOrderId( Integer orderId );
	
	List<NewsMediaOrderSplitEntity> selectNewsMediaSplitByOrderId( Integer orderId );
	
	List<MediaOrderLaunchDetailEntity> selectOrderLaunchDetailByOrderId( Integer orderId );
	
}
