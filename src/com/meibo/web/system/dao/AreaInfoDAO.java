package com.meibo.web.system.dao;

import java.util.List;

import com.meibo.web.system.dto.BaseAreaInfoDTO;
import com.meibo.web.system.dto.CityAreaInfoDTO;
import com.meibo.web.system.dto.ProvinceAreaInfoDTO;

public interface AreaInfoDAO {
	
	List<BaseAreaInfoDTO> selectHotAreaList();
	
	List<ProvinceAreaInfoDTO> selectProvinceList();
	
	List<CityAreaInfoDTO> selectCityList();	

}
