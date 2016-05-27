package com.meibo.web.media.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.WechatMediaInfoEntity;
import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberWechatMediaListQueryViewmodel;
import com.meibo.web.order.dto.WechatMediaOrderSplitDTO;

public interface WechatMediaService {
	
	Boolean insertWechatMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
	Boolean auditWechatMedia( Integer auditUser, Integer wechatMediaId, Integer auditStatus ) throws Exception;
	
	Map<String, Object> getWechatMediaListByAdmin( AdminWechatMediaListQueryViewmodel viewmodel ) throws Exception;
	
	Map<String, Object> getWechatMediaListByMember( MemberWechatMediaListQueryViewmodel viewmodel ) throws Exception;
	
	List<Map<String, Object>> getWechatMediaAreaList() throws Exception;
	
	Boolean editWechatMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;

	BigDecimal getOrderAmountById( int[] wechatMediaId, int[] selectedId );

	List<WechatMediaOrderSplitDTO> getOrderSplitDtoById( int[] wechatMediaId, int[] selectedId );

	WechatMediaInfoEntity getWechatMediaInfoById( Integer wechatMediaId );
	
}
