package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.entity.NewsMediaTypeEntity;

public interface WechatMediaTypeDAO {
	
	Integer selectWechatMediaTypeId( String typeName );
	
	List<Map<String, Object>> selectWechatMediaTypeList();
	
	void insertWechatMediaType( NewsMediaTypeEntity entity );
	
	NewsMediaTypeEntity selectWechatMediaTypeById( Integer typeId );
	
	void updateWechatMediaTypeStatus( NewsMediaTypeEntity entity );
	
	Integer selectMaxSortIndex();

}
