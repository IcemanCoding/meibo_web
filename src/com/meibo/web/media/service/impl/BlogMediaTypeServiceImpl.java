package com.meibo.web.media.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.BlogMediaTypeDAO;
import com.meibo.web.media.entity.NewsMediaTypeEntity;
import com.meibo.web.media.service.BlogMediaTypeService;

public class BlogMediaTypeServiceImpl implements BlogMediaTypeService {
	
	@Autowired
	private BlogMediaTypeDAO blogMediaTypeDao;

	@Override
	public List<Map<String, Object>> getBlogMediaType() throws Exception {
		
		return blogMediaTypeDao.selectBlogMediaTypeList();
		
	}

	@Override
	public Integer getOrAddBlogMediaTypeId( String blogMediaTypeName ) throws Exception {
		
		// select type_id
		Integer typeId = blogMediaTypeDao.selectBlogMediaTypeId( blogMediaTypeName );
		if ( typeId != null && typeId != 0 ) {
			return typeId;
		}
		
		// select max sort_index
		Integer sortIndex = blogMediaTypeDao.selectMaxSortIndex();
		
		// insert type entity
		NewsMediaTypeEntity entity = new NewsMediaTypeEntity();
		entity.setName( blogMediaTypeName );
		entity.setSortIndex( sortIndex + 1 );
		entity.setStatus( 0 );
		blogMediaTypeDao.insertBlogMediaType( entity );
		
		return entity.getId();
		
		
	}

}
