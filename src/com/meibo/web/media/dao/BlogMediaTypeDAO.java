package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.NewsMediaTypeEntity;

public interface BlogMediaTypeDAO {
	
	Integer selectBlogMediaTypeId( String blogMediaTypeName );
	
	Integer selectMaxSortIndex();
	
	void insertBlogMediaType( NewsMediaTypeEntity entity );
	
	NewsMediaTypeEntity selectBlogMediaTypeById( Integer typeId );
	
	void updateBlogMediaTypeStatus( NewsMediaTypeEntity blogMediaType );

	List<Map<String, Object>> selectBlogMediaTypeList();
	
}
