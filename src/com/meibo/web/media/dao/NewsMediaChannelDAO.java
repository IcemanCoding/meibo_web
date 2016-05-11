package com.meibo.web.media.dao;

import java.util.List;

import com.meibo.web.media.entity.NewsMediaChannelEntity;

public interface NewsMediaChannelDAO {
	
	List<NewsMediaChannelEntity> selectNewsMediaChannel();
	
	NewsMediaChannelEntity selectNewsMediaChannelById( Integer channelId );
	
	void updateNewsMediaChannelStatus( NewsMediaChannelEntity newsMediaChannelEntity );
	
	Integer selectNewsMediaChannelId( NewsMediaChannelEntity newsMediaChannelEntity );
	
	void insertNewsMediaChannel( NewsMediaChannelEntity newsMediaChannelEntity );

}
