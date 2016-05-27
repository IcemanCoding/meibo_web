package com.meibo.web.system.service;

import java.util.List;

import com.meibo.web.system.dto.BaseAreaInfoDTO;
import com.meibo.web.system.dto.CityAreaInfoDTO;
import com.meibo.web.system.dto.ProvinceAreaInfoDTO;

public interface AreaInfoService {
	
	List<BaseAreaInfoDTO> getHotAreaList() throws Exception;
	
	List<ProvinceAreaInfoDTO> getProvinceList() throws Exception;
	
	List<CityAreaInfoDTO> getCityList() throws Exception;	

}
