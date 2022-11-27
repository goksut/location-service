package com.jumbo.store.location.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stores {
	
	private List<Store> stores;
	
	private Attributes attributes;
	
}
