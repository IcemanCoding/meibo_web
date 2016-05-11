package com.meibo.web.media.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.NewsMediaDAO;
import com.meibo.web.media.dao.NewsMediaTypeDAO;
import com.meibo.web.media.entity.NewsMediaTypeEntity;
import com.meibo.web.media.service.NewsMediaTypeService;

public class NewsMediaTypeServiceImpl implements NewsMediaTypeService {

	@Autowired
	private NewsMediaDAO newsMediaDao;
	
	@Autowired
	private NewsMediaTypeDAO newsMediaTypeDao;
	
	@Override
	public List<Map<String, Object>> getNewsMediaType() throws Exception {
		
		List<Map<String, Object>> typeList = newsMediaTypeDao.selectNewsMediaType();
		
		return typeList;
		
	}

	@Override
	public Integer getOrAddNewsMediaTypeId( String newsMediaTypeName ) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "newsMediaTypeName", newsMediaTypeName );
		
		// select
		Integer newsMediaTypeId = newsMediaTypeDao.selectNewsMediaTypeId( params );
		if ( newsMediaTypeId != null && newsMediaTypeId != 0 ) {
			return newsMediaTypeId;
		}
		
		// select max( sort_index )
		Integer maxSortIndex = newsMediaTypeDao.selectMaxSortIndex();
		
		// insert
		NewsMediaTypeEntity newsMediaTypeEntity = new NewsMediaTypeEntity();
		newsMediaTypeEntity.setName( newsMediaTypeName );
		newsMediaTypeEntity.setStatus( 0 );
		newsMediaTypeEntity.setSortIndex( maxSortIndex + 1 );
		
		newsMediaTypeDao.insertNewsMediaType( newsMediaTypeEntity );
		
		return newsMediaTypeEntity.getId();
		
	}

}
