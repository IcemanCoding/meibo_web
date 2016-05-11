package com.meibo.web.media.service;

import com.meibo.web.media.entity.WechatMediaChannelEntity;


public interface WechatMediaChannelService {
	
	Integer getOrAddWechatMediaChannelId( WechatMediaChannelEntity entity ) throws Exception;

}
