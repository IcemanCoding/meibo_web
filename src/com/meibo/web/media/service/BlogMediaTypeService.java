package com.meibo.web.media.service;

import java.util.List;
import java.util.Map;

public interface BlogMediaTypeService {
	
	List<Map<String, Object>> getBlogMediaType() throws Exception;
	
	Integer getOrAddBlogMediaTypeId( String blogMediaTypeName ) throws Exception;

}
