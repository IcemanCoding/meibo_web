package com.meibo.web.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.BlogMediaChannelDAO;
import com.meibo.web.media.entity.BlogMediaChannelEntity;
import com.meibo.web.media.service.BlogMediaChannelService;

public class BlogMediaChannelServiceImpl implements BlogMediaChannelService {

	@Autowired
	private BlogMediaChannelDAO blogMediaChannelDao;
	
	@Override
	public Integer getOrAddBlogMediaChannelId( BlogMediaChannelEntity entity ) throws Exception {
		
		Integer channelId = blogMediaChannelDao.selectBlogMediaChannelId( entity.getNickname() );
		if ( channelId != null && channelId != 0 ) {
			return channelId;
		}
		
		blogMediaChannelDao.insertBlogMediaChannel( entity );
		
		return entity.getChannelId();
		
	}

}
