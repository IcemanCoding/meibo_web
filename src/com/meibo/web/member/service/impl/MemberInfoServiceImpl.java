package com.meibo.web.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.MD5Utils;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.entity.MemberInfoEntity;
import com.meibo.web.member.service.MemberInfoService;

public class MemberInfoServiceImpl implements MemberInfoService {

	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Override
	public MemberInfoEntity login( String loginName, String loginPwd ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "loginName", loginName );
		params.put( "loginPwd", MD5Utils.encode( loginPwd ) );
		
		MemberInfoEntity memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		params = null;
		
		if ( memberInfo == null ) {
			return null;
		}
		
		return memberInfo;
		
	}

}
