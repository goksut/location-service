package com.jumbo.store.location.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
	
	private String city;
	
	private String postalCode;
	
	private String street;
	
	private String street2;
	
	private String street3;
	
	private String addressName;
	
	private String uuid;
	
	private Double longitude;
	
	private Double latitude;
	
	private String complexNumber;
	
	private String showWarningMessage;
	
	private String todayOpen;
	
	private String locationType;
	
	private String collectionPoint;
	
	private String sapStoreID;
	
	private String todayClose;

}
