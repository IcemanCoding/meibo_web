package com.meibo.web.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.media.dao.WechatMediaChannelDAO;
import com.meibo.web.media.entity.WechatMediaChannelEntity;
import com.meibo.web.media.service.WechatMediaChannelService;

public class WechatMediaChannelServiceImpl implements WechatMediaChannelService {

	@Autowired
	private WechatMediaChannelDAO wechatMediaChannelDao;
	
	@Override
	public Integer getOrAddWechatMediaChannelId( WechatMediaChannelEntity entity )
			throws Exception {
		
		Integer channelId = wechatMediaChannelDao.selectWechatMediaChannelId( entity.getAccount() );
		if ( channelId != null && channelId != 0 ) {
			return channelId;
		}
		
		wechatMediaChannelDao.insertWechatMediaChannel( entity );
		
		return entity.getChannelId();
		
	}

}
