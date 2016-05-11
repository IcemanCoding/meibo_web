package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.dto.AdminNewsMediaListDTO;
import com.meibo.web.media.dto.BaseNewsMediaListDTO;
import com.meibo.web.media.entity.NewsMediaEntity;
import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;

public interface NewsMediaDAO {
	
	Integer insertNewsMediaInfo( NewsMediaEntity newsMediaEntity );
	
	NewsMediaEntity selectNewsMediaInfo( Integer newsMediaId );
	
	void updateNewsMediaByAudit( NewsMediaEntity newsMediaEntity );
	
	List<AdminNewsMediaListDTO> selectNewsMediaListByAdmin( NewsMediaListQueryParams params );
	
	Integer selectNewsMediaListByAdminCount( NewsMediaListQueryParams params );
	
	List<BaseNewsMediaListDTO> selectNewsMediaListByMember( NewsMediaListQueryParams params );
	
	List<Map<String, Object>> selectNewsMediaArea();

}
