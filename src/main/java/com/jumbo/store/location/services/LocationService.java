package com.jumbo.store.location.services;

import java.util.List;

import com.jumbo.store.location.data.Store;
import com.jumbo.store.location.dto.StoreLocationDto;
import org.springframework.stereotype.Service;

@Service
public interface LocationService {
	
	List<StoreLocationDto> nearestStores(String origin, Integer limit);
	
	void createOrUpdate(Store store);
	
}
