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
import com.meibo.web.system.dto.BaseAreaInfoDTO;
import com.meibo.web.system.dto.CityAreaInfoDTO;
import com.meibo.web.system.dto.ProvinceAreaInfoDTO;
import com.meibo.web.system.dto.SystemAreaInfoDTO;
import com.meibo.web.system.service.AreaInfoService;
import com.meibo.web.system.service.DictionaryService;

@RequestMapping ( "/dictionary" )
@Controller
public class DictionaryController {
	
	private static final Logger logger = LoggerFactory.getLogger( DictionaryController.class );
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private AreaInfoService areaInfoService;
	
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
	
	@RequestMapping ( value = "/hotArea", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> hotArea( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<BaseAreaInfoDTO> hotArea = areaInfoService.getHotAreaList();
			if ( hotArea == null || hotArea.size() == 0 ) {
				resData.put( "hotArea", null );
			} else {
				resData.put( "hotArea", hotArea );
			}
		} catch ( Exception e ) {
			logger.error( "查询热门地区信息失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/provinceInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> provinceInfo( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<ProvinceAreaInfoDTO> provinceInfo = areaInfoService.getProvinceList();
			if ( provinceInfo == null || provinceInfo.size() == 0 ) {
				resData.put( "provinceInfo", null );
			} else {
				resData.put( "provinceInfo", provinceInfo );
			}
		} catch ( Exception e ) {
			logger.error( "查询省信息失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}
	
	@RequestMapping ( value = "/cityInfo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> cityInfo( BaseViewModel viewModel ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			List<CityAreaInfoDTO> cityInfo = areaInfoService.getCityList();
			if ( cityInfo == null || cityInfo.size() == 0 ) {
				resData.put( "cityInfo", null );
			} else {
				resData.put( "cityInfo", cityInfo );
			}
		} catch ( Exception e ) {
			logger.error( "查询城市信息失败!" + e );
			ContainerUtils.buildResFailMap( "操作失败" );
		}
		
		return ContainerUtils.buildResSuccessMap( resData );

	}

}
