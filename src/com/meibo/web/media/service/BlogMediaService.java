package com.meibo.web.media.service;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.MemberBlogMediaListQueryViewmodel;

public interface BlogMediaService {
	
	Boolean insertBlogMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;
	
	Boolean auditBlogMedia( Integer auditUser, Integer blogMediaId ) throws Exception;
	
	Map<String, Object> getBlogMediaListByAdmin( AdminBlogMediaListQueryViewmodel viewmodel ) throws Exception;
	
	Map<String, Object> getBlogMediaListByMember( MemberBlogMediaListQueryViewmodel viewmodel ) throws Exception;
	
	Boolean editBlogMedia( Map<String, Object> formData, String rootDirProject ) throws Exception;

	List<Map<String, Object>> getBlogMediaAreaList() throws Exception;

}
