package com.meibo.web.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.utils.RequestParseUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.media.dto.FlowMediaInfoDTO;
import com.meibo.web.media.service.FlowMediaService;

@RequestMapping ( "/flowMedia" )
@Controller
public class FlowMediaController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( FlowMediaController.class );
	
	@Autowired
	private FlowMediaService flowMediaService;
	
	@RequestMapping ( value = "/packageList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> packageList( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		JSONObject requestJson = RequestParseUtils.loadPostRequest( viewModel.getRequest() );
		String mobile = "";
		if ( requestJson != null ) {
			mobile = requestJson.getString( "mobile" );
		}
		
		try {
			List<FlowMediaInfoDTO> packageList = flowMediaService.getFlowPackage( mobile );
			if ( packageList == null || packageList.size() == 0 ) {
				packageList = null;
			}
			resData.put( "packageList", packageList );
			return ContainerUtils.buildResSuccessMap( resData );
		} catch ( Exception e ) {
			logger.error( "查询流量套餐失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}

	}
	
	@RequestMapping ( value = "/updatePackage", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> updatePackage( BaseViewModel viewModel ) {

		try {
			flowMediaService.updateFlowPackage();
		} catch ( Exception e ) {
			logger.error( "更新流量套餐失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}
		return ContainerUtils.buildResSuccessMap( null );

	}
	
	@RequestMapping ( value = "/importMobileList", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> importMobileList( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			Map<String, Object> formData = RequestParseUtils.loadFormRequest( viewModel.getRequest() );
			String[] mobileList = flowMediaService.importMobileList( ( FileItem )formData.get( "mobileList" ) );
			resData.put( "mobileList", mobileList );
			return ContainerUtils.buildResSuccessMap( resData );
		} catch ( Exception e ) {
			logger.error( "更新流量套餐失败!" + e );
			return ContainerUtils.buildResFailMap( "操作失败" );
		}

	}
	
}
