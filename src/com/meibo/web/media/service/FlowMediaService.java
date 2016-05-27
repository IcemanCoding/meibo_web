package com.meibo.web.media.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.meibo.web.media.dto.FlowMediaInfoDTO;
import com.meibo.web.media.entity.FlowMediaInfoEntity;

public interface FlowMediaService {
	
	void updateFlowPackage() throws Exception;
	
	List<FlowMediaInfoDTO> getFlowPackage( String mobile ) throws Exception;
	
	String[] importMobileList( FileItem mobileList ) throws Exception;
	
	void addFlowMediaInfo( FlowMediaInfoEntity flowEntity ) throws Exception;
	
	Boolean flowRecharge( String mobile, Integer dxPackageId, Integer ltPackageId, Integer ydPackageId ) throws Exception;

}
