package com.meibo.web.media.service;

import java.util.List;

import com.meibo.web.media.entity.NewsMediaChannelEntity;

public interface NewsMediaChannelService {
	
	List<NewsMediaChannelEntity> getNewsMediaChannel() throws Exception;
	
	Integer getOrAddNewsMediaChannelId( NewsMediaChannelEntity newsMediaChannelEntity ) throws Exception;

}
