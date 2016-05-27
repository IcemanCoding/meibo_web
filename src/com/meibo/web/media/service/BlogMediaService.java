package com.meibo.web.media.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.BlogMediaInfoEntity;
import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberBlogMediaListQueryViewmodel;
import com.meibo.web.order.dto.BlogMediaOrderSplitDTO;

public interface BlogMediaService {
	
	Boolean insertBlogMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
	Boolean auditBlogMedia( Integer auditUser, Integer blogMediaId, Integer auditStatus ) throws Exception;
	
	Map<String, Object> getBlogMediaListByAdmin( AdminBlogMediaListQueryViewmodel viewmodel ) throws Exception;
	
	Map<String, Object> getBlogMediaListByMember( MemberBlogMediaListQueryViewmodel viewmodel ) throws Exception;
	
	Boolean editBlogMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;

	List<Map<String, Object>> getBlogMediaAreaList() throws Exception;

	BigDecimal getOrderAmountById( int[] blogMediaId, int[] selectedId );

	List<BlogMediaOrderSplitDTO> getOrderSplitDtoById( int[] blogMediaId, int[] selectedId );

	BlogMediaInfoEntity getBlogMediaInfoById( Integer blogMediaId );

}
