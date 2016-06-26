package com.meibo.web.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.BlogMediaDAO;
import com.meibo.web.media.dao.NewsMediaDAO;
import com.meibo.web.media.dao.WechatMediaDAO;
import com.meibo.web.media.viewmodel.AdminBlogMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.AdminWechatMediaListQueryViewmodel;
import com.meibo.web.media.viewmodel.NewsMediaListQueryParams;
import com.meibo.web.system.service.IndexInfoService;

public class IndexInfoServiceImpl implements IndexInfoService {

	@Autowired
	private BlogMediaDAO blogMediaDao;
	
	@Autowired
	private WechatMediaDAO wechatMediaDao;
	
	@Autowired
	private NewsMediaDAO newsMediaDao;
	
	@Override
	public Map<String, Object> getMediaCount() throws Exception {
		
		Map<String, Object> resData = new HashMap<String, Object>();
		
		// 微信媒体
		Integer wechatCount = wechatMediaDao.selectWechatMediaListByAdminCount( new AdminWechatMediaListQueryViewmodel() );
		if ( wechatCount == null ) {
			wechatCount = 0;
		}
		
		// 微博媒体
		Integer blogCount = blogMediaDao.selectBlogMediaListByAdminCount( new AdminBlogMediaListQueryViewmodel() );
		if ( blogCount == null ) {
			blogCount = 0;
		}
		
		// 新闻媒体
		Integer newsCount = newsMediaDao.selectNewsMediaListByAdminCount( new NewsMediaListQueryParams() );
		if ( newsCount == null ) {
			newsCount = 0;
		}
		
		resData.put( "wechatCount", wechatCount );
		resData.put( "blogCount", blogCount );
		resData.put( "newsCount", newsCount );
		
		return resData;
		
	}

}
