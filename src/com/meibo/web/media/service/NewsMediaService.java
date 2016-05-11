package com.meibo.web.media.service;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;

public interface NewsMediaService {
	
	Boolean insertNewsMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
	Boolean updateNewsMedia( Map<String, Object> formData, String rootDirProject ) throws Exception; 
	
	Boolean auditNewsMedia( Integer auditUser, Integer newsMediaId ) throws Exception;
	
	Map<String, Object> getNewsMediaListByAdmin( NewsMediaListQueryParams viewmodel ) throws Exception;
	
	Map<String, Object> getNewsMediaListByMember( NewsMediaListQueryParams viewmodel ) throws Exception;
	
	List<Map<String, Object>> getNewsMediaAreaList() throws Exception;

}
