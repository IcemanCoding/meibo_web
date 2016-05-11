package com.meibo.web.system.service;

import java.util.List;

import com.meibo.web.system.dto.SystemAreaInfoDTO;

public interface DictionaryService {
	
	List<SystemAreaInfoDTO> getAreaList() throws Exception;

}
