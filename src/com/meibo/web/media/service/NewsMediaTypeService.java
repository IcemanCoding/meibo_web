package com.meibo.web.media.service;

import java.util.List;
import java.util.Map;

/**
 *	媒体渠道分类Service
 */
public interface NewsMediaTypeService {
	
	List<Map<String, Object>> getNewsMediaType() throws Exception;
	
	Integer getOrAddNewsMediaTypeId( String newsMediaTypeName ) throws Exception;

}
