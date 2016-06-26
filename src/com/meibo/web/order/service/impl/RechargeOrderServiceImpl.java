package com.meibo.web.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.order.dao.RechargeOrderDAO;
import com.meibo.web.order.dto.RechargeOfflineDTO;
import com.meibo.web.order.dto.RechargeOfflineListDTO;
import com.meibo.web.order.entity.RechargeOrderEntity;
import com.meibo.web.order.service.OrderInfoService;
import com.meibo.web.order.service.RechargeOrderService;
import com.meibo.web.order.viewmodel.RechargeOfflineAuditViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineCommitViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineEditViewmodel;
import com.meibo.web.order.viewmodel.RechargeOfflineListQueryViewmodel;
import com.meibo.web.system.model.ConsumeTransModel;
import com.meibo.web.system.service.TradeCenterService;

public class RechargeOrderServiceImpl implements RechargeOrderService {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private TradeCenterService tradeCenterService;
	
	@Autowired
	private RechargeOrderDAO rechargeOrderDao;
	
	@Autowired
	private MemberInfoDAO memberInfoDao;

	@Override
	public Integer addOfflineRecharge( RechargeOfflineCommitViewmodel viewmodel ) throws Exception {
		
		RechargeOrderEntity orderEntity = rechargeOrderDao.selectRechargeOrderByVoucherNum( viewmodel.getVoucherNum() );
		if ( orderEntity != null && orderEntity.getOrderId() != null ) {
			return -1;
		}
		orderEntity = new RechargeOrderEntity();
		// init orderCode
		String orderCode = orderInfoService.buildOrderCode( 7, viewmodel.getMemberId() );
		
		Date nowDate = new Date();
		orderEntity.setMemberId( viewmodel.getMemberId() );
		orderEntity.setOrderCode( orderCode );
		orderEntity.setOrderDate( nowDate );
		orderEntity.setCreateDate( nowDate );
		orderEntity.setOrderStatus( 1 );
		orderEntity.setPlatformId( viewmodel.getPlatformId() );
		orderEntity.setTransAmount( viewmodel.getTransAmount() );
		orderEntity.setTransType( 7 );
		orderEntity.setVoucherNum( viewmodel.getVoucherNum() );
		
		rechargeOrderDao.insertRechargeOrder( orderEntity );
		
		return 1;
		
	}

	@Override
	public Integer updateOfflineRecharge( RechargeOfflineEditViewmodel viewmodel ) throws Exception {
		
		RechargeOrderEntity orderEntity = rechargeOrderDao.selectRechargeOrderByOrderId( viewmodel.getOrderId() );
		if ( orderEntity == null ) {
			return 0;
		}
		orderEntity.setEditMsg( viewmodel.getEditMsg() );
		orderEntity.setTransAmount( viewmodel.getTransAmount() );
		
		rechargeOrderDao.updateRechargeOrder( orderEntity );
		
		return 1;
		
	}

	@Override
	public Integer auditOfflineRecharge( RechargeOfflineAuditViewmodel viewmodel ) throws Exception {
		
		if ( viewmodel.getAuditStatus() != 1 && viewmodel.getAuditStatus() != 2 ) {
			return 0;
		}
		RechargeOrderEntity orderEntity = rechargeOrderDao.selectRechargeOrderByOrderId( viewmodel.getOrderId() );
		if ( orderEntity == null ) {
			return 0;
		}
		if ( orderEntity.getOrderStatus() == 2 ) {
			return 0;
		}
		Integer auditStatus = 0;
		if ( viewmodel.getAuditStatus() == 1 ) {
			
			auditStatus = 2;
			
			// account recharge
			ConsumeTransModel model = new ConsumeTransModel();
			model.setMemberId( orderEntity.getMemberId() );
			model.setOrderId( orderEntity.getOrderId() );
			model.setTransAmount( orderEntity.getTransAmount() );
			model.setTransCode( 2 );
			model.setTransType( orderEntity.getTransType() );
			
			tradeCenterService.rechargeTransHandle( model );
			
		}
		if ( viewmodel.getAuditStatus() == 2 ) {
			auditStatus = 9;
		}
		orderEntity.setAuditMsg( viewmodel.getAuditMsg() );
		orderEntity.setAuditUser( viewmodel.getMemberId() );
		orderEntity.setFinishDate( new Date() );
		orderEntity.setOrderStatus( auditStatus );
		
		rechargeOrderDao.updateRechargeOrder( orderEntity );
		
		return 1;

	}

	@Override
	public RechargeOfflineListDTO getOfflineRechargeList( RechargeOfflineListQueryViewmodel viewmodel ) throws Exception {
		
		// get member_info entity
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "memberId", viewmodel.getMemberId() );
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		
		params = new HashMap<String, Object>();
		Integer pageNum = viewmodel.getPageNum();
		Integer recorders = viewmodel.getPageRecorders();
		params.put( "endLimit", recorders );
		params.put( "beginLimit", ( pageNum - 1 ) * recorders );
		params.put( "transType", 7 );
		params.put( "orderStatus", viewmodel.getOrderStatus() );
		
		if ( memberInfo.getMemberType() == 1 ) {
			// user
			params.put( "memberId", viewmodel.getMemberId() );
		} 
		
		List<RechargeOfflineDTO> offlineList = rechargeOrderDao.selectRechargeOrderListByConditions( params );
		if ( offlineList == null || offlineList.size() == 0 ) {
			return null;
		}
		
		Integer totalRows = rechargeOrderDao.selectRechargeOrderListCountByConditions( params );
		Integer totalPages = 0;
		 
		if ( recorders != null && recorders != 0 ) {
			totalPages = ( totalRows / recorders ) == 0 ? 1 : totalRows / recorders;
		}
		RechargeOfflineListDTO resData = new RechargeOfflineListDTO();
		resData.setOfflineList( offlineList );
		resData.setTotalPages( totalPages );
		resData.setTotalRows( totalRows );
		
		return resData;
		
	}

}
