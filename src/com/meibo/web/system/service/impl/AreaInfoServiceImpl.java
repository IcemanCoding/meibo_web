package com.meibo.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.system.dao.AreaInfoDAO;
import com.meibo.web.system.dto.BaseAreaInfoDTO;
import com.meibo.web.system.dto.CityAreaInfoDTO;
import com.meibo.web.system.dto.ProvinceAreaInfoDTO;
import com.meibo.web.system.service.AreaInfoService;

public class AreaInfoServiceImpl implements AreaInfoService {
	
	@Autowired
	private AreaInfoDAO areaInfoDao;

	@Override
	public List<BaseAreaInfoDTO> getHotAreaList() throws Exception {
		
		return areaInfoDao.selectHotAreaList();
		
	}

	@Override
	public List<ProvinceAreaInfoDTO> getProvinceList() throws Exception {
		
		return areaInfoDao.selectProvinceList();
		
	}

	@Override
	public List<CityAreaInfoDTO> getCityList() throws Exception {
		
		return areaInfoDao.selectCityList();
		
	}

}
