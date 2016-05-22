package com.meibo.web.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.media.service.BlogMediaService;
import com.meibo.web.media.service.NewsMediaService;
import com.meibo.web.media.service.WechatMediaService;
import com.meibo.web.system.dto.SystemAreaInfoDTO;
import com.meibo.web.system.service.DictionaryService;

@RequestMapping ( "/dictionary" )
@Controller
public class DictionaryController {
	
	private static final Logger logger = LoggerFactory.getLogger( DictionaryController.class );
	
	@Autowired
	private NewsMediaService newsMediaService;
	
	@Autowired
	private BlogMediaService blogMediaService;
	
	@Autowired
	private WechatMediaService wechatMediaService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	
	@RequestMapping ( value = "/wechatMediaType", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> wechatMediaType( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
		} catch ( Exception e ) {
			logger.error( "查询微信媒体类型失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/blogMediaType", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> blogMediaType( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
		} catch ( Exception e ) {
			logger.error( "查询微博媒体类型失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/areaList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> area( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<SystemAreaInfoDTO> areaList = dictionaryService.getAreaList();
			resData.put( "areaInfo", areaList );
		} catch ( Exception e ) {
			logger.error( "查询地区信息失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
