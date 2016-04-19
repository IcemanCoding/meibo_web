package com.meibo.web.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Override
	public Boolean checkMemberInfoBeforeRegister( String loginName, String mobileNum ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "loginName", loginName );
		
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		if ( memberInfo != null ) {
			return false;
		}
		
		memberInfo = null;
		params = new HashMap<String, Object>();
		params.put( "mobileNum", mobileNum );
		
		memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		if ( memberInfo != null ) {
			return false;
		}
		
		return true;
		
	}

}
