package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.NewsMediaTypeEntity;

public interface NewsMediaTypeDAO {
	
	List<Map<String, Object>> selectNewsMediaType();
	
	NewsMediaTypeEntity selectNewsMediaTypeById( Integer newsTypeId );
	
	void updateNewsMediaTypeStatus( NewsMediaTypeEntity newsMediaTypeEntity );
	
	void insertNewsMediaType( NewsMediaTypeEntity newsMediaTypeEntity );
	
	Integer selectNewsMediaTypeId( Map<String, Object> params );
	
	Integer selectMaxSortIndex();

}
