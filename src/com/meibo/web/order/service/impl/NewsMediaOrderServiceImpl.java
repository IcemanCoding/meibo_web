package com.meibo.web.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.UploadUtils;
import com.meibo.web.media.dao.NewsMediaDAO;
import com.meibo.web.media.entity.NewsMediaEntity;
import com.meibo.web.media.service.NewsMediaService;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.order.dao.NewsMediaOrderDAO;
import com.meibo.web.order.dao.NewsMediaOrderSplitDAO;
import com.meibo.web.order.dto.BaseMediaOrderListDTO;
import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.NewsMediaOrderDetailDTO;
import com.meibo.web.order.dto.NewsMediaOrderSplitDTO;
import com.meibo.web.order.entity.MediaOrderLaunchDetailEntity;
import com.meibo.web.order.entity.NewsMediaOrderDetailEntity;
import com.meibo.web.order.entity.NewsMediaOrderSplitEntity;
import com.meibo.web.order.entity.OrderInfoEntity;
import com.meibo.web.order.service.NewsMediaOrderService;
import com.meibo.web.order.service.OrderInfoService;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.NewsMediaCommitOrderViewmodel;
import com.meibo.web.system.dao.SystemParamsInfoDAO;
import com.meibo.web.system.model.ConsumeTransModel;
import com.meibo.web.system.service.TradeCenterService;
import com.meibo.web.utils.constants.ConstantsForSystemParams;

public class NewsMediaOrderServiceImpl implements NewsMediaOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private NewsMediaService newsMediaService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private TradeCenterService tradeCenterService;
	
	@Autowired
	private NewsMediaOrderDAO newsMediaOrderDao;
	
	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Autowired
	private NewsMediaDAO newsMediaDao;
	
	@Autowired
	private NewsMediaOrderSplitDAO newsMediaOrderSplitDao;
	
	@Autowired
	private SystemParamsInfoDAO systemParamsInfoDao;
	
	@Override
	public Integer commitNewsMediaOrder( NewsMediaCommitOrderViewmodel viewmodel ) throws Exception {
		
		// init orderCode
		String orderCode = orderInfoService.buildOrderCode( 1, viewmodel.getMemberId() );
		
		// rate
		String rate = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.MEIBO_STAGE_RATE );
		
		// get transAmount by newsMediaId
		BigDecimal transAmount = newsMediaService.getOrderAmountById( viewmodel.getNewsMediaId() );
		transAmount = transAmount.add( transAmount.multiply( new BigDecimal( rate ) ) );
		
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
		
		newsMediaOrderDao.insertOrderNewsInfo( orderInfo );
		Integer orderId = orderInfo.getOrderId();
		
		// get newsMedia list by newsMediaId
		List<NewsMediaOrderSplitDTO> newsMediaDtos = newsMediaService.getOrderSplitDtoById( viewmodel.getNewsMediaId() );
		for ( int i = 0; i < newsMediaDtos.size(); i++ ) {
			
			NewsMediaOrderSplitDTO newsMediaDto = newsMediaDtos.get( i );
			
			// insert order_split
			NewsMediaOrderSplitEntity orderSplit = new NewsMediaOrderSplitEntity();
			orderSplit.setCreateDate( nowDate );
			orderSplit.setMediaMemberId( newsMediaDto.getMediaMemberId() );
			orderSplit.setNewsMediaId( newsMediaDto.getNewsMediaId() );
			orderSplit.setOrderId( orderId );
			orderSplit.setOrderStatus( 1 );
			orderSplit.setTransAmount( newsMediaDto.getTransAmount().add( newsMediaDto.getTransAmount().multiply( new BigDecimal( rate ) ) ) );
			
			newsMediaOrderDao.insertOrderNewsSplit( orderSplit );
			
		}
		
		// upload file
		String fileUrl = UploadUtils.uploadFile( viewmodel.getUploadFile(), 6, viewmodel.getRootDirProject() );
				
		// insert order_detail
		NewsMediaOrderDetailEntity orderDetail = new NewsMediaOrderDetailEntity();
		orderDetail.setCreateDate( nowDate );
		orderDetail.setExecuteDate( viewmodel.getExecuteDate() );
		orderDetail.setFileUrl( fileUrl );
		orderDetail.setOrderId( orderId );
		orderDetail.setRemark( viewmodel.getRemark() );
		orderDetail.setResourceLink( viewmodel.getResourceLink() );
		orderDetail.setTitle( viewmodel.getTitle() );
		
		newsMediaOrderDao.insertOrderNewsDetail( orderDetail );
		
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
		transModel.setTransType( 1 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			orderInfo.setPayDate( new Date() );
			orderInfo.setOrderStatus( 2 );
			
			newsMediaOrderDao.updateOrderNewsInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			newsMediaOrderDao.updateOrderNewsSplitStatus( params );
			
		}
		
		return 1;
		
	}

	@Override
	public Map<String, Object> getNewsMediaOrderList( BaseMediaOrderListQueryViewmodel viewmodel ) throws Exception {
		
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
			List<BaseMediaOrderListDTO> orderListTemp = newsMediaOrderDao.selectOrderListByAdmin( viewmodel );
			totalRows = newsMediaOrderDao.selectOrderListByAdminCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = newsMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				orderList.add( orderDto );
				
			}
			
		} else if ( memberInfo.getMemberType() == 2 ) {
			
			// supplier
			List<BaseMediaOrderListDTO> orderListTemp = newsMediaOrderDao.selectOrderListBySupplier( viewmodel );
			totalRows = newsMediaOrderDao.selectOrderListBySupplierCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = newsMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				// get order transAmount
				params.put( "memberId", viewmodel.getMemberId() );
				BigDecimal transAmount = newsMediaOrderSplitDao.countOrderSplitTransAmountByOrderId( params );
				orderDto.setTransAmount( transAmount );
				
				orderList.add( orderDto );
				
			}
			
		} else {
			
			// user
			List<BaseMediaOrderListDTO> orderListTemp = newsMediaOrderDao.selectOrderListByMember( viewmodel );
			totalRows = newsMediaOrderDao.selectOrderListByMemberCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = newsMediaOrderDao.selectOrderSplitMediaId( params );
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
	public NewsMediaOrderDetailDTO getNewsMediaOrderDetail( Integer orderId )
			throws Exception {
		
		NewsMediaOrderDetailDTO dto = new NewsMediaOrderDetailDTO();
		
		NewsMediaOrderDetailEntity orderDetail = newsMediaOrderDao.selectNewsMediaOrderDetailByOrderId( orderId );
		OrderInfoEntity orderInfo = newsMediaOrderDao.selectNewsMediaOrderInfoByOrderId( orderId );
		List<MediaOrderLaunchDetailEntity> launchDetail =  newsMediaOrderDao.selectOrderLaunchDetailByOrderId( orderId );
		
		dto.setLaunchDetail( launchDetail );
		dto.setExecuteDate( orderDetail.getExecuteDate() );
		dto.setOrderStatus( orderInfo.getOrderStatus() );
		dto.setOrderUploadFile( orderDetail.getFileUrl() );
		dto.setRemark( orderDetail.getRemark() );
		dto.setResourceLink( orderDetail.getResourceLink() );
		dto.setTitle( orderDetail.getTitle() );
		dto.setTransAmount( orderInfo.getTransAmount() );
		
		return dto;
		
	}

	@Override
	public Boolean editNewsMediaOrderSplitStatus( Integer orderSplitId, Integer procType, String rejectMsg ) throws Exception {
		
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
			params.put( "rejectMsg", rejectMsg );
		
		} else if ( procType == 3 ) {
			
			// 已投放
			NewsMediaOrderSplitEntity orderSplit = newsMediaOrderSplitDao.selectNewsMediaOrderSplitById( orderSplitId );
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
		newsMediaOrderSplitDao.updateNewsMediaOrderSplitById( params );
		
		return true;
		
	}

	@Override
	public Boolean editNewsMediaOrderLaunch( Integer orderSplitId, String launchUrl, Date launchDate ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "orderSplitId", orderSplitId );
		params.put( "launchUrl", launchUrl );
		params.put( "launchDate", launchDate );
		
		newsMediaOrderSplitDao.updateNewsMediaOrderSplitById( params );
		
		return true;
	
	}

	@Override
	public BaseMediaOrderStatusDetailDTO getOrderSplitStatusDetail( Integer orderSplitId ) throws Exception {
		
		BaseMediaOrderStatusDetailDTO orderStatusDetail = new BaseMediaOrderStatusDetailDTO();
		
		NewsMediaOrderSplitEntity orderSplit = newsMediaOrderSplitDao.selectNewsMediaOrderSplitById( orderSplitId );
		OrderInfoEntity orderInfo = newsMediaOrderDao.selectNewsMediaOrderInfoByOrderId( orderSplit.getOrderId() );
		
		orderStatusDetail.setAcceptDate( orderSplit.getAcceptDate() );
		orderStatusDetail.setFinishDate( orderSplit.getFinishDate() );
		orderStatusDetail.setLaunchDate( orderSplit.getLaunchDate() );
		orderStatusDetail.setOrderDate( orderInfo.getOrderDate() );
		orderStatusDetail.setOrderStatus( orderSplit.getOrderStatus() );
		orderStatusDetail.setRejectDate( orderSplit.getRejectDate() );
		orderStatusDetail.setRejectMsg( orderSplit.getRejectMsg() );
		
		return orderStatusDetail;
		
	}

	@Override
	public Integer payNewsMediaOrder( Integer orderId ) throws Exception {
		
		// get newsMediaOrder
		OrderInfoEntity orderInfo = newsMediaOrderDao.selectNewsMediaOrderInfoByOrderId( orderId ); 
		if ( orderInfo == null || orderInfo.getOrderStatus() != 1 ) {
			return -6;
		}
		
		// get blogMediaOrderSplit
		List<NewsMediaOrderSplitEntity> orderSplitList = newsMediaOrderSplitDao.selectNewsMediaOrderSplitByOrderId( orderId );
		for ( int i = 0; i < orderSplitList.size(); i++ ) {
			
			NewsMediaOrderSplitEntity orderSplit = orderSplitList.get( i );
			Integer newsMediaId = orderSplit.getNewsMediaId();
			NewsMediaEntity newsMedia = newsMediaService.getNewsMediaInfoById( newsMediaId );
			
			if ( newsMedia.getAuditStatus() != 1 ) {
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
			
			newsMediaOrderDao.updateOrderNewsInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			newsMediaOrderDao.updateOrderNewsSplitStatus( params );
			
		}
				
		return 1;
		
	}

}
