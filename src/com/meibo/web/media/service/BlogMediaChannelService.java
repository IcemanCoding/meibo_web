package com.meibo.web.media.service;

import com.meibo.web.media.entity.BlogMediaChannelEntity;

public interface BlogMediaChannelService {
	
	Integer getOrAddBlogMediaChannelId( BlogMediaChannelEntity entity ) throws Exception;

}
