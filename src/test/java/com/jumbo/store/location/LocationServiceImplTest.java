package com.jumbo.store.location;

import java.util.Arrays;
import java.util.List;

import com.jumbo.store.location.dao.LocationServiceDao;
import com.jumbo.store.location.dao.entity.StoreLocationRecord;
import com.jumbo.store.location.data.Store;
import com.jumbo.store.location.dto.StoreLocationDto;
import com.jumbo.store.location.services.LocationServiceImpl;
import com.jumbo.store.location.services.converters.StoreLocationConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {
	
	@InjectMocks
	private LocationServiceImpl locationService;
	
	@Mock
	private LocationServiceDao locationServiceDao;

	@Test
	void testNearestStores() {
		//Arrange
		String originLiteral = "51.43314,5.490035";
		Integer limit = 2;
		List<StoreLocationRecord> expectedStores = Arrays.asList(
				StoreLocationRecord.builder()
				.latitude(51.433142)
				.longitude(5.490035)
				.addressName("Jumbo Eindhoven Geldropseweg")
						.build(),
				StoreLocationRecord.builder()
						.latitude(51.427559)
						.longitude(5.465047)
						.addressName("Jumbo Eindhoven Boutenslaan").build());
		List<StoreLocationDto> expectedStoresDtos =
				expectedStores.stream().map(StoreLocationConverter::toDto).toList();
		when(locationServiceDao.nearestStores(anyDouble(), anyDouble(), anyInt())).thenReturn(expectedStores);
		//Act
		List<StoreLocationDto> nearestStores = locationService.nearestStores(originLiteral, limit);
		
		//Assert
		assertThat(nearestStores.size()).isEqualTo(2);
		assertThat(nearestStores).containsExactlyElementsOf(expectedStoresDtos);
	
	}
	
	@Test
	void testCreateOrUpdate(){
		//Arrange
		Store store = Store.builder()
				.latitude(51.433142)
				.longitude(5.490035)
				.addressName("Jumbo Eindhoven Geldropseweg")
				.build();
		//Act
		locationService.createOrUpdate(store);
		//Assert
		verify(locationServiceDao, times(1)).createOrUpdate(any(StoreLocationRecord.class));
	}
}
