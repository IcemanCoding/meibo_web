package com.meibo.web.member.service;

import com.meibo.web.member.dto.MemberInfoDTO;
import com.meibo.web.member.entity.MemberInfoEntity;

public interface MemberInfoService {
	
	MemberInfoDTO login( String loginName, String loginPwd ) throws Exception;
	
	Integer addMemberInfo( MemberInfoEntity memberInfo ) throws Exception;

	Integer editLoginPassword( Integer memberId, String oriPwd, String newPwd ) throws Exception;
	
	Boolean isMemberExist( String loginName ) throws Exception;

}
