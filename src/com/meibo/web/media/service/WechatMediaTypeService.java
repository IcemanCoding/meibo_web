package com.meibo.web.media.service;

import java.util.List;
import java.util.Map;

public interface WechatMediaTypeService {
	
	List<Map<String, Object>> getWechatMediaType() throws Exception;
	
	Integer getOrAddWechatMediaTypeId( String wechatMediaTypeName ) throws Exception;

}
