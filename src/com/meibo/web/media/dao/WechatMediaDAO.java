package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.dto.AdminWechatMediaListDTO;
import com.meibo.web.media.dto.BaseWechatMediaListDTO;
import com.meibo.web.media.entity.WechatMediaInfoEntity;
import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.WechatMediaUpdateViewmodel;


public interface WechatMediaDAO {
	
	void insertWechatMediaInfo( WechatMediaInfoEntity wechatMediaInfo );
	
	WechatMediaInfoEntity selectWechatMediaInfoById( Integer wechatMediaId );
	
	List<Map<String, Object>> selectWechatMediaAreaList();
	
	void auditWechatMediaInfo( WechatMediaInfoEntity wechatMediaInfo );
	
	List<AdminWechatMediaListDTO> selectWechatMediaListByAdmin( AdminWechatMediaListQueryViewmodel params );
	
	Integer selectWechatMediaListByAdminCount( AdminWechatMediaListQueryViewmodel params );
	
	List<BaseWechatMediaListDTO> selectWechatMediaListByMember( MemberWechatMediaListQueryViewmodel params );
	
	Integer selectWechatMediaListByMemberCount( MemberWechatMediaListQueryViewmodel params );
	
	void updateWechatMediaInfo( WechatMediaUpdateViewmodel params );
	
}
