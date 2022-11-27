package com.jumbo.store.location.services;

import java.util.List;
import java.util.stream.Collectors;

import com.jumbo.store.location.dao.LocationServiceDao;
import com.jumbo.store.location.dao.entity.StoreLocationRecord;
import com.jumbo.store.location.data.Store;
import com.jumbo.store.location.data.LatitudeLongitudePair;
import com.jumbo.store.location.dto.StoreLocationDto;
import com.jumbo.store.location.services.converters.StoreLocationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationServiceDao locationServiceDao;
	
	@Override
	public List<StoreLocationDto> nearestStores(String originLiteral, Integer limit) {
		LatitudeLongitudePair origin = convertToLatitudeLongitudeLiteral(originLiteral);
		return locationServiceDao.nearestStores(origin.getLatitude(), origin.getLongitude(), limit)
				.stream().map(StoreLocationConverter::toDto).collect(Collectors.toList());
	}
	
	@Override
	public void createOrUpdate(Store store) {
		StoreLocationRecord storeLocationRecord = StoreLocationRecord.builder()
				.addressName(store.getAddressName())
				.street(store.getStreet())
				.street2(store.getStreet2())
				.street3(store.getStreet3())
				.latitude(store.getLatitude())
				.longitude(store.getLongitude())
				.city(store.getCity())
				.collectionPoint(store.getCollectionPoint())
				.complexNumber(store.getComplexNumber())
				.postalCode(store.getPostalCode())
				.sapStoreID(store.getSapStoreID())
				.showWarningMessage(store.getShowWarningMessage())
				.todayOpen(store.getTodayOpen())
				.todayClose(store.getTodayClose())
				.locationType(store.getLocationType()).build();
		locationServiceDao.createOrUpdate(storeLocationRecord);
	}
	
	private LatitudeLongitudePair convertToLatitudeLongitudeLiteral(String originLiteral) {
		String[] latitudeLongitudeArray = originLiteral.split(",");
		Double latitude = Double.valueOf(latitudeLongitudeArray[0]);
		Double longitude = Double.valueOf(latitudeLongitudeArray[1]);
		return new LatitudeLongitudePair(latitude, longitude);
	}
}
