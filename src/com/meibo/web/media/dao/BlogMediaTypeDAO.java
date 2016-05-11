package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.NewsMediaTypeEntity;

public interface BlogMediaTypeDAO {
	
	List<Map<String, Object>> selectWechatMediaTypeList();

	Integer selectBlogMediaTypeId( String blogMediaTypeName );
	
	Integer selectMaxSortIndex();
	
	void insertBlogMediaType( NewsMediaTypeEntity entity );
	
	NewsMediaTypeEntity selectBlogMediaTypeById( Integer typeId );
	
	void updateBlogMediaTypeStatus( NewsMediaTypeEntity blogMediaType );
	
}
