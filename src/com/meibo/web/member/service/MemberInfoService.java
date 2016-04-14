package com.meibo.web.member.service;

import com.meibo.web.member.entity.MemberInfoEntity;

public interface MemberInfoService {
	
	MemberInfoEntity login( String loginName, String loginPwd ) throws Exception;

}
