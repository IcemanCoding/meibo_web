package com.meibo.web.member.service;

public interface RegisterService {
	
	Boolean checkMemberInfoBeforeRegister( String loginName, String mobileNum ) throws Exception;

}
