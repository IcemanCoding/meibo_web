package com.meibo.web.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.system.dao.DictionaryDAO;
import com.meibo.web.system.dto.SystemAreaInfoDTO;
import com.meibo.web.system.service.DictionaryService;

public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryDAO dictionaryDao;
	
	@Override
	public List<SystemAreaInfoDTO> getAreaList() throws Exception {
		
		return dictionaryDao.selectSystemAreaList();
		
	}

}
