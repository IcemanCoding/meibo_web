package com.meibo.web.member.dao;

import java.util.Map;

import com.meibo.web.member.entity.MemberInfoEntity;

public interface MemberInfoDAO {
	
	MemberInfoEntity selectMemberInfoByConditions( Map<String, Object> params );

}
