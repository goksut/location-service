package com.jumbo.store.location;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.location.dto.StoreLocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationServiceIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void nearestStoresReturnsStores() throws Exception {
		//Arrange
		InputStream storesInputStream = LocationServiceIntegrationTest.class.getClassLoader()
				.getResourceAsStream("nearestStores.json");
		List<StoreLocationDto> expectedStores = new ObjectMapper().readValue(storesInputStream,
				new TypeReference<>() {});
		//Act
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/nearestStores")
						.param("origin", "51.43314,5.490035")
						.param("limit", "5"))
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		List<StoreLocationDto> response = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<>() {});
		
		//Assert
		assertThat(response).usingRecursiveFieldByFieldElementComparatorIgnoringFields("distanceInMeters")
				.containsExactlyElementsOf(expectedStores);
		
		
	}

}
