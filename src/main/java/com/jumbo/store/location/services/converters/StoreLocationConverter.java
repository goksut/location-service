package com.jumbo.store.location.services.converters;

import com.jumbo.store.location.dao.entity.StoreLocationRecord;
import com.jumbo.store.location.dto.StoreLocationDto;

public class StoreLocationConverter {
	
	public static StoreLocationDto toDto(StoreLocationRecord storeLocationRecord){
		return new StoreLocationDto(storeLocationRecord.getId(), storeLocationRecord.getAddressName(),
				storeLocationRecord.getDistanceInMeters(), storeLocationRecord.getCity(), storeLocationRecord.getPostalCode(),
				storeLocationRecord.getStreet(), storeLocationRecord.getStreet2(),
				storeLocationRecord.getStreet3(), storeLocationRecord.getLongitude(), storeLocationRecord.getLatitude());
	}
	
}
