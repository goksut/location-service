package com.jumbo.store.location.controllers;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.location.dto.StoreLocationDto;
import com.jumbo.store.location.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/nearestStores")
	public ResponseEntity<List<StoreLocationDto>> nearestStores(String origin,
			@RequestParam(required=false,defaultValue="5")Integer limit){
		try{
			return new ResponseEntity<>(locationService.nearestStores(origin, limit), HttpStatus.OK);
		} catch(ArrayIndexOutOfBoundsException | NumberFormatException | PatternSyntaxException ex){
			log.error("Exception in extracting latitude longitude from origin literal: ", ex);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
