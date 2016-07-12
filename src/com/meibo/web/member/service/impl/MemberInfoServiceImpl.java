package com.meibo.web.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.MD5Utils;
import com.meibo.web.member.dao.MemberInfoDAO;
import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.entity.MemberInfoEntity;
import com.meibo.web.member.service.MemberInfoService;

public class MemberInfoServiceImpl implements MemberInfoService {

	@Autowired
	private MemberInfoDAO memberInfoDao;
	
	@Override
	public MemberInfoDTO login( String loginName, String loginPwd ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "loginName", loginName );
		params.put( "loginPwd", MD5Utils.encode( loginPwd ) );
		
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		params = null;
		
		if ( memberInfo == null ) {
			return null;
		}
		
		return memberInfo;
		
	}

	@Override
	public Integer addMemberInfo( MemberInfoEntity memberInfo ) throws Exception {
		
		memberInfoDao.insertMemberInfo( memberInfo );
		
		return memberInfo.getMemberId();
		
	}

	@Override
	public Integer editLoginPassword( Integer memberId, String oriPwd, String newPwd ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "memberId", memberId );
		MemberInfoDTO memberInfo = memberInfoDao.selectMemberInfoByConditions( params );
		
		if ( memberInfo == null ) {
			// 账户异常，请重新登录
			return -2;
		}
		
		oriPwd = MD5Utils.encode( oriPwd );
		
		// check ori password
		if ( !memberInfo.getLoginPwd().equals( oriPwd ) ) {
			// 原密码错误
			return -3;
		}
		
		memberInfo.setLoginPwd( MD5Utils.encode( newPwd ) );
		memberInfoDao.updateMemberInfo( memberInfo );
		
		return 1;
		
	}

	@Override
	public Boolean isMemberExist( String loginName ) throws Exception {
		
		Integer memberId = memberInfoDao.selectMemberIdByLoginName( loginName );
		if ( memberId == null || memberId == 0 ) {
			return false;
		}
		return true;
	}

}
