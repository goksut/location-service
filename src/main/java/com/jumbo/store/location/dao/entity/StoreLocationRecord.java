package com.jumbo.store.location.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLocationRecord implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String city;
	
	@Column
	private String postalCode;
	
	@Column
	private String street;
	
	@Column
	private String street2;
	
	@Column
	private String street3;
	
	@Column
	private String addressName;
	
	@Column
	private Double longitude;
	
	@Column
	private Double latitude;
	
	@Column
	private String complexNumber;
	
	@Column
	private String showWarningMessage;
	
	@Column
	private String todayOpen;
	
	@Column
	private String locationType;
	
	@Column
	private String collectionPoint;
	
	@Column
	private String sapStoreID;
	
	@Column
	private String todayClose;
	
	@Column(columnDefinition = "geography")
	private Geometry geo;
	
	@Column
	private String distanceInMeters;
}
