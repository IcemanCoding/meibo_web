package com.meibo.web.media.dao;

import com.meibo.web.media.entity.WechatMediaChannelEntity;

public interface WechatMediaChannelDAO {
	
	Integer selectWechatMediaChannelId( String wechatAccount );
	
	WechatMediaChannelEntity selectWechatMediaChannelById( Integer channelId );
	
	WechatMediaChannelEntity selectWechatMediaChannelByName( String channelName );
	
	void updateWechatMediaChannelStatus( WechatMediaChannelEntity entity );
	
	void insertWechatMediaChannel( WechatMediaChannelEntity entity );

}
