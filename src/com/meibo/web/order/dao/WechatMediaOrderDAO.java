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
import com.meibo.web.order.entity.WechatMediaOrderDetailEntity;
import com.meibo.web.order.entity.WechatMediaOrderSplitEntity;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;

public interface WechatMediaOrderDAO {
	
	void insertOrderWechatInfo( OrderInfoEntity orderInfoEntity );

	void insertWechatBlogSplit( WechatMediaOrderSplitEntity orderSplit );

	void insertOrderWechatDetail( WechatMediaOrderDetailEntity orderDetail );

	void updateOrderWechatInfoStatus( OrderInfoEntity orderInfo );

	void updateOrderWechatSplitStatus( Map<String, Object> params );

	List<BaseMediaOrderListDTO> selectOrderListByAdmin( BaseMediaOrderListQueryViewmodel viewmodel );

	Integer selectOrderListByAdminCount( BaseMediaOrderListQueryViewmodel viewmodel );

	List<String> selectOrderSplitMediaId( Map<String, Object> params );

	List<BaseMediaOrderListDTO> selectOrderListBySupplier( BaseMediaOrderListQueryViewmodel viewmodel );

	Integer selectOrderListBySupplierCount( BaseMediaOrderListQueryViewmodel viewmodel );

	List<BaseMediaOrderListDTO> selectOrderListByMember( BaseMediaOrderListQueryViewmodel viewmodel );

	Integer selectOrderListByMemberCount( BaseMediaOrderListQueryViewmodel viewmodel );

	
	WechatMediaOrderDetailEntity selectWechatMediaOrderDetailByOrderId( Integer orderId );

	OrderInfoEntity selectWechatMediaOrderInfoByOrderId( Integer orderId );

	List<MediaOrderLaunchDetailEntity> selectOrderLaunchDetailByOrderId( Integer orderId );

	
	
}
