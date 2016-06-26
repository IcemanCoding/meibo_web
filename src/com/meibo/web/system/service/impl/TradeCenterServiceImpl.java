package com.meibo.web.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.service.MemberAccountService;
import com.meibo.web.system.dao.SystemTransDtlDAO;
import com.meibo.web.system.entity.SystemTransDtlEntity;
import com.meibo.web.system.model.ConsumeTransModel;
import com.meibo.web.system.service.TradeCenterService;

public class TradeCenterServiceImpl implements TradeCenterService {
	
	@Autowired
	private SystemTransDtlDAO systemTransDtlDao;
	
	@Autowired
	private MemberAccountService memberAccountService;

	@Override
	public Boolean consumeTransHandle( ConsumeTransModel transModel ) {
		
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String transDate = sdf.format( nowDate );
		sdf = new SimpleDateFormat("HHmmss");
		String transTime = sdf.format( nowDate );
		
		// insert transDetail
		SystemTransDtlEntity transDtlEntity = new SystemTransDtlEntity();
		transDtlEntity.setMemberId( transModel.getMemberId() );
		transDtlEntity.setOrderId( transModel.getOrderId() );
		transDtlEntity.setTransAmount( transModel.getTransAmount() );
		transDtlEntity.setTransCode( transModel.getTransCode() );
		transDtlEntity.setTransDate( transDate );
		transDtlEntity.setTransStatus( 1 );
		transDtlEntity.setTransTime( transTime );
		transDtlEntity.setTransType( transModel.getTransType() );
		
		systemTransDtlDao.insertSystemTransDtl( transDtlEntity );
		
		// memberAccount consume
		Boolean procFlag = memberAccountService.cosumeMemberAccount( transModel.getMemberId(), transModel.getTransAmount() );
		if ( procFlag ) {
			transDtlEntity.setTransStatus( 2 );
		} else {
			transDtlEntity.setTransStatus( 3 );
		}
		
		// update transdtl
		systemTransDtlDao.updateSystemTransDtlStatus( transDtlEntity );
		
		return true;
		
	}

	@Override
	public Boolean rechargeTransHandle( ConsumeTransModel transModel ) {
		
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String transDate = sdf.format( nowDate );
		sdf = new SimpleDateFormat("HHmmss");
		String transTime = sdf.format( nowDate );
		
		// insert transDetail
		SystemTransDtlEntity transDtlEntity = new SystemTransDtlEntity();
		transDtlEntity.setMemberId( transModel.getMemberId() );
		transDtlEntity.setOrderId( transModel.getOrderId() );
		transDtlEntity.setTransAmount( transModel.getTransAmount() );
		transDtlEntity.setTransCode( transModel.getTransCode() );
		transDtlEntity.setTransDate( transDate );
		transDtlEntity.setTransStatus( 1 );
		transDtlEntity.setTransTime( transTime );
		transDtlEntity.setTransType( transModel.getTransType() );
		
		systemTransDtlDao.insertSystemTransDtl( transDtlEntity );
		
		// memberAccount consume
		Boolean procFlag = memberAccountService.rechargeMemberAccount( transModel.getMemberId(), transModel.getTransAmount() );
		if ( procFlag ) {
			transDtlEntity.setTransStatus( 2 );
		} else {
			transDtlEntity.setTransStatus( 3 );
		}
		
		// update transdtl
		systemTransDtlDao.updateSystemTransDtlStatus( transDtlEntity );
		
		return true;
		
	}

}
