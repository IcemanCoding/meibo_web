package com.meibo.web.media.dao;

import java.util.List;
import java.util.Map;

import com.meibo.web.media.dto.FlowMediaInfoDTO;
import com.meibo.web.media.entity.FlowMediaInfoEntity;

public interface FlowMediaInfoDAO {

	void insertFlowPackageInfo( FlowMediaInfoEntity entity );

	void updateFLowPackageUnused( Map<String, Object> params );

	List<FlowMediaInfoDTO> selectFlowPackageDto( Integer carrierType );

	FlowMediaInfoEntity selectFlowMediaInfoByPackageId( Integer packageId );

	void updateFlowMediaInfo( FlowMediaInfoEntity flowEntity );
	
	Integer getPackageIdByNearPrice( Map<String, Object> params );

}
