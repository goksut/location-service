package com.jumbo.store.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLocationDto{
	
	private Long id;
	
	private String addressName;
	
	private String distanceInMeters;
	
	private String city;
	
	private String postalCode;
	
	private String street;
	
	private String street2;
	
	private String street3;
	
	private Double longitude;
	
	private Double latitude;
}
