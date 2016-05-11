package com.meibo.web.media.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.NewsMediaChannelDAO;
import com.meibo.web.media.entity.NewsMediaChannelEntity;
import com.meibo.web.media.service.NewsMediaChannelService;

public class NewsMediaChannelServiceImpl implements NewsMediaChannelService {

	@Autowired
	private NewsMediaChannelDAO newsMediaChannelDao;
	
	@Override
	public List<NewsMediaChannelEntity> getNewsMediaChannel() throws Exception {
		
		List<NewsMediaChannelEntity> channelList = newsMediaChannelDao.selectNewsMediaChannel();
		
		return channelList;
		
	}

	@Override
	public Integer getOrAddNewsMediaChannelId( NewsMediaChannelEntity newsMediaChannelEntity ) throws Exception {
		
		Integer channelId = newsMediaChannelDao.selectNewsMediaChannelId( newsMediaChannelEntity );
		
		if ( channelId != null && channelId != 0 ) {
			return channelId;
		}
		
		newsMediaChannelDao.insertNewsMediaChannel( newsMediaChannelEntity );
		
		return newsMediaChannelEntity.getChannelId();
		
	}

}
