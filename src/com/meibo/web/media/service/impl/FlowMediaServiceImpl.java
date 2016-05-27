package com.meibo.web.media.service.impl;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;

import com.meibo.web.common.utils.ExcelUtils;
import com.meibo.web.external.service.YunXinFlowMediaService;
import com.meibo.web.media.dao.FlowMediaInfoDAO;
import com.meibo.web.media.dto.FlowMediaInfoDTO;
import com.meibo.web.media.entity.FlowMediaInfoEntity;
import com.meibo.web.media.service.FlowMediaService;
import com.meibo.web.media.utils.FlowMediaUtils;

public class FlowMediaServiceImpl implements FlowMediaService {

	@Autowired
	private YunXinFlowMediaService yunXinFlowMediaService;
	
	@Autowired
	private FlowMediaInfoDAO flowMediaInfoDao;
	
	@Override
	public void updateFlowPackage() throws Exception {
		yunXinFlowMediaService.updateYunXinFlowPackage();
	}

	@Override
	public List<FlowMediaInfoDTO> getFlowPackage( String mobile ) throws Exception {
		
		Integer carrierType = FlowMediaUtils.checkMobileCarrier( mobile );
		
		List<FlowMediaInfoDTO> flowPackageDto = flowMediaInfoDao.selectFlowPackageDto( carrierType );
		
		return flowPackageDto;
		
	}

	@Override
	public String[] importMobileList( FileItem mobileList ) throws Exception {
		
		String[] mobile = ExcelUtils.excel2PhoneList( mobileList.getInputStream() );
		
		return mobile;
		
	}

	@Override
	public void addFlowMediaInfo( FlowMediaInfoEntity flowEntity ) throws Exception {
		
		FlowMediaInfoEntity flowMediaInfo = flowMediaInfoDao.selectFlowMediaInfoByPackageId( flowEntity.getPackageId() );
		if ( flowMediaInfo == null ) {
			// insert
			flowMediaInfoDao.insertFlowPackageInfo( flowEntity );
		} else {
			// update
			flowMediaInfoDao.updateFlowMediaInfo( flowEntity );
		}
		
	}

	@Override
	public Boolean flowRecharge( String mobile, Integer dxPackageId, Integer ltPackageId, Integer ydPackageId ) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
