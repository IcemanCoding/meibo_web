package com.meibo.web.order.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.viewmodel.BasePageViewModel;
import com.meibo.web.external.service.YunXinFlowMediaService;
import com.meibo.web.media.dao.FlowMediaInfoDAO;
import com.meibo.web.media.entity.FlowMediaInfoEntity;
import com.meibo.web.media.utils.FlowMediaUtils;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberAccountDTO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.order.dao.FlowMediaOrderDAO;
import com.meibo.web.order.dao.FlowMediaOrderDetailDAO;
import com.meibo.web.order.dao.FlowMediaOrderSplitDAO;
import com.meibo.web.order.dto.FlowMediaOrderDTO;
import com.meibo.web.order.dto.FlowMediaOrderDetailDTO;
import com.meibo.web.order.dto.FlowMediaOrderDetailListDTO;
import com.meibo.web.order.dto.FlowMediaOrderListDTO;
import com.meibo.web.order.entity.FlowMediaOrderDetailEntity;
import com.meibo.web.order.entity.FlowMediaOrderEntity;
import com.meibo.web.order.entity.FlowMediaOrderSplitEntity;
import com.meibo.web.order.service.FlowMediaOrderService;
import com.meibo.web.order.service.OrderInfoService;
import com.meibo.web.order.viewmodel.FlowMediaOrderAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderBatchAddViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderDetailViewmodel;
import com.meibo.web.order.viewmodel.FlowMediaOrderPayViewmodel;
import com.meibo.web.system.dao.SystemParamsInfoDAO;
import com.meibo.web.system.model.ConsumeTransModel;
import com.meibo.web.system.service.TradeCenterService;
import com.meibo.web.utils.constants.ConstantsForSystemParams;

public class FlowMediaOrderServiceImpl implements FlowMediaOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private MemberAccountService memberAccountService;
	
	@Autowired
	private TradeCenterService tradeCenterService;
	
	@Autowired
	private YunXinFlowMediaService yunXinFlowMediaService;
	
	@Autowired
	private FlowMediaInfoDAO flowMediaInfoDao;
	
	@Autowired
	private FlowMediaOrderDAO flowMediaOrderDao;
	
	@Autowired
	private FlowMediaOrderDetailDAO flowMediaOrderDetailDao;
	
	@Autowired
	private FlowMediaOrderSplitDAO flowMediaOrderSplitDao;
	
	@Autowired
	private SystemParamsInfoDAO systemParamsInfoDao;
	
	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Override
	public Integer commitFlowMediaOrder( FlowMediaOrderAddViewmodel viewmodel ) throws Exception {
		
		// validate packageId is exist
		FlowMediaInfoEntity flowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( viewmodel.getPackageId() );
		Integer carrierType = FlowMediaUtils.checkMobileCarrier( viewmodel.getMobile() );
		// 运营商类型：1-电信 2-联通 3-移动
		if ( !carrierType.equals( flowMediaInfo.getCarrierType() ) ) {
			// 套餐不匹配
			return -3;
		}
		
		Integer dxCode = 0;
		Integer ltCode = 0;
		Integer ydCode = 0;
		if ( carrierType == 1 ) {
			
			dxCode = flowMediaInfo.getPackageId();
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "price", flowMediaInfo.getPrice() );
			params.put( "carrierType", 2 );
			params.put( "status", 1 );
			ltCode = flowMediaInfoDao.getPackageIdByNearPrice( params );
			
			params.put( "carrierType", 3 );
			ydCode = flowMediaInfoDao.getPackageIdByNearPrice( params );
			
		} else if ( carrierType == 2 ) {
			
			ltCode = flowMediaInfo.getPackageId();
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "price", flowMediaInfo.getPrice() );
			params.put( "carrierType", 1 );
			params.put( "status", 1 );
			dxCode = flowMediaInfoDao.getPackageIdByNearPrice( params );
			
			params.put( "carrierType", 3 );
			ydCode = flowMediaInfoDao.getPackageIdByNearPrice( params );
			
		} else if ( carrierType == 3 ) {
			
			ydCode = flowMediaInfo.getPackageId();
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "price", flowMediaInfo.getPrice() );
			params.put( "carrierType", 1 );
			params.put( "status", 1 );
			dxCode = flowMediaInfoDao.getPackageIdByNearPrice( params );
			
			params.put( "carrierType", 2 );
			ltCode = flowMediaInfoDao.getPackageIdByNearPrice( params );
			
		} else {
			return -3;
		}
		
		// init orderCode
		String orderCode = orderInfoService.buildOrderCode( 4, viewmodel.getMemberId() );
		
		// get transAmount by flowMediaInfo
		BigDecimal transAmount = flowMediaInfo.getPrice();
		
		// insert order_info
		Date nowDate = new Date();
		FlowMediaOrderEntity orderInfo = new FlowMediaOrderEntity();
		orderInfo.setTransAmount( transAmount );
		orderInfo.setMemberId( viewmodel.getMemberId() );
		orderInfo.setOrderCode( orderCode );
		orderInfo.setOrderStatus( 1 );
		orderInfo.setRemarkMsg( "" );
		orderInfo.setTransType( 5 );
		orderInfo.setOrderDate( nowDate );
		orderInfo.setCreateDate( nowDate );
		orderInfo.setOrderName( viewmodel.getMobile() + "流量充值" + flowMediaInfo.getPackageName() );
		
		flowMediaOrderDao.insertOrderFlowInfo( orderInfo );
		Integer orderId = orderInfo.getOrderId();
		
		// insert media_flow_detail
		FlowMediaOrderDetailEntity orderDetail = new FlowMediaOrderDetailEntity();
		orderDetail.setDxPackageId( dxCode );
		orderDetail.setLtPackageId( ltCode );
		orderDetail.setYdPackageId( ydCode );
		orderDetail.setOrderId( orderId );
		
		flowMediaOrderDetailDao.insertOrderFlowDetail( orderDetail );
		
		// insert media_flow_split
		FlowMediaOrderSplitEntity orderSplit = new FlowMediaOrderSplitEntity();
		orderSplit.setCreateDate( nowDate );
		orderSplit.setDestMobile( viewmodel.getMobile() );
		orderSplit.setOrderId( orderId );
		orderSplit.setOrderStatus( 1 );
		orderSplit.setPackageId( viewmodel.getPackageId() );
		orderSplit.setTransAmount( transAmount );
		
		flowMediaOrderSplitDao.insertOrderFlowSplit( orderSplit );
		
		// trade
		MemberAccountDTO memberAccount = memberAccountService.getMemberAccount( viewmodel.getMemberId() );
		BigDecimal accountBal = memberAccount.getAvailableBalance();
		if ( accountBal.compareTo( transAmount ) < 0 ) {
			// 余额不足
			return -2;
		}
		
		String serialNumber = "";
		// switch control
		// 流量充值开关：1-未开启 2-debug 3-release
		String rechargeSwitch = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.FLOW_RECHARGE_SWITCH );
		if ( rechargeSwitch.equals( "1" ) ) {
			// 流量充值暂未开通
			return -4;
		} else if ( rechargeSwitch.equals( "2" ) ) {
		} else {
			
			// recharge flow
			serialNumber = yunXinFlowMediaService.rechargeFlow( viewmodel.getMobile(), dxCode, ltCode, ydCode );
			if ( serialNumber.isEmpty() ) {
				return -4;
			}
			
		}
		
		ConsumeTransModel transModel = new ConsumeTransModel();
		transModel.setMemberId( viewmodel.getMemberId() );
		transModel.setOrderId( orderId );
		transModel.setTransAmount( transAmount );
		transModel.setTransCode( 1 );
		transModel.setTransType( 5 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			orderInfo.setPayDate( new Date() );
			orderInfo.setOrderStatus( 2 );
			orderInfo.setSerialNumber( serialNumber );
			
			flowMediaOrderDao.updateOrderFlowInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			flowMediaOrderSplitDao.updateOrderFlowSplitStatus( params );
			
		}
		
		return 1;
		
	}

	@Override
	public Integer batchCommitFlowMediaOrder( FlowMediaOrderBatchAddViewmodel viewmodel )
			throws Exception {
		
		// validate packageId is exist
		FlowMediaInfoEntity dxFlowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( viewmodel.getDxPackageId() );
		FlowMediaInfoEntity ltFlowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( viewmodel.getLtPackageId() );
		FlowMediaInfoEntity ydFlowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( viewmodel.getYdPackageId() );
		
		if ( dxFlowMediaInfo == null || ltFlowMediaInfo == null || ydFlowMediaInfo == null ) {
			// 套餐不匹配
			return -3;
		}
		
		BigDecimal transAmount = countBatchFlowRechargeAmount( viewmodel.getMobile(), dxFlowMediaInfo.getPrice(), ltFlowMediaInfo.getPrice(), ydFlowMediaInfo.getPrice() );
		
		// init orderCode
		String orderCode = orderInfoService.buildOrderCode( 4, viewmodel.getMemberId() );
		
		// insert order_info
		Date nowDate = new Date();
		FlowMediaOrderEntity orderInfo = new FlowMediaOrderEntity();
		orderInfo.setTransAmount( transAmount );
		orderInfo.setMemberId( viewmodel.getMemberId() );
		orderInfo.setOrderCode( orderCode );
		orderInfo.setOrderStatus( 1 );
		orderInfo.setRemarkMsg( "" );
		orderInfo.setTransType( 5 );
		orderInfo.setOrderDate( nowDate );
		orderInfo.setCreateDate( nowDate );
		orderInfo.setOrderName( viewmodel.getOrderName() );
		
		flowMediaOrderDao.insertOrderFlowInfo( orderInfo );
		Integer orderId = orderInfo.getOrderId();
		
		// insert media_flow_detail
		FlowMediaOrderDetailEntity orderDetail = new FlowMediaOrderDetailEntity();
		orderDetail.setDxPackageId( dxFlowMediaInfo.getPackageId() );
		orderDetail.setLtPackageId( ltFlowMediaInfo.getPackageId() );
		orderDetail.setYdPackageId( ydFlowMediaInfo.getPackageId() );
		orderDetail.setOrderId( orderId );
		
		flowMediaOrderDetailDao.insertOrderFlowDetail( orderDetail );
		
		// insert media_flow_split
		for ( int i = 0; i < viewmodel.getMobile().length; i++ ) {
			
			String mobile = viewmodel.getMobile()[i];
			Integer carrierType = FlowMediaUtils.checkMobileCarrier( mobile );
			Integer packageId = 0;
			BigDecimal splitAmount = BigDecimal.ZERO;
			if ( carrierType == 1 ) {
				packageId = viewmodel.getDxPackageId();
				splitAmount = dxFlowMediaInfo.getPrice();
			} else if ( carrierType == 2 ) {
				packageId = viewmodel.getLtPackageId();
				splitAmount = ltFlowMediaInfo.getPrice();
			} else if ( carrierType == 3 ) {
				packageId = viewmodel.getYdPackageId();
				splitAmount = ydFlowMediaInfo.getPrice();
			}
			FlowMediaOrderSplitEntity orderSplit = new FlowMediaOrderSplitEntity();
			orderSplit.setCreateDate( nowDate );
			orderSplit.setDestMobile( viewmodel.getMobile()[i] );
			orderSplit.setOrderId( orderId );
			orderSplit.setOrderStatus( 1 );
			orderSplit.setPackageId( packageId );
			orderSplit.setTransAmount( splitAmount );
			
			flowMediaOrderSplitDao.insertOrderFlowSplit( orderSplit );
			
		}
		
		// trade
		MemberAccountDTO memberAccount = memberAccountService.getMemberAccount( viewmodel.getMemberId() );
		BigDecimal accountBal = memberAccount.getAvailableBalance();
		if ( accountBal.compareTo( transAmount ) < 0 ) {
			// 余额不足
			return -2;
		}
		
		String serialNumber = "";
		// switch control
		// 流量充值开关：1-未开启 2-debug 3-release
		String rechargeSwitch = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.FLOW_RECHARGE_SWITCH );
		if ( rechargeSwitch.equals( "1" ) ) {
			// 流量充值暂未开通
			return -4;
		} else if ( rechargeSwitch.equals( "2" ) ) {
		} else {
			
			// recharge flow
			serialNumber = yunXinFlowMediaService.batchRechargeFlow( viewmodel.getMobile(), viewmodel.getDxPackageId(), viewmodel.getLtPackageId(), viewmodel.getYdPackageId() );
			if ( serialNumber.isEmpty() ) {
				return -4;
			}
			
		}
		
		ConsumeTransModel transModel = new ConsumeTransModel();
		transModel.setMemberId( viewmodel.getMemberId() );
		transModel.setOrderId( orderId );
		transModel.setTransAmount( transAmount );
		transModel.setTransCode( 1 );
		transModel.setTransType( 5 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			orderInfo.setPayDate( new Date() );
			orderInfo.setOrderStatus( 2 );
			orderInfo.setSerialNumber( serialNumber );
			
			flowMediaOrderDao.updateOrderFlowInfoStatus( orderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", orderId );
			params.put( "orderStatus", 2 );
			
			flowMediaOrderSplitDao.updateOrderFlowSplitStatus( params );
			
		}
		
		return 1;
		
	}

	private BigDecimal countBatchFlowRechargeAmount( String[] mobile, BigDecimal dxPrice, BigDecimal ltPrice, BigDecimal ydPrice ) {
		
		BigDecimal transAmount = BigDecimal.ZERO;
		
		for ( int i = 0; i < mobile.length; i++ ) {
			
			String mobileNum = mobile[i];
			Integer carrierType = FlowMediaUtils.checkMobileCarrier( mobileNum );
			
			if ( carrierType == 1 ) {
				transAmount = transAmount.add( dxPrice );
			} else if ( carrierType == 2 ) {
				transAmount = transAmount.add( ltPrice );
			} else if ( carrierType == 3 ) {
				transAmount = transAmount.add( ydPrice );
			}
			
		}
		
		return transAmount;
		
	}

	@Override
	public Integer payFlowMediaOrder( FlowMediaOrderPayViewmodel viewmodel ) throws Exception {
		
		// get and check orderInfo
		FlowMediaOrderEntity flowOrderInfo = flowMediaOrderDao.selectOrderFlowInfoByOrderId( viewmodel.getOrderId() );
		if ( flowOrderInfo == null || flowOrderInfo.getOrderStatus() != 1 ) {
			// 订单已完成，请勿重复支付!
			return -6;
		}
		if ( !flowOrderInfo.getMemberId().equals( viewmodel.getMemberId() ) ) {
			// 非法操作
			return -7;
		}
		
		// get and check orderDetail
		FlowMediaOrderDetailEntity orderDetail = flowMediaOrderDetailDao.selectOrderFlowDetailByOrderId( viewmodel.getOrderId() );
		if ( orderDetail == null ) {
			return 0;
		}
		
		// get and check orderSplit
		List<FlowMediaOrderSplitEntity> flowOrderSplit = flowMediaOrderSplitDao.selectOrderFlowSplitByOrderId( viewmodel.getOrderId() );
		if ( flowOrderSplit == null || flowOrderSplit.size() == 0 ) {
			return 0;
		}
		
		// validate packageId is exist
		FlowMediaInfoEntity dxFlowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( orderDetail.getDxPackageId() );
		FlowMediaInfoEntity ltFlowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( orderDetail.getLtPackageId() );
		FlowMediaInfoEntity ydFlowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( orderDetail.getYdPackageId() );
		if ( dxFlowMediaInfo == null || ltFlowMediaInfo == null || ydFlowMediaInfo == null ) {
			// 订单已过期
			return -5;
		}
		if ( dxFlowMediaInfo.getStatus() != 1 || ltFlowMediaInfo.getStatus() != 1 || ydFlowMediaInfo.getStatus() != 1 ) {
			return -5;
		}
		
		BigDecimal transAmount = flowOrderInfo.getTransAmount();
		
		// check account balance
		MemberAccountDTO memberAccount = memberAccountService.getMemberAccount( viewmodel.getMemberId() );
		BigDecimal accountBal = memberAccount.getAvailableBalance();
		if ( accountBal.compareTo( transAmount ) < 0 ) {
			// 余额不足
			return -2;
		}
		
		// check flow packageId
		Integer dxCode = orderDetail.getDxPackageId();
		Integer ltCode = orderDetail.getLtPackageId();
		Integer ydCode = orderDetail.getYdPackageId();
		String serialNumber = "";
		
		if ( flowOrderSplit.size() == 1 ) {
			
			FlowMediaOrderSplitEntity orderSplit = flowOrderSplit.get( 0 );
			
			// switch control
			// 流量充值开关：1-未开启 2-debug 3-release
			String rechargeSwitch = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.FLOW_RECHARGE_SWITCH );
			if ( rechargeSwitch.equals( "1" ) ) {
				// 流量充值暂未开通
				return -4;
			} else if ( rechargeSwitch.equals( "2" ) ) {
			} else {
				
				// recharge flow
				serialNumber = yunXinFlowMediaService.rechargeFlow( orderSplit.getDestMobile(), dxCode, ltCode, ydCode );
				if ( serialNumber.isEmpty() ) {
					return -4;
				}
				
			}
			
		} else {
			
			String[] mobileArr = new String[ flowOrderSplit.size() ];
			// 批量充值
			for ( int i = 0; i < flowOrderSplit.size(); i++ ) {
				
				FlowMediaOrderSplitEntity orderSplit = flowOrderSplit.get( i );
				mobileArr[i] = orderSplit.getDestMobile();
				
			}
			
			// switch control
			// 流量充值开关：1-未开启 2-debug 3-release
			String rechargeSwitch = systemParamsInfoDao.selectSystemParamsInfoByKey( ConstantsForSystemParams.FLOW_RECHARGE_SWITCH );
			if ( rechargeSwitch.equals( "1" ) ) {
				// 流量充值暂未开通
				return -4;
			} else if ( rechargeSwitch.equals( "2" ) ) {
			} else {
				
				// recharge flow
				serialNumber = yunXinFlowMediaService.batchRechargeFlow( mobileArr, dxCode, ltCode, ydCode );
				if ( serialNumber.isEmpty() ) {
					return -4;
				}
				
			}
			
		}
		
		ConsumeTransModel transModel = new ConsumeTransModel();
		transModel.setMemberId( viewmodel.getMemberId() );
		transModel.setOrderId( viewmodel.getOrderId() );
		transModel.setTransAmount( transAmount );
		transModel.setTransCode( 1 );
		transModel.setTransType( 5 );
		
		if ( tradeCenterService.consumeTransHandle( transModel ) ) {
			
			// update order_info set orderStatus = 2
			flowOrderInfo.setPayDate( new Date() );
			flowOrderInfo.setOrderStatus( 2 );
			flowOrderInfo.setSerialNumber( serialNumber );
			
			flowMediaOrderDao.updateOrderFlowInfoStatus( flowOrderInfo );
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put( "orderId", viewmodel.getOrderId() );
			params.put( "orderStatus", 2 );
			
			flowMediaOrderSplitDao.updateOrderFlowSplitStatus( params );
			
		}
		
		return 1;
		
	}

	@Override
	public FlowMediaOrderListDTO getFlowMediaOrderList( BasePageViewModel pageViewModel ) {
		
		// get member_info entity
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "memberId", pageViewModel.getMemberId() );
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		
		Integer pageRecorders = pageViewModel.getPageRecorders();
		Integer pageNum = pageViewModel.getPageNum();
		
		params = new HashMap<String, Object>();
		params.put( "beginLimit", ( pageNum - 1 ) * pageRecorders );
		params.put( "endLimit", pageRecorders );
		
		if ( memberInfo.getMemberType() == 1 ) {
			params.put( "memberId", pageViewModel.getMemberId() );
		}
		
		List<FlowMediaOrderDTO> orderInfo = flowMediaOrderDao.selectOrderFlowListByConditions( params );
		
		if ( orderInfo == null || orderInfo.size() == 0 ) {
			return null;
		}
		
		Integer totalRows = flowMediaOrderDao.selectOrderFlowListCountByConditions( params );
		Integer	totalPages = ( totalRows / pageRecorders ) == 0 ? 1 : totalRows / pageRecorders;
		
		FlowMediaOrderListDTO orderList = new FlowMediaOrderListDTO();
		orderList.setOrderList( orderInfo );
		orderList.setTotalPages( totalPages );
		orderList.setTotalRows( totalRows );
		
		return orderList;
		
	}

	@Override
	public FlowMediaOrderDetailListDTO getFlowMediaOrderDetail( FlowMediaOrderDetailViewmodel detailViewmodel ) {
		
		Integer pageRecorders = detailViewmodel.getPageRecorders();
		Integer pageNum = detailViewmodel.getPageNum();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "beginLimit", ( pageNum - 1 ) * pageRecorders );
		params.put( "endLimit", pageRecorders );
		params.put( "orderId", detailViewmodel.getOrderId() );
		
		List<FlowMediaOrderDetailDTO> orderDetailInfo = flowMediaOrderSplitDao.selectOrderFlowDetailByConditions( params );
		
		if ( orderDetailInfo == null || orderDetailInfo.size() == 0 ) {
			return null;
		}
		
		Integer totalRows = flowMediaOrderSplitDao.selectOrderFlowDetailCountByConditions( params );
		Integer	totalPages = ( totalRows / pageRecorders ) == 0 ? 1 : totalRows / pageRecorders;
		
		FlowMediaOrderDetailListDTO orderDetailList = new FlowMediaOrderDetailListDTO();
		orderDetailList.setOrderDetail( orderDetailInfo );
		orderDetailList.setTotalPages( totalPages );
		orderDetailList.setTotalRows( totalRows );
		
		return orderDetailList;
		
	}

}
