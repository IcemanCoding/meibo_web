package com.meibo.web.media.dao;

import com.meibo.web.media.entity.NewsMediaColumnEntity;

public interface NewsMediaColumnDAO {
	
	Integer selectNewsMediaColumnId( NewsMediaColumnEntity newsMediaColumnEntity );
	
	NewsMediaColumnEntity selectNewsMediaColumnById( Integer columnId );
	
	void updateNewsMediaColumnStatus( NewsMediaColumnEntity newsMediaColumnEntity );
	
	void insertNewsMediaColumnId( NewsMediaColumnEntity newsMediaColumnEntity );

}
