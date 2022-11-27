package com.jumbo.store.location.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LatitudeLongitudePair {
	
	private final Double latitude;
	private final Double longitude;
}
