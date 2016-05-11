package com.meibo.web.media.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.WechatMediaTypeDAO;
import com.meibo.web.media.entity.NewsMediaTypeEntity;
import com.meibo.web.media.service.WechatMediaTypeService;

public class WechatMediaTypeServiceImpl implements WechatMediaTypeService {

	@Autowired
	private WechatMediaTypeDAO wechatMediaTypeDao;
	
	@Override
	public List<Map<String, Object>> getWechatMediaType() throws Exception {
		
		return wechatMediaTypeDao.selectWechatMediaTypeList();
	}

	@Override
	public Integer getOrAddWechatMediaTypeId( String wechatMediaTypeName )
			throws Exception {
		
		// select type_id
		Integer typeId = wechatMediaTypeDao.selectWechatMediaTypeId( wechatMediaTypeName );
		if ( typeId != null && typeId != 0 ) {
			return typeId;
		}
		
		// select max sort_index
		Integer sortIndex = wechatMediaTypeDao.selectMaxSortIndex();
		
		// insert type entity
		NewsMediaTypeEntity entity = new NewsMediaTypeEntity();
		entity.setName( wechatMediaTypeName );
		entity.setSortIndex( sortIndex + 1 );
		entity.setStatus( 0 );
		wechatMediaTypeDao.insertWechatMediaType( entity );
		
		return entity.getId();
	
	}

}
