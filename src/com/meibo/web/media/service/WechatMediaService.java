package com.meibo.web.media.service;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberWechatMediaListQueryViewmodel;

public interface WechatMediaService {
	
	Boolean insertWechatMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
	Boolean auditWechatMedia( Integer auditUser, Integer wechatMediaId ) throws Exception;
	
	Map<String, Object> getWechatMediaListByAdmin( AdminWechatMediaListQueryViewmodel viewmodel ) throws Exception;
	
	Map<String, Object> getWechatMediaListByMember( MemberWechatMediaListQueryViewmodel viewmodel ) throws Exception;
	
	List<Map<String, Object>> getWechatMediaAreaList() throws Exception;
	
	Boolean editWechatMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
}
