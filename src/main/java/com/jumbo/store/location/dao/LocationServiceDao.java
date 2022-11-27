package com.jumbo.store.location.dao;

import java.util.List;

import com.jumbo.store.location.dao.entity.StoreLocationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LocationServiceDao extends JpaRepository<StoreLocationRecord, Long> {
	
	@Query(value = "SELECT id, address_name, city, collection_point, complex_number, geo, latitude, location_type, "
			+ "longitude, postal_code, sap_storeid, show_warning_message, street, street2, street3, today_open, "
			+ "today_close, "
			+ "ST_Distance(geo, ST_SetSRID(ST_Point( :lon, :lat), 4326)\\:\\:geography) AS distance_in_meters "
			+ "FROM store_location_record "
			+ "ORDER BY distance_in_meters "
			+ "LIMIT :limit",
			nativeQuery = true)
	List<StoreLocationRecord> nearestStores(Double lat, Double lon, int limit);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO store_location_record "
			+ "(address_name, city, collection_point, complex_number, geo, latitude, location_type, longitude, "
			+ "postal_code, sap_storeid, show_warning_message, street, street2, street3, today_open, today_close) "
			+ "VALUES"
			+ " (:#{#store.addressName}, :#{#store.city}, :#{#store.collectionPoint}, :#{#store.complexNumber},"
			+ " ST_SetSRID(ST_Point(:#{#store.longitude}, :#{#store.latitude}), 4326)\\:\\:geography, "
			+ ":#{#store.latitude},:#{#store.locationType}, :#{#store.longitude},"
			+ " :#{#store.postalCode}, :#{#store.sapStoreID}, :#{#store.showWarningMessage}, :#{#store"
			+ ".street}, :#{#store.street2}, :#{#store.street3},"
			+ ":#{#store.todayOpen}, :#{#store.todayClose})",
			nativeQuery = true)
	void createOrUpdate(final StoreLocationRecord store);
}