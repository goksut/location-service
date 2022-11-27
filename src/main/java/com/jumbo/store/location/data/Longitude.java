package com.jumbo.store.location.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Longitude implements Serializable {
	
	private Longitude longitude;
	
}
