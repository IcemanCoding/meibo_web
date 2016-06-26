package com.meibo.web.media.dao;

import com.meibo.web.media.entity.BlogMediaChannelEntity;

public interface BlogMediaChannelDAO {
	
	Integer selectBlogMediaChannelId( String nickname );
	
	void insertBlogMediaChannel( BlogMediaChannelEntity channelEntity );
	
	BlogMediaChannelEntity selectBlogMediaChannelById( Integer channelId );
	
	void updateBlogMediaChannelStatus( BlogMediaChannelEntity channelEntity );
	
	void updateBlogMediaChannelByChannelId( BlogMediaChannelEntity channelEntity );
	
	BlogMediaChannelEntity selectBlogMediaChannelByName( String channelName );

}
