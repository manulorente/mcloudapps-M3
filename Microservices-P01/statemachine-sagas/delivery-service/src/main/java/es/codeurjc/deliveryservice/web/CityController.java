package es.codeurjc.deliveryservice.web;

import es.codeurjc.deliveryservice.service.CityService;
import es.codeurjc.deliveryservice.dto.CityResponse;
import es.codeurjc.deliveryservice.dto.CreateCityRequest;
import es.codeurjc.deliveryservice.dto.CreateCityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class CityController {

	private CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

    @GetMapping("city/{cityId}")
    public ResponseEntity<CityResponse> getCity(@PathVariable(value = "cityId") UUID cityId) {
        return new ResponseEntity<>(cityService.getCity(cityId), HttpStatus.OK);
    }	
    
	@PostMapping("city")
	public ResponseEntity<CreateCityResponse> createCity(@RequestBody CreateCityRequest createCityRequest) {
		return new ResponseEntity<>(cityService.createCity(createCityRequest),HttpStatus.CREATED);
	}
	
}