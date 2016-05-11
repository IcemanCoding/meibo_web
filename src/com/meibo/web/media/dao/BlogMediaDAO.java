package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.dto.AdminBlogMediaListDTO;
import com.meibo.web.media.dto.BaseBlogMediaListDTO;
import com.meibo.web.media.entity.BlogMediaInfoEntity;
import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.BlogMediaUpdateViewmodel;
import com.meibo.web.media.viewmodel.MemberBlogMediaListQueryViewmodel;

public interface BlogMediaDAO {
	
	List<Map<String, Object>> selectBlogMediaType();
	
	void insertBlogMediaInfo( BlogMediaInfoEntity blogMediaInfo );
	
	BlogMediaInfoEntity selectBlogMediaInfoById( Integer blogMediaId );
	
	void auditBlogMediaInfo( BlogMediaInfoEntity blogMediaInfo );
	
	List<AdminBlogMediaListDTO> selectBlogMediaListByAdmin( AdminBlogMediaListQueryViewmodel params );
	
	Integer selectBlogMediaListByAdminCount( AdminBlogMediaListQueryViewmodel params );

	List<BaseBlogMediaListDTO> selectBlogMediaListByMember( MemberBlogMediaListQueryViewmodel viewmodel );

	Integer selectWechatMediaListByMemberCount( MemberBlogMediaListQueryViewmodel viewmodel );

	void updateBlogMediaInfo( BlogMediaUpdateViewmodel viewmodel );

	List<Map<String, Object>> selectBlogMediaAreaList();

}
