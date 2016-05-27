package com.meibo.web.media.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.BlogMediaInfoEntity;
import com.meibo.web.media.entity.NewsMediaEntity;
import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;
import com.meibo.web.order.dto.NewsMediaOrderSplitDTO;

public interface NewsMediaService {
	
	Boolean insertNewsMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
	Boolean updateNewsMedia( Map<String, Object> formData, String rootDirProject ) throws Exception; 
	
	Boolean auditNewsMedia( Integer auditUser, Integer newsMediaId, Integer auditStatus ) throws Exception;
	
	Map<String, Object> getNewsMediaListByAdmin( NewsMediaListQueryParams viewmodel ) throws Exception;
	
	Map<String, Object> getNewsMediaListByMember( NewsMediaListQueryParams viewmodel ) throws Exception;
	
	List<Map<String, Object>> getNewsMediaAreaList() throws Exception;
	
	BigDecimal getOrderAmountById( int[] newsMediaId ) throws Exception;
	
	List<NewsMediaOrderSplitDTO> getOrderSplitDtoById( int[] newsMediaId ) throws Exception;

	NewsMediaEntity getNewsMediaInfoById( Integer blogMediaId );

}
