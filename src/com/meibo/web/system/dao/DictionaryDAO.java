package com.meibo.web.system.dao;

import java.util.List;

import com.meibo.web.system.dto.SystemAreaInfoDTO;

public interface DictionaryDAO {
	
	List<SystemAreaInfoDTO> selectSystemAreaList();

}
