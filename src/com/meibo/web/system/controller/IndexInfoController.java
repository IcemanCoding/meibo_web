package com.meibo.web.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meibo.web.common.controller.BaseController;
import com.meibo.web.common.utils.ContainerUtils;
import com.meibo.web.common.viewmodel.BaseViewModel;
import com.meibo.web.system.service.IndexInfoService;

@RequestMapping( "/index" )
@Controller
public class IndexInfoController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger( IndexInfoController.class );
	
	@Autowired
	private IndexInfoService indexInfoService;
	
	@RequestMapping ( value = "/mediaCount", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> mediaCount( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			resData = indexInfoService.getMediaCount();
		} catch ( Exception e ) {
			logger.error( "查询媒体数量失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
