package com.jumbo.store.location;

import java.io.IOException;
import java.io.InputStream;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.location.data.Stores;
import com.jumbo.store.location.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootApplication
@ComponentScan
public class LocationServiceApplication {
	
	@Autowired
	private LocationService locationService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LocationServiceApplication.class, args);
	}
	
	
	@Bean
	public JtsModule jtsModule() {
		return new JtsModule();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Component
	public class CommandLineAppStartupRunner implements CommandLineRunner {
		
		@Override
		public void run(String... args) {
			try {
				InputStream storesInputStream = LocationServiceApplication.class.getClassLoader()
						.getResourceAsStream("stores.json");
				Stores stores = new ObjectMapper().readValue(storesInputStream, Stores.class);
				stores.getStores().forEach(store -> locationService.createOrUpdate(store) );
			} catch (IOException e) {
				log.error("Exception in reading and saving input data to db ", e);
				
			}
		}
	}
}
