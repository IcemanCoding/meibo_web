package com.meibo.web.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.dao.MemberDetailDAO;
import com.meibo.web.member.entity.MemberDetailEntity;
import com.meibo.web.member.service.MemberDetailService;

public class MemberDetailServiceImpl implements MemberDetailService {

	@Autowired
	private MemberDetailDAO memberDetailDao;
	
	@Override
	public void addMemberDetail( Integer memberId ) throws Exception {
		
		MemberDetailEntity entity = new MemberDetailEntity();
		entity.setIsAuthCompany( 0 );
		entity.setMemberId( memberId );
		
		memberDetailDao.insertMemberDetail( entity );
		
	}

	@Override
	public MemberDetailEntity getOrAddMemberDetail( Integer memberId ) throws Exception {
		
		MemberDetailEntity entity = memberDetailDao.selectMemberDetailByMemberId( memberId );
		if ( entity != null ) {
			return entity;
		}
		entity = new MemberDetailEntity();
		entity.setIsAuthCompany( 0 );
		entity.setMemberId( memberId );
		
		memberDetailDao.insertMemberDetail( entity );
		
		return entity;
		
	}

	@Override
	public void updateMemberDetail( MemberDetailEntity memberDetail ) throws Exception {
		
		memberDetailDao.updateMemberDetail( memberDetail );
		
	}

}
