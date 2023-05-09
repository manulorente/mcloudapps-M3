package es.codeurjc.deliveryservice.dto;

import java.util.UUID;

public class CreateCityResponse {

	private UUID cityId;

	public CreateCityResponse() {
	}

	public CreateCityResponse(UUID cityId) {
		this.cityId = cityId;
	}

	public UUID getCityId() {
		return cityId;
	}

	public void setCityId(UUID cityId) {
		this.cityId = cityId;
	}

}