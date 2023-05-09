package es.codeurjc.deliveryservice.service;

import es.codeurjc.deliveryservice.domain.City;
import es.codeurjc.deliveryservice.dto.CityResponse;
import es.codeurjc.deliveryservice.dto.CreateCityRequest;
import es.codeurjc.deliveryservice.dto.CreateCityResponse;
import es.codeurjc.deliveryservice.exception.EntityNotFoundException;
import es.codeurjc.deliveryservice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class CityService {

	private final CityRepository cityRepository;

	@Autowired
	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public CreateCityResponse createCity(CreateCityRequest createCityRequest) {
		final City city = new City.Builder()
				                                 .withName(createCityRequest.getName())
				                                 .withCodCity(createCityRequest.getCodCity())
				                                 .withDeliveryQuantity(createCityRequest.getDeliveryQuantity())
				                                 .build();
		final City citySaved = cityRepository.save(city);
		return new CreateCityResponse(citySaved.getId());
	}

	public CityResponse getCity(UUID cityId) {
		final City city = cityRepository.findById(cityId).orElseThrow(() ->new EntityNotFoundException());
		return toCityResponse(city);
	}

	private CityResponse toCityResponse(final City city) {
		return new CityResponse.Builder()
        .withId(city.getId())
        .withName(city.getName())
        .withCodCity(city.getCodCity())
        .withDeliveryQuantity(city.getDeliveryQuantity())
        .build();
	}
}