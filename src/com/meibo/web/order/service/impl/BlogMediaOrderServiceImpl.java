package com.meibo.web.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.UploadUtils;
import com.meibo.web.media.entity.BlogMediaInfoEntity;
import com.meibo.web.media.service.BlogMediaService;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.order.dao.BlogMediaOrderDAO;
import com.meibo.web.order.dao.BlogMediaOrderSplitDAO;
import com.meibo.web.order.dto.BaseMediaOrderListDTO;
import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.BlogMediaOrderDetailDTO;
import com.meibo.web.order.dto.BlogMediaOrderSplitDTO;
import com.meibo.web.order.entity.BlogMediaOrderDetailEntity;
import com.meibo.web.order.entity.BlogMediaOrderSplitEntity;
import com.meibo.web.order.entity.MediaOrderLaunchDetailEntity;
import com.meibo.web.order.entity.OrderInfoEntity;
import com.meibo.web.order.service.BlogMediaOrderService;
import com.meibo.web.order.service.OrderInfoService;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.BlogMediaCommitOrderViewmodel;
import com.meibo.web.system.model.ConsumeTransModel;
import com.meibo.web.system.service.TradeCenterService;

public class BlogMediaOrderServiceImpl implements BlogMediaOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private BlogMediaService blogMediaService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private TradeCenterService tradeCenterService;
	
	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Autowired
	private BlogMediaOrderDAO blogMediaOrderDao;
	
	@Autowired
	private BlogMediaOrderSplitDAO blogMediaOrderSplitDao;
	
	@Override
	public Integer commitBlogMediaOrder( BlogMediaCommitOrderViewmodel viewmodel ) throws Exception {
		
		// init orderCode
		String orderCode = orderInfoService.buildOrderCode( 2, viewmodel.getMemberId() );
		
		// get transAmount by blogMediaId
		BigDecimal transAmount = blogMediaService.getOrderAmountById( viewmodel.getBlogMediaId(), viewmodel.getSelectedId() );
		
		// insert order_info
		Date nowDate = new Date();
		OrderInfoEntity orderInfo = new OrderInfoEntity();
		orderInfo.setTransAmount( transAmount );
		orderInfo.setMemberId( viewmodel.getMemberId() );
		orderInfo.setOrderCode( orderCode );
		orderInfo.setOrderStatus( 1 );
		orderInfo.setRemarkMsg( "" );
		orderInfo.setTransType( 1 );
		orderInfo.setOrderDate( nowDate );
		orderInfo.setCreateDate( nowDate );
		
		blogMediaOrderDao.insertOrderBlogInfo( orderInfo );
		Integer orderId = orderInfo.getOrderId();
		
		// get blogMedia list by blogMediaId
		List<BlogMediaOrderSplitDTO> blogMediaDtos = blogMediaService.getOrderSplitDtoById( viewmodel.getBlogMediaId(), viewmodel.getSelectedId() );
		for ( int i = 0; i < blogMediaDtos.size(); i++ ) {
			
			BlogMediaOrderSplitDTO blogMediaDto = blogMediaDtos.get( i );
			
			// insert order_split
			BlogMediaOrderSplitEntity orderSplit = new BlogMediaOrderSplitEntity();
			orderSplit.setCreateDate( nowDate );
			orderSplit.setMediaMemberId( blogMediaDto.getMediaMemberId() );
			orderSplit.setBlogMediaId( blogMediaDto.getBlogMediaId() );
			orderSplit.setOrderId( orderId );
			orderSplit.setOrderStatus( 1 );
			orderSplit.setTransAmount( blogMediaDto.getTransAmount() );
			orderSplit.setPriceType( blogMediaDto.getPriceType() );
			
			blogMediaOrderDao.insertOrderBlogSplit( orderSplit );
			
		}
		
		// upload file
		String fileUrl = UploadUtils.uploadFile( viewmodel.getUploadFile(), 8, viewmodel.getRootDirProject() );
				
		// insert order_detail
		BlogMediaOrderDetailEntity orderDetail = new BlogMediaOrderDetailEntity();
		orderDetail.setCreateDate( nowDate );
		orderDetail.setExecuteDate( viewmodel.getExecuteDate() );
		orderDetail.setFileUrl( fileUrl );
		orderDetail.setOrderId( orderId );
		orderDetail.setRemark( viewmodel.getRemark() );
		orderDetail.setResourceLink( viewmodel.getResourceLink() );
		orderDetail.setTextContent( viewmodel.getContent() );
		
		blogMediaOrderDao.insertOrderBlogDetail( orderDetail );
		
		// trade
		MemberAccountDTO memberAccount = memberAccountService.getMemberAccount( viewmodel.getMemberId() );
		BigDecimal accountBal = memberAccount.getAvailableBalance();
		if ( accountBal.compareTo( transAmount ) < 0 ) {
			// 余额不足
			return -2;
		}
		
		ConsumeTransModel transModel = new ConsumeTransModel();
		transModel.setMemberId( viewmodel.getMemberId() );
		transModel.setOrderId( orderId );
		transModel.setTransAmount( transAmount );
		transModel.setTransCode( 1 );
		transModel.setTransType( 2 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			orderInfo.setPayDate( new Date() );
			orderInfo.setOrderStatus( 2 );
			
			blogMediaOrderDao.updateOrderBlogInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			blogMediaOrderDao.updateOrderBlogSplitStatus( params );
			
		}
		
		return 1;
		
	}

	@Override
	public Map<String, Object> getBlogMediaOrderList( BaseMediaOrderListQueryViewmodel viewmodel ) {
		
		// get member_info entity
		Map<String, Object> param = new HashMap<String, Object>();
		param.put( "memberId", viewmodel.getMemberId() );
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( param );
		
		Integer pageNum = viewmodel.getPageNum();
		Integer recorders = viewmodel.getPageRecorders();
		if ( pageNum == null || recorders == null ) {
			viewmodel.setIsLimit( 0 );
		} else {
			viewmodel.setBeginLimit( ( pageNum - 1 ) * recorders );
			viewmodel.setEndLimit( recorders );
			viewmodel.setIsLimit( 1 );
		}
		
		List<BaseMediaOrderListDTO> orderList = new ArrayList<BaseMediaOrderListDTO>();
		Integer totalRows = 0;
		
		if ( memberInfo.getMemberType() == 3 ) {
			
			// admin
			List<BaseMediaOrderListDTO> orderListTemp = blogMediaOrderDao.selectOrderListByAdmin( viewmodel );
			totalRows = blogMediaOrderDao.selectOrderListByAdminCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = blogMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				orderList.add( orderDto );
				
			}
			
		} else if ( memberInfo.getMemberType() == 2 ) {
			
			// supplier
			List<BaseMediaOrderListDTO> orderListTemp = blogMediaOrderDao.selectOrderListBySupplier( viewmodel );
			totalRows = blogMediaOrderDao.selectOrderListBySupplierCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = blogMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				// get order transAmount
				params.put( "memberId", viewmodel.getMemberId() );
				BigDecimal transAmount = blogMediaOrderSplitDao.countOrderSplitTransAmountByOrderId( params );
				orderDto.setTransAmount( transAmount );
				
				orderList.add( orderDto );
				
			}
			
		} else {
			
			// user
			List<BaseMediaOrderListDTO> orderListTemp = blogMediaOrderDao.selectOrderListByMember( viewmodel );
			totalRows = blogMediaOrderDao.selectOrderListByMemberCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = blogMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				orderList.add( orderDto );
				
			}
			
		}
		
		if ( orderList == null || orderList.size() == 0 ) {
			return null;
		}
		
		Integer totalPages = 0;
		 
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		
		Map<String, Object> resData = new HashMap<String, Object>();
		resData.put( "orderList", orderList );
		resData.put( "totalPages", totalPages );
		resData.put( "totalRows", totalRows );
		
		return resData;
		
	}

	@Override
	public BlogMediaOrderDetailDTO getBlogMediaOrderDetail( Integer orderId ) {
		
		BlogMediaOrderDetailDTO dto = new BlogMediaOrderDetailDTO();
		
		BlogMediaOrderDetailEntity orderDetail = blogMediaOrderDao.selectBlogMediaOrderDetailByOrderId( orderId );
		OrderInfoEntity orderInfo = blogMediaOrderDao.selectBlogMediaOrderInfoByOrderId( orderId );
		List<MediaOrderLaunchDetailEntity> launchDetail = blogMediaOrderDao.selectOrderLaunchDetailByOrderId( orderId );
		
		dto.setLaunchDetail( launchDetail );
		dto.setExecuteDate( orderDetail.getExecuteDate() );
		dto.setOrderStatus( orderInfo.getOrderStatus() );
		dto.setOrderUploadFile( orderDetail.getFileUrl() );
		dto.setRemark( orderDetail.getRemark() );
		dto.setResourceLink( orderDetail.getResourceLink() );
		dto.setContent( orderDetail.getTextContent() );
		dto.setTransAmount( orderInfo.getTransAmount() );
		
		return dto;
		
	}

	@Override
	public BaseMediaOrderStatusDetailDTO getOrderSplitStatusDetail( Integer orderSplitId ) {
		
		BaseMediaOrderStatusDetailDTO orderStatusDetail = new BaseMediaOrderStatusDetailDTO();
		
		BlogMediaOrderSplitEntity orderSplit = blogMediaOrderSplitDao.selectBlogMediaOrderSplitById( orderSplitId );
		OrderInfoEntity orderInfo = blogMediaOrderDao.selectBlogMediaOrderInfoByOrderId( orderSplit.getOrderId() );
		
		orderStatusDetail.setAcceptDate( orderSplit.getAcceptDate() );
		orderStatusDetail.setFinishDate( orderSplit.getFinishDate() );
		orderStatusDetail.setLaunchDate( orderSplit.getLaunchDate() );
		orderStatusDetail.setOrderDate( orderInfo.getOrderDate() );
		orderStatusDetail.setOrderStatus( orderSplit.getOrderStatus() );
		orderStatusDetail.setRejectDate( orderSplit.getRejectDate() );
		
		return orderStatusDetail;
		
	}

	@Override
	public Boolean editBlogMediaOrderSplitStatus( Integer orderSplitId, Integer procType ) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		Integer orderStatus = null;
		
		if ( procType == 1 ) {
			
			// 接受
			orderStatus = 3;
			params.put( "acceptDate", new Date() );
			
		} else if ( procType == 2 ) {
			
			// 拒绝
			orderStatus = 7;
			params.put( "rejectDate", new Date() );
		
		} else if ( procType == 3 ) {
			
			// 已投放
			BlogMediaOrderSplitEntity orderSplit = blogMediaOrderSplitDao.selectBlogMediaOrderSplitById( orderSplitId );
			if ( orderSplit.getLaunchDate() == null || orderSplit.getLaunchUrl() == null || orderSplit.getLaunchUrl().equals( "" ) ) {
				return false;
			}
			orderStatus = 5;
			params.put( "finishDate", new Date() );
			
		} else {
			return false;
		}
		
		params.put( "orderSplitId", orderSplitId );
		params.put( "orderStatus", orderStatus );
		blogMediaOrderSplitDao.updateBlogMediaOrderSplitById( params );
		
		return true;
		
	}

	@Override
	public Boolean editBlogMediaOrderLaunch( Integer orderSplitId, String launchUrl, Date launchDate ) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "orderSplitId", orderSplitId );
		params.put( "launchUrl", launchUrl );
		params.put( "launchDate", launchDate );
		
		blogMediaOrderSplitDao.updateBlogMediaOrderSplitById( params );
		
		return true;
		
	}

	@Override
	public Integer payBlogMediaOrder( Integer orderId ) throws Exception {
		
		// get blogMediaOrder
		OrderInfoEntity orderInfo = blogMediaOrderDao.selectBlogMediaOrderInfoByOrderId( orderId ); 
		if ( orderInfo == null || orderInfo.getOrderStatus() != 1 ) {
			return -6;
		}
		
		// get blogMediaOrderSplit
		List<BlogMediaOrderSplitEntity> orderSplitList = blogMediaOrderSplitDao.selectBlogMediaOrderSplitByOrderId( orderId );
		for ( int i = 0; i < orderSplitList.size(); i++ ) {
			
			BlogMediaOrderSplitEntity orderSplit = orderSplitList.get( i );
			Integer blogMediaId = orderSplit.getBlogMediaId();
			BlogMediaInfoEntity blogMedia = blogMediaService.getBlogMediaInfoById( blogMediaId );
			
			if ( blogMedia.getAuditStatus() != 1 || blogMedia.getStatus() != 1 ) {
				return -5;
			}
			
		}
		
		// trade
		MemberAccountDTO memberAccount = memberAccountService.getMemberAccount( orderInfo.getMemberId() );
		BigDecimal accountBal = memberAccount.getAvailableBalance();
		if ( accountBal.compareTo( orderInfo.getTransAmount() ) < 0 ) {
			// 余额不足
			return -2;
		}
		
		ConsumeTransModel transModel = new ConsumeTransModel();
		transModel.setMemberId( orderInfo.getMemberId() );
		transModel.setOrderId( orderId );
		transModel.setTransAmount( orderInfo.getTransAmount() );
		transModel.setTransCode( 1 );
		transModel.setTransType( 2 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			orderInfo.setPayDate( new Date() );
			orderInfo.setOrderStatus( 2 );
			
			blogMediaOrderDao.updateOrderBlogInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			blogMediaOrderDao.updateOrderBlogSplitStatus( params );
			
		}
		
		return 1;
		
	}

}
