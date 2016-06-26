package com.meibo.web.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.UploadUtils;
import com.meibo.web.media.entity.WechatMediaInfoEntity;
import com.meibo.web.media.service.WechatMediaService;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.order.dao.WechatMediaOrderDAO;
import com.meibo.web.order.dao.WechatMediaOrderSplitDAO;
import com.meibo.web.order.dto.BaseMediaOrderListDTO;
import com.meibo.web.order.dto.BaseMediaOrderStatusDetailDTO;
import com.meibo.web.order.dto.WechatMediaOrderDetailDTO;
import com.meibo.web.order.dto.WechatMediaOrderSplitDTO;
import com.meibo.web.order.entity.MediaOrderLaunchDetailEntity;
import com.meibo.web.order.entity.OrderInfoEntity;
import com.meibo.web.order.entity.WechatMediaOrderDetailEntity;
import com.meibo.web.order.entity.WechatMediaOrderSplitEntity;
import com.meibo.web.order.service.OrderInfoService;
import com.meibo.web.order.service.WechatMediaOrderService;
import com.meibo.web.order.viewmodel.BaseMediaOrderListQueryViewmodel;
import com.meibo.web.order.viewmodel.WechatMediaCommitOrderViewmodel;
import com.meibo.web.system.dao.SystemParamsInfoDAO;
import com.meibo.web.system.model.ConsumeTransModel;
import com.meibo.web.system.service.TradeCenterService;
import com.meibo.web.utils.constants.ConstantsForSystemParams;

public class WechatMediaOrderServiceImpl implements WechatMediaOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private WechatMediaService wechatMediaService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private TradeCenterService tradeCenterService;
	
	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Autowired
	private WechatMediaOrderDAO wechatMediaOrderDao;
	
	@Autowired
	private WechatMediaOrderSplitDAO wechatMediaOrderSplitDao;
	
	@Autowired
	private SystemParamsInfoDAO systemParamsInfoDao;
	
	@Override
	public Integer commitWechatMediaOrder( WechatMediaCommitOrderViewmodel viewmodel ) throws Exception {
		
		// init orderCode
		String orderCode = orderInfoService.buildOrderCode( 3, viewmodel.getMemberId() );
		
		// rate
		String rate = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.MEIBO_STAGE_RATE );
		
		// get transAmount by wechatMediaId
		BigDecimal transAmount = wechatMediaService.getOrderAmountById( viewmodel.getWechatMediaId(), viewmodel.getSelectedId() );
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
		
		wechatMediaOrderDao.insertOrderWechatInfo( orderInfo );
		Integer orderId = orderInfo.getOrderId();
		
		// get wechatMedia list by wechatMediaId
		List<WechatMediaOrderSplitDTO> wechatMediaDtos = wechatMediaService.getOrderSplitDtoById( viewmodel.getWechatMediaId(), viewmodel.getSelectedId() );
		for ( int i = 0; i < wechatMediaDtos.size(); i++ ) {
			
			WechatMediaOrderSplitDTO wechatMediaDto = wechatMediaDtos.get( i );
			
			// insert order_split
			WechatMediaOrderSplitEntity orderSplit = new WechatMediaOrderSplitEntity();
			orderSplit.setCreateDate( nowDate );
			orderSplit.setMediaMemberId( wechatMediaDto.getMediaMemberId() );
			orderSplit.setWechatMediaId( wechatMediaDto.getWechatMediaId() );
			orderSplit.setOrderId( orderId );
			orderSplit.setOrderStatus( 1 );
			orderSplit.setTransAmount( wechatMediaDto.getTransAmount().add( wechatMediaDto.getTransAmount().multiply( new BigDecimal( rate ) ) ) );
			orderSplit.setPriceType( wechatMediaDto.getPriceType() );
			
			wechatMediaOrderDao.insertWechatBlogSplit( orderSplit );
			
		}
		
		// upload file
		String fileUrl = UploadUtils.uploadFile( viewmodel.getUploadFile(), 7, viewmodel.getRootDirProject() );
				
		// insert order_detail
		WechatMediaOrderDetailEntity orderDetail = new WechatMediaOrderDetailEntity();
		orderDetail.setCreateDate( nowDate );
		orderDetail.setExecuteDate( viewmodel.getExecuteDate() );
		orderDetail.setFileUrl( fileUrl );
		orderDetail.setOrderId( orderId );
		orderDetail.setRemark( viewmodel.getRemark() );
		orderDetail.setResourceLink( viewmodel.getResourceLink() );
		orderDetail.setTextContent( viewmodel.getContent() );
		orderDetail.setOriginalLink( viewmodel.getOriginalLink() );
		orderDetail.setTitle( viewmodel.getTitle() );
		
		wechatMediaOrderDao.insertOrderWechatDetail( orderDetail );
		
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
		transModel.setTransType( 3 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			orderInfo.setPayDate( new Date() );
			orderInfo.setOrderStatus( 2 );
			
			wechatMediaOrderDao.updateOrderWechatInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			wechatMediaOrderDao.updateOrderWechatSplitStatus( params );
			
		}
		
		return 1;
		
	}

	@Override
	public Map<String, Object> getWechatMediaOrderList( BaseMediaOrderListQueryViewmodel viewmodel ) {
		
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
			List<BaseMediaOrderListDTO> orderListTemp = wechatMediaOrderDao.selectOrderListByAdmin( viewmodel );
			totalRows = wechatMediaOrderDao.selectOrderListByAdminCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = wechatMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				orderList.add( orderDto );
				
			}
			
		} else if ( memberInfo.getMemberType() == 2 ) {
			
			// supplier
			List<BaseMediaOrderListDTO> orderListTemp = wechatMediaOrderDao.selectOrderListBySupplier( viewmodel );
			totalRows = wechatMediaOrderDao.selectOrderListBySupplierCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = wechatMediaOrderDao.selectOrderSplitMediaId( params );
				orderDto.setMediaName( (String[]) mediaNames.toArray( new String[mediaNames.size()] ) );
				
				// get order transAmount
				params.put( "memberId", viewmodel.getMemberId() );
				BigDecimal transAmount = wechatMediaOrderSplitDao.countOrderSplitTransAmountByOrderId( params );
				orderDto.setTransAmount( transAmount );
				
				orderList.add( orderDto );
				
			}
			
		} else {
			
			// user
			List<BaseMediaOrderListDTO> orderListTemp = wechatMediaOrderDao.selectOrderListByMember( viewmodel );
			totalRows = wechatMediaOrderDao.selectOrderListByMemberCount( viewmodel );
			
			for ( int i = 0; i < orderListTemp.size(); i++ ) {
				
				BaseMediaOrderListDTO orderDto = orderListTemp.get( i );
				Integer orderId = orderDto.getOrderId();
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put( "orderId", orderId );
				
				List<String> mediaNames = wechatMediaOrderDao.selectOrderSplitMediaId( params );
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
	public WechatMediaOrderDetailDTO getWechatMediaOrderDetail( Integer orderId ) {
		
		WechatMediaOrderDetailDTO dto = new WechatMediaOrderDetailDTO();
		
		WechatMediaOrderDetailEntity orderDetail = wechatMediaOrderDao.selectWechatMediaOrderDetailByOrderId( orderId );
		OrderInfoEntity orderInfo = wechatMediaOrderDao.selectWechatMediaOrderInfoByOrderId( orderId );
		List<MediaOrderLaunchDetailEntity> launchDetail = wechatMediaOrderDao.selectOrderLaunchDetailByOrderId( orderId );
		
		dto.setLaunchDetail( launchDetail );
		dto.setExecuteDate( orderDetail.getExecuteDate() );
		dto.setOrderStatus( orderInfo.getOrderStatus() );
		dto.setOrderUploadFile( orderDetail.getFileUrl() );
		dto.setRemark( orderDetail.getRemark() );
		dto.setResourceLink( orderDetail.getResourceLink() );
		dto.setContent( orderDetail.getTextContent() );
		dto.setTransAmount( orderInfo.getTransAmount() );
		dto.setOriginalLink( orderDetail.getOriginalLink() );
		dto.setTitle( orderDetail.getTitle() );
		
		return dto;
		
	}

	@Override
	public BaseMediaOrderStatusDetailDTO getOrderSplitStatusDetail( Integer orderSplitId ) {
		
		BaseMediaOrderStatusDetailDTO orderStatusDetail = new BaseMediaOrderStatusDetailDTO();
		
		WechatMediaOrderSplitEntity orderSplit = wechatMediaOrderSplitDao.selectWechatMediaOrderSplitById( orderSplitId );
		OrderInfoEntity orderInfo = wechatMediaOrderDao.selectWechatMediaOrderInfoByOrderId( orderSplit.getOrderId() );
		
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
	public Boolean editWechatMediaOrderSplitStatus( Integer orderSplitId, Integer procType, String rejectMsg ) {
		
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
			WechatMediaOrderSplitEntity orderSplit = wechatMediaOrderSplitDao.selectWechatMediaOrderSplitById( orderSplitId );
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
		wechatMediaOrderSplitDao.updateWechatMediaOrderSplitById( params );
		
		return true;
		
	}

	@Override
	public Boolean editWechatMediaOrderLaunch( Integer orderSplitId, String launchUrl, Date launchDate ) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "orderSplitId", orderSplitId );
		params.put( "launchUrl", launchUrl );
		params.put( "launchDate", launchDate );
		
		wechatMediaOrderSplitDao.updateWechatMediaOrderSplitById( params );
		
		return true;
		
	}

	@Override
	public Integer payWechatMediaOrder( Integer orderId ) throws Exception {
		
		// get wechatMediaOrder
		OrderInfoEntity orderInfo = wechatMediaOrderDao.selectWechatMediaOrderInfoByOrderId( orderId ); 
		if ( orderInfo == null || orderInfo.getOrderStatus() != 1 ) {
			return -6;
		}
		
		// get wechatMediaOrderSplit
		List<WechatMediaOrderSplitEntity> orderSplitList = wechatMediaOrderSplitDao.selectWechatMediaOrderSplitByOrderId( orderId );
		for ( int i = 0; i < orderSplitList.size(); i++ ) {
			
			WechatMediaOrderSplitEntity orderSplit = orderSplitList.get( i );
			Integer wechatMediaId = orderSplit.getWechatMediaId();
			WechatMediaInfoEntity wechatMedia = wechatMediaService.getWechatMediaInfoById( wechatMediaId );
			
			if ( wechatMedia.getAuditStatus() != 1 || wechatMedia.getStatus() != 1 ) {
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
			
			wechatMediaOrderDao.updateOrderWechatInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			wechatMediaOrderDao.updateOrderWechatSplitStatus( params );
			
		}
		
		return 1;
	}

}
