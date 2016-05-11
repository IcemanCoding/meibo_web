package com.meibo.web.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.NewsMediaColumnDAO;
import com.meibo.web.media.entity.NewsMediaColumnEntity;
import com.meibo.web.media.service.NewsMediaColumnService;

public class NewsMediaColumnServiceImpl implements NewsMediaColumnService {

	@Autowired
	private NewsMediaColumnDAO newsMediaColumnDao;
	
	@Override
	public Integer getOrAddNewsMediaColumnId( String columnName ) throws Exception {
		
		NewsMediaColumnEntity newsMediaColumnEntity = new NewsMediaColumnEntity();
		newsMediaColumnEntity.setNewsColumnName( columnName );
		
		Integer columnId = newsMediaColumnDao.selectNewsMediaColumnId( newsMediaColumnEntity );
		if ( columnId != null && columnId != 0 ) {
			return columnId;
		}
		newsMediaColumnEntity.setStatus( 0 );
		newsMediaColumnDao.insertNewsMediaColumnId( newsMediaColumnEntity );
		
		return newsMediaColumnEntity.getNewsColumnId();
		
	}

}
